package roslesinforg.porokhin.geobaseeditor.view

import format
import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyFloatProperty
import javafx.beans.property.ReadOnlyFloatPropertyBase
import javafx.beans.property.SimpleFloatProperty
import javafx.collections.ObservableList
import javafx.scene.control.TableView
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.Kv
import tornadofx.*
import java.text.DecimalFormat

class StrictAreaView: GeoBaseEditorView("Площади") {
    private val logger = logger()
    val controller = find(GeoBaseEditorController::class, MainView.AppScope)
    val before = controller.startSq
    private var tableView: TableView<Kv>? = null
    private var internalTable : TableView<Area>? = null
    init {
        controller.setStrictView(this)
    }

    override val root = anchorpane {
        tableView = tableview(before){
            prefWidth = 200.0

            readonlyColumn("Кв", Kv::number).pctWidth(16).cellFormat {
                text = it.toString()
                style { fontWeight = FontWeight.BOLD } }
            readonlyColumn("До", Kv::oldAr).pctWidth(28).cellFormat { text = it.format() }
            readonlyColumn("После", Kv::ar).pctWidth(28).cellFormat { text = it.format() }
            readonlyColumn("Разница", Kv::kvDiff).pctWidth(28).cellFormat {
                text = it.format()
                if (text != "0") {
                    style {
                        textFill = Color.RED
                    }
                }else style{
                    textFill = Color.BLACK
                }
            }
            smartResize()
            rowExpander(true) { kv ->
                internalTable = tableview(kv.areas.toObservable()){
                    column<Area, Int>("Выд") { it.value.field1.number.toProperty() as Property<Int> }
                    column<Area, Float>("До"){ kv.internalArs[it.value.field1.number].toProperty() as Property<Float> }
                    column<Area, Float>("После"){it.value.field1.area.toProperty() as Property<Float>}
                    column<Area, Float>("Разница"){
                        val change = (it.value.field1.area - kv.internalArs[it.value.field1.number]!!)
                        change.toProperty() as Property<Float>
                    }.cellFormat {
                        text = it.format()
                        if (text != "0") style{textFill = Color.RED}
                        else style{textFill = Color.BLACK}
                    }
                }
            }



        }
    }

    fun update(){
        tableView!!.refresh()
        internalTable?.refresh()
    }
}

