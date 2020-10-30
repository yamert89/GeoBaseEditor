package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import tornadofx.*

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind { area.observable(Area::kv)} as IntegerProperty
    val field1Model = Field1ViewModel(area.field1)
    val field2ViewModel = Field2ViewModel(area.field2)
    val f10Elements = ArrayList<ElementOfForestViewModel>(8)


    init {
        area.field10.forestElements.forEach { f10Elements.add(ElementOfForestViewModel(it)) }
        while (f10Elements.size < 9) f10Elements.add(ElementOfForestViewModel(ElementOfForest()))
    }





    override fun onCommit() {
        super.onCommit()
        field1Model.commit()
        field2ViewModel.commit()
    }



}


