package roslesinforg.porokhin.geobaseeditor.model

import javafx.util.StringConverter
import javafx.util.converter.FloatStringConverter
import javafx.util.converter.IntegerStringConverter
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition

class FieldIntConverter: IntegerStringConverter() {

    override fun toString(value: Int?): String {
        return if (value == 0) "" else value.toString()
    }
}

class FieldFloatConverter: FloatStringConverter(){
    override fun toString(value: Float?): String {
        val strValue = value.toString().replace(".", ",")
        return when{
            value == 0f -> ""
            strValue.endsWith(",0") -> strValue.substringBeforeLast(",0")
            else -> strValue
        }
    }

    override fun fromString(value: String?): Float {
        return when{
            value == null || value.isEmpty() -> 0f
            else -> value.replace(",", ".").toDouble().toFloat()
        }
    }
}

class FieldStringConverter: StringConverter<String>(){ //fixme not running
    override fun toString(s: String?): String {
        return s?.toUpperCase()?.trim().let {
            println("Converted value $it")
        it} ?: ""/*when{
            s == "0" || s == "0.0" || s == null -> ""
            s.endsWith(".0") -> s.replace(".0", "")
            else -> s
        }*/
    }

    override fun fromString(string: String?): String {
        return string ?: ""
    }

}

class StringFormat : Format() {
    override fun format(obj: Any?, toAppendTo: StringBuffer, pos: FieldPosition): StringBuffer {
        println(obj)
        return StringBuffer((obj as String).toUpperCase().trim())
    }

    override fun parseObject(source: String?, pos: ParsePosition): Any {
        TODO("Not yet implemented")
    }

}




