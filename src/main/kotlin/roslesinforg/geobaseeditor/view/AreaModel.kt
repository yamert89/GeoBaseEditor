package roslesinforg.geobaseeditor.view

import javafx.beans.property.BooleanProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field1
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.observable
import tornadofx.setValue
import kotlin.reflect.KProperty

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind{ area.observable(Area::kv)}
    var kv: Int by kvProperty
    val field1Property = bind{area.observable(Area::field1)}


    init {

    }
        //var testProp = bind(SimpleBooleanProperty(area.builded)) as BooleanProperty

}


