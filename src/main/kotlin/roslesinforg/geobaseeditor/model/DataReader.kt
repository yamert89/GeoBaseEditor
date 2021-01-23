package roslesinforg.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import java.io.File

interface DataReader {
    fun read(file: File): List<Area>
}