package roslesinforg.geobaseeditor.view.viewmodels

import roslesinforg.porokhin.areatypes.fields.Field31
import tornadofx.ItemViewModel

class Field31ViewModel(field31: Field31) : ItemViewModel<Field31>(field31) {
    val countProperty = bind(Field31::count)
    val hProperty = bind(Field31::h)
    val ageProperty = bind(Field31::age)
    val proportion1Property = bind(Field31::proportion1)
    val proportion2Property = bind(Field31::proportion2)
    val element1Property = bind(Field31::element1)
    val element2Property = bind(Field31::element2)

}