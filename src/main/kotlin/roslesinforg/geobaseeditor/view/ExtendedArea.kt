package roslesinforg.geobaseeditor.view

import javafx.beans.property.SimpleIntegerProperty
import roslesinforg.porokhin.areatypes.Area

class ExtendedArea(area: Area) {
    var kvProp =  SimpleIntegerProperty(this, "", area.kv)
    var kv by kvProp
    val categoryProtection = area.categoryProtection
}