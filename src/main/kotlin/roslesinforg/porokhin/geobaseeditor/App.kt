import javafx.scene.control.ButtonBar
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import tornadofx.*
import java.text.DecimalFormat

const val FILTERING = "filtering"
const val AUTOSELECT = "autoselect"
const val CLASS_SELECT_BTN_ACTIVE = "selectBtnActive"
const val SQUARE_CONTROL = "squarecontrol"

fun Float.format() = DecimalFormat("#.#").format(this)

