package roslesinforg.geobaseeditor.model

import javafx.util.StringConverter
import javafx.util.converter.FloatStringConverter
import javafx.util.converter.IntegerStringConverter

class FieldIntConverter: IntegerStringConverter() {

    override fun toString(value: Int?): String {
        return if (value == 0) "" else value.toString()
    }
}

class FieldFloatConverter: FloatStringConverter(){
    override fun toString(value: Float?): String {
        val strValue = value.toString()
        return when{
            value == 0f -> ""
            strValue.endsWith(".0") -> strValue.substringBeforeLast(".0")
            else -> strValue
        }
    }
}



