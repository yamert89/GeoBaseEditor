package roslesinforg.geobaseeditor.view

import javafx.beans.property.*
import javafx.scene.control.*
import javafx.scene.input.*
import javafx.scene.layout.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import tornadofx.*
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import roslesinforg.geobaseeditor.GeoBaseEditorController
import roslesinforg.geobaseeditor.model.FieldFloatConverter
import roslesinforg.geobaseeditor.model.FieldIntConverter
import roslesinforg.geobaseeditor.model.FieldStringConverter
import roslesinforg.geobaseeditor.view.viewmodels.*
import java.lang.Exception


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
    lateinit var kv_list: TableView<Area>
    val btn_open: Button by fxid()
    val btn_save: Button by fxid()

    var path: Path
    var input: Path //todo for test

    val context = ValidationContext()

    
    var model: AreaModel
    val controller = find(GeoBaseEditorController::class)

    val text: StringProperty = SimpleStringProperty("dd")
    init {
        model = AreaModel(Area())
        path = Paths.get("D:/my/json")
        input = Paths.get("D:/my/0309")
        if (Files.notExists(Paths.get("D:/my"))) {
            path = Paths.get("J:/json")
            input = Paths.get("J:/0309")
        }

        /*
        model = if(Files.exists(path)) {
            val str = Files.readAllLines(path, UTF_8).joinToString()
            println("json loaded: $str")
            AreaModel(Json.decodeFromString<Area>(str))
           // model.area = Json.decodeFromString<Area>(str)
        } else
            AreaModel(Area())*/
        bindModel()
        buildKvList() //todo load list
        applyButtons()
        controller.read(input.toFile())


        primaryStage.setOnCloseRequest {
            model.commit()
            val out = Json.encodeToString(model.area)
            println(out)
            Files.write(path, out.toByteArray(UTF_8))
        }


       //todo replace
        context.addValidator(field_areaNumber, field_areaNumber.textProperty()){
            try {
                it!!.toInt()
                null
            }catch (e: Exception){
                error("Введите целое число")
            }
        }
    }

    private fun bindModel(){
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
    }

    private fun bindDop(){
        with(model.dopViewModel){
            bindDopLine(field_dop1_n, field_dop1_1, field_dop1_2, field_dop1_3, field_dop1_4, field_dop1_5, field_dop1_6,
                field_dop1_7, field_dop1_8, dopFieldViewModels[0])
            bindDopLine(field_dop2_n, field_dop2_1, field_dop2_2, field_dop2_3, field_dop2_4, field_dop2_5, field_dop2_6,
                field_dop2_7, field_dop2_8, dopFieldViewModels[1])
            bindDopLine(field_dop3_n, field_dop3_1, field_dop3_2, field_dop3_3, field_dop3_4, field_dop3_5, field_dop3_6,
                field_dop3_7, field_dop3_8, dopFieldViewModels[2])
            bindDopLine(field_dop4_n, field_dop4_1, field_dop4_2, field_dop4_3, field_dop4_4, field_dop4_5, field_dop4_6,
                field_dop4_7, field_dop4_8, dopFieldViewModels[3])
            bindDopLine(field_dop5_n, field_dop5_1, field_dop5_2, field_dop5_3, field_dop5_4, field_dop5_5, field_dop5_6,
                field_dop5_7, field_dop5_8, dopFieldViewModels[4])
            bindDopLine(field_dop6_n, field_dop6_1, field_dop6_2, field_dop6_3, field_dop6_4, field_dop6_5, field_dop6_6,
                field_dop6_7, field_dop6_8, dopFieldViewModels[5])
        }
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
        dopViewModel: DopViewModel.DopFieldViewModel
    ){
        with(dopViewModel){
            fieldName byint numberProperty
            col1 bystr col1Property
            col2 bystr col2Property
            col3 bystr col3Property
            col4 bystr col4Property
            col5 bystr col5Property
            col6 bystr col6Property
            col7 bystr col7Property
            col8 bystr col8Property
        }

    }

    private fun buildKvList(){
        root.apply { //todo table view with row expander
            val format = DataFormat("application/x-java-serialized-object")

            kv_list = tableview(controller.areas){
            model.rebindOnChange(this){
                if (!context.validate()){
                    alert(content = "Fuck", header = "alert", type = Alert.AlertType.ERROR) //todo
                    rollback()
                    return@rebindOnChange
                }
                if (it == null) return@rebindOnChange
                item = it
                println("Selection kv: ${item.kv} vid: ${item.field1.number}")
            }
            isEditable = true
            anchorpaneConstraints {
                topAnchor = 32
                leftAnchor = 0
                bottomAnchor = 0
            }
            prefWidth = 130.0
                shortcut(KeyCombination.keyCombination(KeyCode.DELETE.name)){
                    println("hi") //todo
                }
            readonlyColumn("Kv", Area::kv)
            column<Area, Int>("Выд"){
                SimpleIntegerProperty(it.value.field1.number) as Property<Int>
            }.makeEditable()
            setRowFactory {
                val row = TableRow<Area>()
                row.setOnDragDetected {
                    val index = row.index
                    if(row.isEmpty) return@setOnDragDetected
                    val dragboard = row.startDragAndDrop(TransferMode.MOVE)
                    dragboard.dragView = row.snapshot(null, null)
                    val cc = ClipboardContent()
                    cc[format] = index
                    dragboard.setContent(cc)
                    it.consume()
                    println("drag detected")
                }

                row.setOnDragOver {
                    val db = it.dragboard
                    if (!db.hasContent(format)) return@setOnDragOver
                    if (row.index != db.getContent(format) as Int){
                        it.acceptTransferModes(TransferMode.COPY, TransferMode.MOVE)
                        it.consume()
                    }
                }
                row.setOnDragDropped {
                    val db = it.dragboard
                    if(!db.hasContent(format)) return@setOnDragDropped
                    val dragIndex: Int = db.getContent(format) as Int
                    //val area = kv_list.items.removeAt(dragIndex)
                    val area = controller.areas.removeAt(dragIndex)
                    val dropIndex = if (row.isEmpty) kv_list.items.size else row.index
                    controller.areas.add(dropIndex, area)
                    it.isDropCompleted = true

                    selectionModel.select(dropIndex)
                    it.consume()

                }
                row
            }
            }

            controller.updateCounter.onChange { reloadKvList() }




            //kv_list = treeview<Area>()
        }

        //kv_list.addTo(root)

    }

    fun reloadKvList(){
        //kv_list.items.clear()
        //kv_list.items.addAll(controller.areas)

    }

    private fun applyButtons(){
        btn_open.apply {
            action {
                val files = chooseFile(
                    "Выберите файл",
                    owner = primaryStage,
                    mode = FileChooserMode.Single,
                    filters = arrayOf()
                )

                if (files.isEmpty()) {
                    controller.read() //todo for test
                    println(controller.areas.size)
                    return@action
                }
                controller.read(files[0])
            }
            tooltip("Открыть")
        }

        btn_save.apply {
            action {
                val dir = chooseDirectory(
                    "Сохранить",
                    owner = primaryStage
                ) ?: return@action
                controller.writeToRawFile(dir)
            }
            tooltip("Сохранить")

        }
    }


    private infix fun TextField.bystr(other: Property<String>) = this.bind(property = other, readonly = false, converter = FieldStringConverter())
    private infix fun TextField.byint(other: Property<Int>) = this.bind(property = other, readonly = false, converter = FieldIntConverter())
    private infix fun TextField.byfloat(other: Property<Float>) = this.bind(property = other, converter = FieldFloatConverter())

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


