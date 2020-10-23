package roslesinforg.geobaseeditor

import roslesinforg.geobaseeditor.model.DataReader
import roslesinforg.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import java.util.*

class Controller {
    val areas: MutableList<Area> = LinkedList()
    private val dataReader: DataReader = RawDataReader()
    fun read(){
        areas.addAll(dataReader.read())
    }

    fun selectArea(kv: Int, area: Int){
        
    }
}