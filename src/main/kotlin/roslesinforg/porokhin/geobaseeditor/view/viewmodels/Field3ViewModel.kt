package roslesinforg.porokhin.geobaseeditor.view.viewmodels

import javafx.beans.property.IntegerProperty
import roslesinforg.porokhin.areatypes.fields.Field3
import tornadofx.ItemViewModel

class Field3ViewModel(field3: Field3): ItemViewModel<Field3>(field3) {
    val speciesProperty = bind(Field3::species)
    val bonProperty = bind(Field3::bon)
    val typeProperty = bind(Field3::type)
    val subTypeProperty = bind(Field3::subType)
    val yearOfDeforestationProperty = bind(Field3::yearOfDeforestation)
    val countOfStumpProperty = bind(Field3::countOfStump)
    val countOfPinusStumpProperty = bind(Field3::countOfPinusStump)
    val stumpDiameterProperty = bind(Field3::stumpDiameter)
    val typeOfDeforestationProperty = bind(Field3::typeOfDeforestation)
}