package roslesinforg.geobaseeditor.view

import javafx.beans.property.*
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import roslesinforg.geobaseeditor.view.viewmodels.AreaModel
import roslesinforg.geobaseeditor.view.viewmodels.DopViewModel
import roslesinforg.geobaseeditor.view.viewmodels.ElementOfForestViewModel
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import roslesinforg.porokhin.areatypes.fields.Field
import tornadofx.*
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

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
    val field_categoryArea: TextField by fxid()
    val field_ozu: TextField by fxid()
    val field_dp: TextField by fxid()
    val field_species: TextField by fxid()
    val field_bon: TextField by fxid()
    val field_type: TextField by fxid()
    val field_subType: TextField by fxid()
    val field_yearOfDeforest: TextField by fxid()
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
    val field_hrang1: TextField by fxid()
    val field_hrang2: TextField by fxid()
    val field_hrang3: TextField by fxid()
    val field_hrang4: TextField by fxid()
    val field_hrang5: TextField by fxid()
    val field_hrang6: TextField by fxid()
    val field_hrang7: TextField by fxid()
    val field_hrang8: TextField by fxid()
    val field_hrang9: TextField by fxid()
    val field_hrang10: TextField by fxid()
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
    val field_dop1_n: TextField by fxid()
    val field_dop2_n: TextField by fxid()
    val field_dop3_n: TextField by fxid()
    val field_dop4_n: TextField by fxid()
    val field_dop5_n: TextField by fxid()
    val field_dop6_n: TextField by fxid()
    val field_dop1_1: TextField by fxid()
    val field_dop2_1: TextField by fxid()
    val field_dop3_1: TextField by fxid()
    val field_dop4_1: TextField by fxid()
    val field_dop5_1: TextField by fxid()
    val field_dop6_1: TextField by fxid()
    val field_dop1_2: TextField by fxid()
    val field_dop2_2: TextField by fxid()
    val field_dop3_2: TextField by fxid()
    val field_dop4_2: TextField by fxid()
    val field_dop5_2: TextField by fxid()
    val field_dop6_2: TextField by fxid()
    val field_dop1_3: TextField by fxid()
    val field_dop2_3: TextField by fxid()
    val field_dop3_3: TextField by fxid()
    val field_dop4_3: TextField by fxid()
    val field_dop5_3: TextField by fxid()
    val field_dop6_3: TextField by fxid()
    val field_dop1_4: TextField by fxid()
    val field_dop2_4: TextField by fxid()
    val field_dop3_4: TextField by fxid()
    val field_dop4_4: TextField by fxid()
    val field_dop5_4: TextField by fxid()
    val field_dop6_4: TextField by fxid()
    val field_dop1_5: TextField by fxid()
    val field_dop2_5: TextField by fxid()
    val field_dop3_5: TextField by fxid()
    val field_dop4_5: TextField by fxid()
    val field_dop5_5: TextField by fxid()
    val field_dop6_5: TextField by fxid()
    val field_dop1_6: TextField by fxid()
    val field_dop2_6: TextField by fxid()
    val field_dop3_6: TextField by fxid()
    val field_dop4_6: TextField by fxid()
    val field_dop5_6: TextField by fxid()
    val field_dop6_6: TextField by fxid()
    val field_dop1_7: TextField by fxid()
    val field_dop2_7: TextField by fxid()
    val field_dop3_7: TextField by fxid()
    val field_dop4_7: TextField by fxid()
    val field_dop5_7: TextField by fxid()
    val field_dop6_7: TextField by fxid()
    val field_dop1_8: TextField by fxid()
    val field_dop2_8: TextField by fxid()
    val field_dop3_8: TextField by fxid()
    val field_dop4_8: TextField by fxid()
    val field_dop5_8: TextField by fxid()
    val field_dop6_8: TextField by fxid()
    lateinit var kv_list: TreeView<Int>

    var path: Path


    
    var model: AreaModel

    val text: StringProperty = SimpleStringProperty("dd")
    init {
        path = Paths.get("D:/my/json")
        if (Files.notExists(Paths.get("D:/my"))) path = Paths.get("J:/json")
        kv_list = TreeView(TreeItem(1)).apply {
            prefHeight = 600.0
            prefWidth = 100.0
            background = Background(BackgroundFill(Paint.valueOf("#000000"), CornerRadii.EMPTY, Insets.EMPTY))

        }
        kv_list.addTo(root)
        model = if(Files.exists(path)) {
            val str = Files.readAllLines(path, UTF_8).joinToString()
            println("json loaded: $str")
            AreaModel(Json.decodeFromString<Area>(str))
           // model.area = Json.decodeFromString<Area>(str)
        } else
            AreaModel(Area().apply {
                kv = 0
                field1 = Field1(0, 0f, 0, 0, 0)
                field2 = Field2(0, 0, 0)
                field3 = Field3("0", "0", "0", "0", 0, 0 , 0, 0, "0")
                field4 = Field4(0, 0, 0)
                field10 = Field10(mutableListOf(
                        ElementOfForest(0, 0, "0", 0, 0f, 0, 0, 0, 0f, 0),
                        ElementOfForest(0, 0, "0", 0, 0f, 0, 0, 0, 0f, 0)
                ))
                field11 = Field11(0, 0, 0, 0f, 0f, 0f, 0, 0)
                field12 = Field12(0, 0, "0", 0, 0, 0, 0)
                field19 = Field19(0, 0, 0f)
                field29 = Field29(0, 0, 0, "0", 0f, 0f, "0")
                field23 = Field23(mutableListOf(0, 0, 0))
                field31 = Field31(1.5f, 2f, 30, 10, "E")
            })
        with(model){
            field_kvNumber byint kvProperty
            //field_areaNumber.bind(model.numProperty)
            field1Model.apply {
                field_areaNumber byint numberProperty
                field_area byfloat areaProperty
                field_categoryArea byint categoryProperty
                field_dp byint dpProperty
                field_ozu byint typeOfProtectionProperty
            }
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
                field_yearOfDeforest byint yearOfDeforestationProperty
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
            f10Elements[0].bind10(field_hrang1, field_proportion1, field_species1, field_age1, field_h1, field_d1,
                field_tradeClass1, field_origin1, field_weight1, field_sumOfTimber1)
            f10Elements[1].bind10(field_hrang2, field_proportion2, field_species2, field_age2, field_h2, field_d2,
                field_tradeClass2, field_origin2, field_weight2, field_sumOfTimber2)
            f10Elements[2].bind10(field_hrang3, field_proportion3, field_species3, field_age3, field_h3, field_d3,
                field_tradeClass3, field_origin3, field_weight3, field_sumOfTimber3)
            f10Elements[3].bind10(field_hrang4, field_proportion4, field_species4, field_age4, field_h4, field_d4,
                field_tradeClass4, field_origin4, field_weight4, field_sumOfTimber4)
            f10Elements[4].bind10(field_hrang5, field_proportion5, field_species5, field_age5, field_h5, field_d5,
                field_tradeClass5, field_origin5, field_weight5, field_sumOfTimber5)
            f10Elements[5].bind10(field_hrang6, field_proportion6, field_species6, field_age6, field_h6, field_d6,
                field_tradeClass6, field_origin6, field_weight6, field_sumOfTimber6)
            f10Elements[6].bind10(field_hrang7, field_proportion7, field_species7, field_age7, field_h7, field_d7,
                field_tradeClass7, field_origin7, field_weight7, field_sumOfTimber7)
            f10Elements[7].bind10(field_hrang8, field_proportion8, field_species8, field_age8, field_h8, field_d8,
                field_tradeClass8, field_origin8, field_weight8, field_sumOfTimber8)
            f10Elements[8].bind10(field_hrang9, field_proportion9, field_species9, field_age9, field_h9, field_d9,
                field_tradeClass9, field_origin9, field_weight9, field_sumOfTimber9)
            f10Elements[9].bind10(field_hrang10, field_proportion10, field_species10, field_age10, field_h10, field_d10,
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

            bindDop()


        }

        primaryStage.setOnCloseRequest {
            model.commit()
            val out = Json.encodeToString(model.area)
            println(out)
            Files.write(path, out.toByteArray(UTF_8))
        }
    }

    private fun bindDop(){
        val dopFields = model.dopViewModel.dopFields
        val size = dopFields.size
        var idx = 0

        bindDopLine(field_dop1_n, field_dop1_1, field_dop1_2, field_dop1_3, field_dop1_4,
            field_dop1_5, field_dop1_6, field_dop1_7, field_dop1_8, dopFields[idx++])
        if (idx == size) return
        bindDopLine(field_dop2_n, field_dop2_1, field_dop2_2, field_dop2_3, field_dop2_4,
            field_dop2_5, field_dop2_6, field_dop2_7, field_dop2_8, dopFields[idx++])
        if (idx == size) return
        bindDopLine(field_dop3_n, field_dop3_1, field_dop3_2, field_dop3_3, field_dop3_4,
            field_dop3_5, field_dop3_6, field_dop3_7, field_dop3_8, dopFields[idx++])
        if (idx == size) return
        bindDopLine(field_dop4_n, field_dop4_1, field_dop4_2, field_dop4_3, field_dop4_4,
            field_dop4_5, field_dop4_6, field_dop4_7, field_dop4_8, dopFields[idx++])
        if (idx == size) return
        bindDopLine(field_dop5_n, field_dop5_1, field_dop5_2, field_dop5_3, field_dop5_4,
            field_dop5_5, field_dop5_6, field_dop5_7, field_dop5_8, dopFields[idx++])
        if (idx == size) return
        bindDopLine(field_dop6_n, field_dop6_1, field_dop6_2, field_dop6_3, field_dop6_4,
            field_dop6_5, field_dop6_6, field_dop6_7, field_dop6_8, dopFields[idx])
    }

    private fun bindDopLine(
        fieldName: TextField,
        col1: TextField,
        col2: TextField,
        col3: TextField,
        col4: TextField,
        col5: TextField,
        col6: TextField,
        col7: TextField,
        col8: TextField,
        dopViewModel: DopViewModel.DopFieldViewModel<out Field>
    ){

        with(dopViewModel){
            fieldName byint number
            when(this){
                is DopViewModel.Field11ViewModel -> {
                    col1 byint birthYearProperty
                    col2 byint prepareTypeProperty
                    col3 byint createTypeProperty
                    col4 byfloat  inLineProperty
                    col5 byfloat betweenRowsProperty
                    col6 byfloat countProperty
                    col7 byint stateProperty
                    col8 byint reasonOfDeathProperty
                }
                is DopViewModel.Field12ViewModel -> {
                    col1 byint reasonOfDamageProperty
                    col2 byint yearOfDamageProperty
                    col3 bystr speciesOfDamageProperty
                    col4 byint typeEnemy1
                    col5 byint degreeDamage1
                    col6 byint typeEnemy2
                    col7 byint degreeDamage2
                }
                is DopViewModel.Field13ViewModel -> {
                    col1 byfloat widthProperty
                    col2 byfloat lengthProperty
                    col3 byint stateProperty
                    col4 byint purposeProperty
                    col5 byint typeOfRoadSurfaceProperty
                    col6 byint widthOfRoadProperty
                    col7 byint seasonalityProperty
                }
                is DopViewModel.Field19ViewModel -> {
                    col1 byint typeSwampProperty
                    col2 byint typePlantsProperty
                    col3 byfloat weightOfPeatProperty
                }
                is DopViewModel.Field29ViewModel -> {
                    col1 byint typeProperty
                    col2 byint yearProperty
                    col3 byint categoryBeforeProperty
                    col4 bystr speciesBeforeProperty
                    col5 byfloat lengthBeforeProperty
                    col6 byfloat lengthBetweenProperty
                    col7 bystr bonProperty
                }
                is DopViewModel.Field23ViewModel -> {
                    col1 byint val1Property
                    col2 byint val2Property
                    col3 byint val3Property
                    col4 byint val4Property
                    col5 byint val5Property
                    col6 byint val6Property
                    col7 byint val7Property
                    col8 byint val8Property
                }
            }
        }

    }


    private fun unbindDop(){

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
