package roslesinforg.porokhin.geobaseeditor.view

import com.sun.glass.events.MouseEvent
import com.sun.imageio.plugins.common.ImageUtil
import com.sun.imageio.plugins.jpeg.JPEG
import javafx.beans.property.*
import javafx.embed.swing.SwingFXUtils
import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventType
import javafx.scene.control.*
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import tornadofx.*
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.model.*
import roslesinforg.porokhin.geobaseeditor.model.validation.FilteringHelper
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidationHelper
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidatorFactory
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidatorFactory.*
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.*
import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.rawxlsconverter.RootView
import java.awt.image.BufferedImage
import java.awt.image.ColorModel
import java.awt.image.RenderedImage
import java.io.File
import java.lang.reflect.Modifier
import javax.imageio.ImageIO
import javax.swing.text.html.ImageView


fun main() {
    launch<GeoBaseEditorApp>()
}

class GeoBaseEditorApp: App(MainView::class)

class MainView : View("My View") {
    override val root: AnchorPane by fxml("/gui/MainView.fxml")
    val cardLayout: AnchorPane by fxid()
    val fGir: TextFieldImpl by fxid()
    val fKvNumber: TextFieldImpl by fxid()
    val fAreaNumber: TextFieldImpl by fxid()
    val fArea: TextFieldImpl by fxid()
    val fCategoryArea: TextFieldImpl by fxid()
    val fOzu: TextFieldImpl by fxid()
    val fDP: TextFieldImpl by fxid()
    val fSpecies: TextFieldImpl by fxid()
    val fBon: TextFieldImpl by fxid()
    val fType: TextFieldImpl by fxid()
    val fSubType: TextFieldImpl by fxid()
    val fYearOfDeforest: TextFieldImpl by fxid()
    val fCountOfStump: TextFieldImpl by fxid()
    val fCountOfPinusStump: TextFieldImpl by fxid()
    val fStumpDiameter: TextFieldImpl by fxid()
    val fTypeDeforest: TextFieldImpl by fxid()
    val fDisorder: TextFieldImpl by fxid()
    val fValidDisorder: TextFieldImpl by fxid()
    val fDryTimber: TextFieldImpl by fxid()
    val fAction1: TextFieldImpl by fxid()
    val fAction2: TextFieldImpl by fxid()
    val fAction3: TextFieldImpl by fxid()
    val container23: FlowPane by fxid()
    val container23_2: FlowPane by fxid()
    val container_10: FlowPane by fxid()
    val fHrang1: TextFieldImpl by fxid()
    val fHrang2: TextFieldImpl by fxid()
    val fHrang3: TextFieldImpl by fxid()
    val fHrang4: TextFieldImpl by fxid()
    val fHrang5: TextFieldImpl by fxid()
    val fHrang6: TextFieldImpl by fxid()
    val fHrang7: TextFieldImpl by fxid()
    val fHrang8: TextFieldImpl by fxid()
    val fHrang9: TextFieldImpl by fxid()
    val fHrang10: TextFieldImpl by fxid()
    val fProportion1: TextFieldImpl by fxid()
    val fProportion2: TextFieldImpl by fxid()
    val fProportion3: TextFieldImpl by fxid()
    val fProportion4: TextFieldImpl by fxid()
    val fProportion5: TextFieldImpl by fxid()
    val fProportion6: TextFieldImpl by fxid()
    val fProportion7: TextFieldImpl by fxid()
    val fProportion8: TextFieldImpl by fxid()
    val fProportion9: TextFieldImpl by fxid()
    val fProportion10: TextFieldImpl by fxid()
    val fSpecies1: TextFieldImpl by fxid()
    val fSpecies2: TextFieldImpl by fxid()
    val fSpecies3: TextFieldImpl by fxid()
    val fSpecies4: TextFieldImpl by fxid()
    val fSpecies5: TextFieldImpl by fxid()
    val fSpecies6: TextFieldImpl by fxid()
    val fSpecies7: TextFieldImpl by fxid()
    val fSpecies8: TextFieldImpl by fxid()
    val fSpecies9: TextFieldImpl by fxid()
    val fSpecies10: TextFieldImpl by fxid()
    val fAge1: TextFieldImpl by fxid()
    val fAge2: TextFieldImpl by fxid()
    val fAge3: TextFieldImpl by fxid()
    val fAge4: TextFieldImpl by fxid()
    val fAge5: TextFieldImpl by fxid()
    val fAge6: TextFieldImpl by fxid()
    val fAge7: TextFieldImpl by fxid()
    val fAge8: TextFieldImpl by fxid()
    val fAge9: TextFieldImpl by fxid()
    val fAge10: TextFieldImpl by fxid()
    val fH1: TextFieldImpl by fxid()
    val fH2: TextFieldImpl by fxid()
    val fH3: TextFieldImpl by fxid()
    val fH4: TextFieldImpl by fxid()
    val fH5: TextFieldImpl by fxid()
    val fH6: TextFieldImpl by fxid()
    val fH7: TextFieldImpl by fxid()
    val fH8: TextFieldImpl by fxid()
    val fH9: TextFieldImpl by fxid()
    val fH10: TextFieldImpl by fxid()
    val fD1: TextFieldImpl by fxid()
    val fD2: TextFieldImpl by fxid()
    val fD3: TextFieldImpl by fxid()
    val fD4: TextFieldImpl by fxid()
    val fD5: TextFieldImpl by fxid()
    val fD6: TextFieldImpl by fxid()
    val fD7: TextFieldImpl by fxid()
    val fD8: TextFieldImpl by fxid()
    val fD9: TextFieldImpl by fxid()
    val fD10: TextFieldImpl by fxid()
    val fTradeClass1: TextFieldImpl by fxid()
    val fTradeClass2: TextFieldImpl by fxid()
    val fTradeClass3: TextFieldImpl by fxid()
    val fTradeClass4: TextFieldImpl by fxid()
    val fTradeClass5: TextFieldImpl by fxid()
    val fTradeClass6: TextFieldImpl by fxid()
    val fTradeClass7: TextFieldImpl by fxid()
    val fTradeClass8: TextFieldImpl by fxid()
    val fTradeClass9: TextFieldImpl by fxid()
    val fTradeClass10: TextFieldImpl by fxid()
    val fOrigin1: TextFieldImpl by fxid()
    val fOrigin2: TextFieldImpl by fxid()
    val fOrigin3: TextFieldImpl by fxid()
    val fOrigin4: TextFieldImpl by fxid()
    val fOrigin5: TextFieldImpl by fxid()
    val fOrigin6: TextFieldImpl by fxid()
    val fOrigin7: TextFieldImpl by fxid()
    val fOrigin8: TextFieldImpl by fxid()
    val fOrigin9: TextFieldImpl by fxid()
    val fOrigin10: TextFieldImpl by fxid()
    val fWeight1: TextFieldImpl by fxid()
    val fWeight2: TextFieldImpl by fxid()
    val fWeight3: TextFieldImpl by fxid()
    val fWeight4: TextFieldImpl by fxid()
    val fWeight5: TextFieldImpl by fxid()
    val fWeight6: TextFieldImpl by fxid()
    val fWeight7: TextFieldImpl by fxid()
    val fWeight8: TextFieldImpl by fxid()
    val fWeight9: TextFieldImpl by fxid()
    val fWeight10: TextFieldImpl by fxid()
    val fSumOfTimber1: TextFieldImpl by fxid()
    val fSumOfTimber2: TextFieldImpl by fxid()
    val fSumOfTimber3: TextFieldImpl by fxid()
    val fSumOfTimber4: TextFieldImpl by fxid()
    val fSumOfTimber5: TextFieldImpl by fxid()
    val fSumOfTimber6: TextFieldImpl by fxid()
    val fSumOfTimber7: TextFieldImpl by fxid()
    val fSumOfTimber8: TextFieldImpl by fxid()
    val fSumOfTimber9: TextFieldImpl by fxid()
    val fSumOfTimber10: TextFieldImpl by fxid()
    val f31_count: TextFieldImpl by fxid()
    val f31_h: TextFieldImpl by fxid()
    val f31_age: TextFieldImpl by fxid()
    val f31_proportion1: TextFieldImpl by fxid()
    val f31_element1: TextFieldImpl by fxid()
    val f31_proportion2: TextFieldImpl by fxid()
    val f31_element2: TextFieldImpl by fxid()
    val f31_proportion3: TextFieldImpl by fxid()
    val f31_element3: TextFieldImpl by fxid()
    val fDop1_n: TextFieldImpl by fxid()
    val fDop2_n: TextFieldImpl by fxid()
    val fDop3_n: TextFieldImpl by fxid()
    val fDop4_n: TextFieldImpl by fxid()
    val fDop5_n: TextFieldImpl by fxid()
    val fDop6_n: TextFieldImpl by fxid()
    val fDop1_1: TextFieldImpl by fxid()
    val fDop2_1: TextFieldImpl by fxid()
    val fDop3_1: TextFieldImpl by fxid()
    val fDop4_1: TextFieldImpl by fxid()
    val fDop5_1: TextFieldImpl by fxid()
    val fDop6_1: TextFieldImpl by fxid()
    val fDop1_2: TextFieldImpl by fxid()
    val fDop2_2: TextFieldImpl by fxid()
    val fDop3_2: TextFieldImpl by fxid()
    val fDop4_2: TextFieldImpl by fxid()
    val fDop5_2: TextFieldImpl by fxid()
    val fDop6_2: TextFieldImpl by fxid()
    val fDop1_3: TextFieldImpl by fxid()
    val fDop2_3: TextFieldImpl by fxid()
    val fDop3_3: TextFieldImpl by fxid()
    val fDop4_3: TextFieldImpl by fxid()
    val fDop5_3: TextFieldImpl by fxid()
    val fDop6_3: TextFieldImpl by fxid()
    val fDop1_4: TextFieldImpl by fxid()
    val fDop2_4: TextFieldImpl by fxid()
    val fDop3_4: TextFieldImpl by fxid()
    val fDop4_4: TextFieldImpl by fxid()
    val fDop5_4: TextFieldImpl by fxid()
    val fDop6_4: TextFieldImpl by fxid()
    val fDop1_5: TextFieldImpl by fxid()
    val fDop2_5: TextFieldImpl by fxid()
    val fDop3_5: TextFieldImpl by fxid()
    val fDop4_5: TextFieldImpl by fxid()
    val fDop5_5: TextFieldImpl by fxid()
    val fDop6_5: TextFieldImpl by fxid()
    val fDop1_6: TextFieldImpl by fxid()
    val fDop2_6: TextFieldImpl by fxid()
    val fDop3_6: TextFieldImpl by fxid()
    val fDop4_6: TextFieldImpl by fxid()
    val fDop5_6: TextFieldImpl by fxid()
    val fDop6_6: TextFieldImpl by fxid()
    val fDop1_7: TextFieldImpl by fxid()
    val fDop2_7: TextFieldImpl by fxid()
    val fDop3_7: TextFieldImpl by fxid()
    val fDop4_7: TextFieldImpl by fxid()
    val fDop5_7: TextFieldImpl by fxid()
    val fDop6_7: TextFieldImpl by fxid()
    val fDop1_8: TextFieldImpl by fxid()
    val fDop2_8: TextFieldImpl by fxid()
    val fDop3_8: TextFieldImpl by fxid()
    val fDop4_8: TextFieldImpl by fxid()
    val fDop5_8: TextFieldImpl by fxid()
    val fDop6_8: TextFieldImpl by fxid()
    lateinit var kv_list: TableView<Area>
   /* val btnOpen: Button by fxid()*/
    lateinit var btnOpen: Button
    lateinit var btnSave: Button
    lateinit var screen: Button
    lateinit var btnChanges: Button
    val buttonBar: ButtonBar by fxid()

