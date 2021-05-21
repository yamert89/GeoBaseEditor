package roslesinforg.porokhin.geobaseeditor.model

import javafx.beans.property.SimpleDoubleProperty
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location
import roslesinforg.porokhin.nabparser.Parser
import roslesinforg.porokhin.nabparser.ParsingResult
import tornadofx.runAsync
import tornadofx.ui
import java.io.File

class RawDataReader(override val progressStatusProperty: SimpleDoubleProperty) : DataReader {
    override fun read(file: File): ParsingResult {
        val parser = Parser(file)
        var running = true
        var result = ParsingResult(emptyList(), null, emptySet(), ParsingResult.Result.UNKNOWN)
       runAsync {
            result = parser.parse()
        } ui {
            running = false
        }
        val length = file.length().toDouble() * 0.8
        while (running){
            progressStatusProperty.value = parser.byteCounter / length
            Thread.sleep(20)
        }
        return result
    }
}