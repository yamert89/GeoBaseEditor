package roslesinforg.porokhin.geobaseeditor.service

import com.pretty_tools.dde.client.DDEClientConversation
import com.pretty_tools.dde.server.DDEServer
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.fields.*
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.model.Attribute
import roslesinforg.porokhin.geobaseeditor.model.Parameter
import roslesinforg.porokhin.geobaseeditor.model.ParameterFactory
import roslesinforg.porokhin.geobaseeditor.model.Selector


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
                                controller.selectArea(arr[0].toInt())
                                controller.log("Выд. ${arr[0]}, картографическая площадь: ${arr[1].toFloat()}")
                            }
                            "paint" -> {

                            }
                            else -> logger.debug("unknown item")
                        }
                    }
                    else -> logger.debug("unknown topic")
                }
                return true
            }

            override fun onRequest(topic: String?, item: String?): String {
                return when(topic){
                    "paint" -> {
                        if (selector == null) selector = Selector(controller.areas)
                        val params = mutableListOf<Parameter<*, *>>()
                        val arr = item!!.split("|")
                        for (it in arr) {
                            val (p, condition, value) = it.split("?", limit = 3)
                            if (p.isEmpty() || condition.isEmpty() || value.isEmpty()) continue
                            val f = ParameterFactory
                            val param: Parameter<*, *> = when(p.toInt()){
                                1 -> f.createParameter<Field1, Int>(Attribute.OZU, condition, value.toInt())
                                2 -> f.createParameter<Field1, Int>(Attribute.CATEGORY_PROTECTION, condition, value.toInt())
                                3 -> f.createParameter<ElementOfForest, String>(Attribute.SPECIES, condition, value)
                                4 -> f.createParameter<Field3, String>(Attribute.BON, condition, value)
                                5 -> f.createParameter<ElementOfForest, Float>(Attribute.WEIGHT, condition, value.toFloat())
                                6 -> f.createParameter<ElementOfForest, Int>(Attribute.SUM_OF_TIMBER, condition, value.toInt())
                                7 -> f.createParameter<Field1.Empty1, Int>(Attribute.INFO, "", value.toInt())
                                8 -> f.createParameter<Field1, Int>(Attribute.CATEGORY, condition, value.toInt())
                                else -> throw IllegalArgumentException("unknown param $p")
                            }
                            params.add(param)
                        }

                        selector!!.selectForId(*params.toTypedArray()).joinToString{
                            val s = StringBuilder(it)
                            while (s.length < 6) s.append(" ")
                            s.toString()
                        }
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