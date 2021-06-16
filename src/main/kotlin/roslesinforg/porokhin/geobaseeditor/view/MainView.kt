package roslesinforg.porokhin.geobaseeditor.view

import CLASS_SELECT_BTN_ACTIVE
import javafx.application.Platform
import javafx.beans.property.*
import javafx.scene.control.*
import javafx.scene.input.*
import javafx.scene.layout.*
import roslesinforg.porokhin.areatypes.Area
import tornadofx.*
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.model.*
import roslesinforg.porokhin.geobaseeditor.model.validation.FilteringHelper
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidationHelper
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidatorFactory
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.*
import roslesinforg.porokhin.areatypes.GeneralTypes
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.geobaseeditor.service.UpdateManager
import roslesinforg.porokhin.geobaseeditor.view.constructors.*
import tornadofx.controlsfx.bindAutoCompletion
import java.io.File
import java.nio.file.Paths
import kotlin.reflect.KProperty0


fun main(args: Array<String>) {
    launch<GeoBaseEditorApp>(args)
}

class GeoBaseEditorApp: App(MainView::class)

class MainView : GeoBaseEditorView("Редактор базы") {
    internal val logger = logger()
    override val root: TabPane by fxml("/gui/MainView.fxml")
    internal val borderPane: BorderPane by fxid()
    internal val cardLayout: AnchorPane by fxid()
    internal val fGir: TextFieldImpl by fxid()
    internal val fKvNumber: TextFieldImpl by fxid()
    internal val fAreaNumber: TextFieldImpl by fxid()
    internal val fArea: TextFieldImpl by fxid()
    internal val fCategoryArea: TextFieldImpl by fxid()
    internal val fOzu: TextFieldImpl by fxid()
    internal val fDP: TextFieldImpl by fxid()
    internal val fSpecies: TextFieldImpl by fxid()
    internal val fBon: TextFieldImpl by fxid()
    internal val fType: TextFieldImpl by fxid()
    internal val fSubType: TextFieldImpl by fxid()
    internal val fYearOfDeforest: TextFieldImpl by fxid()
    internal val fCountOfStump: TextFieldImpl by fxid()
    internal val fCountOfPinusStump: TextFieldImpl by fxid()
    internal val fStumpDiameter: TextFieldImpl by fxid()
    internal val fTypeDeforest: TextFieldImpl by fxid()
    internal val fDisorder: TextFieldImpl by fxid()
    internal val fValidDisorder: TextFieldImpl by fxid()
    internal val fDryTimber: TextFieldImpl by fxid()
    internal val fAction1: TextFieldImpl by fxid()
    internal val fAction2: TextFieldImpl by fxid()
    internal val fAction3: TextFieldImpl by fxid()
    internal val container23: FlowPane by fxid()
    internal val container23_2: FlowPane by fxid()
    internal val container_10: FlowPane by fxid()
    internal val fHrang1: TextFieldImpl by fxid()
    internal val fHrang2: TextFieldImpl by fxid()
    internal val fHrang3: TextFieldImpl by fxid()
    internal val fHrang4: TextFieldImpl by fxid()
    internal val fHrang5: TextFieldImpl by fxid()
    internal val fHrang6: TextFieldImpl by fxid()
    internal val fHrang7: TextFieldImpl by fxid()
    internal val fHrang8: TextFieldImpl by fxid()
    internal val fHrang9: TextFieldImpl by fxid()
    internal val fHrang10: TextFieldImpl by fxid()
    internal val fProportion1: TextFieldImpl by fxid()
    internal val fProportion2: TextFieldImpl by fxid()
    internal val fProportion3: TextFieldImpl by fxid()
    internal val fProportion4: TextFieldImpl by fxid()
    internal val fProportion5: TextFieldImpl by fxid()
    internal val fProportion6: TextFieldImpl by fxid()
    internal val fProportion7: TextFieldImpl by fxid()
    internal val fProportion8: TextFieldImpl by fxid()
    internal val fProportion9: TextFieldImpl by fxid()
    internal val fProportion10: TextFieldImpl by fxid()
    internal val fSpecies1: TextFieldImpl by fxid()
    internal val fSpecies2: TextFieldImpl by fxid()
    internal val fSpecies3: TextFieldImpl by fxid()
    internal val fSpecies4: TextFieldImpl by fxid()
    internal val fSpecies5: TextFieldImpl by fxid()
    internal val fSpecies6: TextFieldImpl by fxid()
    internal val fSpecies7: TextFieldImpl by fxid()
    internal val fSpecies8: TextFieldImpl by fxid()
    internal val fSpecies9: TextFieldImpl by fxid()
    internal val fSpecies10: TextFieldImpl by fxid()
    internal val fAge1: TextFieldImpl by fxid()
    internal val fAge2: TextFieldImpl by fxid()
    internal val fAge3: TextFieldImpl by fxid()
    internal val fAge4: TextFieldImpl by fxid()
    internal val fAge5: TextFieldImpl by fxid()
    internal val fAge6: TextFieldImpl by fxid()
    internal val fAge7: TextFieldImpl by fxid()
    internal val fAge8: TextFieldImpl by fxid()
    internal val fAge9: TextFieldImpl by fxid()
    internal val fAge10: TextFieldImpl by fxid()
    internal val fH1: TextFieldImpl by fxid()
    internal val fH2: TextFieldImpl by fxid()
    internal val fH3: TextFieldImpl by fxid()
    internal val fH4: TextFieldImpl by fxid()
    internal val fH5: TextFieldImpl by fxid()
    internal val fH6: TextFieldImpl by fxid()
    internal val fH7: TextFieldImpl by fxid()
    internal val fH8: TextFieldImpl by fxid()
    internal val fH9: TextFieldImpl by fxid()
    internal val fH10: TextFieldImpl by fxid()
    internal val fD1: TextFieldImpl by fxid()
    internal val fD2: TextFieldImpl by fxid()
    internal val fD3: TextFieldImpl by fxid()
    internal val fD4: TextFieldImpl by fxid()
    internal val fD5: TextFieldImpl by fxid()
    internal val fD6: TextFieldImpl by fxid()
    internal val fD7: TextFieldImpl by fxid()
    internal val fD8: TextFieldImpl by fxid()
    internal val fD9: TextFieldImpl by fxid()
    internal val fD10: TextFieldImpl by fxid()
    internal val fTradeClass1: TextFieldImpl by fxid()
    internal val fTradeClass2: TextFieldImpl by fxid()
    internal val fTradeClass3: TextFieldImpl by fxid()
    internal val fTradeClass4: TextFieldImpl by fxid()
    internal val fTradeClass5: TextFieldImpl by fxid()
    internal val fTradeClass6: TextFieldImpl by fxid()
    internal val fTradeClass7: TextFieldImpl by fxid()
    internal val fTradeClass8: TextFieldImpl by fxid()
    internal val fTradeClass9: TextFieldImpl by fxid()
    internal val fTradeClass10: TextFieldImpl by fxid()
    internal val fOrigin1: TextFieldImpl by fxid()
    internal val fOrigin2: TextFieldImpl by fxid()
    internal val fOrigin3: TextFieldImpl by fxid()
    internal val fOrigin4: TextFieldImpl by fxid()
    internal val fOrigin5: TextFieldImpl by fxid()
    internal val fOrigin6: TextFieldImpl by fxid()
    internal val fOrigin7: TextFieldImpl by fxid()
    internal val fOrigin8: TextFieldImpl by fxid()
    internal val fOrigin9: TextFieldImpl by fxid()
    internal val fOrigin10: TextFieldImpl by fxid()
    internal val fWeight1: TextFieldImpl by fxid()
    internal val fWeight2: TextFieldImpl by fxid()
    internal val fWeight3: TextFieldImpl by fxid()
    internal val fWeight4: TextFieldImpl by fxid()
    internal val fWeight5: TextFieldImpl by fxid()
    internal val fWeight6: TextFieldImpl by fxid()
    internal val fWeight7: TextFieldImpl by fxid()
    internal val fWeight8: TextFieldImpl by fxid()
    internal val fWeight9: TextFieldImpl by fxid()
    internal val fWeight10: TextFieldImpl by fxid()
    internal val fSumOfTimber1: TextFieldImpl by fxid()
    internal val fSumOfTimber2: TextFieldImpl by fxid()
    internal val fSumOfTimber3: TextFieldImpl by fxid()
    internal val fSumOfTimber4: TextFieldImpl by fxid()
    internal val fSumOfTimber5: TextFieldImpl by fxid()
    internal val fSumOfTimber6: TextFieldImpl by fxid()
    internal val fSumOfTimber7: TextFieldImpl by fxid()
    internal val fSumOfTimber8: TextFieldImpl by fxid()
    internal val fSumOfTimber9: TextFieldImpl by fxid()
    internal val fSumOfTimber10: TextFieldImpl by fxid()
    internal val f31_count: TextFieldImpl by fxid()
    internal val f31_h: TextFieldImpl by fxid()
    internal val f31_age: TextFieldImpl by fxid()
    internal val f31_proportion1: TextFieldImpl by fxid()
    internal val f31_element1: TextFieldImpl by fxid()
    internal val f31_proportion2: TextFieldImpl by fxid()
    internal val f31_element2: TextFieldImpl by fxid()
    internal val f31_proportion3: TextFieldImpl by fxid()
    internal val f31_element3: TextFieldImpl by fxid()
    internal val fDop1_n: TextFieldImpl by fxid()
    internal val fDop2_n: TextFieldImpl by fxid()
    internal val fDop3_n: TextFieldImpl by fxid()
    internal val fDop4_n: TextFieldImpl by fxid()
    internal val fDop5_n: TextFieldImpl by fxid()
    internal val fDop6_n: TextFieldImpl by fxid()
    internal val fDop1_1: TextFieldImpl by fxid()
    internal val fDop2_1: TextFieldImpl by fxid()
    internal val fDop3_1: TextFieldImpl by fxid()
    internal val fDop4_1: TextFieldImpl by fxid()
    internal val fDop5_1: TextFieldImpl by fxid()
    internal val fDop6_1: TextFieldImpl by fxid()
    internal val fDop1_2: TextFieldImpl by fxid()
    internal val fDop2_2: TextFieldImpl by fxid()
    internal val fDop3_2: TextFieldImpl by fxid()
    internal val fDop4_2: TextFieldImpl by fxid()
    internal val fDop5_2: TextFieldImpl by fxid()
    internal val fDop6_2: TextFieldImpl by fxid()
    internal val fDop1_3: TextFieldImpl by fxid()
    internal val fDop2_3: TextFieldImpl by fxid()
    internal val fDop3_3: TextFieldImpl by fxid()
    internal val fDop4_3: TextFieldImpl by fxid()
    internal val fDop5_3: TextFieldImpl by fxid()
    internal val fDop6_3: TextFieldImpl by fxid()
    internal val fDop1_4: TextFieldImpl by fxid()
    internal val fDop2_4: TextFieldImpl by fxid()
    internal val fDop3_4: TextFieldImpl by fxid()
    internal val fDop4_4: TextFieldImpl by fxid()
    internal val fDop5_4: TextFieldImpl by fxid()
    internal val fDop6_4: TextFieldImpl by fxid()
    internal val fDop1_5: TextFieldImpl by fxid()
    internal val fDop2_5: TextFieldImpl by fxid()
    internal val fDop3_5: TextFieldImpl by fxid()
    internal val fDop4_5: TextFieldImpl by fxid()
    internal val fDop5_5: TextFieldImpl by fxid()
    internal val fDop6_5: TextFieldImpl by fxid()
    internal val fDop1_6: TextFieldImpl by fxid()
    internal val fDop2_6: TextFieldImpl by fxid()
    internal val fDop3_6: TextFieldImpl by fxid()
    internal val fDop4_6: TextFieldImpl by fxid()
    internal val fDop5_6: TextFieldImpl by fxid()
    internal val fDop6_6: TextFieldImpl by fxid()
    internal val fDop1_7: TextFieldImpl by fxid()
    internal val fDop2_7: TextFieldImpl by fxid()
    internal val fDop3_7: TextFieldImpl by fxid()
    internal val fDop4_7: TextFieldImpl by fxid()
    internal val fDop5_7: TextFieldImpl by fxid()
    internal val fDop6_7: TextFieldImpl by fxid()
    internal val fDop1_8: TextFieldImpl by fxid()
    internal val fDop2_8: TextFieldImpl by fxid()
    internal val fDop3_8: TextFieldImpl by fxid()
    internal val fDop4_8: TextFieldImpl by fxid()
    internal val fDop5_8: TextFieldImpl by fxid()
    internal val fDop6_8: TextFieldImpl by fxid()
    internal val selection1: Pane by fxid()
    internal val selection2: Pane by fxid()
    internal val selection3: Pane by fxid()
    internal val selection4: Pane by fxid()
    internal val selection5: Pane by fxid()
    internal val selection6: Pane by fxid()
    internal val selection7: Pane by fxid()
    internal val selection8: Pane by fxid()
    internal val selection9: Pane by fxid()
    internal val selection10: Pane by fxid()
    internal val selectionsF10 = mutableListOf<Pair<ToggleButton, Pane>>()
    internal val selectBtn1: ToggleButton by fxid()
    internal val selectBtn2: ToggleButton by fxid()
    internal val selectBtn3: ToggleButton by fxid()
    internal val selectBtn4: ToggleButton by fxid()
    internal val selectBtn5: ToggleButton by fxid()
    internal val selectBtn6: ToggleButton by fxid()
    internal val selectBtn7: ToggleButton by fxid()
    internal val selectBtn8: ToggleButton by fxid()
    internal val selectBtn9: ToggleButton by fxid()
    internal val selectBtn10: ToggleButton by fxid()
    private val fLog: Label by fxid()
    private lateinit var btnOpen: Button
    private lateinit var btnSave: Button
    private lateinit var screen: Button
    private lateinit var btnChanges: Button
    internal val buttonBar: ButtonBar by fxid()
    internal val topPane: HBox by fxid()
    internal val fProgress: ProgressBar by fxid()


