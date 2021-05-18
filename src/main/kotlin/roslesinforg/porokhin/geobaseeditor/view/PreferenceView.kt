package roslesinforg.porokhin.geobaseeditor.view

import javafx.geometry.Orientation
import roslesinforg.porokhin.geobaseeditor.model.GeoBaseEditorPreferences
import tornadofx.*

class PreferenceView() : View("Настройки") {
    private val mainView: MainView by inject()
    override val root = hbox {
        paddingAll = 20.0
        vbox {
            label("Карточка")
            checkbox("Фильтрация полей при вводе", GeoBaseEditorPreferences.filtering){
                action {
                    if (!isSelected)  mainView.clearFilters()
                    else mainView.applyFilters()
                }
                vboxConstraints {
                    marginTop = 10.0
                }
            }
            checkbox("Автовыделение ячеек", GeoBaseEditorPreferences.autoSelect){
                vboxConstraints {
                    marginTop = 10.0
                }
            }
            separator(Orientation.HORIZONTAL){
                vboxConstraints {
                    marginTop = 10.0
                }
            }
            checkbox("Контроль площадей", GeoBaseEditorPreferences.squareControl) {
                action { if(isSelected) mainView.openStrictAreaView()}
            }
        }
    }
}