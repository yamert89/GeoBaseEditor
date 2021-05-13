package roslesinforg.porokhin.geobaseeditor.model

import org.apache.poi.ss.formula.functions.Areas
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.Location

class ReadEntity(val areas: List<Area>, val location: Location, val notOperatedFields: MutableSet<Int>) {
}