package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import kotlin.reflect.KMutableProperty1

object ParameterFactory {
    fun <R, T>createParameter(attribute: Attribute, condition: String, value: T): Parameter<R, T>{
        return when(attribute){
            Attribute.OZU -> Field1Parameter(Field1::typeOfProtection, condition, value as Int)
            Attribute.CATEGORY_PROTECTION -> AreaParameter(Area::categoryProtection, condition, value as Int)
            Attribute.SPECIES -> Element10Parameter(ElementOfForest::species, "=", value as String)
            Attribute.BON -> Field3Parameter(Field3::bon, condition, value as String)
            Attribute.WEIGHT -> Element10Parameter(ElementOfForest::weight, condition, value as Float)
            Attribute.SUM_OF_TIMBER -> Element10Parameter(ElementOfForest::sumOfTimber, condition, value as Int)
            Attribute.CATEGORY -> Field1Parameter(Field1::category, condition, value as Int)
            Attribute.INFO -> Field23Parameter(Field1::category, value as Int)
            else -> throw IllegalArgumentException("Unsupported attribute")
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
    INFO
}