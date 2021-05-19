package roslesinforg.porokhin.geobaseeditor.view

import CLASS_SELECT_BTN_ACTIVE
import javafx.application.Platform
import javafx.beans.property.*
import javafx.embed.swing.SwingFXUtils
import javafx.scene.control.*
import javafx.scene.effect.DropShadow
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import javafx.stage.FileChooser
import roslesinforg.porokhin.areatypes.Area
import tornadofx.*
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.model.*
import roslesinforg.porokhin.geobaseeditor.model.validation.FilteringHelper
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidationHelper
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidatorFactory
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.*
import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.rawxlsconverter.RawToXLSConverterView
import javax.imageio.ImageIO
import org.apache.logging.log4j.kotlin.logger
import tornadofx.controlsfx.bindAutoCompletion
import java.io.File


fun main(args: Array<String>) {
    launch<GeoBaseEditorApp>(args)
}

class GeoBaseEditorApp: App(MainView::class) //todo area listener

class MainView : GeoBaseEditorView("Редактор базы") {
    private val logger = logger()
    override val root: TabPane by fxml("/gui/MainView.fxml")
    private val borderPane: BorderPane by fxid()
    private val cardLayout: AnchorPane by fxid()
    private val fGir: TextFieldImpl by fxid()
    private val fKvNumber: TextFieldImpl by fxid()
    private val fAreaNumber: TextFieldImpl by fxid()
    private val fArea: TextFieldImpl by fxid()
    private val fCategoryArea: TextFieldImpl by fxid()
    private val fOzu: TextFieldImpl by fxid()
    private val fDP: TextFieldImpl by fxid()
    private val fSpecies: TextFieldImpl by fxid()
    private val fBon: TextFieldImpl by fxid()
    private val fType: TextFieldImpl by fxid()
    private val fSubType: TextFieldImpl by fxid()
    private val fYearOfDeforest: TextFieldImpl by fxid()
    private val fCountOfStump: TextFieldImpl by fxid()
    private val fCountOfPinusStump: TextFieldImpl by fxid()
    private val fStumpDiameter: TextFieldImpl by fxid()
    private val fTypeDeforest: TextFieldImpl by fxid()
    private val fDisorder: TextFieldImpl by fxid()
    private val fValidDisorder: TextFieldImpl by fxid()
    private val fDryTimber: TextFieldImpl by fxid()
    private val fAction1: TextFieldImpl by fxid()
    private val fAction2: TextFieldImpl by fxid()
    private val fAction3: TextFieldImpl by fxid()
    private val container23: FlowPane by fxid()
    private val container23_2: FlowPane by fxid()
    private val container_10: FlowPane by fxid()
    private val fHrang1: TextFieldImpl by fxid()
    private val fHrang2: TextFieldImpl by fxid()
    private val fHrang3: TextFieldImpl by fxid()
    private val fHrang4: TextFieldImpl by fxid()
    private val fHrang5: TextFieldImpl by fxid()
    private val fHrang6: TextFieldImpl by fxid()
    private val fHrang7: TextFieldImpl by fxid()
    private val fHrang8: TextFieldImpl by fxid()
    private val fHrang9: TextFieldImpl by fxid()
    private val fHrang10: TextFieldImpl by fxid()
    private val fProportion1: TextFieldImpl by fxid()
    private val fProportion2: TextFieldImpl by fxid()
    private val fProportion3: TextFieldImpl by fxid()
    private val fProportion4: TextFieldImpl by fxid()
    private val fProportion5: TextFieldImpl by fxid()
    private val fProportion6: TextFieldImpl by fxid()
    private val fProportion7: TextFieldImpl by fxid()
    private val fProportion8: TextFieldImpl by fxid()
    private val fProportion9: TextFieldImpl by fxid()
    private val fProportion10: TextFieldImpl by fxid()
    private val fSpecies1: TextFieldImpl by fxid()
    private val fSpecies2: TextFieldImpl by fxid()
    private val fSpecies3: TextFieldImpl by fxid()
    private val fSpecies4: TextFieldImpl by fxid()
    private val fSpecies5: TextFieldImpl by fxid()
    private val fSpecies6: TextFieldImpl by fxid()
    private val fSpecies7: TextFieldImpl by fxid()
    private val fSpecies8: TextFieldImpl by fxid()
    private val fSpecies9: TextFieldImpl by fxid()
    private val fSpecies10: TextFieldImpl by fxid()
    private val fAge1: TextFieldImpl by fxid()
    private val fAge2: TextFieldImpl by fxid()
    private val fAge3: TextFieldImpl by fxid()
    private val fAge4: TextFieldImpl by fxid()
    private val fAge5: TextFieldImpl by fxid()
    private val fAge6: TextFieldImpl by fxid()
    private val fAge7: TextFieldImpl by fxid()
    private val fAge8: TextFieldImpl by fxid()
    private val fAge9: TextFieldImpl by fxid()
    private val fAge10: TextFieldImpl by fxid()
    private val fH1: TextFieldImpl by fxid()
    private val fH2: TextFieldImpl by fxid()
    private val fH3: TextFieldImpl by fxid()
    private val fH4: TextFieldImpl by fxid()
    private val fH5: TextFieldImpl by fxid()
    private val fH6: TextFieldImpl by fxid()
    private val fH7: TextFieldImpl by fxid()
    private val fH8: TextFieldImpl by fxid()
    private val fH9: TextFieldImpl by fxid()
    private val fH10: TextFieldImpl by fxid()
    private val fD1: TextFieldImpl by fxid()
    private val fD2: TextFieldImpl by fxid()
    private val fD3: TextFieldImpl by fxid()
    private val fD4: TextFieldImpl by fxid()
    private val fD5: TextFieldImpl by fxid()
    private val fD6: TextFieldImpl by fxid()
    private val fD7: TextFieldImpl by fxid()
    private val fD8: TextFieldImpl by fxid()
    private val fD9: TextFieldImpl by fxid()
    private val fD10: TextFieldImpl by fxid()
    private val fTradeClass1: TextFieldImpl by fxid()
    private val fTradeClass2: TextFieldImpl by fxid()
    private val fTradeClass3: TextFieldImpl by fxid()
    private val fTradeClass4: TextFieldImpl by fxid()
    private val fTradeClass5: TextFieldImpl by fxid()
    private val fTradeClass6: TextFieldImpl by fxid()
    private val fTradeClass7: TextFieldImpl by fxid()
    private val fTradeClass8: TextFieldImpl by fxid()
    private val fTradeClass9: TextFieldImpl by fxid()
    private val fTradeClass10: TextFieldImpl by fxid()
    private val fOrigin1: TextFieldImpl by fxid()
    private val fOrigin2: TextFieldImpl by fxid()
    private val fOrigin3: TextFieldImpl by fxid()
    private val fOrigin4: TextFieldImpl by fxid()
    private val fOrigin5: TextFieldImpl by fxid()
    private val fOrigin6: TextFieldImpl by fxid()
    private val fOrigin7: TextFieldImpl by fxid()
    private val fOrigin8: TextFieldImpl by fxid()
    private val fOrigin9: TextFieldImpl by fxid()
    private val fOrigin10: TextFieldImpl by fxid()
    private val fWeight1: TextFieldImpl by fxid()
    private val fWeight2: TextFieldImpl by fxid()
    private val fWeight3: TextFieldImpl by fxid()
    private val fWeight4: TextFieldImpl by fxid()
    private val fWeight5: TextFieldImpl by fxid()
    private val fWeight6: TextFieldImpl by fxid()
    private val fWeight7: TextFieldImpl by fxid()
    private val fWeight8: TextFieldImpl by fxid()
    private val fWeight9: TextFieldImpl by fxid()
    private val fWeight10: TextFieldImpl by fxid()
    private val fSumOfTimber1: TextFieldImpl by fxid()
    private val fSumOfTimber2: TextFieldImpl by fxid()
    private val fSumOfTimber3: TextFieldImpl by fxid()
    private val fSumOfTimber4: TextFieldImpl by fxid()
    private val fSumOfTimber5: TextFieldImpl by fxid()
    private val fSumOfTimber6: TextFieldImpl by fxid()
    private val fSumOfTimber7: TextFieldImpl by fxid()
    private val fSumOfTimber8: TextFieldImpl by fxid()
    private val fSumOfTimber9: TextFieldImpl by fxid()
    private val fSumOfTimber10: TextFieldImpl by fxid()
    private val f31_count: TextFieldImpl by fxid()
    private val f31_h: TextFieldImpl by fxid()
    private val f31_age: TextFieldImpl by fxid()
    private val f31_proportion1: TextFieldImpl by fxid()
    private val f31_element1: TextFieldImpl by fxid()
    private val f31_proportion2: TextFieldImpl by fxid()
    private val f31_element2: TextFieldImpl by fxid()
    private val f31_proportion3: TextFieldImpl by fxid()
    private val f31_element3: TextFieldImpl by fxid()
    private val fDop1_n: TextFieldImpl by fxid()
    private val fDop2_n: TextFieldImpl by fxid()
    private val fDop3_n: TextFieldImpl by fxid()
    private val fDop4_n: TextFieldImpl by fxid()
    private val fDop5_n: TextFieldImpl by fxid()
    private val fDop6_n: TextFieldImpl by fxid()
    private val fDop1_1: TextFieldImpl by fxid()
    private val fDop2_1: TextFieldImpl by fxid()
    private val fDop3_1: TextFieldImpl by fxid()
    private val fDop4_1: TextFieldImpl by fxid()
    private val fDop5_1: TextFieldImpl by fxid()
    private val fDop6_1: TextFieldImpl by fxid()
    private val fDop1_2: TextFieldImpl by fxid()
    private val fDop2_2: TextFieldImpl by fxid()
    private val fDop3_2: TextFieldImpl by fxid()
    private val fDop4_2: TextFieldImpl by fxid()
    private val fDop5_2: TextFieldImpl by fxid()
    private val fDop6_2: TextFieldImpl by fxid()
    private val fDop1_3: TextFieldImpl by fxid()
    private val fDop2_3: TextFieldImpl by fxid()
    private val fDop3_3: TextFieldImpl by fxid()
    private val fDop4_3: TextFieldImpl by fxid()
    private val fDop5_3: TextFieldImpl by fxid()
    private val fDop6_3: TextFieldImpl by fxid()
    private val fDop1_4: TextFieldImpl by fxid()
    private val fDop2_4: TextFieldImpl by fxid()
    private val fDop3_4: TextFieldImpl by fxid()
    private val fDop4_4: TextFieldImpl by fxid()
    private val fDop5_4: TextFieldImpl by fxid()
    private val fDop6_4: TextFieldImpl by fxid()
    private val fDop1_5: TextFieldImpl by fxid()
    private val fDop2_5: TextFieldImpl by fxid()
    private val fDop3_5: TextFieldImpl by fxid()
    private val fDop4_5: TextFieldImpl by fxid()
    private val fDop5_5: TextFieldImpl by fxid()
    private val fDop6_5: TextFieldImpl by fxid()
    private val fDop1_6: TextFieldImpl by fxid()
    private val fDop2_6: TextFieldImpl by fxid()
    private val fDop3_6: TextFieldImpl by fxid()
    private val fDop4_6: TextFieldImpl by fxid()
    private val fDop5_6: TextFieldImpl by fxid()
    private val fDop6_6: TextFieldImpl by fxid()
    private val fDop1_7: TextFieldImpl by fxid()
    private val fDop2_7: TextFieldImpl by fxid()
    private val fDop3_7: TextFieldImpl by fxid()
    private val fDop4_7: TextFieldImpl by fxid()
    private val fDop5_7: TextFieldImpl by fxid()
    private val fDop6_7: TextFieldImpl by fxid()
    private val fDop1_8: TextFieldImpl by fxid()
    private val fDop2_8: TextFieldImpl by fxid()
    private val fDop3_8: TextFieldImpl by fxid()
    private val fDop4_8: TextFieldImpl by fxid()
    private val fDop5_8: TextFieldImpl by fxid()
    private val fDop6_8: TextFieldImpl by fxid()
    private val selection1: Pane by fxid()
    private val selection2: Pane by fxid()
    private val selection3: Pane by fxid()
    private val selection4: Pane by fxid()
    private val selection5: Pane by fxid()
    private val selection6: Pane by fxid()
    private val selection7: Pane by fxid()
    private val selection8: Pane by fxid()
    private val selection9: Pane by fxid()
    private val selection10: Pane by fxid()
    private val selectionsF10 = mutableListOf<Pair<ToggleButton, Pane>>()
    private val selectBtn1: ToggleButton by fxid()
    private val selectBtn2: ToggleButton by fxid()
    private val selectBtn3: ToggleButton by fxid()
    private val selectBtn4: ToggleButton by fxid()
    private val selectBtn5: ToggleButton by fxid()
    private val selectBtn6: ToggleButton by fxid()
    private val selectBtn7: ToggleButton by fxid()
    private val selectBtn8: ToggleButton by fxid()
    private val selectBtn9: ToggleButton by fxid()
    private val selectBtn10: ToggleButton by fxid()
    private val fLog: Label by fxid()
    private lateinit var btnOpen: Button
    private lateinit var btnSave: Button
    private lateinit var screen: Button
    private lateinit var btnChanges: Button
    private val buttonBar: ButtonBar by fxid()
    private val topPane: HBox by fxid()
    private val fProgress: ProgressBar by fxid()
    private val validationContext = ValidationContext()
    private val factory = ValidatorFactory(validationContext)
    private val validationHelper = ValidationHelper(validationContext, factory)
    private val filteringHelper = FilteringHelper()
    private val enableFieldsTrigger = SimpleBooleanProperty()
    private var isDragged = false
    lateinit var kv_list: TableView<Area>
    object AppScope: Scope()
    val controller : GeoBaseEditorController by inject(AppScope)
    var model: AreaModel = controller.areaModel

