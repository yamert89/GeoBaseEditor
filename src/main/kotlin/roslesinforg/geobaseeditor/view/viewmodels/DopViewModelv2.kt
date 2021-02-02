package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.IntegerProperty
import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import roslesinforg.porokhin.areatypes.fields.Field
import tornadofx.*
@Suppress("UNCHECKED_CAST")
class DopViewModelv2(area: Area): ItemViewModel<Area>(area) {



    abstract class DopFieldViewModel() : ItemViewModel<String>(){
        var numberProperty = SimpleIntegerProperty()
        var col1Property = SimpleStringProperty()
        var col2Property = SimpleStringProperty()
        var col3Property = SimpleStringProperty()
        var col4Property = SimpleStringProperty()
        var col5Property = SimpleStringProperty()
        var col6Property = SimpleStringProperty()
        var col7Property = SimpleStringProperty()
        var col8Property = SimpleStringProperty()
    }


}

