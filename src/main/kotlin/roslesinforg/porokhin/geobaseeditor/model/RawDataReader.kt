package roslesinforg.porokhin.geobaseeditor.model

import javafx.beans.property.SimpleDoubleProperty
import roslesinforg.porokhin.nabparser.RawParser
import roslesinforg.porokhin.nabparser.RawParsingResult
import tornadofx.runAsync
import tornadofx.ui
import java.io.File

class RawDataReader(override val progressStatusProperty: SimpleDoubleProperty) : DataReader {
    override fun read(file: File): RawParsingResult {
        val parser = RawParser(file)
        var running = true
        var result = RawParsingResult(emptyList(), null, emptySet(), RawParsingResult.Result.UNKNOWN)
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