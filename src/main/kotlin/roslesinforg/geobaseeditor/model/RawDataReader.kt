package roslesinforg.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location
import roslesinforg.porokhin.nabparser.Parser
import java.io.File

class RawDataReader: DataReader {
    override fun read(file: File): Pair<Location, List<Area>> {
        val parser = Parser(file)
        parser.parse()
        return parser.location!! to parser.areas
    }
}