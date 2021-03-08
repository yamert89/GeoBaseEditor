package roslesinforg.geobaseeditor.view

import javafx.geometry.Insets
import javafx.scene.Parent
import roslesinforg.geobaseeditor.model.Preferences
import tornadofx.*

class PreferenceView() : View("Настройки") {
    private val mainView: MainView by inject()
    override val root = hbox {
        paddingAll = 30.0
        vbox {
            checkbox("Фильтрация полей при вводе", Preferences.filtering){
                action {
                    if (!isSelected)  mainView.clearFilters()
                    else mainView.applyFilters()
                }
            }
        }
    }
}