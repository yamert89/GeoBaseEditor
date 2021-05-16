package roslesinforg.porokhin.geobaseeditor.view.viewmodels

import javafx.beans.property.*
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import tornadofx.*

class AreaModel(var area: Area) : ItemViewModel<Area>(area) {
    val kvProperty = bind (Area::kv)
    val field1Model = Field1ViewModel(area.field1)
    val field2ViewModel = Field2ViewModel(area.field2)
    val field3ViewModel = Field3ViewModel(area.field3)
    val field4ViewModel = Field4ViewModel(area.field4)
    val f10Elements = ArrayList<ElementOfForestViewModel>(10)
    val field31ViewModel = Field31ViewModel(area.field31)
    val dopViewModel = DopViewModel(area)


    init {
        f10Elements.apply { for (i in 0..9){ add(ElementOfForestViewModel(ElementOfForest()))} }
       /* with(area.field10.forestElements){
            while (size < 10) add(ElementOfForest())
            forEach { f10Elements.add(ElementOfForestViewModel(it)) }
        }*/

        itemProperty.addListener { _, oldArea, newArea ->
            //commit()
            if (newArea == null) return@addListener
            replaceEmptyFields(oldArea)
            with(newArea){
                if (!field1.isNotEmpty()) field1 = Field1()
                if (!field2.isNotEmpty()) field2 = Field2()
                if (!field3.isNotEmpty()) field3 = Field3()
                if (!field4.isNotEmpty()) field4 = Field4()
                if (!field31.isNotEmpty()) field31 = Field31()
                if (!field10.isNotEmpty()) field10 = Field10()
            }
            field1Model.item = newArea.field1
            field2ViewModel.item = newArea.field2
            field3ViewModel.item = newArea.field3
            field4ViewModel.item = newArea.field4
            field31ViewModel.item = newArea.field31
            with(newArea.field10.forestElements){
                while (size < 10) add(ElementOfForest())
            }
            bindF10(newArea)

            /*for (i in newArea.field10.forestElements.lastIndex + 1 .. f10Elements.lastIndex){
                f10Elements[i].item = ElementOfForest()
            }*/
            dopViewModel.item = newArea
        }

    }

    fun bindF10(newArea: Area = item){
        for (i in newArea.field10.forestElements.indices){
            f10Elements[i].item = newArea.field10.forestElements[i]
        }
    }

    fun replaceEmptyFields(area: Area){
        with(area){
            if (field1.deepEmpty()) field1 = Field1.Empty1
            if (field2.deepEmpty()) field2 = Field2.Empty2
            if (field3.deepEmpty()) field3 = Field3.Empty3
            if (field4.deepEmpty()) field4 = Field4.Empty4
            if (field31.deepEmpty()) field31 = Field31.Empty31
            if (field10.deepEmpty()) field10 = Field10.Empty10
        }

    }

    override fun onCommit() {
        super.onCommit()
        field1Model.commit()
        field2ViewModel.commit()
        field3ViewModel.commit()
        field4ViewModel.commit()
        field31ViewModel.commit()
        dopViewModel.commit()
        f10Elements.forEach { it.commit() }
    }



   /* override fun roolback(){
        field1Model.rollback()
        field2ViewModel.rollback()
        field3ViewModel.rollback()
        field4ViewModel.rollback()
        field31ViewModel.rollback()
        dopViewModel.rollback()
        f10Elements.forEach { it.rollback() }
    }*/




}


