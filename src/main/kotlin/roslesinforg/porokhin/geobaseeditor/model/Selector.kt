package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0

class Selector(private val data: List<Area>) {
    fun select(vararg parameters: Parameter): List<Area>{
        var list = mutableListOf<Area>()
    }

    private fun List<Area>.filterByParam(parameter: Parameter): List<Area>{
        val p: KMutableProperty<Int>
        return this.filter {

        }
    }

    /**
     * Return ids of areas
     */
    fun selectForId(vararg parameters: Parameter): List<Int>{
        return select(*parameters).map { it.id }
    }

}

class Parameter<T>(val value: Any, val condition: Condition){

    constructor(value: Any, condition: String): this(value,
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

