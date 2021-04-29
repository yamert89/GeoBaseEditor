import org.junit.Assert
import org.junit.Test
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.geobaseeditor.model.*
import roslesinforg.porokhin.nabparser.Parser
import tornadofx.App
import tornadofx.View
import tornadofx.stackpane
import java.io.File

class SelectorTest {

    @Test
    fun selector(){
        //launch<SelectorApp>()
        val file = File(this::class.java.classLoader.getResource("0309").toURI())
        val parser = Parser(file)
        parser.parse()
        val selector = Selector(parser.areas)
        val res = selector.selectForId(AreaParameter(Area::kv, 1, "="))
        Assert.assertEquals(21, res.size)
        Assert.assertEquals(1021, res.last())
        val res2 = selector.select(
            Field1Parameter(Field1::number, ">", 10),
            Field1Parameter(Field1::number, "<", 22),
            AreaParameter(Area::kv, 1, "="))
        Assert.assertArrayEquals((11..21).toList().toIntArray(), res2.map { it.field1.number }.toIntArray())
        val res3 = selector.selectForId(
            Field1Parameter(Field1::number, "<", 5),
            Field1Parameter(Field1::area, ">", 2f),
            AreaParameter(Area::kv, 1, "=")
        )
        Assert.assertEquals(2, res3.size)
    }

}
