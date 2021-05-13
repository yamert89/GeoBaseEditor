package roslesinforg.porokhin.geobaseeditor.model

import javafx.beans.property.SimpleDoubleProperty
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location
import java.io.File

interface DataReader {
    val progressStatusProperty: SimpleDoubleProperty
    fun read(file: File): ReadEntity
}