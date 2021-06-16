package roslesinforg.porokhin.geobaseeditor.view.constructors

import javafx.beans.property.SimpleBooleanProperty
import roslesinforg.porokhin.geobaseeditor.model.TextFieldImpl
import roslesinforg.porokhin.geobaseeditor.view.MainView
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.*
import tornadofx.dirtyStateFor

class DirtyStateConstructor: ViewConstructor<MainView> {
    private var enableFieldsTrigger: SimpleBooleanProperty = SimpleBooleanProperty()
    override fun construct(view: MainView) {
        enableFieldsTrigger = view.enableFieldsTrigger
        with(view){
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
        }
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
        f8: TextFieldImpl
    ){
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
}