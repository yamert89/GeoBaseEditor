package roslesinforg.geobaseeditor.view

import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import roslesinforg.porokhin.areatypes.Area
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue
import kotlin.reflect.KProperty

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    var kvProperty: SimpleIntegerProperty = SimpleIntegerProperty(this, "kv", area.kv)
    var kv: Int by kvProperty
    var numberProperty = SimpleIntegerProperty(this, "number", area.field1.number)
    var number by numberProperty
    var testProp = bind(SimpleBooleanProperty(area.builded)) as BooleanProperty

}


