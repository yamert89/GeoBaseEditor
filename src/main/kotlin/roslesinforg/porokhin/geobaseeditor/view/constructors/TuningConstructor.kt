package roslesinforg.porokhin.geobaseeditor.view.constructors

import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.geobaseeditor.model.TextFieldImpl
import roslesinforg.porokhin.geobaseeditor.view.MainView
import tornadofx.c
import tornadofx.controlsfx.bindAutoCompletion
import tornadofx.enableWhen
import tornadofx.style
import tornadofx.tooltip
import kotlin.reflect.KProperty0

class TuningConstructor: ViewConstructor<MainView> {
    override fun construct(view: MainView) {
        with(view){
            fGir.enableWhen { enableFieldsTrigger }

            fKvNumber.apply {
                isEditable = false
                style{
                    backgroundColor += c(0, 0, 0, 0.0)
                }
            }
            fType.apply {
                bindAutoCompletion(GeneralTypes.typesOfForest.map { it.name })
            }
            fCategoryArea.setContextHelp(GeneralTypes::categoryArea)
            fOzu.setContextHelp(GeneralTypes::typesOfProtection)
            fDop1_n.setContextHelp(GeneralTypes::fieldNames)
            fDop2_n.setContextHelp(GeneralTypes::fieldNames)
            fDop3_n.setContextHelp(GeneralTypes::fieldNames)
            fDop4_n.setContextHelp(GeneralTypes::fieldNames)
            fDop5_n.setContextHelp(GeneralTypes::fieldNames)
            fDop6_n.setContextHelp(GeneralTypes::fieldNames)
            //todo dop field context help
        }
    }

    private fun TextFieldImpl.setContextHelp(map: KProperty0<Map<Int, String>>){
        tooltip{
            setOnShowing {
                if (this@setContextHelp.text.isNotEmpty()) this.text = map.get()[this@setContextHelp.text.toInt()]
            }
        }
    }
}