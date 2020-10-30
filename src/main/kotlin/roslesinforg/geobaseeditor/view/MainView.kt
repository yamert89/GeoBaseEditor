package roslesinforg.geobaseeditor.view

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.event.EventHandler
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.FlowPane
import roslesinforg.geobaseeditor.view.viewmodels.AreaModel
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.areatypes.fields.Field10
import roslesinforg.porokhin.areatypes.fields.Field2
import tornadofx.*

fun main() {
    launch<GeoBaseEditorApp>()
}

class GeoBaseEditorApp: App(MainView::class)

class MainView : View("My View") {
    override val root: AnchorPane by fxml("/gui/MainView.fxml")
    val field_gir: TextField by fxid()
    val field_kvNumber: TextField by fxid()
    val field_areaNumber: TextField by fxid()
    val field_area: TextField by fxid()
    val field_species: TextField by fxid()
    val field_bon: TextField by fxid()
    val field_type: TextField by fxid()
    val field_subType: TextField by fxid()
    val field_yearDeforest: TextField by fxid()
    val field_countOfStump: TextField by fxid()
    val field_countOfPinusStump: TextField by fxid()
    val field_stumpDiameter: TextField by fxid()
    val field_typeDeforest: TextField by fxid()
    val field_disorder: TextField by fxid()
    val field_validDisorder: TextField by fxid()
    val field_dryTimber: TextField by fxid()
    val field_action1: TextField by fxid()
    val field_action2: TextField by fxid()
    val field_action3: TextField by fxid()
    val container23: FlowPane by fxid()
    val container23_2: FlowPane by fxid()
    val field_31_count: TextField by fxid()
    val field_31_h: TextField by fxid()
    val field_31_age: TextField by fxid()
    val field_31_proportion1: TextField by fxid()
    val field_31_element1: TextField by fxid()
    val field_31_proportion2: TextField by fxid()
    val field_31_element2: TextField by fxid()
    val container_10: FlowPane by fxid()
    val model = AreaModel(Area().apply {
        kv = 888
        field1 = Field1(77, 1.0f, 110100, 7, 445)
        field2 = Field2(33, 0, 0)
        field10 = Field10(mutableListOf(
            ElementOfForest(1, 8, "E", 180, 19f, 20, 1, 0, 0.7f, 120),
            ElementOfForest(1, 2, "B", 0, 22f, 22, 3, 0, 0f, 0)
        ))
    })
/*    val field_hRang1: TextField by fxid()
    val field_hRang2: TextField by fxid()
    val field_hRang3: TextField by fxid()
    val field_hRang4: TextField by fxid()
    val field_hRang5: TextField by fxid()
    val field_hRang6: TextField by fxid()
    val field_hRang7: TextField by fxid()
    val field_hRang8: TextField by fxid()
    val field_hRang9: TextField by fxid()
    val field_hRang10: TextField by fxid()
    val field_proportion1: TextField by fxid()
    val field_proportion2: TextField by fxid()
    val field_proportion3: TextField by fxid()
    val field_proportion4: TextField by fxid()
    val field_proportion5: TextField by fxid()
    val field_proportion6: TextField by fxid()
    val field_proportion7: TextField by fxid()
    val field_proportion8: TextField by fxid()
    val field_proportion9: TextField by fxid()
    val field_proportion10: TextField by fxid()
    val field_species1: TextField by fxid()
    val field_species2: TextField by fxid()
    val field_species3: TextField by fxid()
    val field_species4: TextField by fxid()
    val field_species5: TextField by fxid()
    val field_species6: TextField by fxid()
    val field_species7: TextField by fxid()
    val field_species8: TextField by fxid()
    val field_species9: TextField by fxid()
    val field_species10: TextField by fxid()
    val field_age1: TextField by fxid()
    val field_age2: TextField by fxid()
    val field_age3: TextField by fxid()
    val field_age4: TextField by fxid()
    val field_age5: TextField by fxid()
    val field_age6: TextField by fxid()
    val field_age7: TextField by fxid()
    val field_age8: TextField by fxid()
    val field_age9: TextField by fxid()
    val field_age10: TextField by fxid()
    val field_h1: TextField by fxid()
    val field_h2: TextField by fxid()
    val field_h3: TextField by fxid()
    val field_h4: TextField by fxid()
    val field_h5: TextField by fxid()
    val field_h6: TextField by fxid()
    val field_h7: TextField by fxid()
    val field_h8: TextField by fxid()
    val field_h9: TextField by fxid()
    val field_h10: TextField by fxid()
    val field_d1: TextField by fxid()
    val field_d2: TextField by fxid()
    val field_d3: TextField by fxid()
    val field_d4: TextField by fxid()
    val field_d5: TextField by fxid()
    val field_d6: TextField by fxid()
    val field_d7: TextField by fxid()
    val field_d8: TextField by fxid()
    val field_d9: TextField by fxid()
    val field_d10: TextField by fxid()*/

    val text: StringProperty = SimpleStringProperty("dd")
    init {
        field_kvNumber.bind(model.kvProperty)
        //field_areaNumber.bind(model.numProperty)
        field_areaNumber.bind(model.field1Model.numberProperty)
        field_area.bind(model.field1Model.areaProperty)
        field_action1.bind(model.field2ViewModel.firstActionProperty)
        var idxOfTextField = 0
        for (i in 0 until model.f10Elements.size){
            val element = model.f10Elements[i]
            val field1 = container_10.children[idxOfTextField] as TextField
            val field2 = container_10.children[idxOfTextField + 1] as TextField
            val field3 = container_10.children[idxOfTextField + 2] as TextField
            val field4 = container_10.children[idxOfTextField + 3] as TextField
            val field5 = container_10.children[idxOfTextField + 4] as TextField
            val field6 = container_10.children[idxOfTextField + 5] as TextField
            val field7 = container_10.children[idxOfTextField + 6] as TextField
            val field8 = container_10.children[idxOfTextField + 7] as TextField
            val field9 = container_10.children[idxOfTextField + 8] as TextField
            val field10 = container_10.children[idxOfTextField + 9] as TextField
            with(element){
                field1.bind(hRangProperty)
                field2.bind(proportionProperty)
                field3.bind(speciesProperty)
                field4.bind(ageProperty)
                field5.bind(hProperty)
                field6.bind(dProperty)
                field7.bind(tradeClassProperty)
                field8.bind(generationProperty)
                field9.bind(weightProperty)
                field10.bind(sumOfTimberProperty)
            }
            idxOfTextField += 8
        }
        /*for (i in 0 until model.f10.size){
            when(i){
                0 ->{
                    field_
                }
            }
        }*/
        //field_gir.bind(text)
        field_kvNumber.onMouseClicked = EventHandler {
            model.commit()
            println("kv = ${model.area.kv} , num = ${model.area.field1.number}," +
                    "area = ${model.area.field1.area}, action1 = ${model.area.field2.firstAction}") }
    }

}
