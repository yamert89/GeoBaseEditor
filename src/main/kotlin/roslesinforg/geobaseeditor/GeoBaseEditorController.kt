package roslesinforg.geobaseeditor

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import roslesinforg.geobaseeditor.model.DataReader
import roslesinforg.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.areawriter.RawSoliAreaWriter
import roslesinforg.porokhin.filecomparator.FileComparator
import roslesinforg.porokhin.filecomparator.service.ComparedLine
import roslesinforg.porokhin.filecomparator.service.ComparedPair
import roslesinforg.porokhin.filecomparator.service.LineType
import tornadofx.Controller
import tornadofx.toObservable
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.Charset
import java.nio.file.Files

class GeoBaseEditorController: Controller() {
    var areas: SimpleListProperty<Area> = SimpleListProperty()
    var location: Location? = null
    var updateCounter = SimpleIntegerProperty(0)
    private val dataReader: DataReader = RawDataReader()
    private var inputFilePath = ""
    fun read(file: File){
        val data = dataReader.read(file)
        location = data.first
        areas.set(data.second.toObservable())
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
        //val current = Files.createTempFile("", "").toFile() //todo uncomment in prod
        //val current = File("D:/my/rawTest")
        val current = File("J:/rawTest")
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