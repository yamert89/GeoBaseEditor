package roslesinforg.geobaseeditor

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import roslesinforg.geobaseeditor.model.DataReader
import roslesinforg.geobaseeditor.model.RawDataReader
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.areawriter.RawSoliAreaWriter
import roslesinforg.porokhin.filecomparator.service.ComparedPair
import roslesinforg.porokhin.filecomparator.service.LineType
import tornadofx.Controller
import tornadofx.toObservable
import java.io.File

class GeoBaseEditorController: Controller() {
    var areas: SimpleListProperty<Area> = SimpleListProperty()
    var updateCounter = SimpleIntegerProperty(0)
    private val dataReader: DataReader = RawDataReader()
    fun read(file: File){
        areas.set(dataReader.read(file).toObservable())
        updateCounter.set(updateCounter.value++)
        println("areas loaded")
    }

    fun read(){
        areas.set(
            listOf<Area>(Area(),
                Area(1, 2, 2, Field1(2, 2f, 2, 0, 0)),
                Area(3, 3, 3, Field1(3, 3f, 3))).toObservable())
        updateCounter.set(updateCounter.value++)
    }

    fun writeToRawFile(file: File){
        prepareForSaving()
        val writer = RawSoliAreaWriter(file)
        writer.writeAreas(areas)
    }

    fun newEmptyArea(selected: Area){
        val proto = areas[0]
        areas.add(areas.indexOf(selected) + 1, Area(proto.region, proto.kv))
    }

    fun copyArea(selected: Area){
        val idx = areas.indexOf(selected)
        with(selected.field1){
            areas.add(idx + 1, selected.copy(field1 = Field1(number, area, category, dp, typeOfProtection)))
        }

    }

    fun diff(): List<ComparedPair>{
        return listOf(
            ComparedPair("1111111", LineType.CHANGED, "11112222"),
            ComparedPair("333333333", LineType.CHANGED, "333333334")
        )
    }

    private fun prepareForSaving(){
        areas.forEach { area ->
            with(area.field10.forestElements){
                val filtered = filter { it.hRang != 0 }
                clear()
                addAll(filtered)
            }
        }
    }

}