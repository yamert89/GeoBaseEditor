package roslesinforg.geobaseeditor.view

import javafx.scene.layout.AnchorPane
import tornadofx.*

class MainView : View("My View") {
    override val root: AnchorPane by fxml("/gui/MainView.fxml")
}
