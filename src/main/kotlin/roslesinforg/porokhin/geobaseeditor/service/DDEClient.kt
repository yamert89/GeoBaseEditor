package roslesinforg.porokhin.geobaseeditor.service

import com.pretty_tools.dde.client.DDEClientConversation
import com.pretty_tools.dde.server.DDEServer
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.model.*


class DDEClient(private val controller: GeoBaseEditorController) {
    private val logger = logger()
    var server: DDEServer? = null
    var client: DDEClientConversation? = null
    var selector: Selector? = null

    fun initiate(){
        server = object : DDEServer("geobaseeditor"){
            override fun onPoke(topic: String?, item: String?, data: String?): Boolean {
                when(topic){
                    "areaselection" -> {
                        when(item){
                            "selection" -> {
                                logger.debug("$data area selected from MapInfo")
                                controller.selectArea(data!!.toInt())
                            }
                            "selection_with_area" -> {
                                val arr = data!!.split("|")
                                val id = arr[0].toInt()
                                if (!controller.areas.any { it.id == id }) return false
                                controller.selectArea(arr[0].toInt())
                                controller.log("Выд. ${arr[0]}, картографическая площадь: ${arr[1].toFloat()}")
                            }
                            else -> logger.debug("unknown item")
                        }
                    }
                    else -> logger.debug("unknown topic")
                }
                return true
            }

            override fun onRequest(topic: String?, item: String?): String {
                logger.debug("Request with topic: $topic, item: $item")
                return when(topic){
                    "areaselection" -> {
                        if (selector == null) selector = Selector(controller.areas)
                        val params = mutableListOf<Parameter<*, *>>()
                        val arr = item!!.split("|")
                        for (it in arr) {
                            val (logicCond, p, comparingCondition, value) = it.split("?", limit = 4)
                            val logicCondition: LogicCondition = if (logicCond == "1") LogicCondition.AND else LogicCondition.OR
                            if (p.isEmpty() || comparingCondition.isEmpty() || value.isEmpty()) continue
                            val f = ParameterFactory
                            val param: Parameter<*, *> = when(p.toInt()){
                                1 -> f.createParameter<Field1, Int>(logicCondition, Attribute.OZU, comparingCondition, value.toInt())
                                2 -> f.createParameter<Field1, Int>(logicCondition, Attribute.CATEGORY_PROTECTION, comparingCondition, value.toInt())
                                3 -> f.createParameter<ElementOfForest, String>(logicCondition, Attribute.SPECIES, comparingCondition, value)
                                4 -> f.createParameter<Field3, String>(logicCondition, Attribute.BON, comparingCondition, value)
                                5 -> f.createParameter<ElementOfForest, Float>(logicCondition, Attribute.WEIGHT, comparingCondition, value.toFloat())
                                6 -> f.createParameter<ElementOfForest, Int>(logicCondition, Attribute.SUM_OF_TIMBER, comparingCondition, value.toInt())
                                7 -> f.createParameter<Field1.Empty1, Int>(logicCondition, Attribute.INFO, "", value.toInt())
                                8 -> f.createParameter<Field1, Int>(logicCondition, Attribute.CATEGORY, comparingCondition, value.toInt())
                                else -> throw IllegalArgumentException("unknown param $p")
                            }
                            params.add(param)
                        }

                        val ids = selector!!.selectForId(*params.toTypedArray())

                        val response = if (ids.isEmpty()) "0" else ids.joinToString(","){
                            val s = StringBuilder(it.toString())
                            while (s.length < 6) s.append(" ")
                            s.toString()
                        }
                        logger.debug("Response : $response")
                        response
                    }
                    else -> throw IllegalArgumentException("Unknown topic $topic")
                }
            }

            override fun onConnected(topic: String?, hconv: Long) {
                logger.debug("DDE input connection with number = $hconv")
                /*client = DDEClientConversation()
                try {
                    client!!.connect("MapInfo", "System")
                    print(client!!.request("Version"))
                }catch (e: Exception){
                    logger.error(e)
                }
                logger.debug("DDE client connected")*/

                //client!!.request("1001")
                //client!!.poke("paint", "1001")
                //client!!.p

            }

            override fun start() {

                logger.debug("DDE Server started")
                super.start()
            }
        }
        server!!.start()
    }

    fun close() {
        server!!.stop()
        logger.debug("DDE server stopped")
    }

    fun paint(){
        if (client == null) return
        client!!.execute("1001")
        logger.debug("paint 1001")
    }
}