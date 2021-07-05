package roslesinforg.porokhin.geobaseeditor.view.constructors

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter
import org.controlsfx.tools.Borders
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.geobaseeditor.model.Attribute
import roslesinforg.porokhin.geobaseeditor.model.ComparingCondition
import roslesinforg.porokhin.geobaseeditor.model.LogicCondition
import roslesinforg.porokhin.geobaseeditor.view.MainView
import toAttribute
import tornadofx.*
import tornadofx.controlsfx.toggleswitch
import java.util.stream.Stream

class SelectionPaneConstructor: ViewConstructor<MainView> {
    lateinit var counterLabel: Label
    lateinit var p1: ComboBox<String>
    lateinit var c1: ComboBox<String>
    lateinit var v1: ComboBox<String>
    lateinit var p2: ComboBox<String>
    lateinit var c2: ComboBox<String>
    lateinit var v2: ComboBox<String>
    lateinit var p3: ComboBox<String>
    lateinit var c3: ComboBox<String>
    lateinit var v3: ComboBox<String>
    lateinit var lc1: ComboBox<String>
    lateinit var lc2: ComboBox<String>
    lateinit var pEd: ComboBox<String>
    lateinit var cEd: Label
    lateinit var vEd: ComboBox<String>
    private val conds = ComparingCondition.values().map { it.toString() }
    private val readyForApplying = SimpleBooleanProperty()
    private val readyForAction = SimpleBooleanProperty()
    private val readySelection2 = SimpleBooleanProperty()
    private val readySelection3 = SimpleBooleanProperty()

    private val manualSelection = SimpleBooleanProperty()
    override fun construct(view: MainView) {
        with(view){
            selectionPane.apply {
                padding = Insets(3.0)

                borderpane {

                    anchorpaneConstraints {
                        topAnchor = 0
                        leftAnchor = 0
                        rightAnchor = 0
                        bottomAnchor = 0
                    }
                    top{
                        toggleswitch("Выбор по параметрам", manualSelection){
                            borderpaneConstraints {
                                margin = Insets(3.0)
                            }
                        }
                    }
                    center{
                        val selPanels = hbox {
                            selectionTable = tableview(controller.areas){
                                disableWhen { manualSelection }
                                maxWidth = 100.0

                                column("Id", Area::id){
                                    prefWidth = 100.0
                                }
                                multiSelect()
                            }
                            val paramsBox = vbox {
                                enableWhen { manualSelection }
                                val attrs = Attribute.values().map { it.toString() }

                                val logicConds = LogicCondition.values().map { it.toString() }
                                hbox {
                                    vboxConstraints { margin = Insets(5.0) }
                                    p1 = combobox(SimpleStringProperty(), attrs)
                                    c1 = combobox(SimpleStringProperty(), conds){hboxConstraints { marginLeftRight(5.0) }}
                                    v1 = combobox(SimpleStringProperty(), emptyList()) {
                                        valueProperty().onChange { readySelection2.value = true }
                                    }
                                }
                                val lc1 = combobox(SimpleStringProperty(), logicConds){
                                    vboxConstraints { margin = Insets(5.0) }
                                    selectionModel.select(0)
                                }
                                hbox {
                                    enableWhen { readySelection2 }
                                    vboxConstraints { margin = Insets(5.0) }
                                    p2 = combobox(SimpleStringProperty(), attrs)
                                    c2 = combobox(SimpleStringProperty(), conds){hboxConstraints { marginLeftRight(5.0) }}
                                    v2 = combobox(SimpleStringProperty(), emptyList()) {
                                        valueProperty().onChange { readySelection3.value = true }
                                    }
                                }
                                val lc2 = combobox(SimpleStringProperty(), logicConds){
                                    vboxConstraints { margin = Insets(5.0) }
                                    selectionModel.select(0)
                                }
                                hbox {
                                    enableWhen { readySelection3 }
                                    vboxConstraints { margin = Insets(5.0) }
                                    p3 = combobox(SimpleStringProperty(), attrs)
                                    c3 = combobox(SimpleStringProperty(), conds){hboxConstraints { marginLeftRight(5.0) }}
                                    v3 = combobox(SimpleStringProperty(), emptyList()) {  }
                                }
                                separator{}
                                vbox {
                                    enableWhen { readyForAction }
                                    vboxConstraints { margin = Insets(10.0) }
                                    label("Применить к выборке:")
                                    hbox {
                                        vboxConstraints { margin = Insets(10.0, 0.0, 10.0, 0.0) }
                                        pEd = combobox(SimpleStringProperty(), attrs)
                                        cEd = label("="){hboxConstraints { marginLeftRight(5.0) }}
                                        vEd = combobox(SimpleStringProperty(), emptyList()) {
                                            valueProperty().onChange { readyForApplying.value = true }
                                        }
                                        button("Применить"){
                                            hboxConstraints { marginLeftRight(5.0) }
                                            enableWhen { readyForApplying }
                                            action {
                                                //todo
                                            }
                                        }
                                    }
                                    separator {}
                                    hbox {
                                        vboxConstraints { margin = Insets(10.0, 0.0, 10.0, 0.0) }
                                        button("Открыть в новом окне"){
                                            action {
                                                //todo
                                            }
                                        }
                                    }
                                }

                            }


                        }
                    }
                    bottom {
                        hbox {
                            label("Выбрано записей:")
                            counterLabel = label()
                        }
                    }
                }

            }

            p1.addChangeListener()
            p2.addChangeListener()
            p3.addChangeListener()
            pEd.addChangeListener(false)







        }
    }
    @Suppress("unchecked_cast")
    private fun ComboBox<String>.addChangeListener(withConditions: Boolean = true){
        valueProperty().onChange {
            if (it == null) return@onChange
            var sum = 0
            val list: List<String> = when(it.toAttribute()){
                Attribute.OZU -> GeneralTypes.typesOfProtection.values.toList()
                Attribute.CATEGORY_PROTECTION -> GeneralTypes.categoryProtection.values.toList()
                Attribute.SPECIES -> GeneralTypes.species
                Attribute.BON -> GeneralTypes.bons
                Attribute.WEIGHT -> listOf("0,3", "0,4", "0,5", "0,6", "0,7", "0,8", "0,9", "1,0", "1,1", "1,2", "1,3", "1,4", "1,5")
                Attribute.SUM_OF_TIMBER ->  generateSequence {
                    sum += 10
                    if (sum < 360) sum else null
                }.map { i -> i.toString() }.asIterable().toMutableList().apply { this.add(0, "5") }
                Attribute.CATEGORY -> GeneralTypes.categoryArea.values.toList()
                Attribute.INFO -> (11..999).map { i -> i.toString() }.toList()
            }
            val neighbors = this.parent.getChildList()!!
            (neighbors[2] as ComboBox<String>).items = list.asObservable()
            if (!withConditions) return@onChange
            if (it.toAttribute() in listOf(Attribute.OZU, Attribute.INFO, Attribute.CATEGORY_PROTECTION, Attribute.SPECIES, Attribute.BON)){
                with(neighbors[1] as ComboBox<String>){
                    items = listOf("=").asObservable()
                    selectionModel.select(0)
                }
            } else with(neighbors[1] as ComboBox<String>){
                items = conds.asObservable()
                selectionModel.select(2)
            }
        }
    }
}