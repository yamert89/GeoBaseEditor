package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KMutableProperty1

class Selector(private val data: List<Area>) {
    fun <T: AreaFieldType>select(vararg parameters: Parameter<T>): List<Area>{
        var list = mutableListOf<Area>()
    }

    private fun <T: AreaFieldType>List<Area>.filterByParam(parameter: Parameter<T>): List<Area>{
        return this.filter {
            with(parameter){
                val v = areaProperty.get(it)
                kotlin.run {
                    if (v is StringField) (v as StringField).value == value.toString()
                    /*var intValue : Int = Int.MIN_VALUE
                    var floatValue: Float = Float.MIN_VALUE
                    val intProp: Int = intValue
                    val floatProp = floatValue
                    if (v is Int) {
                        v as Int
                        intValue = value as Int
                    }else{
                        v as Float
                        floatValue = value as Float
                    }*/
                    else when(condition){
                        Condition.EQUAL -> v == value
                        Condition.LESS -> /*intValue == intProp && floatValue == floatProp*/
                        Condition.MORE ->
                    }
                }
            }

        }
    }

    /**
     * Return ids of areas
     */
    fun <T: AreaFieldType>selectForId(vararg parameters: Parameter<T>): List<Int>{
        return select(*parameters).map { it.id }
    }

}

class Parameter<T: AreaFieldType>(val areaProperty: KMutableProperty1<Area, T>, val value: T, val condition: Condition){

    constructor(areaProperty: KMutableProperty1<Area, T>, value: T, condition: String): this(areaProperty, value,
        kotlin.run {
            when(condition) {
                "=" -> Condition.EQUAL
                "<" -> Condition.LESS
                ">" -> Condition.MORE
                else -> throw IllegalArgumentException("unsupported condition: $condition")
            }
        }
    )
}

enum class Condition{
    MORE,
    LESS,
    EQUAL
}
enum class AreaProperty{
    KV,
    ID,
    F1AREA,
    F1
}

