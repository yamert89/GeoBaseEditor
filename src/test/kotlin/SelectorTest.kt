import org.junit.Assert
import org.junit.Test
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.ElementOfForest
import roslesinforg.porokhin.areatypes.fields.Field1
import roslesinforg.porokhin.geobaseeditor.model.*
import roslesinforg.porokhin.nabparser.parsing.RawParser
import java.io.File

class SelectorTest {

    @Test
    fun selector(){
        //launch<SelectorApp>()
        val file = File(this::class.java.classLoader.getResource("0309").toURI())
        val parser = RawParser(file)
        parser.parse()
        val selector = Selector(parser.areas)
        val res = selector.selectForId(AreaParameter(LogicCondition.AND, Area::kv, "=", 1))
        Assert.assertEquals(21, res.size)
        Assert.assertEquals(1021, res.last())
        val res2 = selector.select(
            Field1Parameter(LogicCondition.AND, Field1::number, ">", 10),
            Field1Parameter(LogicCondition.AND, Field1::number, "<", 22),
            AreaParameter(LogicCondition.AND, Area::kv, "=", 1))
        Assert.assertArrayEquals((11..21).toList().toIntArray(), res2.map { it.field1.number }.toIntArray())
        val res3 = selector.selectForId(
            Field1Parameter(LogicCondition.AND, Field1::number, "<", 5),
            Field1Parameter(LogicCondition.AND, Field1::area, ">", 2f),
            AreaParameter(LogicCondition.AND, Area::kv, "=", 1)
        )
        Assert.assertEquals(2, res3.size)
        val res4 = selector.select(
            AreaParameter(LogicCondition.AND, Area::kv, "=", 1),
            Element10Parameter(LogicCondition.AND, ElementOfForest::age, ">", 100),
        )
        Assert.assertEquals(3, res4.size)
        val res5 = selector.select(
            AreaParameter(LogicCondition.AND, Area::kv, "=", 1),
            AreaParameter(LogicCondition.AND, Area::id, "=", 1001),
            AreaParameter(LogicCondition.OR, Area::id, "=", 1002)
        ).sortedBy { it.id }
        Assert.assertEquals(2, res5.size)
        Assert.assertEquals(1002, res5[1].id)

    }

}