    init {
        selectionsF10.addAll(listOf(
            selectBtn1 to selection1,
            selectBtn2 to selection2,
            selectBtn3 to selection3,
            selectBtn4 to selection4,
            selectBtn5 to selection5,
            selectBtn6 to selection6,
            selectBtn7 to selection7,
            selectBtn8 to selection8,
            selectBtn9 to selection9,
            selectBtn10 to selection10,
        ))



        //controller.read(input.toFile())
        bindModel()
        buildKvList()
        applyButtons()



        fKvNumber.apply {
            isEditable = false
            style{
                backgroundColor += c(0, 0, 0, 0.0)
            }
        }
        if (GeoBaseEditorPreferences.filtering.value) applyFilters()

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

        validationHelper.dValidatorFor(fD1, fD2, fD3, fD4, fD5, fD6, fD7, fD8, fD9, fD10)

        primaryStage.setOnCloseRequest {
            GeoBaseEditorPreferences.savePrefs()
        }
        primaryStage.isResizable = false
        primaryStage.icons.add(resources.image("/gui/Desktop.png"))
        controller.setMainView(this)
        fProgress.bind(controller.progressStatusProperty)
        fGir.enableWhen { enableFieldsTrigger }

        if ( app.parameters.raw.isNotEmpty() && app.parameters.raw[0] == "-d"){
            val file = File(this.javaClass.classLoader.getResource("0309").toURI())
            runAsync {
                controller.read(file)
                fProgress.isVisible = false
            } ui{
                val loc = controller.location!!
                val forestry = GeneralTypes.forestries[loc.forestry.toInt()]
                with(loc){
                    fGir.apply {
                        text = forestry?.sub?.get(subForestry.toInt()) ?: ""
                        isEditable = false
                        style{
                            backgroundColor += c(0, 0, 0, 0.0)
                        }
                    }
                }

                kv_list.items = controller.areas
                kv_list.smartResize()

                val path = file.absolutePath.let { if (it.length > 50) "...${it.substring(it.lastIndex - 10, it.length)}" else it}
                flog("Открыт файл ${path}.  Лесничество: ${forestry?.name ?: loc.forestry} , Участок: ${forestry?.sub?.get(loc.subForestry.toInt()) ?: loc.subForestry}")
                kv_list.selectionModel.select(0)
            }
        }



    }

