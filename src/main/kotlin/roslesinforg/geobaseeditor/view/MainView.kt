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
import roslesinforg.geobaseeditor.model.TextFieldImpl
import roslesinforg.geobaseeditor.model.validation.ValidationHelper
import roslesinforg.geobaseeditor.model.validation.ValidatorFactory
import roslesinforg.geobaseeditor.model.validation.ValidatorFactory.*
import roslesinforg.geobaseeditor.view.viewmodels.*
import kotlin.RuntimeException


fun main() {
    launch<GeoBaseEditorApp>()
}

class GeoBaseEditorApp: App(MainView::class)

class MainView : View("My View") {
    override val root: AnchorPane by fxml("/gui/MainView.fxml")
    val fGir: TextField by fxid()
    val fKvNumber: TextFieldImpl by fxid()
    val fAreaNumber: TextField by fxid()
    val fArea: TextField by fxid()
    val fCategoryArea: TextField by fxid()
    val fOzu: TextField by fxid()
    val fDP: TextField by fxid()
    val fSpecies: TextField by fxid()
    val fBon: TextField by fxid()
    val fType: TextField by fxid()
    val fSubType: TextField by fxid()
    val fYearOfDeforest: TextField by fxid()
    val fCountOfStump: TextField by fxid()
    val fCountOfPinusStump: TextField by fxid()
    val fStumpDiameter: TextField by fxid()
    val fTypeDeforest: TextField by fxid()
    val fDisorder: TextField by fxid()
    val fValidDisorder: TextField by fxid()
    val fDryTimber: TextField by fxid()
    val fAction1: TextField by fxid()
    val fAction2: TextField by fxid()
    val fAction3: TextField by fxid()
    val container23: FlowPane by fxid()
    val container23_2: FlowPane by fxid()
    val container_10: FlowPane by fxid()
    val fHrang1: TextField by fxid()
    val fHrang2: TextField by fxid()
    val fHrang3: TextField by fxid()
    val fHrang4: TextField by fxid()
    val fHrang5: TextField by fxid()
    val fHrang6: TextField by fxid()
    val fHrang7: TextField by fxid()
    val fHrang8: TextField by fxid()
    val fHrang9: TextField by fxid()
    val fHrang10: TextField by fxid()
    val fProportion1: TextField by fxid()
    val fProportion2: TextField by fxid()
    val fProportion3: TextField by fxid()
    val fProportion4: TextField by fxid()
    val fProportion5: TextField by fxid()
    val fProportion6: TextField by fxid()
    val fProportion7: TextField by fxid()
    val fProportion8: TextField by fxid()
    val fProportion9: TextField by fxid()
    val fProportion10: TextField by fxid()
    val fSpecies1: TextField by fxid()
    val fSpecies2: TextField by fxid()
    val fSpecies3: TextField by fxid()
    val fSpecies4: TextField by fxid()
    val fSpecies5: TextField by fxid()
    val fSpecies6: TextField by fxid()
    val fSpecies7: TextField by fxid()
    val fSpecies8: TextField by fxid()
    val fSpecies9: TextField by fxid()
    val fSpecies10: TextField by fxid()
    val fAge1: TextField by fxid()
    val fAge2: TextField by fxid()
    val fAge3: TextField by fxid()
    val fAge4: TextField by fxid()
    val fAge5: TextField by fxid()
    val fAge6: TextField by fxid()
    val fAge7: TextField by fxid()
    val fAge8: TextField by fxid()
    val fAge9: TextField by fxid()
    val fAge10: TextField by fxid()
    val fH1: TextField by fxid()
    val fH2: TextField by fxid()
    val fH3: TextField by fxid()
    val fH4: TextField by fxid()
    val fH5: TextField by fxid()
    val fH6: TextField by fxid()
    val fH7: TextField by fxid()
    val fH8: TextField by fxid()
    val fH9: TextField by fxid()
    val fH10: TextField by fxid()
    val fD1: TextField by fxid()
    val fD2: TextField by fxid()
    val fD3: TextField by fxid()
    val fD4: TextField by fxid()
    val fD5: TextField by fxid()
    val fD6: TextField by fxid()
    val fD7: TextField by fxid()
    val fD8: TextField by fxid()
    val fD9: TextField by fxid()
    val fD10: TextField by fxid()
    val fTradeClass1: TextField by fxid()
    val fTradeClass2: TextField by fxid()
    val fTradeClass3: TextField by fxid()
    val fTradeClass4: TextField by fxid()
    val fTradeClass5: TextField by fxid()
    val fTradeClass6: TextField by fxid()
    val fTradeClass7: TextField by fxid()
    val fTradeClass8: TextField by fxid()
    val fTradeClass9: TextField by fxid()
    val fTradeClass10: TextField by fxid()
    val fOrigin1: TextField by fxid()
    val fOrigin2: TextField by fxid()
    val fOrigin3: TextField by fxid()
    val fOrigin4: TextField by fxid()
    val fOrigin5: TextField by fxid()
    val fOrigin6: TextField by fxid()
    val fOrigin7: TextField by fxid()
    val fOrigin8: TextField by fxid()
    val fOrigin9: TextField by fxid()
    val fOrigin10: TextField by fxid()
    val fWeight1: TextField by fxid()
    val fWeight2: TextField by fxid()
    val fWeight3: TextField by fxid()
    val fWeight4: TextField by fxid()
    val fWeight5: TextField by fxid()
    val fWeight6: TextField by fxid()
    val fWeight7: TextField by fxid()
    val fWeight8: TextField by fxid()
    val fWeight9: TextField by fxid()
    val fWeight10: TextField by fxid()
    val fSumOfTimber1: TextField by fxid()
    val fSumOfTimber2: TextField by fxid()
    val fSumOfTimber3: TextField by fxid()
    val fSumOfTimber4: TextField by fxid()
    val fSumOfTimber5: TextField by fxid()
    val fSumOfTimber6: TextField by fxid()
    val fSumOfTimber7: TextField by fxid()
    val fSumOfTimber8: TextField by fxid()
    val fSumOfTimber9: TextField by fxid()
    val fSumOfTimber10: TextField by fxid()
    val f31_count: TextField by fxid()
    val f31_h: TextField by fxid()
    val f31_age: TextField by fxid()
    val f31_proportion1: TextField by fxid()
    val f31_element1: TextField by fxid()
    val f31_proportion2: TextField by fxid()
    val f31_element2: TextField by fxid()
    val fDop1_n: TextField by fxid()
    val fDop2_n: TextField by fxid()
    val fDop3_n: TextField by fxid()
    val fDop4_n: TextField by fxid()
    val fDop5_n: TextField by fxid()
    val fDop6_n: TextField by fxid()
    val fDop1_1: TextField by fxid()
    val fDop2_1: TextField by fxid()
    val fDop3_1: TextField by fxid()
    val fDop4_1: TextField by fxid()
    val fDop5_1: TextField by fxid()
    val fDop6_1: TextField by fxid()
    val fDop1_2: TextField by fxid()
    val fDop2_2: TextField by fxid()
    val fDop3_2: TextField by fxid()
    val fDop4_2: TextField by fxid()
    val fDop5_2: TextField by fxid()
    val fDop6_2: TextField by fxid()
    val fDop1_3: TextField by fxid()
    val fDop2_3: TextField by fxid()
    val fDop3_3: TextField by fxid()
    val fDop4_3: TextField by fxid()
    val fDop5_3: TextField by fxid()
    val fDop6_3: TextField by fxid()
    val fDop1_4: TextField by fxid()
    val fDop2_4: TextField by fxid()
    val fDop3_4: TextField by fxid()
    val fDop4_4: TextField by fxid()
    val fDop5_4: TextField by fxid()
    val fDop6_4: TextField by fxid()
    val fDop1_5: TextField by fxid()
    val fDop2_5: TextField by fxid()
    val fDop3_5: TextField by fxid()
    val fDop4_5: TextField by fxid()
    val fDop5_5: TextField by fxid()
    val fDop6_5: TextField by fxid()
    val fDop1_6: TextField by fxid()
    val fDop2_6: TextField by fxid()
    val fDop3_6: TextField by fxid()
    val fDop4_6: TextField by fxid()
    val fDop5_6: TextField by fxid()
    val fDop6_6: TextField by fxid()
    val fDop1_7: TextField by fxid()
    val fDop2_7: TextField by fxid()
    val fDop3_7: TextField by fxid()
    val fDop4_7: TextField by fxid()
    val fDop5_7: TextField by fxid()
    val fDop6_7: TextField by fxid()
    val fDop1_8: TextField by fxid()
    val fDop2_8: TextField by fxid()
    val fDop3_8: TextField by fxid()
    val fDop4_8: TextField by fxid()
    val fDop5_8: TextField by fxid()
    val fDop6_8: TextField by fxid()
    lateinit var kv_list: TableView<Area>
    val btnOpen: Button by fxid()
    val btnSave: Button by fxid()

