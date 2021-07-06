package roslesinforg.porokhin.geobaseeditor

import com.jacob.activeX.ActiveXComponent
import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import roslesinforg.porokhin.nabparser.reading.DataReader
import roslesinforg.porokhin.nabparser.reading.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.areawriter.RawSoliAreaWriter
import roslesinforg.porokhin.filecomparator.FileComparator
import roslesinforg.porokhin.filecomparator.service.ComparedPair
import roslesinforg.porokhin.geobaseeditor.service.DDEClient
import roslesinforg.porokhin.geobaseeditor.view.MainView
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import roslesinforg.porokhin.areatypes.fields.Field10
import roslesinforg.porokhin.geobaseeditor.model.Kv
import roslesinforg.porokhin.geobaseeditor.view.StrictAreaView
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.AreaModel
import roslesinforg.porokhin.nabparser.parsing.ParsingResult
import tornadofx.*
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class GeoBaseEditorController: Controller() {
    private val logger = logger()
    var areas: ObservableList<Area> = SimpleListProperty()
    val startSq = mutableListOf<Kv>().toObservable()
    var location: Location? = null
    var updateCounter = SimpleIntegerProperty(0)
    val progressStatusProperty = SimpleDoubleProperty()
    private val dataReader = RawDataReader(progressStatusProperty)
    var inputFilePath = ""
    var view: MainView? = null
    var strictAreaView: StrictAreaView? = null
    var ddeSession: DDEClient = DDEClient(this)
    val areaModel = AreaModel(Area(field10 = Field10(ArrayList<ElementOfForest>().apply { fill(ElementOfForest()) })))


    init {
        areaModel.sqIsChanged.onChange {
            updateStrictAreaView()
            //if (areaModel.field1Model.categoryProperty.value in 9..37) error("Площадь лк изменена")
        }
    }


    fun setMainView(mainView: MainView) {
        view = mainView
    }

    fun setStrictView(strictAreaView: StrictAreaView){
        this.strictAreaView = strictAreaView
    }

    fun selectArea(id: Int) {
        try {
            val ar = areas.find { it.id == id }
            logger.debug("selection = ${view!!.kv_list.selectedItem}")
            if (ar != null) view!!.selectItem(areas.indexOf(ar))
            else logger.debug("Area with id = $id not found")
            logger.debug("selection = ${view!!.kv_list.selectedItem}")
        }catch (e: Exception){
            logger.error(e)
        }

    }

    fun paint() = ddeSession.paint()

    fun log(message: String) = Platform.runLater { view!!.flog(message) }

    fun error(message: String) = Platform.runLater { view!!.error(message) }

    fun startDDESession() = ddeSession.initiate()

    fun stopDDESession() = ddeSession.close()

    fun read(file: File){

        val readEntity = dataReader.read(file)

        var errorMessage = when(readEntity.result){
            ParsingResult.Result.UNKNOWN_ERROR -> "Обнаружена неизвестная ошибка при чтении файла."
            ParsingResult.Result.INCORRECT_FILE -> "Файл некорректен."
            ParsingResult.Result.NOT_OPERATED_FIELDS -> "Неизвестные макеты [${readEntity.notOperatedFields.joinToString()}] были пропущены."
            ParsingResult.Result.SUCCESS -> ""
        }
        if (errorMessage.isNotEmpty()) {
          errorMessage += " Сообщите разработчику"
            error(errorMessage)
            return
        }
        location = readEntity.location
        //areas.addAll(data.second)
        areas = readEntity.areas.asObservable()
        updateCounter.set(updateCounter.value++)
        inputFilePath = file.path

        areas.groupBy{ it.kv }.map { it.key to it.value }.forEach {
            startSq.add(Kv(it.first, it.second.toMutableList()))
        }
        var out: File = File("fakepath")
        try {
            val backupsFolder = File(this::class.java.protectionDomain.codeSource.location.toURI()).parentFile.resolve("backups")
            if (!backupsFolder.exists()) Files.createDirectory(backupsFolder.toPath())
            out = backupsFolder.resolve("${file.name}_${DateTimeFormatter.ofPattern("dd.MM.yy_HH.mm.ss")
                .withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault()).format(Instant.now())}.bak")

            Files.copy(file.toPath(), out.toPath())
        }catch (e: Exception){
            logger.error("Error of creating backup on path: ${out.absolutePath}")
            e.printStackTrace()
        }


    }

    /*fun read(){
        areas.set(
            listOf<Area>(Area(),
                Area(1, 2, 2, Field1(2, 2f, 2, 0, 0)),
                Area(3, 3, 3, Field1(3, 3f, 3))).toObservable())
        updateCounter.set(updateCounter.value++)
    }*/

    fun writeToRawFile(file: File){
        prepareForSaving()
        val writer = RawSoliAreaWriter(file)

       // val writer = RawSoliAreaWriter(file.toPath().resolve(Paths.get("/saved")).toFile())
        writer.writeAreas(location!!, areas)
    }

    fun newEmptyArea(selected: Area){
        val proto = areas[0]
        val new = Area(proto.region, proto.kv)
        val idx = areas.indexOf(selected) + 1
        areas.add(idx, new)
        findKv(selected.kv).areas.add(idx, new)
    }

    fun copyArea(selected: Area){
        val idx = areas.indexOf(selected) + 1
        with(selected.field1){
            val new = selected.copy(field1 = Field1(number, area, category, dp, typeOfProtection))
            areas.add(idx, new)
            val kv = findKv(selected.kv)
            if (idx == kv.areas.size) kv.areas.add(new) else kv.areas.add(idx, new)
        }
    }

    fun removeArea(selected: Area){
        areas.remove(selected)
        findKv(selected.kv).areas.remove(selected)
    }

    private fun findKv(number: Int) = startSq.find { it.number == number}!!

    fun diff(): ObservableList<ComparedPair>{
        val inputFile = File(inputFilePath)
        val current = Files.createTempFile("", "").toFile()
        //val current = File("D:/my/rawTest")
        //val current = File("J:/rawTest")
        writeToRawFile(current)
        return FileComparator(inputFile, current, Charset.forName("Cp866")).compare().toObservable()
    }


    private fun prepareForSaving(){
        areas.forEach { area ->
            with(area.field10.forestElements){
                val filtered = filter { it.hRang != 0 }
                clear()
                addAll(filtered)
            }
        }
    }

    private fun updateStrictAreaView() = strictAreaView?.update()

    fun squareIsNotEqual() = startSq.any { it.kvDiff != 0f }




}

