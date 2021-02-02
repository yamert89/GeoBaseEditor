package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.IntegerProperty
import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.ObservableList
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import roslesinforg.porokhin.areatypes.fields.Field
import tornadofx.*
@Suppress("UNCHECKED_CAST")
class DopViewModel(area: Area): ItemViewModel<Area>(area) {



    var field11ViewModel: Field11ViewModel? = if (area.field11 == Field11.Empty11) null else Field11ViewModel(area.field11)
    var field12ViewModel: Field12ViewModel? = if (area.field12 == Field12.Empty12) null else Field12ViewModel(area.field12)
    var field13ViewModel: Field13ViewModel? = if (area.field13 == Field13.Empty13) null else Field13ViewModel(area.field13)
    var field19ViewModel: Field19ViewModel? = if (area.field19 == Field19.Empty19) null else Field19ViewModel(area.field19)
    var field29ViewModel: Field29ViewModel? = if (area.field29 == Field29.Empty29) null else Field29ViewModel(area.field29)
    var field23ViewModel: Field23ViewModel? = Field23ViewModel(area.field23)
    val dopFields = mutableListOf<DopFieldViewModel<out Field>>()

    init {
        initProperties(area)
        updateDopFields()
        itemProperty.onChange {
            it!!
            initProperties(it)
            field12ViewModel?.item = it.field12
            field11ViewModel?.item = it.field11
            field13ViewModel?.item = it.field13
            field19ViewModel?.item = it.field19
            field23ViewModel?.item = it.field23
            field29ViewModel?.item = it.field29
            updateDopFields() }
    }

    private fun initProperties(area: Area){
       field12ViewModel = if (area.field12 == Field12.Empty12) null else Field12ViewModel(area.field12)
       field13ViewModel = if (area.field13 == Field13.Empty13) null else Field13ViewModel(area.field13)
       field19ViewModel = if (area.field19 == Field19.Empty19) null else Field19ViewModel(area.field19)
       field29ViewModel = if (area.field29 == Field29.Empty29) null else Field29ViewModel(area.field29)
    }

    private fun updateDopFields(){
        dopFields.clear()
        dopFields.apply {
            field11ViewModel?.let { add(it) }
            field12ViewModel?.let { add(it) }
            field13ViewModel?.let { add(it) }
            field19ViewModel?.let { add(it) }
            field23ViewModel?.let { add(it) }
            field29ViewModel?.let { add(it) }
        }
    }

    override fun onCommit() {
        super.onCommit()
        field23ViewModel?.commit()
        field11ViewModel?.commit()
        field13ViewModel?.commit()
        field19ViewModel?.commit()
        field29ViewModel?.commit()
    }

    abstract class DopFieldViewModel <T>(field: T) : ItemViewModel<T>(field){
        abstract fun isempty(): Boolean
        open var number: Property<Int> = SimpleIntegerProperty(0) as Property<Int>
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
        init {
            itemProperty.onChange {
                it!!
                birthYearProperty.value = it.birthYear
                prepareTypeProperty.value = it.prepareType
                createTypeProperty.value = it.createType
                inLineProperty.value = it.inLine
                betweenRowsProperty.value = it.betweenRows
                countProperty.value = it.count
                stateProperty.value = it.state
                reasonOfDeathProperty.value = it.reazonOfDeath
            }
        }
        override fun isempty() = field11 == Field11.Empty11
        override var number = SimpleIntegerProperty(11)  as Property<Int>

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
        override var number = SimpleIntegerProperty(12)  as Property<Int>
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
        override var number = SimpleIntegerProperty(13)  as Property<Int>
    }

    class Field19ViewModel(private val field19: Field19): DopFieldViewModel<Field19>(field19){
        val typeSwampProperty = bind(Field19::typeSwamp)
        val typePlantsProperty = bind(Field19::typePlants)
        val weightOfPeatProperty = bind(Field19::weightOfPeat)
        override fun isempty() = field19 == Field19.Empty19
        override var number = SimpleIntegerProperty(19)  as Property<Int>
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
        override var number = SimpleIntegerProperty(29)  as Property<Int>
    }

    class Field23ViewModel(private val field23: Field23): DopFieldViewModel<Field23>(field23){
        val val1Property: Property<Int> = SimpleIntegerProperty(if (field23.info.isNotEmpty()) field23.info[0] else 0)  as  Property<Int>
        val val2Property: Property<Int> = SimpleIntegerProperty(if (field23.info.size >= 2) field23.info[1] else 0)  as  Property<Int>
        val val3Property: Property<Int> = SimpleIntegerProperty(if (field23.info.size >= 3) field23.info[2] else 0)  as  Property<Int>
        val val4Property: Property<Int> = SimpleIntegerProperty(if (field23.info.size >= 4) field23.info[3] else 0)  as  Property<Int>
        val val5Property: Property<Int> = SimpleIntegerProperty(if (field23.info.size >= 5) field23.info[4] else 0)  as  Property<Int>
        val val6Property: Property<Int> = SimpleIntegerProperty(if (field23.info.size >= 6) field23.info[5] else 0)  as  Property<Int>
        val val7Property: Property<Int> = SimpleIntegerProperty(if (field23.info.size >= 7) field23.info[6] else 0)  as  Property<Int>
        val val8Property: Property<Int> = SimpleIntegerProperty(if (field23.info.size == 8) field23.info[7] else 0)  as  Property<Int>

        init {
            itemProperty.onChange {
                it!!
                if (it.info.isEmpty()) return@onChange
                val1Property.value = if (it.info.isNotEmpty()) it.info[0] else 0
                val2Property.value = if (it.info.size >= 2) it.info[1] else 0
                val3Property.value = if (it.info.size >= 3) it.info[2] else 0
                val4Property.value = if (it.info.size >= 4) it.info[3] else 0
                val5Property.value = if (it.info.size >= 5) it.info[4] else 0
                val6Property.value = if (it.info.size >= 6) it.info[5] else 0
                val7Property.value = if (it.info.size >= 7) it.info[6] else 0
                val8Property.value = if (it.info.size == 8) it.info[7] else 0
            }
        }

        override fun isempty(): Boolean {
            return field23.info.isEmpty()
        }
        override var number = SimpleIntegerProperty(23) as  Property<Int>


        override fun onCommit() {
            super.onCommit()
            field23.info.apply {
                clear()
                addAll(listOf(
                    val1Property.value,
                    val2Property.value,
                    val3Property.value,
                    val4Property.value,
                    val5Property.value,
                    val6Property.value,
                    val7Property.value,
                    val8Property.value))
            }

        }
    }

}