    fun applyFilters(){
        with(filteringHelper){
            filter(fSpecies, fSpecies1, fSpecies2, fSpecies3, fSpecies4, fSpecies5, fSpecies6, fSpecies7, fSpecies8, fSpecies9,
                f31_element1, f31_element2, f31_element3){
                it.controlNewText.isEmpty() || it.controlNewText.matches("[ЕБСЛПОАаебслпо\\s]{1,4}".toRegex())
            }
            filter(fHrang1, fHrang2, fHrang3, fHrang4,fHrang5, fHrang6, fHrang7, fHrang8, fHrang9, fHrang10){ formatter ->
                formatter.controlNewText.let { it.isEmpty() || it.isInt() && it.length == 1 }
            }
            filter(fProportion1, fProportion2, fProportion3, fProportion4, fProportion5, fProportion6, fProportion7,
                fProportion8, fProportion9, fProportion10, f31_proportion1, f31_proportion2, f31_proportion3){ formatter ->
                formatter.controlNewText.let { it.isEmpty() || it.isInt() && it.toInt() < 11 }
            }
            filter(fAge1, fAge2, fAge3, fAge4, fAge5, fAge6, fAge7, fAge8, fAge9, fAge10, f31_age){ f ->
                f.controlNewText.let { it.isEmpty() || it.isInt() && it.toInt() in 1..400 }
            }
            filter(fTradeClass1, fTradeClass2, fTradeClass3, fTradeClass4, fTradeClass5, fTradeClass6, fTradeClass7, fTradeClass8,
                fTradeClass9, fTradeClass10){ f ->
                f.controlNewText.let { it.isEmpty() || it.isInt() && it.toInt() in 1..4 } //todo origin
            }
            filter(fWeight1, fWeight2, fWeight3, fWeight4, fWeight5, fWeight6, fWeight7, fWeight8, fWeight9, fWeight10){ f ->
                f.controlNewText.let { it.isEmpty() || it.isInt() && it.toInt() in 0..150 }
            }
            filter(fSumOfTimber1, fSumOfTimber2, fSumOfTimber3, fSumOfTimber4, fSumOfTimber5, fSumOfTimber6, fSumOfTimber7, fSumOfTimber8,
                fSumOfTimber9, fSumOfTimber10){ f ->
                f.controlNewText.let { it.isEmpty() || it.isInt() && it.toInt() in 5..900 }
            }
            filter(fAreaNumber, fCategoryArea, fDP, fOzu, fAction1, fAction2, fAction3, fDop1_n, fDop2_n, fDop3_n, fDop4_n,
                fDop5_n, fDop6_n){ f ->
                f.controlNewText.let {  it.isEmpty() || it.isInt() }
            }
            filter(fH1, fH2, fH3, fH4, fH5, fH6, fH7, fH8, fH9, fH10, f31_count, f31_h){ f ->
                f.controlNewText.let { str ->
                    val fl = str.replace(",", ".") + "f"
                    fl.isFloat() && fl.toFloat().let { it in 0f..50f }
                }
            }
            filter(fD1, fD2, fD3, fD4, fD5, fD6, fD7, fD8, fD9, fD10){ f ->
                f.controlNewText.let { d ->
                    d.isInt() && d.toInt() in 1..80/* &&
                            d.let { it.endsWith("0") || it.endsWith("2") || it.endsWith("4") ||
                            it.endsWith("6") || it.endsWith("8")}*/
                }
            }
            filter(fBon){ it.controlNewText.matches("[1-5АБаб]{1,2}".toRegex()) }
            filter(fType){ it.controlNewText.matches("[А-Яа-я\\s]{1,5}".toRegex()) }
            filter(fSubType){ it.controlNewText.matches("[А-Яа-я]{1,2}".toRegex()) }
        }
    }

