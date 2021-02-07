package roslesinforg.geobaseeditor.model.validators

import javafx.scene.control.TextField
import tornadofx.ValidationContext

class ValidationHelper(private val validationContext: ValidationContext, private val validationFactory: ValidatorFactory) {
    fun stringValidatorFor(vararg fields: TextField){
        fields.forEach {
            validationContext.addValidator(validationFactory.stringValidator(it))
        }
    }

    fun numberValidatorFor(vararg fields: TextField){
        fields.forEach { validationContext.addValidator(validationFactory.numberValidator(it)) }
    }
}