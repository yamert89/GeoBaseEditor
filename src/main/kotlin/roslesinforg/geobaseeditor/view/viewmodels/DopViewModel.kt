package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.IntegerProperty
import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.ObservableList
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import tornadofx.*

class DopViewModel(area: Area): ItemViewModel<Area>(area) {

    var field11ViewModel: Field11ViewModel? = if (area.field11 == Field11.Empty11) null else Field11ViewModel(area.field11)
    var field12ViewModel: Field12ViewModel? = if (area.field12 == Field12.Empty12) null else Field12ViewModel(area.field12)
    var field13ViewModel: Field13ViewModel? = if (area.field13 == Field13.Empty13) null else Field13ViewModel(area.field13)
    var field19ViewModel: Field19ViewModel? = if (area.field19 == Field19.Empty19) null else Field19ViewModel(area.field19)
    var field29ViewModel: Field29ViewModel? = if (area.field29 == Field29.Empty29) null else Field29ViewModel(area.field29)
    var field23ViewModel: Field23ViewModel? = Field23ViewModel(area.field23)
    var f23 = area.field23.info



    //todo 23


    fun getLine(): Array<Property<Any>>{
        TODO()
    }

    override fun onCommit() {
        super.onCommit()
        field23ViewModel?.commit()
    }

    abstract class DopFieldViewModel <T>(field: T) : ItemViewModel<T>(field){
        abstract fun isempty(): Boolean
    }

    class Field11ViewModel(private val field11: Field11): DopFieldViewModel<Field11>(field11){
        val birthYearProperty = bind(Field11::birthYear)
        val prepareTypeProperty = bind(Field11::prepareType)
        val createTypeProperty = bind(Field11::createType)
        val inLineProperty = bind(Field11::inLine)
        val betweenRowsProperty = bind(Field11::betweenRows)
        val countProperty = bind(Field11::count)
        val stateProperty = bind(Field11::state)
        val reasonOfDeathProperty = bind(Field11::reazonOfDeath)
        override fun isempty() = field11 == Field11.Empty11

    }

    class Field12ViewModel(private val field12: Field12): DopFieldViewModel<Field12>(field12){
        val reasonOfDamageProperty = bind(Field12::reasonOfDamage)
        val yearOfDamageProperty = bind(Field12::yearOfDamage)
        val speciesOfDamageProperty = bind(Field12::speciesOfDamage)
        val typeEnemy1 = bind(Field12::typeEnemy1)
        val degreeDamage1 = bind(Field12::degreeDamage1)
        val typeEnemy2 = bind(Field12::typeEnemy2)
        val degreeDamage2 = bind(Field12::degreeDamage2)
        override fun isempty() = field12 == Field12.Empty12
    }

    class Field13ViewModel(private val field13: Field13): DopFieldViewModel<Field13>(field13){
        val widthProperty = bind(Field13::width)
        val lengthProperty = bind(Field13::length)
        val stateProperty = bind(Field13::state)
        val purposeProperty = bind(Field13::purpose)
        val typeOfRoadSurfaceProperty = bind(Field13::typeOfRoadSurface)
        val widthOfRoadProperty = bind(Field13::widthOfRoad)
        val seasonalityProperty = bind(Field13::seasonality)
        override fun isempty() = field13 == Field13.Empty13
    }

    class Field19ViewModel(private val field19: Field19): DopFieldViewModel<Field19>(field19){
        val typeSwampProperty = bind(Field19::typeSwamp)
        val typePlantsProperty = bind(Field19::typePlants)
        val weightOfPeatProperty = bind(Field19::weightOfPeat)
        override fun isempty() = field19 == Field19.Empty19
    }

    class Field29ViewModel(private val field29: Field29): DopFieldViewModel<Field29>(field29){
        val typeProperty = bind(Field29::type)
        val yearProperty = bind(Field29::year)
        var categoryBeforeProperty = bind(Field29::categoryBefore)
        var speciesBeforeProperty = bind(Field29::speciesBefore)
        var lengthBeforeProperty = bind(Field29::lengthBefore)
        var lengthBetweenProperty = bind(Field29::lengthBetween)
        var bonProperty = bind(Field29::bon)
        override fun isempty() = field29 == Field29.Empty29
    }

    class Field23ViewModel(private val field23: Field23): DopFieldViewModel<Field23>(field23){
        val val1Property = SimpleIntegerProperty(if (field23.info.isNotEmpty()) field23.info[0] else 0)
        val val2Property = SimpleIntegerProperty(if (field23.info.size >= 2) field23.info[1] else 0)
        val val3Property = SimpleIntegerProperty(if (field23.info.size >= 3) field23.info[2] else 0)
        val val4Property = SimpleIntegerProperty(if (field23.info.size >= 4) field23.info[3] else 0)
        val val5Property = SimpleIntegerProperty(if (field23.info.size >= 5) field23.info[4] else 0)
        val val6Property = SimpleIntegerProperty(if (field23.info.size >= 6) field23.info[5] else 0)
        val val7Property = SimpleIntegerProperty(if (field23.info.size >= 7) field23.info[6] else 0)
        val val8Property = SimpleIntegerProperty(if (field23.info.size == 8) field23.info[7] else 0)

        override fun isempty(): Boolean {
            return field23.info.isEmpty()
        }

        override fun onCommit() {
            super.onCommit()
            field23.info.apply {
                clear()
                addAll(listOf(
                    val1Property.get(),
                    val2Property.get(),
                    val3Property.get(),
                    val4Property.get(),
                    val5Property.get(),
                    val6Property.get(),
                    val7Property.get(),
                    val8Property.get()))
            }

        }
    }
}

