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
            //commit()
            invalidateViewModels()
            updateDopFields(it)
        }
    }

    override fun onCommit() {
        println("commit dop model")
        val area = item
            for(i in dopFieldViewModels.indices){
                val model = dopFieldViewModels[i]
                model.commit()
                when(model.number.value){
                    0 -> continue
                    11 -> area.field11 = Field11(
                        model.col1.value.toInt(),
                        model.col2.value.toInt(),
                        model.col3.value.toInt(),
                        model.col4.value.toFloat(),
                        model.col5.value.toFloat(),
                        model.col6.value.toFloat(),
                        model.col7.value.toInt(),
                        model.col8.value.toInt()
                    )
                    12 -> area.field12 = Field12(
                         model.col1.value.toInt(),
                         model.col2.value.toInt(),
                         model.col3.value,
                         model.col4.value.toInt(),
                         model.col5.value.toInt(),
                         model.col6.value.toInt(),
                         model.col7.value.toInt()
                    )
                    13 -> area.field13 = Field13(
                         model.col1.value.toFloat(),
                         model.col2.value.toFloat(),
                         model.col3.value.toInt(),
                         model.col4.value.toInt(),
                         model.col5.value.toInt(),
                         model.col6.value.toInt(),
                         model.col7.value.toInt()
                    )
                    19 -> area.field19 = Field19(
                         model.col1.value.toInt(),
                         model.col2.value.toInt(),
                         model.col3.value.toFloat()
                    )
                    21 -> area.field21 = Field21(
                         model.col1.value.toInt(),
                         model.col2.value.toInt(),
                         model.col3.value.toInt(),
                         model.col4.value.toInt(),
                         model.col5.value.toInt(),
                         model.col6.value.toInt(),
                         model.col7.value.toInt(),
                         model.col8.value.toInt(),
                    )
                    23 -> {
                        area.field23 = Field23().apply {
                            with(info){
                                clear()
                                if ( model.col1.value?.isNotEmpty() == true) add( model.col1.value.toInt()) else return@apply
                                if ( model.col2.value?.isNotEmpty() == true) add( model.col2.value.toInt()) else return@apply
                                if ( model.col3.value?.isNotEmpty() == true) add( model.col3.value.toInt()) else return@apply
                                if ( model.col4.value?.isNotEmpty() == true) add( model.col4.value.toInt()) else return@apply
                                if ( model.col5.value?.isNotEmpty() == true) add( model.col5.value.toInt()) else return@apply
                                if ( model.col6.value?.isNotEmpty() == true) add( model.col6.value.toInt()) else return@apply
                                if ( model.col7.value?.isNotEmpty() == true) add( model.col7.value.toInt()) else return@apply
                                if ( model.col8.value?.isNotEmpty() == true) add( model.col8.value.toInt()) else return@apply
                            }

                        }
                    }
                    29 -> area.field29 = Field29(
                        model.col1.value.toInt(),
                        model.col2.value.toInt(),
                        model.col3.value.toInt(),
                        model.col4.value,
                        model.col5.value.toFloat(),
                        model.col6.value.toFloat(),
                        model.col7.value
                    )
                }
            }


    }

    private fun updateDopFields(area: Area){
        println("update dop fields")
        with(area){
            if (field11.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field11
                model.isBounds = true
            }
            if (field12.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field12
                model.isBounds = true
            }
            if (field13.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field13
                model.isBounds = true
            }
            if (field19.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field19
                model.isBounds = true
            }
            if (field21.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field21
                model.isBounds = true
            }
            if (field23.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field23
                model.isBounds = true
            }
            if (field29.isNotEmpty()) {
                val model = changeDopFieldViewModel()
                model.item = field29
                model.isBounds = true
            }
        }
    }

    private fun invalidateViewModels(){
        println("dop models invalidated")
        dopFieldViewModels.forEach {
            it.apply {
                isBounds = false
                number.value = 0
                col1.value = ""
                col2.value = ""
                col3.value = ""
                col4.value = ""
                col5.value = ""
                col6.value = ""
                col7.value = ""
                col8.value = ""
                it.item = null
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
                if (it == null) return@onChange
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
                        if (it.info.isEmpty()) return@onChange
                        number.value = 23
                        var idx = 0
                        col1.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col2.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col3.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col4.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col5.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col6.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col7.value = it.info[idx++].prepare()
                        if (it.info.size > idx) col8.value = it.info[idx].prepare()
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