    var path: Path
    var input: Path //todo for test

    val validationContext = ValidationContext()
    val factory = ValidatorFactory(validationContext)
    val validationHelper = ValidationHelper(validationContext, factory)

    
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



        validationHelper.stringValidatorFor(fSpecies, fType, fSubType, fTypeDeforest,
          fSpecies1, fSpecies2, fSpecies3, fSpecies4, fSpecies5, fSpecies6, fSpecies7, fSpecies8, fSpecies9,
          f31_element1, f31_element2)

        validationHelper.numberValidatorFor(fAreaNumber, fCategoryArea, fOzu, fDP, fYearOfDeforest, fCountOfStump,
        fCountOfPinusStump, fStumpDiameter, fDisorder, fValidDisorder, fDryTimber, fAction1, fAction2, fAction3,
        fHrang1, fHrang2, fHrang3, fHrang4, fHrang5, fHrang6, fHrang7, fHrang8, fHrang9, fHrang10, fProportion1,
        fProportion2, fProportion3, fProportion4, fProportion5, fProportion6, fProportion7, fProportion8, fProportion9,
        fProportion10, fAge1, fAge2, fAge3, fAge4, fAge5, fAge6, fAge7, fAge8, fAge9, fAge10, fH1, fH2, fH3, fH4, fH5,
        fH6, fH7, fH8, fH9, fH10, fD1, fD2, fD3, fD4, fD5, fD6, fD7, fD8, fD9, fD10, fTradeClass1, fTradeClass2,
        fTradeClass3, fTradeClass4, fTradeClass5, fTradeClass6, fTradeClass7, fTradeClass8, fTradeClass9, fTradeClass10,
        fOrigin1, fOrigin2, fOrigin3, fOrigin4, fOrigin5, fOrigin6, fOrigin7, fOrigin8, fOrigin9, fOrigin10, fWeight1,
        fWeight2, fWeight3, fWeight4, fWeight5, fWeight6, fWeight7, fWeight8, fWeight9, fWeight10, fSumOfTimber1,
        fSumOfTimber2, fSumOfTimber3, fSumOfTimber4, fSumOfTimber5, fSumOfTimber6, fSumOfTimber7, fSumOfTimber8,
        fSumOfTimber9, fSumOfTimber10, f31_count, f31_h, f31_age, f31_proportion1, f31_proportion2)

    }

    private fun bindModel(){
        with(model){
            fKvNumber byint kvProperty
            fKvNumber.bindDirty(model.dirtyStateFor(AreaModel::kvProperty))//todo YESSS!!!!





            //field_areaNumber.bind(model.numProperty)
            field1Model.apply {
                fAreaNumber byint numberProperty

                fArea byfloat areaProperty
                fCategoryArea byint categoryProperty
                fDP byint dpProperty
                fOzu byint typeOfProtectionProperty
            }
            field2ViewModel.apply {
                fAction1 byint firstActionProperty
                fAction2 byint secondActionProperty
                fAction3 byint thirdActionProperty
            }
            field3ViewModel.apply {
                fSpecies bystr speciesProperty
                fBon bystr bonProperty
                fType bystr typeProperty
                fSubType bystr subTypeProperty
                fYearOfDeforest byint yearOfDeforestationProperty
                fCountOfStump byint countOfStumpProperty
                fCountOfPinusStump byint countOfPinusStumpProperty
                fStumpDiameter byint stumpDiameterProperty
                fTypeDeforest bystr typeOfDeforestationProperty
            }
            field4ViewModel.apply {
                fDisorder byint disorderProperty
                fValidDisorder byint validDisorderProperty
                fDryTimber byint dryTimberProperty
            }
            f10Elements[0].bind10(fHrang1, fProportion1, fSpecies1, fAge1, fH1, fD1,
                fTradeClass1, fOrigin1, fWeight1, fSumOfTimber1)
            f10Elements[1].bind10(fHrang2, fProportion2, fSpecies2, fAge2, fH2, fD2,
                fTradeClass2, fOrigin2, fWeight2, fSumOfTimber2)
            f10Elements[2].bind10(fHrang3, fProportion3, fSpecies3, fAge3, fH3, fD3,
                fTradeClass3, fOrigin3, fWeight3, fSumOfTimber3)
            f10Elements[3].bind10(fHrang4, fProportion4, fSpecies4, fAge4, fH4, fD4,
                fTradeClass4, fOrigin4, fWeight4, fSumOfTimber4)
            f10Elements[4].bind10(fHrang5, fProportion5, fSpecies5, fAge5, fH5, fD5,
                fTradeClass5, fOrigin5, fWeight5, fSumOfTimber5)
            f10Elements[5].bind10(fHrang6, fProportion6, fSpecies6, fAge6, fH6, fD6,
                fTradeClass6, fOrigin6, fWeight6, fSumOfTimber6)
            f10Elements[6].bind10(fHrang7, fProportion7, fSpecies7, fAge7, fH7, fD7,
                fTradeClass7, fOrigin7, fWeight7, fSumOfTimber7)
            f10Elements[7].bind10(fHrang8, fProportion8, fSpecies8, fAge8, fH8, fD8,
                fTradeClass8, fOrigin8, fWeight8, fSumOfTimber8)
            f10Elements[8].bind10(fHrang9, fProportion9, fSpecies9, fAge9, fH9, fD9,
                fTradeClass9, fOrigin9, fWeight9, fSumOfTimber9)
            f10Elements[9].bind10(fHrang10, fProportion10, fSpecies10, fAge10, fH10, fD10,
                fTradeClass10, fOrigin10, fWeight10, fSumOfTimber10)

            field31ViewModel.apply {
                f31_count byfloat countProperty
                f31_age byint ageProperty
                f31_h byfloat hProperty
                f31_proportion1 byint proportion1Property
                f31_element1 bystr element1Property
                f31_proportion2 byint proportion2Property
                f31_element2 bystr element2Property
            }

           bindDop()
        }
    }

    private fun bindDop(){
        with(model.dopViewModel){
            bindDopLine(fDop1_n, fDop1_1, fDop1_2, fDop1_3, fDop1_4, fDop1_5, fDop1_6,
                fDop1_7, fDop1_8, dopFieldViewModels[0])
            bindDopLine(fDop2_n, fDop2_1, fDop2_2, fDop2_3, fDop2_4, fDop2_5, fDop2_6,
                fDop2_7, fDop2_8, dopFieldViewModels[1])
            bindDopLine(fDop3_n, fDop3_1, fDop3_2, fDop3_3, fDop3_4, fDop3_5, fDop3_6,
                fDop3_7, fDop3_8, dopFieldViewModels[2])
            bindDopLine(fDop4_n, fDop4_1, fDop4_2, fDop4_3, fDop4_4, fDop4_5, fDop4_6,
                fDop4_7, fDop4_8, dopFieldViewModels[3])
            bindDopLine(fDop5_n, fDop5_1, fDop5_2, fDop5_3, fDop5_4, fDop5_5, fDop5_6,
                fDop5_7, fDop5_8, dopFieldViewModels[4])
            bindDopLine(fDop6_n, fDop6_1, fDop6_2, fDop6_3, fDop6_4, fDop6_5, fDop6_6,
                fDop6_7, fDop6_8, dopFieldViewModels[5])
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

                onSelectionChange {
                    if (!this@MainView.validationContext.validate()){
                        error("Внимание", "Имеются некорректно заполненные поля, сохранить их?", ButtonType.OK, ButtonType.NO, title = "Ошибка"){
                            if (it == ButtonType.NO){
                                model.rollback()
                                selectionModel.clearSelection()//fixme IOB exception
                            }
                        }
                    }
                }
            model.rebindOnChange(this){ model ->
                if (model == null) return@rebindOnChange
                item = model
                println("Selection kv: ${item.kv} vid: ${item.field1.number}")
            }
            isEditable = true
            anchorpaneConstraints {
                topAnchor = 32
                leftAnchor = 0
                bottomAnchor = 0
            }
            prefWidth = 100.0
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
        btnOpen.apply {
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

        btnSave.apply {
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
    private infix fun TextFieldImpl.byint(other: Property<Int>) = this.bind(property = other, readonly = false, converter = FieldIntConverter())
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


