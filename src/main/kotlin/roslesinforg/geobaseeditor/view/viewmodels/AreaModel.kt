package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import roslesinforg.porokhin.areatypes.fields.Field31
import tornadofx.*

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind (Area::kv)
    val field1Model = Field1ViewModel(area.field1)
    val field2ViewModel = Field2ViewModel(area.field2)
    val field3ViewModel = Field3ViewModel(area.field3)
    val field4ViewModel = Field4ViewModel(area.field4)
    val f10Elements = ArrayList<ElementOfForestViewModel>(10) //fixme sorting by hrang
    val field31ViewModel = Field31ViewModel(area.field31)
    val dopViewModel = DopViewModel(area)


    init {
        area.field10.forestElements.forEach { f10Elements.add(ElementOfForestViewModel(it)) }
        while (f10Elements.size < 10) f10Elements.add(ElementOfForestViewModel(ElementOfForest()))
    }

    override fun onCommit() {
        super.onCommit()
        println("commit ${area.kv}")
        field1Model.commit()
        field2ViewModel.commit()
        field3ViewModel.commit()
        field4ViewModel.commit()
        field31ViewModel.commit()
        dopViewModel.commit()
        area.field10.forestElements.clear()
        f10Elements.forEach {
            area.field10.forestElements.add(
                    ElementOfForest(it.hRangProperty.value, it.proportionProperty.value, it.speciesProperty.value,
                    it.ageProperty.value, it.hProperty.value, it.dProperty.value, it.tradeClassProperty.value, it.generationProperty.value,
                    it.weightProperty.value, it.sumOfTimberProperty.value)
            )
        }
    }






}


