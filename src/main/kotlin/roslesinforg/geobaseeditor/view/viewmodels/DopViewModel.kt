package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import roslesinforg.porokhin.areatypes.fields.Field
import tornadofx.*
import kotlin.text.toFloat as originalToFloat

@Suppress("UNCHECKED_CAST") //todo refactoring with lists?
class DopViewModel(area: Area): ItemViewModel<Area>(area) {
    val dopFieldViewModels = listOf(
        DopFieldViewModel(Field23.Empty23),
        DopFieldViewModel(Field23.Empty23),
        DopFieldViewModel(Field23.Empty23),
        DopFieldViewModel(Field23.Empty23),
        DopFieldViewModel(Field23.Empty23),
        DopFieldViewModel(Field23.Empty23)
    )

    init {
        itemProperty.onChange {
            if (it == null) return@onChange
            invalidateViewModels()
            updateDopFields(it)
        }
    }

    override fun onCommit() {
        println("commit dop model")
        val area = item
        var model: DopFieldViewModel = dopFieldViewModels[0]
        with(model){
            while (model.isBounds){
                isBounds = true
                println(numberProperty.value)
                when(numberProperty.value){
                    11 -> area.field11 = Field11(
                        col1Property.value.toInt(),
                        col2Property.value.toInt(),
                        col3Property.value.toInt(),
                        col4Property.value.toFloat(),
                        col5Property.value.toFloat(),
                        col6Property.value.toFloat(),
                        col7Property.value.toInt(),
                        col8Property.value.toInt()
                    )
                    12 -> area.field12 = Field12(
                        col1Property.value.toInt(),
                        col2Property.value.toInt(),
                        col3Property.value,
                        col4Property.value.toInt(),
                        col5Property.value.toInt(),
                        col6Property.value.toInt(),
                        col7Property.value.toInt()
                    )
                    13 -> area.field13 = Field13(
                        col1Property.value.toFloat(),
                        col2Property.value.toFloat(),
                        col3Property.value.toInt(),
                        col4Property.value.toInt(),
                        col5Property.value.toInt(),
                        col6Property.value.toInt(),
                        col7Property.value.toInt()
                    )
                    19 -> area.field19 = Field19(
                        col1Property.value.toInt(),
                        col2Property.value.toInt(),
                        col3Property.value.toFloat()
                    )
                    21 -> area.field21 = Field21(
                        col1Property.value.toInt(),
                        col2Property.value.toInt(),
                        col3Property.value.toInt(),
                        col4Property.value.toInt(),
                        col5Property.value.toInt(),
                        col6Property.value.toInt(),
                        col7Property.value.toInt(),
                        col8Property.value.toInt(),
                    )
                    23 -> {
                        area.field23.info.apply {
                            clear()
                            if (col1Property.value.isNotEmpty()) add(col1Property.value.toInt()) else return@apply
                            if (col2Property.value.isNotEmpty()) add(col2Property.value.toInt()) else return@apply
                            if (col3Property.value.isNotEmpty()) add(col3Property.value.toInt()) else return@apply
                            if (col4Property.value.isNotEmpty()) add(col4Property.value.toInt()) else return@apply
                            if (col5Property.value.isNotEmpty()) add(col5Property.value.toInt()) else return@apply
                            if (col6Property.value.isNotEmpty()) add(col6Property.value.toInt()) else return@apply
                            if (col7Property.value.isNotEmpty()) add(col7Property.value.toInt()) else return@apply
                            if (col8Property.value.isNotEmpty()) add(col8Property.value.toInt()) else return@apply
                        }
                    }
                    29 -> area.field29 = Field29(
                       col1Property.value.toInt(),
                       col2Property.value.toInt(),
                       col3Property.value.toInt(),
                       col4Property.value,
                       col5Property.value.toFloat(),
                       col6Property.value.toFloat(),
                       col7Property.value
                    )
                }
                model = changeDopFieldViewModel()
            }
        }

    }

    private fun updateDopFields(area: Area){
        println("update dop fields")
        with(area){
            if (field11.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field11
            }
            if (field12.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field12
            }
            if (field13.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field13
            }
            if (field19.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field19
            }
            if (field21.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field21
            }
            if (field23.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field23
            }
            if (field29.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field29
            }
        }
    }

