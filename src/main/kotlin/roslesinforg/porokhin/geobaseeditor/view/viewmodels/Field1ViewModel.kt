package roslesinforg.porokhin.geobaseeditor.view.viewmodels

import javafx.beans.property.FloatProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.ObjectProperty
import roslesinforg.porokhin.areatypes.fields.Field1
import tornadofx.ItemViewModel
import tornadofx.observable
import tornadofx.onChange
import kotlin.reflect.KMutableProperty1

class Field1ViewModel(field1: Field1, private val parent: AreaModel): ItemViewModel<Field1>(field1) {
    val numberProperty = bind(Field1::number)
    val areaProperty = bind(Field1::area).apply { onChange { parent.sqIsChanged.set(parent.sqIsChanged.value + 1) } }
    val categoryProperty = bind(Field1::category)
    val dpProperty = bind(Field1::dp)
    val typeOfProtectionProperty = bind(Field1::typeOfProtection)


}