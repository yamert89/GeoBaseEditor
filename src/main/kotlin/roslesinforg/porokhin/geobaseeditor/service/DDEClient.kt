package roslesinforg.porokhin.geobaseeditor.service

import com.pretty_tools.dde.server.DDEServer
import org.apache.logging.log4j.kotlin.logger


class DDEClient {
    private val logger = logger()

    fun initiate(){
        val server = object : DDEServer("GeoBaseEditor"){
            override fun onPoke(topic: String?, item: String?, data: String?): Boolean {
                when(topic){
                    "AreaSelection" -> {

                        when(item){
                            "selection" -> logger.debug("$data area selected")
                            else -> logger.debug("unknown item")
                        }
                    }
                    else -> logger.debug("unknown topic")
                }
                return true
            }

            override fun start() {
                super.start()
            }
        }
        logger.debug("DDE Server started")
    }
}