    var path: Path
    var input: Path //todo for test

    val validationContext = ValidationContext()
    val factory = ValidatorFactory(validationContext)
    val validationHelper = ValidationHelper(validationContext, factory)
    val filteringHelper = FilteringHelper()

    
    var model: AreaModel
    object AppScope: Scope()
    val controller : roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController by inject(AppScope)

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
        if (Preferences.filtering.value) applyFilters()



        //todo add smart validators

        /*
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
        fSumOfTimber9, fSumOfTimber10, f31_count, f31_h, f31_age, f31_proportion1, f31_proportion2)*/

        primaryStage.setOnCloseRequest {
            model.commit()
            val out = Json.encodeToString(model.area)
            println(out)
            Files.write(path, out.toByteArray(UTF_8))
            Preferences.savePrefs()
        }
        primaryStage.isResizable = false

    }

    fun applyFilters(){
        with(filteringHelper){
            filter(fSpecies, fSpecies1, fSpecies2, fSpecies3, fSpecies4, fSpecies5, fSpecies6, fSpecies7, fSpecies8, fSpecies9,
                f31_element1, f31_element2, f31_element3){
                it.controlNewText.isEmpty() || it.controlNewText.matches("[ЕБСЛПОА\\s]{1,4}".toRegex())
            }
            filter(fHrang1, fHrang2, fHrang3, fHrang4,fHrang5, fHrang6, fHrang7, fHrang8, fHrang9, fHrang10){ formatter ->
                formatter.controlNewText.let { it.isEmpty() || it.isInt() && it.length == 1 }
            }
            filter(fProportion1, fProportion2, fProportion3, fProportion4, fProportion5, fProportion6, fProportion7,
                fProportion8, fProportion9, fProportion10, f31_proportion1, f31_proportion2, f31_proportion3){ formatter ->
                formatter.controlNewText.let { it.isInt() && it.toInt() < 11 }
            }
            filter(fAge1, fAge2, fAge3, fAge4, fAge5, fAge6, fAge7, fAge8, fAge9, fAge10, f31_age){ f ->
                f.controlNewText.let { it.isInt() && it.toInt() in 1..400 }
            }
            filter(fTradeClass1, fTradeClass2, fTradeClass3, fTradeClass4, fTradeClass5, fTradeClass6, fTradeClass7, fTradeClass8,
                fTradeClass9, fTradeClass10){ f ->
                f.controlNewText.let { it.isInt() && it.toInt() in 1..4 } //todo origin
            }
            filter(fWeight1, fWeight2, fWeight3, fWeight4, fWeight5, fWeight6, fWeight7, fWeight8, fWeight9, fWeight10){ f ->
                f.controlNewText.let { it.isInt() && it.toInt() in 0..150 }
            }
            filter(fSumOfTimber1, fSumOfTimber2, fSumOfTimber3, fSumOfTimber4, fSumOfTimber5, fSumOfTimber6, fSumOfTimber7, fSumOfTimber8,
                fSumOfTimber9, fSumOfTimber10){ f ->
                f.controlNewText.let { it.isInt() && it.toInt() in 5..900 }
            }
            filter(fAreaNumber, fCategoryArea, fDP, fOzu, fAction1, fAction2, fAction3, fDop1_n, fDop2_n, fDop3_n, fDop4_n,
                fDop5_n, fDop6_n){ f ->
                f.controlNewText.isInt()
            }
            filter(fH1, fH2, fH3, fH4, fH5, fH6, fH7, fH8, fH9, fH10, f31_count, f31_h){ f ->
                f.controlNewText.let { str ->
                    val fl = str.replace(",", ".") + "f"
                    fl.isFloat() && fl.toFloat().let { it in 0f..50f }
                }
            }
            filter(fD1, fD2, fD3, fD4, fD5, fD6, fD7, fD8, fD9, fD10){ f ->
                f.controlNewText.let { d ->
                    d.isInt() && d.toInt().let { it in 1..80 && it % 2 == 0 } // fixme wrong for 30, 50
                }
            }
            fBon.filterInput { it.controlNewText.matches("[1-5АБ]{1,2}".toRegex()) }
            fType.filterInput { it.controlNewText.matches("[А-Яа-я\\s]{1,5}".toRegex()) }
            fSubType.filterInput { it.controlNewText.matches("[А-Я]{2}".toRegex()) }
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

    private fun bindModel(){
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
            prefWidth = 130.0

            readonlyColumn("Кв", Area::kv){
                style{
                    textAlignment = TextAlignment.CENTER
                }
            }
            column<Area, Int>("Выд"){
                SimpleIntegerProperty(it.value.field1.number) as Property<Int>
            }.makeEditable()
            column("ЦНЛ", Area::categoryProtection){
                style{ textAlignment = TextAlignment.CENTER}
                setOnEditCommit {
                    if (GeneralTypes.categoryProtection(it.newValue) == it.newValue.toString()) {
                        error(header = "Ошибка", content = "Некорректное значение")
                        editModel.rollbackSelected()
                    }
                }
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
        }

    }

    private fun applyButtons(){
        buttonBar.apply {
            addNewButton("Run.png", "Настройки"){
                openInternalWindow(PreferenceView::class)
            }
            addNewButton("Export to Document.png", "Сохранить в MS Excel"){
                openInternalWindow(RootView::class)
            }
            addNewButton("Export To Picture Document.png", "Сохранить в GIF"){
                val image = cardLayout.snapshot(null, null)
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "GIF", path.resolve(Paths.get("/out.gif")).toFile())
            }
            addNewButton("Coherence.png", "Изменения"){
                openInternalWindow(ChangesView::class, Scope())
            }
            addNewButton("CD.png", "Сохранить"){
                val dir = chooseDirectory(
                    "Сохранить",
                    owner = primaryStage
                ) ?: return@addNewButton
                controller.writeToRawFile(dir)
            }
            addNewButton("New Document.png", "Открыть"){
                val files = chooseFile(
                    "Выберите файл",
                    owner = primaryStage,
                    mode = FileChooserMode.Single,
                    filters = arrayOf()
                )
                /*if (files.isEmpty()) {
                    controller.read() //todo for test
                    println(controller.areas.size)
                    return@addNewButton
                }*/
                controller.read(files[0])
            }
            onHover {
                print(height)
            }
        }
    }

    private fun ButtonBar.addNewButton(picture: String, tooltip: String, action: () -> Unit){
        ButtonBar.setButtonUniformSize(this, false)
        button{
            action {
                action()
            }
            style{
                background = null
                maxWidth = Dimension(26.0, Dimension.LinearUnits.px)
            }
            onHover {
                background = Background(BackgroundFill(c(0, 0, 0, 0.3), CornerRadii(4.0), null))
            }
            setOnMouseExited {
                background = null
            }
            tooltip(tooltip)
            graphic = resources.imageview("/gui/$picture").apply {
                fitHeight = 20.0
                fitWidth = 20.0
            }
        }
    }

    private fun stylize(){
        fKvNumber.bindDirty(model.dirtyStateFor(AreaModel::kvProperty))
        with(model.field1Model){
            fAreaNumber.bindDirty(dirtyStateFor(Field1ViewModel::numberProperty))
            fArea.bindDirty(dirtyStateFor(Field1ViewModel::areaProperty))
            fCategoryArea.bindDirty(dirtyStateFor(Field1ViewModel::categoryProperty))
            fDP.bindDirty(dirtyStateFor(Field1ViewModel::dpProperty))
            fOzu.bindDirty(dirtyStateFor(Field1ViewModel::typeOfProtectionProperty))
        }
        with(model.field2ViewModel){
            fAction1.bindDirty(dirtyStateFor(Field2ViewModel::firstActionProperty))
            fAction2.bindDirty(dirtyStateFor(Field2ViewModel::secondActionProperty))
            fAction3.bindDirty(dirtyStateFor(Field2ViewModel::thirdActionProperty))
        }
        with(model.field3ViewModel){
            fSpecies.bindDirty(dirtyStateFor(Field3ViewModel::speciesProperty))
            fBon.bindDirty(dirtyStateFor(Field3ViewModel::bonProperty))
            fType.bindDirty(dirtyStateFor(Field3ViewModel::typeProperty))
            fSubType.bindDirty(dirtyStateFor(Field3ViewModel::subTypeProperty))
            fYearOfDeforest.bindDirty(dirtyStateFor(Field3ViewModel::yearOfDeforestationProperty))
            fCountOfStump.bindDirty(dirtyStateFor(Field3ViewModel::countOfStumpProperty))
            fCountOfPinusStump.bindDirty(dirtyStateFor(Field3ViewModel::countOfPinusStumpProperty))
            fStumpDiameter.bindDirty(dirtyStateFor(Field3ViewModel::stumpDiameterProperty))
            fTypeDeforest.bindDirty(dirtyStateFor(Field3ViewModel::typeOfDeforestationProperty))
        }
        with(model.field4ViewModel){
            fDisorder.bindDirty(dirtyStateFor(Field4ViewModel::disorderProperty))
            fValidDisorder.bindDirty(dirtyStateFor(Field4ViewModel::validDisorderProperty))
            fDryTimber.bindDirty(dirtyStateFor(Field4ViewModel::dryTimberProperty))
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
            f31_count.bindDirty(dirtyStateFor(Field31ViewModel::countProperty))
            f31_h.bindDirty(dirtyStateFor(Field31ViewModel::hProperty))
            f31_age.bindDirty(dirtyStateFor(Field31ViewModel::ageProperty))
            f31_proportion1.bindDirty(dirtyStateFor(Field31ViewModel::proportion1Property))
            f31_proportion2.bindDirty(dirtyStateFor(Field31ViewModel::proportion2Property))
            f31_element1.bindDirty(dirtyStateFor(Field31ViewModel::element1Property))
            f31_element2.bindDirty(dirtyStateFor(Field31ViewModel::element2Property))
        }
        val dops = model.dopViewModel.dopFieldViewModels
        bindDirtyDop(dops[0], fDop1_n, fDop1_1, fDop1_2, fDop1_3, fDop1_4, fDop1_5, fDop1_6, fDop1_7, fDop1_8)
        bindDirtyDop(dops[1], fDop2_n, fDop2_1, fDop2_2, fDop2_3, fDop2_4, fDop2_5, fDop2_6, fDop2_7, fDop2_8)
        bindDirtyDop(dops[2], fDop3_n, fDop3_1, fDop3_2, fDop3_3, fDop3_4, fDop3_5, fDop3_6, fDop3_7, fDop3_8)
        bindDirtyDop(dops[3], fDop4_n, fDop4_1, fDop4_2, fDop4_3, fDop4_4, fDop4_5, fDop4_6, fDop4_7, fDop4_8)
        bindDirtyDop(dops[4], fDop5_n, fDop5_1, fDop5_2, fDop5_3, fDop5_4, fDop5_5, fDop5_6, fDop5_7, fDop5_8)
        bindDirtyDop(dops[5], fDop6_n, fDop6_1, fDop6_2, fDop6_3, fDop6_4, fDop6_5, fDop6_6, fDop6_7, fDop6_8)


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
            fn.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::numberProperty))
            f1.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col1Property))
            f2.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col2Property))
            f3.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col3Property))
            f4.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col4Property))
            f5.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col5Property))
            f6.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col6Property))
            f7.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col7Property))
            f8.bindDirty(dirtyStateFor(DopViewModel.DopFieldViewModel::col8Property))
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
            fHrang.bindDirty(dirtyStateFor(ElementOfForestViewModel::hRangProperty))
            fProp.bindDirty(dirtyStateFor(ElementOfForestViewModel::proportionProperty))
            fSpec.bindDirty(dirtyStateFor(ElementOfForestViewModel::speciesProperty))
            fAge.bindDirty(dirtyStateFor(ElementOfForestViewModel::ageProperty))
            fH.bindDirty(dirtyStateFor(ElementOfForestViewModel::hProperty))
            fD.bindDirty(dirtyStateFor(ElementOfForestViewModel::dProperty))
            fTrade.bindDirty(dirtyStateFor(ElementOfForestViewModel::tradeClassProperty))
            fOrig.bindDirty(dirtyStateFor(ElementOfForestViewModel::generationProperty))
            fWeight.bindDirty(dirtyStateFor(ElementOfForestViewModel::weightProperty))
            fSumOfT.bindDirty(dirtyStateFor(ElementOfForestViewModel::sumOfTimberProperty))
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

    private infix fun TextFieldImpl.bystr(other: Property<String>) = this.bind(property = other, readonly = false, converter = FieldStringConverter())
    private infix fun TextFieldImpl.byint(other: Property<Int>) = this.bind(property = other, readonly = false, converter = FieldIntConverter())
    private infix fun TextFieldImpl.byfloat(other: Property<Float>) = this.bind(property = other, converter = FieldFloatConverter())



}


