package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area

class Kv(val number: Int, val areas: List<Area>){
    val oldAr = calculateArea()
    val ar: Float get() = calculateArea()
    val kvDiff: Float get() = ar - oldAr
    val internalArs = areas.associate { it.field1.number to it.field1.area }

    private fun calculateArea() = areas.sumOf { it.field1.area.toDouble() }.toFloat()
}