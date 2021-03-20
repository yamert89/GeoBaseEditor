package roslesinforg.porokhin.geobaseeditor.service

import com.pretty_tools.dde.server.DDEServer
import org.apache.logging.log4j.kotlin.logger


class DDEClient {
    private val logger = logger()
    var server: DDEServer? = null

    fun initiate(){
        server = object : DDEServer("geobaseeditor"){
            override fun onPoke(topic: String?, item: String?, data: String?): Boolean {
                when(topic){
                    "areaselection" -> {
                        when(item){
                            "selection" -> logger.debug("$data area selected")
                            else -> logger.debug("unknown item")
                        }
                    }
                    else -> logger.debug("unknown topic")
                }
                return true
            }

            override fun onConnected(topic: String?, hconv: Long) {
                logger.debug("connected")
                super.onConnected(topic, hconv)
            }

            override fun start() {
                logger.debug("start")
                super.start()
            }
        }
        server!!.start()
        logger.debug("DDE Server started")
    }
}