package roslesinforg.porokhin.geobaseeditor.model

import roslesinforg.porokhin.areatypes.Area

class Kv(val number: Int, val areas: MutableList<Area>){
    val oldAr: Float = calculateArea()
    val ar: Float get() = calculateArea()
    val kvDiff: Float get() = ar - oldAr
    val oldArs = areas.associate { it.field1.number to it.field1.area }

    private fun calculateArea() = areas.sumOf { it.field1.area.toDouble() }.toFloat()
}