package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import tornadofx.*

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind { area.observable(Area::kv)} as IntegerProperty
    val field1Model = Field1ViewModel(area.field1)
    val field2ViewModel = Field2ViewModel(area.field2)




    override fun onCommit() {
        super.onCommit()
        field1Model.commit()
        field2ViewModel.commit()
    }

    //val numProperty: IntegerProperty = field1Model.numberProperty


    init {

    }
        //var testProp = bind(SimpleBooleanProperty(area.builded)) as BooleanProperty

}


