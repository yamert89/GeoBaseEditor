package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.IntegerProperty
import roslesinforg.porokhin.areatypes.fields.Field2
import tornadofx.ItemViewModel

class Field2ViewModel(var field2: Field2): ItemViewModel<Field2>(field2) {
    val firstActionProperty = bind(Field2::firstAction)
    val secondActionProperty = bind(Field2::secondAction)
    val thirdActionProperty = bind(Field2::thirdAction)

}