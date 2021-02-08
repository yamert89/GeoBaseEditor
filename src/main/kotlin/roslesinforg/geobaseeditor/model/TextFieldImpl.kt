package roslesinforg.geobaseeditor.model

import javafx.beans.binding.BooleanBinding
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.TextField
import tornadofx.c
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
    }
    fun bindDirty(binding: BooleanBinding){
        isDirty.bind(binding)
    }

}