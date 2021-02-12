package roslesinforg.geobaseeditor.model.validation

import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.util.StringConverter
import roslesinforg.geobaseeditor.model.TextFieldImpl
import tornadofx.CustomTextFilter
import tornadofx.filterInput
import java.util.function.UnaryOperator


class FilteringHelper {

    /*fun filter(vararg fields: TextField, condition:  Boolean){
        fields.forEach { it.filterInput { condition } }
    }*/

    fun filter(vararg fields: TextFieldImpl, discriminator: (TextFormatter.Change) -> Boolean){
        fields.forEach { tf ->
            tf.textFormatter = TextFormatter<Any>(CustomTextFilter (discriminator))
        }
    }

    class MyTextFilter(private val discriminator: (TextFormatter.Change) -> Boolean) : UnaryOperator<TextFormatter.Change> {
        override fun apply(c: TextFormatter.Change): TextFormatter.Change =
            if (discriminator(c)) c else c.clone().apply {
                text = if (controlText.length < 1) controlText else ""
            }
    }


}