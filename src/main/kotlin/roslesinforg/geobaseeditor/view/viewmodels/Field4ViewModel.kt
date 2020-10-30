package roslesinforg.geobaseeditor.view.viewmodels

import roslesinforg.porokhin.areatypes.fields.Field4
import tornadofx.ItemViewModel

class Field4ViewModel(field4: Field4): ItemViewModel<Field4>(field4) {
    val disorderProperty = bind(Field4::disorder)
    val validDisorderProperty = bind(Field4::validDisorder)
    val dryTimberProperty = bind(Field4::dryTimber)
}