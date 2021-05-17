package roslesinforg.porokhin.geobaseeditor.view

import javafx.beans.property.FloatProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.Property
import javafx.beans.property.SimpleFloatProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import tornadofx.*

class StrictAreaView: GeoBaseEditorView("Площади") {
    private val logger = logger()
    val controller = tornadofx.find(GeoBaseEditorController::class, MainView.AppScope)
    val before: ObservableList<Pair<Int, List<Area>>> = controller.areas.groupBy{ it.kv }.map { it.key to it.value }.toObservable()


    override val root = borderpane {
        tableview(before) {
            isEditable = false
            column<Pair<Int, List<Area>>, Int>("Кв"){
                it.value.first.toProperty() as Property<Int>
            }
            column<Pair<Int, List<Area>>, Float>("До"){
                it.value.second.map { it.field1.area }.sum().toProperty() as Property<Float>
                /*(if (before.containsKey(it.value.id))
                    before[it.value.id].toProperty() else 0f.toProperty()) as Property<Float>*/
            }
            column<Area, Int>("После"){
                it.value.field1.area.toProperty() as Property<Int>
            }
            rowExpander {
                tableview(it) {  }
            }

        }
    }
}