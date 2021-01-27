package roslesinforg.geobaseeditor

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import roslesinforg.geobaseeditor.model.DataReader
import roslesinforg.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.areawriter.RawAreaWriter
import tornadofx.Controller
import tornadofx.toObservable
import java.io.File
import java.util.*

class GeoBaseEditorController: Controller() {
    var areas: SimpleListProperty<Area> = SimpleListProperty()
    var updateCounter = SimpleIntegerProperty(0)
    private val dataReader: DataReader = RawDataReader()
    fun read(file: File){
        areas.set(dataReader.read(file).toObservable())
        updateCounter.set(updateCounter.value++)
        println("areas loaded")
    }

    fun read(){
        areas.set(
            listOf<Area>(Area(),
                Area(1, 2, 2, Field1(2, 2f, 2, 0, 0)),
                Area(3, 3, 3, Field1(3, 3f, 3))).toObservable())
        updateCounter.set(updateCounter.value++)
    }

    fun writeToRawFile(file: File){
        val writer = RawAreaWriter(file)
        writer.writeAreas(areas)
    }

    fun selectArea(kv: Int, area: Int){
        
    }
}