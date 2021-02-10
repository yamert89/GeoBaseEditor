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
            println("dopviewmodel item changed")
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
                when(numberProperty.value){ //todo refactoring
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
                            if (col1Property.value?.isNotEmpty() == true) add(col1Property.value.toInt()) else return@apply
                            if (col2Property.value?.isNotEmpty() == true) add(col2Property.value.toInt()) else return@apply
                            if (col3Property.value?.isNotEmpty() == true) add(col3Property.value.toInt()) else return@apply
                            if (col4Property.value?.isNotEmpty() == true) add(col4Property.value.toInt()) else return@apply
                            if (col5Property.value?.isNotEmpty() == true) add(col5Property.value.toInt()) else return@apply
                            if (col6Property.value?.isNotEmpty() == true) add(col6Property.value.toInt()) else return@apply
                            if (col7Property.value?.isNotEmpty() == true) add(col7Property.value.toInt()) else return@apply
                            if (col8Property.value?.isNotEmpty() == true) add(col8Property.value.toInt()) else return@apply
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
        var number = SimpleIntegerProperty() as Property<Int> //todo replace with val
        var col1 = SimpleStringProperty()
        var col2 = SimpleStringProperty()
        var col3 = SimpleStringProperty()
        var col4 = SimpleStringProperty()
        var col5 = SimpleStringProperty()
        var col6 = SimpleStringProperty()
        var col7 = SimpleStringProperty()
        var col8 = SimpleStringProperty()
        var numberProperty = bind{number}
        var col1Property = bind{col1}
        var col2Property = bind{col2}
        var col3Property = bind{col3}
        var col4Property = bind{col4}
        var col5Property = bind{col5}
        var col6Property = bind{col6}
        var col7Property = bind{col7}
        var col8Property = bind{col8}
        var isBounds = false

        init {

            itemProperty.onChange {
                println("dop field model changed")
                when(it){
                    is Field11 -> with(it){
                        updateProperties(11, birthYear, prepareType, createType, inLine, betweenRows, count, state, reazonOfDeath)
                    }
                    is Field12 -> with(it){
                        updateProperties(12, reasonOfDamage, yearOfDamage, speciesOfDamage, typeEnemy1, degreeDamage1, typeEnemy2, degreeDamage2)
                    }
                    is Field13 -> with(it){
                        updateProperties(13, width, length, state, purpose, typeOfRoadSurface, widthOfRoad, seasonality)
                    }
                    is Field19 -> with(it){
                        updateProperties(19, typeSwamp, typePlants, weightOfPeat)
                    }
                    is Field21 -> with(it){
                        updateProperties(21, landscape, ethetic, sanytary, stability, freeSpace, visualDistance,
                        health, antropoElements)
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
                    is Field29 -> with(it){
                        updateProperties(29, type, year, categoryBefore, speciesBefore, lengthBefore, lengthBetween, bon)
                    }
                }
                isBounds = true

            }
        }

        private fun updateProperties(num: Int, vararg col: Any){
            number.value = num
            for (i in col.indices){
                val par = col[i].prepare()
                when(i){
                    0 -> col1.value = par
                    1 -> col2.value = par
                    2 -> col3.value = par
                    3 -> col4.value = par
                    4 -> col5.value = par
                    5 -> col6.value = par
                    6 -> col7.value = par
                    7 -> col8.value = par
                }
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

    private fun String.toInt():Int = if (this.isEmpty()) 0 else Integer.parseInt(this)

    private fun String.toFloat(): Float = if (this.isEmpty()) 0f else this.originalToFloat()


}

