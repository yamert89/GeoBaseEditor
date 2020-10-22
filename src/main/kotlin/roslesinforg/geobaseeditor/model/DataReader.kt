package roslesinforg.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area

interface DataReader {
    fun read(): List<Area>
}