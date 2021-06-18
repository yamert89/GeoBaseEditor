package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*

object ParameterFactory {
    fun <R, T>createParameter(logicCondition: LogicCondition, attribute: Attribute, comparingCondition: String, value: T): Parameter<R, T>{
        return when(attribute){
            Attribute.OZU -> Field1Parameter(logicCondition, Field1::typeOfProtection, comparingCondition, value as Int)
            Attribute.CATEGORY_PROTECTION -> AreaParameter(logicCondition, Area::categoryProtection, comparingCondition, value as Int)
            Attribute.SPECIES -> Element10Parameter(logicCondition, ElementOfForest::species, "=", value as String)
            Attribute.BON -> Field3Parameter(logicCondition, Field3::bon, comparingCondition, value as String)
            Attribute.WEIGHT -> Element10Parameter(logicCondition, ElementOfForest::weight, comparingCondition, value as Float)
            Attribute.SUM_OF_TIMBER -> Element10Parameter(logicCondition, ElementOfForest::sumOfTimber, comparingCondition, value as Int)
            Attribute.CATEGORY -> Field1Parameter(logicCondition, Field1::category, comparingCondition, value as Int)
            Attribute.INFO -> Field23Parameter(logicCondition, Field1::category, value as Int)
        } as Parameter<R, T>
    }

}
enum class Attribute{
    OZU,
    CATEGORY_PROTECTION,
    SPECIES,
    BON,
    WEIGHT,
    SUM_OF_TIMBER,
    CATEGORY,
    INFO;

    override fun toString(): String {
        return when(this){
            OZU -> "ОЗУ"
            CATEGORY_PROTECTION -> "ЦНЛ"
            SPECIES -> "Порода"
            BON -> "Бонитет"
            WEIGHT -> "Полнота"
            SUM_OF_TIMBER -> "Запас"
            CATEGORY -> "Категория земель"
            INFO -> "Особенности насаждений(23)"
        }
    }
}
