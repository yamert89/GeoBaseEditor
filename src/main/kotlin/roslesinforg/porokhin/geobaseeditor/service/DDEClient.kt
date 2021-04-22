package roslesinforg.porokhin.geobaseeditor.service

import com.pretty_tools.dde.client.DDEClientConversation
import com.pretty_tools.dde.server.DDEServer
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController


class DDEClient(private val controller: GeoBaseEditorController) {
    private val logger = logger()
    var server: DDEServer? = null
    var client: DDEClientConversation? = null

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
                return "1001"
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