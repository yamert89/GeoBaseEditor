package roslesinforg.geobaseeditor.view

import com.sun.deploy.panel.TextFieldProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.event.EventHandler
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.*

class MainView : View("My View") {
    override val root: AnchorPane by fxml("/gui/MainView.fxml")
    val field_gir: TextField by fxid()
    val field_kvNumber: TextField by fxid()
    val field_species: TextField by fxid()
    val field_bon: TextField by fxid()
    val field_type: TextField by fxid()
    val field_subType: TextField by fxid()
    val field_yearDeforest: TextField by fxid()
    val field_countOfStump: TextField by fxid()
    val field_countOfPinusStump: TextField by fxid()
    val field_stumpDiameter: TextField by fxid()
    val field_typeDeforest: TextField by fxid()
    val field_disorder: TextField by fxid()
    val field_validDisorder: TextField by fxid()
    val field_dryTimber: TextField by fxid()
    val field_hRang1: TextField by fxid()
    val field_hRang2: TextField by fxid()
    val field_hRang3: TextField by fxid()
    val field_hRang4: TextField by fxid()
    val field_hRang5: TextField by fxid()
    val field_hRang6: TextField by fxid()
    val field_hRang7: TextField by fxid()
    val field_hRang8: TextField by fxid()
    val field_hRang9: TextField by fxid()
    val field_hRang10: TextField by fxid()
    val field_: TextField by fxid()
    val field_: TextField by fxid()
    val field_: TextField by fxid()
    val text: StringProperty = SimpleStringProperty("dd")
    init {
        field_gir.bind(text)
        text.onChange { print(text.value) }
    }

}
