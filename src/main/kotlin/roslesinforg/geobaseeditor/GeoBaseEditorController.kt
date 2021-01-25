package roslesinforg.geobaseeditor

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import roslesinforg.geobaseeditor.model.DataReader
import roslesinforg.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import tornadofx.Controller
import tornadofx.toObservable
import java.io.File
import java.util.*

class GeoBaseEditorController: Controller() {
    var areas: MutableList<Area> = LinkedList()
    var updateCounter = SimpleIntegerProperty(0)
    private val dataReader: DataReader = RawDataReader()
    fun read(file: File){
        areas = SimpleListProperty(dataReader.read(file).toObservable())
        updateCounter.set(updateCounter.value++)
        println("areas loaded")
    }

    fun selectArea(kv: Int, area: Int){
        
    }
}