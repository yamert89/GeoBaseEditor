package roslesinforg.geobaseeditor.view

import javafx.scene.Parent
import roslesinforg.geobaseeditor.model.Preferences
import tornadofx.*

class PreferenceView() : View("Настройки") {
    val mainView: MainView by inject()
    override val root = hbox {
        vbox {
            checkbox("Фильтрация полей", Preferences.filtering){
                action {
                    if (!isSelected)  mainView.clearFilters()
                }


            }
        }
    }
}