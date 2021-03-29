package roslesinforg.porokhin.geobaseeditor.model.validation

import javafx.scene.control.TextField
import tornadofx.ValidationContext

class ValidationHelper(private val validationContext: ValidationContext, private val validationFactory: ValidatorFactory) {
    val failedAreas = mutableSetOf<Int>()
    fun stringValidatorFor(vararg fields: TextField){
        fields.forEach {
            validationContext.addValidator(validationFactory.stringValidator(it))
        }
    }

    fun numberValidatorFor(vararg fields: TextField){
        fields.forEach { validationContext.addValidator(validationFactory.numberValidator(it)) }
    }

    fun dValidatorFor(vararg fields: TextField){
        fields.forEach { validationContext.addValidator(validationFactory.dValidator(it)) }
    }

    fun generalChecking(proportions: List<TextField>, hRangs: List<TextField>): String{
        val messages =  StringBuilder()
        if (proportions.filter { it.text.isNotEmpty() }
                .sumBy { it.text.toInt() } != 10) messages.append("Сумма коэффициентов запаса не равна 10")
        for (i in 0..9) {
            if (hRangs[i].text.isEmpty() && proportions[i].text.isNotEmpty()) {
                messages.append("\nЯрус должен быть заполнен")
                break
            }
        }
        return messages.toString()
    }


}