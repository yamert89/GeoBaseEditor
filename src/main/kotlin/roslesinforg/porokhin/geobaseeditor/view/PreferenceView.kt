package roslesinforg.porokhin.geobaseeditor.view

import roslesinforg.porokhin.geobaseeditor.model.GeoBaseEditorPreferences
import tornadofx.*

class PreferenceView() : View("Настройки") {
    private val mainView: MainView by inject()
    override val root = hbox {
        paddingAll = 30.0
        vbox {
            checkbox("Фильтрация полей при вводе", GeoBaseEditorPreferences.filtering){
                action {
                    if (!isSelected)  mainView.clearFilters()
                    else mainView.applyFilters()
                }
            }
        }
    }
}