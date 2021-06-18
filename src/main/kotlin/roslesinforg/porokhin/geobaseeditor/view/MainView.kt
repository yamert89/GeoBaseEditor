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
    internal val selectionPane: AnchorPane by fxid()
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
    private val validationContext = ValidationContext()
    internal val validatorFactory = ValidatorFactory(validationContext)
    internal val validationHelper = ValidationHelper(validationContext, validatorFactory)
    internal val enableFieldsTrigger = SimpleBooleanProperty()
    lateinit var kv_list: TableView<Area>
    lateinit var selectionTable: TableView<Area>
    object AppScope: Scope()
    val controller : GeoBaseEditorController by inject(AppScope)
    var model: AreaModel = controller.areaModel
    val updateManager =  UpdateManager(Paths.get("\\\\POROHIN\\share\\update\\"))

    init {
        construct(SelectionsF10Constructor())
        construct(BindingsConstructor())
        construct(DirtyStateConstructor())
        construct(TuningConstructor())
        construct(KvListConstructor())
        construct(TopPaneConstructor())
        construct(ValidationConstructor())
        construct(SelectionPaneConstructor())

        if (GeoBaseEditorPreferences.filtering.value) applyFilters()

        with(primaryStage){
            setOnCloseRequest {
                GeoBaseEditorPreferences.savePrefs()
            }
            isResizable = false
            icons.add(resources.image("/gui/Desktop.png"))
        }

        controller.setMainView(this)

        if ( app.parameters.raw.isNotEmpty() && app.parameters.raw[0] == "-d"){
            construct(DebugModeConstructor())
            logger.debug("Debug Mode activated")
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
        construct(FilteringConstructor())
    }

    fun clearFilters(){
        deconstruct(FilteringConstructor())
    }

    fun selectItem(idx: Int){
        kv_list.selectionModel.select(idx)
        Platform.runLater { kv_list.scrollTo(idx - 10) }
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