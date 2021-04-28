import javafx.scene.Parent
import org.junit.Assert
import org.junit.Test
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.areatypes.fields.Field2
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.model.*
import roslesinforg.porokhin.nabparser.Parser
import tornadofx.App
import tornadofx.View
import tornadofx.launch
import tornadofx.stackpane
import java.io.File
import kotlin.reflect.KProperty1

class SelectorTest {

    @Test
    fun selector(){
        //launch<SelectorApp>()
        val parser = Parser(File("J:/0309_2.txt"))
        parser.parse()
        val selector = Selector(parser.areas)
        val res = selector.selectForId(Parameter(Area::kv, 1, "="))
        Assert.assertEquals(21, res.size)
        Assert.assertEquals(1021, res.last())
        val res2 = selector.selectForId(Parameter(Field1::number, 10, ">"))
        Assert.assertArrayEquals((11..21).toList().toIntArray(), res2.toIntArray())
        val res3 = selector.selectForId(
            Field1Parameter(Field1::number, 5, "<"),
            AreaParameter(Area::kv, 2, ">"),
        )
        Assert.assertEquals(2, res3.size)
    }

}
class SelectorApp(): App(SelView::class)
class SelView() : View(){
    override val root = stackpane {

    }
    init {
        Parameter(Area::kv, 1, Condition.EQUAL )
    }
}