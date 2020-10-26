package roslesinforg.geobaseeditor.view

import roslesinforg.porokhin.areatypes.Area
import tornadofx.ItemViewModel

class AreaModel(area: Area) : ItemViewModel<Area>() {

    val kvProp: Int by area.kv

}