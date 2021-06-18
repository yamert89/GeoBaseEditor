package roslesinforg.porokhin.geobaseeditor.model

import toCondition
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import kotlin.reflect.KMutableProperty1

class Selector(private val data: List<Area>) {
    fun select(vararg parameters: Parameter<*, *>): List<Area>{
        var list: MutableList<Area> = ArrayList(data)
        parameters.forEach {
            list = when(it.logicCondition){
                LogicCondition.AND -> list.filterByParam(it).toMutableList()
                LogicCondition.OR -> data.filterByParam(it).toMutableList().apply { addAll(list) }.distinct().toMutableList()
            }

        }
        return list
    }
    @Suppress("unchecked_cast")
    private fun <R, T>List<Area>.filterByParam(parameter: Parameter<R, T>): List<Area>{
        return this.filter {
            with(parameter){
                var f10False = false
                var f23False = false
                val v = when(parameter){
                    is Element10Parameter -> if (it.field10.forestElements.isEmpty()) {
                        f10False = true
                        Any() as T
                    } else areaProperty.get(it.field10.forestElements[0] as R)
                    is AreaParameter -> areaProperty.get(it as R)
                    is Field1Parameter -> areaProperty.get(it.field1 as R)
                    is Field2Parameter -> areaProperty.get(it.field2 as R)
                    is Field3Parameter -> areaProperty.get(it.field3 as R)
                    is Field4Parameter -> areaProperty.get(it.field4 as R)
                    is Field11Parameter -> areaProperty.get(it.field11 as R)
                    is Field12Parameter -> areaProperty.get(it.field12 as R)
                    is Field13Parameter -> areaProperty.get(it.field13 as R)
                    is Field15Parameter -> areaProperty.get(it.field15 as R)
                    is Field19Parameter -> areaProperty.get(it.field19 as R)
                    is Field21Parameter -> areaProperty.get(it.field21 as R)
                    is Field23Parameter -> if (it.field23.info.isEmpty()){
                        f23False = true
                        Any() as T
                    } else it.field23.info.contains(value) as T
                    is Field27Parameter -> areaProperty.get(it.field27 as R)
                    is Field29Parameter -> areaProperty.get(it.field29 as R)
                    else -> throw IllegalArgumentException("Unsupported FieldParameter")
                }
                kotlin.run {
                    when{
                        f10False || f23False -> false
                        v is String -> (v as String) == value.toString()
                        v is Int -> when(comparingCondition){
                            ComparingCondition.EQUAL -> v == value as Int
                            ComparingCondition.LESS -> v < value as Int
                            ComparingCondition.MORE -> v > value as Int
                        }
                        v is Float -> when(comparingCondition){
                            ComparingCondition.EQUAL -> v == value as Float
                            ComparingCondition.LESS -> v < value as Float
                            ComparingCondition.MORE -> v > value as Float
                        }
                        v is Boolean -> v
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
    val logicCondition: LogicCondition
    val areaProperty: KMutableProperty1<R, T>
    val comparingCondition: ComparingCondition
    val value: T

    protected constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<R, T>, comparingCondition: ComparingCondition, value: T){
        this.logicCondition = logicCondition
        this.areaProperty = areaProperty
        this.value = value
        this.comparingCondition = comparingCondition
    }
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<R, T>, comparingCondition: String, value: T)
            : this(logicCondition, areaProperty, comparingCondition.toCondition(), value)
}

class AreaParameter<T> : Parameter<Area, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Area, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Area, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}

open class FieldParameter<F: Field, T> : Parameter<F, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<F, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<F, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field1Parameter<T>: FieldParameter<Field1, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field1, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field1, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field2Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field3Parameter<T>: FieldParameter<Field3, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field3, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field3, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field4Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field10Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field11Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field12Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field13Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field15Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field19Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field21Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field23Parameter<T>(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field1, T>, value: T) :
    FieldParameter<Field1, T>(logicCondition, areaProperty, ComparingCondition.EQUAL, value) {
}
class Field27Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Field29Parameter<T>: FieldParameter<Field2, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<Field2, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}
class Element10Parameter<T>: Parameter<ElementOfForest, T>{
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<ElementOfForest, T>, comparingCondition: ComparingCondition, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
    constructor(logicCondition: LogicCondition, areaProperty: KMutableProperty1<ElementOfForest, T>, comparingCondition: String, value: T)
            : super(logicCondition, areaProperty, comparingCondition, value)
}

enum class ComparingCondition{
    MORE,
    LESS,
    EQUAL;

    override fun toString(): String {
        return when(this){
            MORE -> ">"
            LESS -> "<"
            EQUAL -> "="
        }
    }
}

enum class LogicCondition{
    AND,
    OR;

    override fun toString(): String {
        return when(this){
            AND -> "и"
            OR -> "или"
        }
    }
}