    private fun invalidateViewModels(){
        println("dop models invalidated")
        dopFieldViewModels.forEach {
            it.apply {
                isBounds = false
                numberProperty.value = 0
                col1Property.value = ""
                col2Property.value = ""
                col3Property.value = ""
                col4Property.value = ""
                col5Property.value = ""
                col6Property.value = ""
                col7Property.value = ""
                col8Property.value = ""
            }
        }
    }

    private fun changeDopFieldViewModel(): DopFieldViewModel{
        return dopFieldViewModels.first { !it.isBounds }
    }


    class DopFieldViewModel(field: Field) : ItemViewModel<Field>(field){
        var numberProperty = SimpleIntegerProperty() as Property<Int>
        var col1Property = SimpleStringProperty()
        var col2Property = SimpleStringProperty()
        var col3Property = SimpleStringProperty()
        var col4Property = SimpleStringProperty()
        var col5Property = SimpleStringProperty()
        var col6Property = SimpleStringProperty()
        var col7Property = SimpleStringProperty()
        var col8Property = SimpleStringProperty()
        var isBounds = false

        init {
            itemProperty.onChange {
                println("dop field model changed")
                when(it){
                    is Field11 -> {
                        numberProperty.value = 11
                        col1Property.value = it.birthYear.prepare()
                        col2Property.value = it.prepareType.prepare()
                        col3Property.value = it.createType.prepare()
                        col4Property.value = it.inLine.prepare()
                        col5Property.value = it.betweenRows.prepare()
                        col6Property.value = it.count.prepare()
                        col7Property.value = it.state.prepare()
                        col8Property.value = it.reazonOfDeath.prepare()
                    }
                    is Field12 -> {
                        numberProperty.value = 12
                        col1Property.value = it.reasonOfDamage.prepare()
                        col2Property.value = it.yearOfDamage.prepare()
                        col3Property.value = it.speciesOfDamage
                        col4Property.value = it.typeEnemy1.prepare()
                        col5Property.value = it.degreeDamage1.prepare()
                        col6Property.value = it.typeEnemy2.prepare()
                        col7Property.value = it.degreeDamage2.prepare()
                    }
                    is Field13 -> {
                        numberProperty.value = 13
                        col1Property.value = it.width.prepare()
                        col2Property.value = it.length.prepare()
                        col3Property.value = it.state.prepare()
                        col4Property.value = it.purpose.prepare()
                        col5Property.value = it.typeOfRoadSurface.prepare()
                        col6Property.value = it.widthOfRoad.prepare()
                        col7Property.value = it.seasonality.prepare()
                    }
                    is Field19 -> {
                        numberProperty.value = 19
                        col1Property.value = it.typeSwamp.prepare()
                        col2Property.value = it.typePlants.prepare()
                        col3Property.value = it.weightOfPeat.prepare()
                    }
                    is Field21 -> {
                        numberProperty.value = 21
                        col1Property.value = it.landscape.prepare()
                        col2Property.value = it.ethetic.prepare()
                        col3Property.value = it.sanytary.prepare()
                        col4Property.value = it.stability.prepare()
                        col5Property.value = it.freeSpace.prepare()
                        col6Property.value = it.visualDistance.prepare()
                        col7Property.value = it.health.prepare()
                        col8Property.value = it.antropoElements.prepare()
                    }
                    is Field23 -> {
                        numberProperty.value = 23
                        if (it.info.isEmpty()) return@onChange
                        var idx = 0
                        col1Property.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col2Property.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col3Property.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col4Property.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col5Property.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col6Property.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col7Property.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col8Property.value = it.info[idx].prepare()
                    }
                    is Field29 -> {
                        numberProperty.value = 29
                        col1Property.value = it.type.prepare()
                        col2Property.value = it.year.prepare()
                        col3Property.value = it.categoryBefore.prepare()
                        col4Property.value = it.speciesBefore
                        col5Property.value = it.lengthBefore.prepare()
                        col6Property.value = it.lengthBetween.prepare()
                        col7Property.value = it.bon
                    }
                }
                isBounds = true
            }
        }

        override fun toString(): String {
            return "DopFieldViewModel_${numberProperty.value}"
        }
        
        private fun Any.prepare(): String{
            val str: String = when(this){
                is Float, is Int -> this.toString()
                else -> this as String
            }
            return when(str){
                "0", "0.0" -> ""
                else -> str
            }
        }


    }

    fun String.toInt():Int = if (this.isEmpty()) 0 else Integer.parseInt(this)

    fun String.toFloat(): Float = if (this.isEmpty()) 0f else this.originalToFloat()


}

