package roslesinforg.porokhin.geobaseeditor.model

import javafx.beans.binding.BooleanBinding
import javafx.beans.property.Property
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.value.ObservableBooleanValue
import javafx.beans.value.ObservableValue
import javafx.scene.control.TextField
import tornadofx.c
import tornadofx.enableWhen
import tornadofx.onChange
import tornadofx.style

class TextFieldImpl: TextField() {
    private val isDirty = SimpleBooleanProperty(false)

    init {
        isDirty.onChange {
            if (it){
                style {
                    textFill = c( "0000FF" )
                }
            } else {
                style{
                    textFill = c("000")
                }
            }

        }
        hoverProperty().onChange {
            if (GeoBaseEditorPreferences.autoSelect.value) {
                requestFocus()
                selectAll()
            }
        }




    }
    fun configure(enableTrigger: SimpleBooleanProperty, binding: BooleanBinding){
        enableWhen(enableTrigger as ObservableValue<Boolean>)
        isDirty.bind(binding)
    }



}