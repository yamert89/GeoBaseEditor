package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import roslesinforg.porokhin.areatypes.fields.Field31
import tornadofx.*

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind { area.observable(Area::kv)}
    val field1Model = Field1ViewModel(area.field1)
    val field2ViewModel = Field2ViewModel(area.field2)
    val field3ViewModel = Field3ViewModel(area.field3)
    val field4ViewModel = Field4ViewModel(area.field4)
    val f10Elements = ArrayList<ElementOfForestViewModel>(8)
    val field31ViewModel = Field31ViewModel(area.field31)


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


