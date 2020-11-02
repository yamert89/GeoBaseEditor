package roslesinforg.geobaseeditor.view

import javafx.beans.property.*
import javafx.event.EventHandler
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.FlowPane
import roslesinforg.geobaseeditor.view.viewmodels.AreaModel
import roslesinforg.geobaseeditor.view.viewmodels.ElementOfForestViewModel
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
    val container_10: FlowPane by fxid()
    val field_hRang1: TextField by fxid()
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
    val field_d10: TextField by fxid()
    val field_tradeClass1: TextField by fxid()
    val field_tradeClass2: TextField by fxid()
    val field_tradeClass3: TextField by fxid()
    val field_tradeClass4: TextField by fxid()
    val field_tradeClass5: TextField by fxid()
    val field_tradeClass6: TextField by fxid()
    val field_tradeClass7: TextField by fxid()
    val field_tradeClass8: TextField by fxid()
    val field_tradeClass9: TextField by fxid()
    val field_tradeClass10: TextField by fxid()
    val field_origin1: TextField by fxid()
    val field_origin2: TextField by fxid()
    val field_origin3: TextField by fxid()
    val field_origin4: TextField by fxid()
    val field_origin5: TextField by fxid()
    val field_origin6: TextField by fxid()
    val field_origin7: TextField by fxid()
    val field_origin8: TextField by fxid()
    val field_origin9: TextField by fxid()
    val field_origin10: TextField by fxid()
    val field_weight1: TextField by fxid()
    val field_weight2: TextField by fxid()
    val field_weight3: TextField by fxid()
    val field_weight4: TextField by fxid()
    val field_weight5: TextField by fxid()
    val field_weight6: TextField by fxid()
    val field_weight7: TextField by fxid()
    val field_weight8: TextField by fxid()
    val field_weight9: TextField by fxid()
    val field_weight10: TextField by fxid()
    val field_sumOfTimber1: TextField by fxid()
    val field_sumOfTimber2: TextField by fxid()
    val field_sumOfTimber3: TextField by fxid()
    val field_sumOfTimber4: TextField by fxid()
    val field_sumOfTimber5: TextField by fxid()
    val field_sumOfTimber6: TextField by fxid()
    val field_sumOfTimber7: TextField by fxid()
    val field_sumOfTimber8: TextField by fxid()
    val field_sumOfTimber9: TextField by fxid()
    val field_sumOfTimber10: TextField by fxid()
    val field_31_count: TextField by fxid()
    val field_31_h: TextField by fxid()
    val field_31_age: TextField by fxid()
    val field_31_proportion1: TextField by fxid()
    val field_31_element1: TextField by fxid()
    val field_31_proportion2: TextField by fxid()
    val field_31_element2: TextField by fxid()



    val model = AreaModel(Area().apply {
        kv = 888
        field1 = Field1(77, 1.0f, 110100, 7, 445)
        field2 = Field2(33, 0, 0)
        field10 = Field10(mutableListOf(
            ElementOfForest(1, 8, "E", 180, 19f, 20, 1, 0, 0.7f, 120),
            ElementOfForest(1, 2, "B", 0, 22f, 22, 3, 0, 0f, 0)
        ))
    })

    val text: StringProperty = SimpleStringProperty("dd")
    init {
        with(model){
            field_kvNumber byint kvProperty
            //field_areaNumber.bind(model.numProperty)
            field_areaNumber byint field1Model.numberProperty
            field_area byfloat field1Model.areaProperty
            field2ViewModel.apply {
                field_action1 byint firstActionProperty
                field_action2 byint secondActionProperty
                field_action3 byint thirdActionProperty
            }
            field3ViewModel.apply {
                field_species bystr speciesProperty
                field_bon bystr bonProperty
                field_type bystr typeProperty
                field_subType bystr subTypeProperty
                field_yearDeforest byint yearOfDeforestationProperty
                field_countOfStump byint countOfStumpProperty
                field_countOfPinusStump byint countOfPinusStumpProperty
                field_stumpDiameter byint stumpDiameterProperty
                field_typeDeforest bystr typeOfDeforestationProperty
            }
            field4ViewModel.apply {
                field_disorder byint disorderProperty
                field_validDisorder byint validDisorderProperty
                field_dryTimber byint dryTimberProperty
            }
            f10Elements[0].bind10(field_hRang1, field_proportion1, field_species1, field_age1, field_h1, field_d1,
                field_tradeClass1, field_origin1, field_weight1, field_sumOfTimber1)
            f10Elements[1].bind10(field_hRang2, field_proportion2, field_species2, field_age2, field_h2, field_d2,
                field_tradeClass2, field_origin2, field_weight2, field_sumOfTimber2)
            f10Elements[2].bind10(field_hRang3, field_proportion3, field_species3, field_age3, field_h3, field_d3,
                field_tradeClass3, field_origin3, field_weight3, field_sumOfTimber3)
            f10Elements[3].bind10(field_hRang4, field_proportion4, field_species4, field_age4, field_h4, field_d4,
                field_tradeClass4, field_origin4, field_weight4, field_sumOfTimber4)
            f10Elements[4].bind10(field_hRang5, field_proportion5, field_species5, field_age5, field_h5, field_d5,
                field_tradeClass5, field_origin5, field_weight5, field_sumOfTimber5)
            f10Elements[5].bind10(field_hRang6, field_proportion6, field_species6, field_age6, field_h6, field_d6,
                field_tradeClass6, field_origin6, field_weight6, field_sumOfTimber6)
            f10Elements[6].bind10(field_hRang7, field_proportion7, field_species7, field_age7, field_h7, field_d7,
                field_tradeClass7, field_origin7, field_weight7, field_sumOfTimber7)
            f10Elements[7].bind10(field_hRang8, field_proportion8, field_species8, field_age8, field_h8, field_d8,
                field_tradeClass8, field_origin8, field_weight8, field_sumOfTimber8)
            f10Elements[8].bind10(field_hRang9, field_proportion9, field_species9, field_age9, field_h9, field_d9,
                field_tradeClass9, field_origin9, field_weight9, field_sumOfTimber9)
            f10Elements[9].bind10(field_hRang10, field_proportion10, field_species10, field_age10, field_h10, field_d10,
                field_tradeClass10, field_origin10, field_weight10, field_sumOfTimber10)

            field31ViewModel.apply {
                field_31_count byfloat countProperty
                field_31_age byint ageProperty
                field_31_h byfloat hProperty
                field_31_proportion1 byint proportion1Property
                field_31_element1 bystr element1Property
                field_31_proportion2 byint proportion2Property
                field_31_element2 bystr element2Property
            }
        }

        field_kvNumber.onMouseClicked = EventHandler {
            model.commit()
            println("kv = ${model.area.kv} , num = ${model.area.field1.number}," +
                    "area = ${model.area.field1.area}, action1 = ${model.area.field2.firstAction}") }
    }

    private infix fun TextField.bystr(other: Property<String>) = this.bind(other)
    private infix fun TextField.byint(other: Property<Int>) = this.bind(other as IntegerProperty)
    private infix fun TextField.byfloat(other: Property<Float>) = this.bind(other as FloatProperty)

    private fun ElementOfForestViewModel.bind10(
        hRang: TextField,
        proportion: TextField,
        species: TextField,
        age: TextField,
        h: TextField,
        d: TextField,
        tradeClass: TextField,
        origin: TextField,
        weight: TextField,
        sumOfT: TextField
    ){
        hRang byint hRangProperty
        proportion byint proportionProperty
        species bystr speciesProperty
        age byint ageProperty
        h byfloat hProperty
        d byint dProperty
        tradeClass byint tradeClassProperty
        origin byint generationProperty
        weight byfloat weightProperty
        sumOfT byint sumOfTimberProperty
    }

}