    private val filteringHelper = FilteringHelper()
    internal val enableFieldsTrigger = SimpleBooleanProperty()
    lateinit var kv_list: TableView<Area>
    object AppScope: Scope()
    val controller : GeoBaseEditorController by inject(AppScope)
    var model: AreaModel = controller.areaModel
    val updateManager =  UpdateManager(Paths.get("\\\\POROHIN\\share\\update\\"))

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


        construct(BindingsConstructor())
        construct(DirtyStateConstructor())
        construct(TuningConstructor())
        construct(KvListConstructor())
        construct(TopPaneConstructor())
        construct(SelectionsF10Constructor())
        construct(ValidationConstructor())



        fKvNumber.apply {
            isEditable = false
            style{
                backgroundColor += c(0, 0, 0, 0.0)
            }
        }
        if (GeoBaseEditorPreferences.filtering.value) applyFilters()

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

        runAsync {
           updateManager.checkVersion()
        } ui{
            if (it.api != 0) warning("Доступна новая версия ${it.version}", "Что нового: \n${it.history.entries.first().value}", ButtonType.NO, ButtonType.APPLY){ bt ->
                if (bt.buttonData.isCancelButton) return@warning
                logger.trace("updating")
                updateManager.update()
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
                f.controlNewText.let { it.isEmpty() || it.isInt() && it.toInt() in 1..900 }
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
            filter(fType){ input ->  GeneralTypes.typesOfForest.any{it.name.startsWith(input.controlNewText.toUpperCase())} }
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
    @Suppress("unchecked_cast")
    fun <V: GeoBaseEditorView>construct(constructor: ViewConstructor<V>){
        constructor.construct(this as V)
    }


    fun MutableList<Pair<ToggleButton, Pane>>.indexOf(btn: ToggleButton) = this.indexOf(find { it.first == btn })

    fun openStrictAreaView() = find<StrictAreaView>().openWindow(owner = this.currentWindow)

    fun flog(message: String) {
        fLog.text = "> $message"
    }

    fun error(message: String){
        error("Ошибка", message)
    }

}