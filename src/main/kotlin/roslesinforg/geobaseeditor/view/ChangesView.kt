package roslesinforg.geobaseeditor.view

import javafx.beans.property.Property
import roslesinforg.geobaseeditor.GeoBaseEditorController
import roslesinforg.geobaseeditor.view.viewmodels.AreaModel
import roslesinforg.porokhin.filecomparator.StringResult.Companion.toToken
import roslesinforg.porokhin.filecomparator.service.ComparedPair
import roslesinforg.porokhin.filecomparator.service.LineType
import tornadofx.*

class ChangesView : View("My View") {
    val controller = find(GeoBaseEditorController::class)
    override val root = anchorpane {
        tableview(controller.diff().toObservable()){
            smartResize()
            column<ComparedPair, String>("<>"){
                isEditable = false
                it.value.first.type.toToken().toProperty()
            }.contentWidth(useAsMin = true)
            column<ComparedPair, String>("До"){
                isEditable = false
                it.value.first.value.toProperty()
            }.contentWidth(useAsMax = true)
            column<ComparedPair, String>("<>"){
                isEditable = false
                it.value.second.type.toToken().toProperty()
            }.contentWidth(useAsMin = true)
            column<ComparedPair, String>("После"){
                isEditable = false
                it.value.second.value.toProperty()
            }.contentWidth(useAsMax = true)

        }

    }


}
