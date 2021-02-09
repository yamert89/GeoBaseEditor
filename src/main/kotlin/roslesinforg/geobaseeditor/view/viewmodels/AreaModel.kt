package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import tornadofx.*

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind (Area::kv)
    val categoryProtectionProperty = bind(Area::categoryProtection) //todo
    val field1Model = Field1ViewModel(area.field1)
    val field2ViewModel = Field2ViewModel(area.field2)
    val field3ViewModel = Field3ViewModel(area.field3)
    val field4ViewModel = Field4ViewModel(area.field4)
    val f10Elements = ArrayList<ElementOfForestViewModel>(10)
    val field31ViewModel = Field31ViewModel(area.field31)
    val dopViewModel = DopViewModel(area)


    init {
        with(area.field10.forestElements){
            while (size < 10) add(ElementOfForest())
            forEach { f10Elements.add(ElementOfForestViewModel(it)) }
        }
        itemProperty.onChange {
            commit()
            if (it == null) return@onChange
            field1Model.item = it.field1
            field2ViewModel.item = it.field2
            field3ViewModel.item = it.field3
            field4ViewModel.item = it.field4
            field31ViewModel.item = it.field31
            for (i in it.field10.forestElements.indices){
                f10Elements[i].item = it.field10.forestElements[i]
            }
            dopViewModel.item = it
        }
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
        f10Elements.forEach { it.commit() }
    }




}


