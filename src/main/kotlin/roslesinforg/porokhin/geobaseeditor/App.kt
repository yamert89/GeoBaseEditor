import javafx.scene.control.ButtonBar
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import roslesinforg.porokhin.geobaseeditor.model.Attribute
import roslesinforg.porokhin.geobaseeditor.model.ComparingCondition
import roslesinforg.porokhin.geobaseeditor.model.LogicCondition
import tornadofx.*
import java.text.DecimalFormat

const val FILTERING = "filtering"
const val AUTOSELECT = "autoselect"
const val CLASS_SELECT_BTN_ACTIVE = "selectBtnActive"
const val SQUARE_CONTROL = "squarecontrol"

fun Float.format() = DecimalFormat("#.#").format(this)

fun String.toAttribute() = when(this){
    Attribute.OZU.toString() -> Attribute.OZU
    Attribute.CATEGORY_PROTECTION.toString() -> Attribute.CATEGORY_PROTECTION
    Attribute.SPECIES.toString() -> Attribute.SPECIES
    Attribute.BON.toString() -> Attribute.BON
    Attribute.WEIGHT.toString() -> Attribute.WEIGHT
    Attribute.SUM_OF_TIMBER.toString() -> Attribute.SUM_OF_TIMBER
    Attribute.CATEGORY.toString() -> Attribute.CATEGORY
    Attribute.INFO.toString() -> Attribute.INFO
    else -> throw IllegalArgumentException("Attribute with $this name not found")
}

fun String.toCondition(): ComparingCondition {
    return when(this) {
        "=" -> ComparingCondition.EQUAL
        "<" -> ComparingCondition.LESS
        ">" -> ComparingCondition.MORE
        else -> throw IllegalArgumentException("unsupported comparingCondition: $this")
    }
}

fun String.toLogicCondition() = when(this){
    LogicCondition.OR.toString() -> LogicCondition.OR
    LogicCondition.AND.toString() -> LogicCondition.AND
    else -> throw IllegalArgumentException("Logic condition $this not found")
}

