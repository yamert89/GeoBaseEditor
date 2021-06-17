package roslesinforg.porokhin.geobaseeditor.view.constructors

import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.geobaseeditor.view.MainView
import tornadofx.c
import tornadofx.smartResize
import tornadofx.style
import java.io.File
class DebugModeConstructor: ViewConstructor<MainView> {
    override fun construct(view: MainView) {
        with(view){
            val file = File(this.javaClass.classLoader.getResource("0309").toURI())
            runAsync {
                controller.read(file)
                fProgress.isVisible = false
            } ui{
                val loc = controller.location!!
                val forestry = GeneralTypes.forestries[loc.forestry.toInt()]
                with(loc){
                    fGir.apply {
                        text = forestry?.sub?.get(subForestry.toInt()) ?: ""
                        isEditable = false
                        style{
                            backgroundColor += c(0, 0, 0, 0.0)
                        }
                    }
                }

                kv_list.items = controller.areas
                kv_list.smartResize()

                val path = file.absolutePath.let { if (it.length > 50) "...${it.substring(it.lastIndex - 10, it.length)}" else it}
                flog("Открыт файл ${path}.  Лесничество: ${forestry?.name ?: loc.forestry} , Участок: ${forestry?.sub?.get(loc.subForestry.toInt()) ?: loc.subForestry}")
                kv_list.selectionModel.select(0)
            }
        }
    }
}