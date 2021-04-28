package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field
import roslesinforg.porokhin.areatypes.fields.Field1
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KMutableProperty1

class Selector(private val data: List<Area>) {
    fun select(vararg parameters: Parameter<*, *>): List<Area>{
        var list: List<Area> = ArrayList(data)
        parameters.forEach { list = list.filterByParam(it) }
        return list
    }

    private fun <R, T>List<Area>.filterByParam(parameter: Parameter<R, T>): List<Area>{
        return this.filter {
            with(parameter){
                val v = when(parameter){
                    is AreaParameter -> areaProperty.get(it as R)
                    is Field1Parameter -> areaProperty.get(it.field1 as R)
                    is Field2Parameter -> areaProperty.get(it.field1 as R)
                    is Field3Parameter -> areaProperty.get(it.field1 as R)
                    is Field4Parameter -> areaProperty.get(it.field1 as R)
                    is Field10Parameter -> areaProperty.get(it.field1 as R)
                    is Field11Parameter -> areaProperty.get(it.field1 as R)
                    is Field12Parameter -> areaProperty.get(it.field1 as R)
                    is Field13Parameter -> areaProperty.get(it.field1 as R)
                    is Field15Parameter -> areaProperty.get(it.field1 as R)
                    is Field19Parameter -> areaProperty.get(it.field1 as R)
                    is Field21Parameter -> areaProperty.get(it.field1 as R)
                    is Field23Parameter -> areaProperty.get(it.field1 as R)
                    is Field27Parameter -> areaProperty.get(it.field1 as R)
                    is Field29Parameter -> areaProperty.get(it.field1 as R)
                    else -> throw IllegalArgumentException("Unsupported FieldParameter")
                }
                kotlin.run {
                    when(v){
                        is String -> (v as String) == value.toString()
                        is Int -> when(condition){
                            Condition.EQUAL -> v == value as Int
                            Condition.LESS -> v < value as Int
                            Condition.MORE -> v > value as Int
                        }
                        is Float -> when(condition){
                            Condition.EQUAL -> v == value as Float
                            Condition.LESS -> v < value as Float
                            Condition.MORE -> v > value as Float
                        }
                        else -> throw IllegalArgumentException("Value of type ${v!!::class} not supported")
                    }

                }
            }

        }
    }

    /**
     * Return ids of areas
     */
    fun selectForId(vararg parameters: Parameter<*, *>): List<Int>{
        return select(*parameters).map { it.id }
    }

}

open class Parameter<R, T>(val areaProperty: KMutableProperty1<R, T>, val value: T, val condition: Condition){

    constructor(areaProperty: KMutableProperty1<R, T>, value: T, condition: String): this(areaProperty, value, condition.parseCondition())
}

class AreaParameter<T>(areaProperty: KMutableProperty1<Area, T>, value: T, condition: Condition)
    : Parameter<Area, T>(areaProperty, value, condition)

open class FieldParameter<T>(areaProperty: KMutableProperty1<out Field, T>, value: T, condition: Condition)
    : Parameter<Field, T>(areaProperty, value, condition){

    }
class Field1Parameter<T>(areaProperty: KMutableProperty1<Field1, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition){
    constructor(areaProperty: KMutableProperty1<Field1, T>, value: T, condition: String) : this(areaProperty, value, condition.parseCondition())

}
class Field2Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field3Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field4Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field10Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field11Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field12Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field13Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field15Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field19Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field21Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field23Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field27Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)
class Field29Parameter<T>(areaProperty: KMutableProperty1<Field, T>, value: T, condition: Condition): FieldParameter<T>(areaProperty, value, condition)

fun String.parseCondition(): Condition{
    return when(this) {
        "=" -> Condition.EQUAL
        "<" -> Condition.LESS
        ">" -> Condition.MORE
        else -> throw IllegalArgumentException("unsupported condition: $this")
    }
}

enum class Condition{
    MORE,
    LESS,
    EQUAL
}

