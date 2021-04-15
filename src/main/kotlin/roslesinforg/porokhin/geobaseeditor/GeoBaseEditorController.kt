package roslesinforg.porokhin.geobaseeditor

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import roslesinforg.porokhin.geobaseeditor.model.DataReader
import roslesinforg.porokhin.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.areawriter.RawSoliAreaWriter
import roslesinforg.porokhin.filecomparator.FileComparator
import roslesinforg.porokhin.filecomparator.service.ComparedLine
import roslesinforg.porokhin.filecomparator.service.ComparedPair
import roslesinforg.porokhin.filecomparator.service.LineType
import roslesinforg.porokhin.geobaseeditor.service.DDEClient
import roslesinforg.porokhin.geobaseeditor.view.MainView
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import org.apache.logging.log4j.kotlin.logger
import tornadofx.*

class GeoBaseEditorController: Controller() {
    private val logger = logger()
    var areas: ObservableList<Area> = SimpleListProperty()
    var location: Location? = null
    var updateCounter = SimpleIntegerProperty(0)
    val progressStatusProperty = SimpleDoubleProperty()
    private val dataReader: DataReader = RawDataReader(progressStatusProperty)
    var inputFilePath = ""
    var view: MainView? = null
    var ddeSession: DDEClient = DDEClient(this)


    fun setMainView(mainView: MainView) {
        view = mainView
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

    fun startDDESession() = ddeSession.initiate()

    fun stopDDESession() = ddeSession.close()

    fun read(file: File){

        val data = dataReader.read(file)
        location = data.first
        //areas.addAll(data.second)
        areas = data.second.asObservable()
        updateCounter.set(updateCounter.value++)
        inputFilePath = file.path
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
        areas.add(areas.indexOf(selected) + 1, Area(proto.region, proto.kv))
    }

    fun copyArea(selected: Area){
        val idx = areas.indexOf(selected)
        with(selected.field1){
            areas.add(idx + 1, selected.copy(field1 = Field1(number, area, category, dp, typeOfProtection)))
        }

    }

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

}