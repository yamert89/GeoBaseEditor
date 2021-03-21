package roslesinforg.porokhin.geobaseeditor.service

import com.pretty_tools.dde.server.DDEServer
import org.apache.logging.log4j.kotlin.logger
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController


class DDEClient(private val controller: GeoBaseEditorController) {
    private val logger = logger()
    var server: DDEServer? = null

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
                            else -> logger.debug("unknown item")
                        }
                    }
                    else -> logger.debug("unknown topic")
                }
                return true
            }

            override fun onConnected(topic: String?, hconv: Long) {
                logger.debug("DDE input connection with number = $hconv")
                super.onConnected(topic, hconv)
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
}