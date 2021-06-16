package roslesinforg.porokhin.geobaseeditor.view.constructors

import CLASS_SELECT_BTN_ACTIVE
import javafx.beans.property.Property
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import roslesinforg.porokhin.geobaseeditor.model.FieldFloatConverter
import roslesinforg.porokhin.geobaseeditor.model.FieldIntConverter
import roslesinforg.porokhin.geobaseeditor.model.TextFieldImpl
import roslesinforg.porokhin.geobaseeditor.view.MainView
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.DopViewModel
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.ElementOfForestViewModel
import tornadofx.bind
import tornadofx.rebind

class BindingsConstructor: ViewConstructor<MainView> {
    override fun construct(view: MainView) {
        with(view){
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

    private infix fun TextFieldImpl.bystr(other: Property<String>) = this.bind(property = other)
    private infix fun TextFieldImpl.byint(other: Property<Int>) = this.bind(property = other, readonly = false, converter = FieldIntConverter())
    private infix fun TextFieldImpl.byfloat(other: Property<Float>) = this.bind(property = other, converter = FieldFloatConverter())
}