package roslesinforg.geobaseeditor.model.validators

import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.control.TextField
import tornadofx.ValidationContext
import tornadofx.ValidationMessage
import tornadofx.ValidationSeverity
import tornadofx.ValidationTrigger

class ValidatorFactory(private val context: ValidationContext) {
    fun stringValidator(textField: TextField): ValidationContext.Validator<String>{
        return textFieldValidatorError(textField, "Неправильное значение"){
            it!!.isNotEmpty() && !it.matches("[а-яА-Я\\s]{1,5}".toRegex())
        }
    }
    fun numberValidator(textField: TextField): ValidationContext.Validator<String> {
        return textFieldValidatorError(textField, "Введите целое число"){
            try {
                if (it!!.isNotEmpty()) it.replace(",", ".").toFloat()
                false
            }catch (e: Exception){
                true
            }
        }
    }

    private fun textFieldValidatorError(textField: TextField, message: String, condition: (String?) -> Boolean): ValidationContext.Validator<String>{
        return context.Validator(textField, textField.textProperty(), ValidationTrigger.OnChange(300)){
            if(condition(it)) error(message) else null
        }
    }


}


