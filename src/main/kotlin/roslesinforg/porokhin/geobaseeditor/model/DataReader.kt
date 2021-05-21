package roslesinforg.porokhin.geobaseeditor.model

import javafx.beans.property.SimpleDoubleProperty
import roslesinforg.porokhin.nabparser.RawParsingResult
import java.io.File

interface DataReader {
    val progressStatusProperty: SimpleDoubleProperty
    fun read(file: File): RawParsingResult
}