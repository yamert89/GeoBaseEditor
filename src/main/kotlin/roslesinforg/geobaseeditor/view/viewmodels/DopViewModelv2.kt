package roslesinforg.geobaseeditor.view.viewmodels

import javafx.beans.property.IntegerProperty
import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import roslesinforg.porokhin.areatypes.fields.Field
import tornadofx.*
@Suppress("UNCHECKED_CAST") //todo refactoring with lists?
class DopViewModelv2(area: Area): ItemViewModel<Area>(area) {
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
        //invalidateViewModels()
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
        var numberProperty = SimpleIntegerProperty()
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
                        col1Property.value = it.birthYear.toString()
                        col2Property.value = it.prepareType.toString()
                        col3Property.value = it.createType.toString()
                        col4Property.value = it.inLine.toString()
                        col5Property.value = it.betweenRows.toString()
                        col6Property.value = it.count.toString()
                        col7Property.value = it.state.toString()
                        col8Property.value = it.reazonOfDeath.toString()
                    }
                    is Field12 -> {
                        numberProperty.value = 12
                        col1Property.value = it.reasonOfDamage.toString()
                        col2Property.value = it.yearOfDamage.toString()
                        col3Property.value = it.speciesOfDamage
                        col4Property.value = it.typeEnemy1.toString()
                        col5Property.value = it.degreeDamage1.toString()
                        col6Property.value = it.typeEnemy2.toString()
                        col7Property.value = it.degreeDamage2.toString()
                    }
                    is Field13 -> {
                        numberProperty.value = 13
                        col1Property.value = it.width.toString()
                        col2Property.value = it.length.toString()
                        col3Property.value = it.state.toString()
                        col4Property.value = it.purpose.toString()
                        col5Property.value = it.typeOfRoadSurface.toString()
                        col6Property.value = it.widthOfRoad.toString()
                        col7Property.value = it.seasonality.toString()
                    }
                    is Field19 -> {
                        numberProperty.value = 19
                        col1Property.value = it.typeSwamp.toString()
                        col2Property.value = it.typePlants.toString()
                        col3Property.value = it.weightOfPeat.toString()
                    }
                    is Field21 -> {
                        numberProperty.value = 21
                        col1Property.value = it.landscape.toString()
                        col2Property.value = it.ethetic.toString()
                        col3Property.value = it.sanytary.toString()
                        col4Property.value = it.stability.toString()
                        col5Property.value = it.freeSpace.toString()
                        col6Property.value = it.visualDistance.toString()
                        col7Property.value = it.health.toString()
                        col8Property.value = it.antropoElements.toString()
                    }
                    is Field23 -> {
                        numberProperty.value = 23
                        if (it.info.isEmpty()) return@onChange
                        var idx = 0
                        col1Property.value = it.info[idx++].toString()
                        if (it.info.size > idx) col2Property.value = it.info[idx++].toString()
                        if (it.info.size > idx) col3Property.value = it.info[idx++].toString()
                        if (it.info.size > idx) col4Property.value = it.info[idx++].toString()
                        if (it.info.size > idx) col5Property.value = it.info[idx++].toString()
                        if (it.info.size > idx) col6Property.value = it.info[idx++].toString()
                        if (it.info.size > idx) col7Property.value = it.info[idx++].toString()
                        if (it.info.size > idx) col8Property.value = it.info[idx].toString()
                    }
                    is Field29 -> {
                        numberProperty.value = 29
                        col1Property.value = it.type.toString()
                        col2Property.value = it.year.toString()
                        col3Property.value = it.categoryBefore.toString()
                        col4Property.value = it.speciesBefore
                        col5Property.value = it.lengthBefore.toString()
                        col6Property.value = it.lengthBetween.toString()
                        col7Property.value = it.bon
                    }
                }
                isBounds = true
            }
        }

        override fun toString(): String {
            return "DopFieldViewModel_${numberProperty.value}"
        }
    }


}

