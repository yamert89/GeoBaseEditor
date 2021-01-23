package roslesinforg.geobaseeditor

import roslesinforg.geobaseeditor.model.DataReader
import roslesinforg.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import tornadofx.Controller
import java.io.File
import java.util.*

class GeoBaseEditorController: Controller() {
    val areas: MutableList<Area> = LinkedList()
    private val dataReader: DataReader = RawDataReader()
    fun read(file: File){
        areas.addAll(dataReader.read(file))
    }

    fun selectArea(kv: Int, area: Int){
        
    }
}