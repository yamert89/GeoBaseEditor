package roslesinforg.geobaseeditor.view

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field1
import tornadofx.*
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind{ area.observable(Area::kv)} as IntegerProperty
    val numProperty = bind { area.field1.observable(Field1::number)} as IntegerProperty


    //val numProperty = bind { field1Property.value.observable(Field1::number) } as IntegerProperty
    var kv: Int by kvProperty
    val field1Property = bind{area.observable(Area::field1)} as ObjectProperty<Field1>
    //val numProperty = bind { field1Property.value.observable(Field1::number) } as IntegerProperty
    //val numProperty = bind { area.field1.observable(Field1::number)} as IntegerProperty
    var field1 by field1Property


    init {
        numProperty.addListener { ChangeListener() }
    }
        //var testProp = bind(SimpleBooleanProperty(area.builded)) as BooleanProperty

}


