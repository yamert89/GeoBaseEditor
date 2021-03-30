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

    fun generalChecking(hRangsAndProportions: List<Pair<TextField, TextField>>): String{ //todo other hRangs
        val messages =  StringBuilder()
        val elements = hRangsAndProportions.filter { it.second.text.isNotEmpty() }
        val hR1 = elements.filter { it.first.text == "1" }
        val hR9 = elements.filter { it.first.text == "9" }
        if (hR1.isNotEmpty() && hR1.sumBy { it.second.text.toInt() } != 10 ||
            hR9.isNotEmpty() && hR9.sumBy { it.second.text.toInt() } != 10) messages.append("Сумма коэффициентов запаса не равна 10")
        if (hRangsAndProportions.any { it.first.text.isEmpty() && it.second.text.isNotEmpty() }) messages.append("\nЯрус должен быть заполнен")
        return messages.toString()
    }


}