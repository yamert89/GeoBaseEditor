package roslesinforg.porokhin.geobaseeditor.model.validation

import javafx.scene.control.TextField
import roslesinforg.porokhin.areatypes.GeneralTypes
import tornadofx.ValidationContext
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
    fun dValidator(textField: TextField): ValidationContext.Validator<String>{
        return textFieldValidatorError(textField, "Неправильный диаметр"){
            it!!.isNotEmpty() && !it.matches("[1-9]?[24680]".toRegex())
        }
    }

    fun typeValidator(textField: TextField): ValidationContext.Validator<String>{
        return textFieldValidatorError(textField, "Неправильный тип леса"){
            it!!.isNotEmpty() && !GeneralTypes.typesOfForest.any{ v -> v.name == it}
        }
    }

    private fun textFieldValidatorError(textField: TextField, message: String, condition: (String?) -> Boolean): ValidationContext.Validator<String>{
        return context.Validator(textField, textField.textProperty(), ValidationTrigger.OnChange(300)){
            if(condition(it)) error(message) else null
        }
    }



}


