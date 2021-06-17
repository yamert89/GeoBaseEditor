package roslesinforg.porokhin.geobaseeditor.view.constructors

import roslesinforg.porokhin.geobaseeditor.model.validation.ValidationHelper
import roslesinforg.porokhin.geobaseeditor.model.validation.ValidatorFactory
import roslesinforg.porokhin.geobaseeditor.view.MainView
import tornadofx.ValidationContext

class ValidationConstructor: ViewConstructor<MainView> {
    override fun construct(view: MainView) {
        with(view){
            validationHelper.stringValidatorFor(fSpecies, fType, fSubType, fTypeDeforest,
                fSpecies1, fSpecies2, fSpecies3, fSpecies4, fSpecies5, fSpecies6, fSpecies7, fSpecies8, fSpecies9,
                f31_element1, f31_element2)

            validatorFactory.typeValidator(fType)

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

        }
    }
}