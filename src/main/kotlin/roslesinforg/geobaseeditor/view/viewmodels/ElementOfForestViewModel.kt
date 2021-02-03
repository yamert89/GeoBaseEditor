package roslesinforg.geobaseeditor.view.viewmodels

import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import tornadofx.ItemViewModel
import tornadofx.onChange

class ElementOfForestViewModel(element: ElementOfForest): ItemViewModel<ElementOfForest>(element) {
    val hRangProperty = bind(ElementOfForest::hRang)
    val proportionProperty = bind(ElementOfForest::proportion)
    val speciesProperty = bind(ElementOfForest::species)
    val ageProperty = bind(ElementOfForest::age)
    val hProperty = bind(ElementOfForest::h)
    val dProperty = bind(ElementOfForest::d)
    val tradeClassProperty = bind(ElementOfForest::classOfTrade)
    val generationProperty = bind(ElementOfForest::generation)
    val weightProperty = bind(ElementOfForest::weight)
    val sumOfTimberProperty = bind(ElementOfForest::sumOfTimber)
    override fun onCommit() {
        println("commit element of forest")
    }
}