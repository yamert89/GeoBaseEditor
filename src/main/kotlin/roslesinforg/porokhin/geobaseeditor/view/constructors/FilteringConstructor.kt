package roslesinforg.porokhin.geobaseeditor.view.constructors

import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.geobaseeditor.model.validation.FilteringHelper
import roslesinforg.porokhin.geobaseeditor.view.MainView
import tornadofx.isFloat
import tornadofx.isInt

class FilteringConstructor: ViewConstructor<MainView>, ViewDeconstructor<MainView> {
    private val filteringHelper = FilteringHelper()
    override fun construct(view: MainView) {
        with(view){
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
    }

    override fun deconstruct(view: MainView) {
        with(view){
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
    }
}