    fun clearFilters(){
        filteringHelper.clearFiltering(fSpecies, fType, fSubType, fTypeDeforest,
            fSpecies1, fSpecies2, fSpecies3, fSpecies4, fSpecies5, fSpecies6, fSpecies7, fSpecies8, fSpecies9,
            f31_element1, f31_element2, fAreaNumber, fCategoryArea, fOzu, fDP, fYearOfDeforest, fCountOfStump,
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

    fun selectItem(idx: Int){
        kv_list.selectionModel.select(idx)
        Platform.runLater { kv_list.scrollTo(idx - 10) }
    }



    private fun buildKvList(){

        borderPane.apply {
            left {

                kv_list = tableview(controller.areas){

                    model.rebindOnChange(this){ area ->
                        if (area == null) return@rebindOnChange

                        if (!enableFieldsTrigger.value) {
                            enableFieldsTrigger.value = true
                        } else controller.paint()


                        val message = validationHelper.hRangsAndProportionsChecking( listOf(
                            fHrang1 to fProportion1,
                            fHrang2 to fProportion2,
                            fHrang3 to fProportion3,
                            fHrang4 to fProportion4,
                            fHrang5 to fProportion5,
                            fHrang6 to fProportion6,
                            fHrang7 to fProportion7,
                            fHrang8 to fProportion8,
                            fHrang9 to fProportion9,
                            fHrang10 to fProportion10)
                        )
                        var notice = ""
                        if (!validationContext.validate()) notice += "Имеются некорректно заполненные поля"
                        if (message.isNotEmpty()) notice += "\n$message"
                        if (notice.isNotEmpty()) {
                            error("Внимание", notice, ButtonType.OK, title = "Ошибка")
                            validationHelper.failedAreas.add(item.id)
                        } else validationHelper.failedAreas.remove(item.id)

                        selectionsF10.forEach {
                            it.second.isVisible = false
                            it.first.styleClass.remove(CLASS_SELECT_BTN_ACTIVE)
                        }

                        commit()
                        item = area
                        println("Selection kv: ${item.kv} vid: ${item.field1.number}")

                    }

                    shortcut(KeyCodeCombination(KeyCode.SUBTRACT)){
                        val selected = kv_list.selectionModel.selectedItem
                        kv_list.items.remove(selected)
                    }
                    shortcut(KeyCodeCombination(KeyCode.ADD)){
                        if (kv_list.selectedItem == null) return@shortcut
                        controller.copyArea(kv_list.selectedItem!!)
                        kv_list.selectionModel.select(kv_list.selectionModel.selectedIndex + 1)
                    }
                    shortcut(KeyCodeCombination(KeyCode.ADD, KeyCombination.SHIFT_ANY)){
                        if (kv_list.selectedItem == null) return@shortcut
                        controller.newEmptyArea(kv_list.selectedItem!!)
                        kv_list.selectionModel.select(kv_list.selectionModel.selectedIndex + 1)
                    }

                    isEditable = true
                    prefWidth = 130.0

                    readonlyColumn("Кв", Area::kv){
                        style{
                            textAlignment = TextAlignment.CENTER
                            fontWeight = FontWeight.BOLD
                        }
                    }
                    column<Area, Int>("Выд"){
                        it.value.field1.number.toProperty() as Property<Int>
                    }.makeEditable().apply {
                        this.setOnEditCommit {
                            model.field1Model.numberProperty.value = it.newValue
                        }
                    }
                    column("ЦНЛ", Area::categoryProtection){
                        style{ textAlignment = TextAlignment.CENTER}
                        setOnEditCommit {
                            if (GeneralTypes.categoryProtection[it.newValue] == it.newValue.toString()) {
                                error(header = "Ошибка", content = "Некорректное значение")
                                editModel.rollbackSelected()
                            }
                        }
                    }.makeEditable()

                    setRowFactory {
                        val row = TableRow<Area>()
                        val selectableEffect = DropShadow(3.0,  0.0, 0.4, c("#000", 0.9))
                        val fakeEffect = DropShadow(0.0, c("#000", 0.0))
                        val format = DataFormat.lookupMimeType("application/x-java-serialized-object") ?: DataFormat("application/x-java-serialized-object")
                        var draggedRow: TableRow<Area> = TableRow()
                        row.setOnDragDetected {
                            draggedRow = row
                            isDragged = true
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
                            isDragged = false
                            val db = it.dragboard
                            if(!db.hasContent(format)) return@setOnDragDropped
                            val dragIndex: Int = db.getContent(format) as Int
                            val area = controller.areas.removeAt(dragIndex)
                            val dropIndex = if (row.isEmpty) kv_list.items.size else
                                if (row.index < dragIndex) row.index else row.index - 1
                            controller.areas.add(dropIndex, area)
                            it.isDropCompleted = true

                            selectionModel.select(dropIndex)
                            it.consume()

                        }
                        row.setOnDragEntered {
                            row.style {
                                effect = selectableEffect
                            }
                        }
                        row.setOnDragExited {
                            row.style {
                                effect = fakeEffect
                            }
                        }
                        row
                    }
                    smartResize()
                }

            }
        }

    }

    private fun applyButtons(){
        buttonBar.apply {
            addNewButton("info.png", "О программе"){ //todo Replace with resources
                information("Редкатор Базы v. 1.0.0", """
                    Добавить выдел - NUM+
                    Скопировать выдел - CTRL + NUM+
                    Удалить выдел - NUM-
                    
                    Порохин А. А. 2021 г.
                """.trimIndent(), ButtonType.OK, title = "О программе" )
            }
            addNewButton("prefs.png", "Настройки"){
                openInternalWindow(PreferenceView::class)
            }

            addNewButton("Excel.png", "Сохранить в MS Excel"){
                find<RawToXLSConverterView>(params = mapOf(
                    "initAreas" to controller.areas,
                    "initOutputPath" to controller.inputFilePath)).openWindow(owner = null)
            }.apply { enableWhen { enableFieldsTrigger } }
            addNewButton("screen.png", "Скриншот карточки"){
                val file = chooseFile("Сохарнить GIF", arrayOf(FileChooser.ExtensionFilter("Файлы изображений", "*.gif", "*.jpg", "*.bmp", "*.tiff")), mode = FileChooserMode.Save, owner = primaryStage)
                if (file.isEmpty()) return@addNewButton
                val image = cardLayout.snapshot(null, null)
                //val path = "${kv_list.selectionModel.selectedItem.id}.gif"
                val ext = if (!file[0].path.matches(".+\\.gif".toRegex())) ".gif" else ""
                val path = "${file[0].path}$ext"
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "GIF", File(path))
                flog("Скриншот сохранен в $path")
            }.apply { enableWhen { enableFieldsTrigger } }
            addNewButton("change.png", "Изменения"){
                model.replaceEmptyFields(model.item)
                model.commit()
                openInternalWindow(ChangesView::class, Scope())
            }.apply { enableWhen { enableFieldsTrigger } }
            addNewButton("save.png", "Сохранить"){
                with(validationHelper.failedAreas){
                    var allow = true
                    if (isNotEmpty()) {
                        allow = false
                        error("Внимание", "Найдены ошибки в выделах ${this.joinToString()}")
                    }
                    else if (controller.squareIsNotEqual()) {
                        allow = false
                        confirm("Внимание", "Площади развязаны. Сохранить?"){
                            allow = true
                        }
                    }
                    if (!allow) return@addNewButton

                    kv_list.editModel.commit()
                    val file = chooseFile(
                        "Сохранить",
                        emptyArray(),
                        mode = FileChooserMode.Save,
                        owner = primaryStage
                    )
                    if (file.isEmpty()) return@addNewButton
                    model.replaceEmptyFields(model.item)
                    controller.writeToRawFile(file[0])
                    flog("Файл сохранен: ${file[0].absolutePath}")

                }
            }.apply { enableWhen { enableFieldsTrigger } }
            addNewButton("add.png", "Открыть"){
                val files = chooseFile(
                    "Выберите файл",
                    owner = primaryStage,
                    mode = FileChooserMode.Single,
                    filters = arrayOf()
                )
                if (files.isEmpty()) return@addNewButton
                fProgress.isVisible = true
                runAsync {
                    controller.read(files[0])
                    fProgress.isVisible = false
                } ui{
                    val loc = controller.location!!
                    val forestry = GeneralTypes.forestries[loc.forestry.toInt()]
                    with(loc){
                        fGir.apply {
                            text = forestry?.sub?.get(subForestry.toInt()) ?: ""
                            isEditable = false
                            style{
                                backgroundColor += c(0, 0, 0, 0.0)
                            }
                        }
                    }

                    kv_list.items = controller.areas
                    kv_list.smartResize()

                    val path = files[0].absolutePath.let { if (it.length > 50) "...${it.substring(it.lastIndex - 10, it.length)}" else it}
                    flog("Открыт файл ${path}.  Лесничество: ${forestry?.name ?: loc.forestry} , Участок: ${forestry?.sub?.get(loc.subForestry.toInt()) ?: loc.subForestry}")
                    kv_list.selectionModel.select(0)
                    if (GeoBaseEditorPreferences.squareControl.value) openStrictAreaView()
                }





            }
        }
        topPane.apply {
            style{
                backgroundColor += c("#696966")
            }
            togglebutton(selectFirst = false){
                tooltip("Связь с MapInfo")
                maxHeight = 20.0
                background = null
                hboxConstraints {
                    marginLeft = 300.0
                }
                contentDisplay = ContentDisplay.GRAPHIC_ONLY
                graphic = getImageResource(20.0, 40.0, "toggle-off_s.png")
                action {
                    logger.debug("click")
                    if (this.isSelected) {
                        controller.startDDESession()
                        graphic = getImageResource(20.0, 40.0, "toggle-on_s.png")
                        flog("Открыт канал связи с MapInfo")
                    }
                    else {
                        controller.stopDDESession()
                        graphic = getImageResource(20.0, 40.0, "toggle-off_s.png")
                        flog("Канал связи с MapInfo закрыт")
                    }
                }
                enableWhen { enableFieldsTrigger }
            }
        }

        selectionsF10.forEach { pair ->
            val btn = pair.first
            btn.apply {
                action {
                    if (isSelected){
                        pair.second.isVisible = true
                        btn.styleClass.add(CLASS_SELECT_BTN_ACTIVE)
                        selectionsF10.forEach { if(it.first != btn) {
                            it.first.deselect()
                            if (it.second != pair.second) it.second.isVisible = false
                        }}
                    } else{
                        pair.second.isVisible = false
                        btn.styleClass.remove(CLASS_SELECT_BTN_ACTIVE)
                    }
                }

            }
            btn.enableWhen { enableFieldsTrigger }

            btn.setOnDragDetected {
                selectionsF10.forEach { it.first.deselect() }
                btn.startDragAndDrop(TransferMode.MOVE).apply {
                    dragView = pair.second.snapshot(null, null)
                    setContent(ClipboardContent().apply { putString(selectionsF10.indexOf(btn).toString()) })
                }

            }
            btn.setOnDragDropped {
                val dragboard = it.dragboard
                if (!dragboard.hasString()) return@setOnDragDropped
                val oldIdx = dragboard.string.toInt()
                val newIdx = selectionsF10.indexOf(btn)
                val elements = model.item.field10.forestElements
                val replacingElement = elements[oldIdx]
                elements.removeAt(oldIdx)
                elements.add(newIdx, replacingElement)
                it.isDropCompleted = true
                it.consume()
                model.bindF10()
            }
            btn.setOnDragOver {
                if (selectionsF10.indexOf(btn) != it.dragboard.string.toInt()){
                    it.acceptTransferModes(TransferMode.MOVE)
                    it.consume()
                }

            }
            btn.setOnDragEntered {
                selectionsF10[selectionsF10.indexOf(btn)].second.isVisible = true
            }
            btn.setOnDragExited {
                selectionsF10[selectionsF10.indexOf(btn)].second.isVisible = false
            }


        }
    }

    fun ToggleButton.deselect(){
        isSelected = false
        styleClass.remove(CLASS_SELECT_BTN_ACTIVE)
    }
    fun MutableList<Pair<ToggleButton, Pane>>.indexOf(btn: ToggleButton) = this.indexOf(find { it.first == btn })

    private fun bindModel(){
        logger.debug("build model")
        with(model){
            fKvNumber byint kvProperty
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

            shortcut(KeyCodeCombination(KeyCode.DELETE)){
                val selected = selectionsF10.find { it.second.isVisible } ?: return@shortcut
                val idx = selectionsF10.indexOf(selected)
                f10Elements[idx].rebind { item.apply {
                    hRang = 0
                    proportion = 0
                    species = ""
                    age = 0
                    h = 0f
                    d = 0
                    classOfTrade = 0
                    weight = 0f
                    sumOfTimber = 0
                } }
                f10Elements[idx].commit()
                selected.second.isVisible = false
                selected.first.styleClass.remove(CLASS_SELECT_BTN_ACTIVE)
            }

            field31ViewModel.apply {
                f31_count byfloat countProperty
                f31_age byint ageProperty
                f31_h byfloat hProperty
                f31_proportion1 byint proportion1Property
                f31_element1 bystr element1Property
                f31_proportion2 byint proportion2Property
                f31_element2 bystr element2Property
                f31_proportion3 byint proportion3Property
                f31_element3 bystr element3Property
            }

            bindDop()
            stylize()
            tuneFields()
        }
    }

    private fun tuneFields(){
        fType.apply {
            bindAutoCompletion(GeneralTypes.typesOfForest.map { it.name })
        }
    }

    private fun ElementOfForestViewModel.bind10(
        hRang: TextFieldImpl,
        proportion: TextFieldImpl,
        species: TextFieldImpl,
        age: TextFieldImpl,
        h: TextFieldImpl,
        d: TextFieldImpl,
        tradeClass: TextFieldImpl,
        origin: TextFieldImpl,
        weight: TextFieldImpl,
        sumOfT: TextFieldImpl
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

        //todo replacing / deleting line features
    }

    private fun bindDopLine(
        fieldName: TextFieldImpl,
        col1: TextFieldImpl,
        col2: TextFieldImpl,
        col3: TextFieldImpl,
        col4: TextFieldImpl,
        col5: TextFieldImpl,
        col6: TextFieldImpl,
        col7: TextFieldImpl,
        col8: TextFieldImpl,
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

    private fun stylize(){
        fKvNumber.configure(enableFieldsTrigger, model.dirtyStateFor(AreaModel::kvProperty))
        with(model.field1Model){
            fAreaNumber.configure(enableFieldsTrigger, dirtyStateFor(Field1ViewModel::numberProperty))
            fArea.configure(enableFieldsTrigger, dirtyStateFor(Field1ViewModel::areaProperty))
            fCategoryArea.configure(enableFieldsTrigger, dirtyStateFor(Field1ViewModel::categoryProperty))
            fDP.configure(enableFieldsTrigger, dirtyStateFor(Field1ViewModel::dpProperty))
            fOzu.configure(enableFieldsTrigger, dirtyStateFor(Field1ViewModel::typeOfProtectionProperty))
        }
        with(model.field2ViewModel){
            fAction1.configure(enableFieldsTrigger, dirtyStateFor(Field2ViewModel::firstActionProperty))
            fAction2.configure(enableFieldsTrigger, dirtyStateFor(Field2ViewModel::secondActionProperty))
            fAction3.configure(enableFieldsTrigger, dirtyStateFor(Field2ViewModel::thirdActionProperty))
        }
        with(model.field3ViewModel){
            fSpecies.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::speciesProperty))
            fBon.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::bonProperty))
            fType.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::typeProperty))
            fSubType.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::subTypeProperty))
            fYearOfDeforest.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::yearOfDeforestationProperty))
            fCountOfStump.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::countOfStumpProperty))
            fCountOfPinusStump.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::countOfPinusStumpProperty))
            fStumpDiameter.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::stumpDiameterProperty))
            fTypeDeforest.configure(enableFieldsTrigger, dirtyStateFor(Field3ViewModel::typeOfDeforestationProperty))
        }
        with(model.field4ViewModel){
            fDisorder.configure(enableFieldsTrigger, dirtyStateFor(Field4ViewModel::disorderProperty))
            fValidDisorder.configure(enableFieldsTrigger, dirtyStateFor(Field4ViewModel::validDisorderProperty))
            fDryTimber.configure(enableFieldsTrigger, dirtyStateFor(Field4ViewModel::dryTimberProperty))
        }

        with(model.f10Elements){
            bindDirtyF10(get(0), fHrang1, fProportion1, fSpecies1, fAge1, fH1, fD1, fTradeClass1, fOrigin1, fWeight1, fSumOfTimber1)
            bindDirtyF10(get(1), fHrang2, fProportion2, fSpecies2, fAge2, fH2, fD2, fTradeClass2, fOrigin2, fWeight2, fSumOfTimber2)
            bindDirtyF10(get(2), fHrang3, fProportion3, fSpecies3, fAge3, fH3, fD3, fTradeClass3, fOrigin3, fWeight3, fSumOfTimber3)
            bindDirtyF10(get(3), fHrang4, fProportion4, fSpecies4, fAge4, fH4, fD4, fTradeClass4, fOrigin4, fWeight4, fSumOfTimber4)
            bindDirtyF10(get(4), fHrang5, fProportion5, fSpecies5, fAge5, fH5, fD5, fTradeClass5, fOrigin5, fWeight5, fSumOfTimber5)
            bindDirtyF10(get(5), fHrang6, fProportion6, fSpecies6, fAge6, fH6, fD6, fTradeClass6, fOrigin6, fWeight6, fSumOfTimber6)
            bindDirtyF10(get(6), fHrang7, fProportion7, fSpecies7, fAge7, fH7, fD7, fTradeClass7, fOrigin7, fWeight7, fSumOfTimber7)
            bindDirtyF10(get(7), fHrang8, fProportion8, fSpecies8, fAge8, fH8, fD8, fTradeClass8, fOrigin8, fWeight8, fSumOfTimber8)
            bindDirtyF10(get(8), fHrang9, fProportion9, fSpecies9, fAge9, fH9, fD9, fTradeClass9, fOrigin9, fWeight9, fSumOfTimber9)
            bindDirtyF10(get(9), fHrang10, fProportion10, fSpecies10, fAge10, fH10, fD10, fTradeClass10, fOrigin10, fWeight10, fSumOfTimber10)
        }

        with(model.field31ViewModel){
            f31_count.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::countProperty))
            f31_h.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::hProperty))
            f31_age.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::ageProperty))
            f31_proportion1.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::proportion1Property))
            f31_proportion2.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::proportion2Property))
            f31_proportion3.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::proportion3Property))
            f31_element1.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::element1Property))
            f31_element2.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::element2Property))
            f31_element3.configure(enableFieldsTrigger, dirtyStateFor(Field31ViewModel::element3Property))
        }
        val dops = model.dopViewModel.dopFieldViewModels
        bindDirtyDop(dops[0], fDop1_n, fDop1_1, fDop1_2, fDop1_3, fDop1_4, fDop1_5, fDop1_6, fDop1_7, fDop1_8)
        bindDirtyDop(dops[1], fDop2_n, fDop2_1, fDop2_2, fDop2_3, fDop2_4, fDop2_5, fDop2_6, fDop2_7, fDop2_8)
        bindDirtyDop(dops[2], fDop3_n, fDop3_1, fDop3_2, fDop3_3, fDop3_4, fDop3_5, fDop3_6, fDop3_7, fDop3_8)
        bindDirtyDop(dops[3], fDop4_n, fDop4_1, fDop4_2, fDop4_3, fDop4_4, fDop4_5, fDop4_6, fDop4_7, fDop4_8)
        bindDirtyDop(dops[4], fDop5_n, fDop5_1, fDop5_2, fDop5_3, fDop5_4, fDop5_5, fDop5_6, fDop5_7, fDop5_8)
        bindDirtyDop(dops[5], fDop6_n, fDop6_1, fDop6_2, fDop6_3, fDop6_4, fDop6_5, fDop6_6, fDop6_7, fDop6_8)
        fGir
    }

    private fun bindDirtyDop(
        model: DopViewModel.DopFieldViewModel,
        fn: TextFieldImpl,
        f1: TextFieldImpl,
        f2: TextFieldImpl,
        f3: TextFieldImpl,
        f4: TextFieldImpl,
        f5: TextFieldImpl,
        f6: TextFieldImpl,
        f7: TextFieldImpl,
        f8: TextFieldImpl){
        with(model){
            fn.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::numberProperty))
            f1.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col1Property))
            f2.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col2Property))
            f3.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col3Property))
            f4.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col4Property))
            f5.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col5Property))
            f6.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col6Property))
            f7.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col7Property))
            f8.configure(enableFieldsTrigger, dirtyStateFor(DopViewModel.DopFieldViewModel::col8Property))
        }
    }

    private fun bindDirtyF10(
        model: ElementOfForestViewModel,
        fHrang: TextFieldImpl,
        fProp: TextFieldImpl,
        fSpec: TextFieldImpl,
        fAge: TextFieldImpl,
        fH: TextFieldImpl,
        fD: TextFieldImpl,
        fTrade: TextFieldImpl,
        fOrig: TextFieldImpl,
        fWeight: TextFieldImpl,
        fSumOfT: TextFieldImpl
        ){
        with(model){
            fHrang.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::hRangProperty))
            fProp.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::proportionProperty))
            fSpec.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::speciesProperty))
            fAge.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::ageProperty))
            fH.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::hProperty))
            fD.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::dProperty))
            fTrade.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::tradeClassProperty))
            fOrig.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::generationProperty))
            fWeight.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::weightProperty))
            fSumOfT.configure(enableFieldsTrigger, dirtyStateFor(ElementOfForestViewModel::sumOfTimberProperty))
        }
    }



    private infix fun TextFieldImpl.bystr(other: Property<String>) = this.bind(property = other)
    private infix fun TextFieldImpl.byint(other: Property<Int>) = this.bind(property = other, readonly = false, converter = FieldIntConverter())
    private infix fun TextFieldImpl.byfloat(other: Property<Float>) = this.bind(property = other, converter = FieldFloatConverter())

    fun openStrictAreaView() = find<StrictAreaView>().openWindow(owner = this.currentWindow)

    fun flog(message: String) {
        fLog.text = "> $message"
    }

    fun error(message: String){
        error("Ошибка", message)
    }

}