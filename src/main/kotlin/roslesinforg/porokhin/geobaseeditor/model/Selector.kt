package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import kotlin.reflect.KMutableProperty1

class Selector(private val data: List<Area>) {
    fun select(vararg parameters: Parameter<*, *>): List<Area>{
        var list: List<Area> = ArrayList(data)
        parameters.forEach { list = list.filterByParam(it) }
        return list
    }
    @Suppress("unchecked_cast")
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

open class Parameter<R, T>{
    val areaProperty: KMutableProperty1<R, T>
    val condition: Condition
    val value: T

    protected constructor(areaProperty: KMutableProperty1<R, T>, condition: Condition, value: T){
        this.areaProperty = areaProperty
        this.value = value
        this.condition = condition
    }
    constructor(areaProperty: KMutableProperty1<R, T>, condition: String, value: T): this(areaProperty, condition.parseCondition(), value)
}

class AreaParameter<T> : Parameter<Area, T>{
    constructor(areaProperty: KMutableProperty1<Area, T>, value: T, condition: Condition) : super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Area, T>, value: T, condition: String) : super(areaProperty, condition, value)
}

open class FieldParameter<F: Field, T> : Parameter<F, T>{
    constructor(areaProperty: KMutableProperty1<F, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<F, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field1Parameter<T>: FieldParameter<Field1, T>{
    constructor(areaProperty: KMutableProperty1<Field1, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field1, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field2Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field3Parameter<T>: FieldParameter<Field3, T>{
    constructor(areaProperty: KMutableProperty1<Field3, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field3, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field4Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field10Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field11Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field12Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field13Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field15Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field19Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field21Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field23Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field27Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}
class Field29Parameter<T>: FieldParameter<Field2, T>{
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: Condition, value: T): super(areaProperty, condition, value)
    constructor(areaProperty: KMutableProperty1<Field2, T>, condition: String, value: T): super(areaProperty, condition, value)
}

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

