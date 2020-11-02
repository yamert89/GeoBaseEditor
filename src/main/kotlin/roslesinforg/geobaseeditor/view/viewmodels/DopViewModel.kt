package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.Property
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import tornadofx.ItemViewModel

class DopViewModel(area: Area): ItemViewModel<Area>(area) {

    //todo 23


    fun getLine(): Array<Property<Any>>{
        TODO()

    }

    class Field11ViewModel(field11: Field11): ItemViewModel<Field11>(field11){
        val birthYearProperty = bind(Field11::birthYear)
        val prepareTypeProperty = bind(Field11::prepareType)
        val createTypeProperty = bind(Field11::createType)
        val inLineProperty = bind(Field11::inLine)
        val betweenRowsProperty = bind(Field11::betweenRows)
        val countProperty = bind(Field11::count)
        val stateProperty = bind(Field11::state)
        val reasonOfDeathProperty = bind(Field11::reazonOfDeath)
    }

    class Field12ViewModel(field12: Field12): ItemViewModel<Field12>(field12){
        val reasonOfDamageProperty = bind(Field12::reasonOfDamage)
        val yearOfDamageProperty = bind(Field12::yearOfDamage)
        val speciesOfDamageProperty = bind(Field12::speciesOfDamage)
        val typeEnemy1 = bind(Field12::typeEnemy1)
        val degreeDamage1 = bind(Field12::degreeDamage1)
        val typeEnemy2 = bind(Field12::typeEnemy2)
        val degreeDamage2 = bind(Field12::degreeDamage2)
    }

    class Field13ViewModel(field13: Field13): ItemViewModel<Field13>(field13){
        val widthProperty = bind(Field13::width)
        val lengthProperty = bind(Field13::length)
        val stateProperty = bind(Field13::state)
        val purposeProperty = bind(Field13::purpose)
        val typeOfRoadSurfaceProperty = bind(Field13::typeOfRoadSurface)
        val widthOfRoadProperty = bind(Field13::widthOfRoad)
        val seasonalityProperty = bind(Field13::seasonality)
    }

    class Field19ViewModel(field19: Field19): ItemViewModel<Field19>(field19){
        val typeSwampProperty = bind(Field19::typeSwamp)
        val typePlantsProperty = bind(Field19::typePlants)
        val weightOfPeatProperty = bind(Field19::weightOfPeat)
    }

    class Field29ViewModel(field29: Field29): ItemViewModel<Field29>(field29){
        val typeProperty = bind(Field29::type)
        val yearProperty = bind(Field29::year)
        var categoryBeforeProperty = bind(Field29::categoryBefore)
        var speciesBeforeProperty = bind(Field29::speciesBefore)
        var lengthBeforeProperty = bind(Field29::lengthBefore)
        var lengthBetweenProperty = bind(Field29::lengthBetween)
        var bonProperty = bind(Field29::bon)
    }



}

