package roslesinforg.porokhin.geobaseeditor.model.validation

import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.util.StringConverter
import roslesinforg.porokhin.geobaseeditor.model.FieldStringConverter
import roslesinforg.porokhin.geobaseeditor.model.TextFieldImpl
import tornadofx.CustomTextFilter
import tornadofx.filterInput
import tornadofx.mutateOnChange
import java.util.function.UnaryOperator


class FilteringHelper {

    fun filter(vararg fields: TextFieldImpl, discriminator: (TextFormatter.Change) -> Boolean){
        fields.forEach { tf ->
            tf.textFormatter = TextFormatter<String>(FieldStringConverter(), "", CustomTextFilter (discriminator))
        }
    }

    fun clearFiltering(vararg fields: TextFieldImpl){
        fields.forEach { f -> f.textFormatter = null }
    }

    class MyTextFilter(private val discriminator: (TextFormatter.Change) -> Boolean) : UnaryOperator<TextFormatter.Change> {
        override fun apply(c: TextFormatter.Change): TextFormatter.Change =
            if (discriminator(c)) c.apply { text = controlNewText.toUpperCase().trim() } else c.apply {
                text = controlText

                //text = if (controlText.length < 1) controlText else ""
            }
    }


}