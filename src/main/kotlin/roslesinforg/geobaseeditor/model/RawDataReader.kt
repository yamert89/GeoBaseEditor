package roslesinforg.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.nabparser.Parser
import java.io.File

class RawDataReader: DataReader {
    override fun read(): List<Area> {
        val parser = Parser(File(""))
        parser.parse()
        return parser.areas
    }
}