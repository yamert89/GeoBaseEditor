package roslesinforg.geobaseeditor

import roslesinforg.geobaseeditor.model.DataReader
import roslesinforg.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area

class Controller {
    val areas: MutableList<Area> = ArrayList()
    private val dataReader = RawDataReader()
    fun read(){
        areas.addAll(dataReader.read())
    }
}