package roslesinforg.geobaseeditor.view

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import tornadofx.getValue
import tornadofx.setValue

class ExtendedArea(area: Area, k: Int) {
    var kvProperty: SimpleIntegerProperty = SimpleIntegerProperty(this, "kv", k)
    var kv: Int by kvProperty
    var numberProperty = SimpleIntegerProperty(this, "number", area.field1.number)
    var number by numberProperty



}


