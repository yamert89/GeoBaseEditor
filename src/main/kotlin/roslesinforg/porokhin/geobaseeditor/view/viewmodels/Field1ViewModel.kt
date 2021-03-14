package roslesinforg.porokhin.geobaseeditor.view.viewmodels

import javafx.beans.property.FloatProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.ObjectProperty
import roslesinforg.porokhin.areatypes.fields.Field1
import tornadofx.ItemViewModel
import tornadofx.observable
import kotlin.reflect.KMutableProperty1

class Field1ViewModel(val field1: Field1): ItemViewModel<Field1>(field1) {
    val numberProperty = bind( Field1::number)
    val areaProperty = bind(Field1::area)
    val categoryProperty = bind(Field1::category)
    val dpProperty = bind(Field1::dp)
    val typeOfProtectionProperty = bind(Field1::typeOfProtection)


}