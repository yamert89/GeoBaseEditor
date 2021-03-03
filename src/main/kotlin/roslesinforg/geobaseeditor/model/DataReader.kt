package roslesinforg.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location
import java.io.File

interface DataReader {
    fun read(file: File): Pair<Location, List<Area>>
}