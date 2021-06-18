package roslesinforg.porokhin.geobaseeditor.view.constructors

import javafx.embed.swing.SwingFXUtils
import javafx.scene.control.ButtonType
import javafx.scene.control.ContentDisplay
import javafx.stage.FileChooser
import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.geobaseeditor.model.GeoBaseEditorPreferences
import roslesinforg.porokhin.geobaseeditor.view.ChangesView
import roslesinforg.porokhin.geobaseeditor.view.MainView
import roslesinforg.porokhin.geobaseeditor.view.PreferenceView
import roslesinforg.porokhin.rawxlsconverter.RawToXLSConverterView
import tornadofx.*
import java.io.File
import javax.imageio.ImageIO

class TopPaneConstructor: ViewConstructor<MainView> {
    override fun construct(view: MainView) {
        with(view){
            buttonBar.apply {
                addNewButton("info.png", "О программе"){ //todo Replace with resources
                    information("Редкатор Базы v. ${updateManager.currentVersion.version}", """
                    Добавить выдел - NUM+
                    Скопировать выдел - CTRL + NUM+
                    Удалить выдел - NUM-
                    
                    Порохин А. А. 2021 г.
                """.trimIndent(), ButtonType.OK, title = "О программе" )
                }
                addNewButton("prefs.png", "Настройки"){
                    openInternalWindow(PreferenceView::class)
                }

                addNewButton("Excel.png", "Сохранить в MS Excel"){
                    find<RawToXLSConverterView>(params = mapOf(
                        "initAreas" to controller.areas,
                        "initOutputPath" to controller.inputFilePath)).openWindow(owner = null)
                }.apply { enableWhen { enableFieldsTrigger } }
                addNewButton("screen.png", "Скриншот карточки"){
                    val file = chooseFile("Сохарнить GIF", arrayOf(FileChooser.ExtensionFilter("Файлы изображений", "*.gif", "*.jpg", "*.bmp", "*.tiff")), mode = FileChooserMode.Save, owner = primaryStage)
                    if (file.isEmpty()) return@addNewButton
                    val image = cardLayout.snapshot(null, null)
                    //val path = "${kv_list.selectionModel.selectedItem.id}.gif"
                    val ext = if (!file[0].path.matches(".+\\.gif".toRegex())) ".gif" else ""
                    val path = "${file[0].path}$ext"
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "GIF", File(path))
                    flog("Скриншот сохранен в $path")
                }.apply { enableWhen { enableFieldsTrigger } }
                addNewButton("change.png", "Изменения"){
                    model.replaceEmptyFields(model.item)
                    model.commit()
                    openInternalWindow(ChangesView::class, Scope())
                }.apply { enableWhen { enableFieldsTrigger } }
                addNewButton("save.png", "Сохранить"){
                    with(validationHelper.failedAreas){
                        var allow = true
                        if (isNotEmpty()) {
                            allow = false
                            error("Внимание", "Найдены ошибки в выделах ${this.joinToString()}")
                        }
                        else if (controller.squareIsNotEqual()) {
                            allow = false
                            confirm("Внимание", "Площади развязаны. Сохранить?"){
                                allow = true
                            }
                        }
                        if (!allow) return@addNewButton

                        kv_list.editModel.commit()
                        val file = chooseFile(
                            "Сохранить",
                            emptyArray(),
                            mode = FileChooserMode.Save,
                            owner = primaryStage
                        )
                        if (file.isEmpty()) return@addNewButton
                        model.replaceEmptyFields(model.item)
                        controller.writeToRawFile(file[0])
                        flog("Файл сохранен: ${file[0].absolutePath}")

                    }
                }.apply { enableWhen { enableFieldsTrigger } }
                addNewButton("add.png", "Открыть"){
                    val files = chooseFile(
                        "Выберите файл",
                        owner = primaryStage,
                        mode = FileChooserMode.Single,
                        filters = arrayOf()
                    )
                    if (files.isEmpty()) return@addNewButton
                    fProgress.isVisible = true
                    runAsync {
                        controller.read(files[0])
                        fProgress.isVisible = false
                    } ui{
                        val loc = controller.location
                        if (loc != null) {
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
                            selectionTable.items = controller.areas

                            val path = files[0].absolutePath.let { if (it.length > 50) "...${it.substring(it.lastIndex - 10, it.length)}" else it}
                            flog("Открыт файл ${path}.  Лесничество: ${forestry?.name ?: loc.forestry} , Участок: ${forestry?.sub?.get(loc.subForestry.toInt()) ?: loc.subForestry}")
                            kv_list.selectionModel.select(0)
                            if (GeoBaseEditorPreferences.squareControl.value) openStrictAreaView()
                        }
                    }
                }
            }
            topPane.apply {
                style{
                    backgroundColor += c("#696966")
                }
                togglebutton(selectFirst = false){
                    tooltip("Связь с MapInfo")
                    maxHeight = 20.0
                    background = null
                    hboxConstraints {
                        marginLeft = 300.0
                    }
                    contentDisplay = ContentDisplay.GRAPHIC_ONLY
                    graphic = getImageResource(20.0, 40.0, "toggle-off_s.png")
                    action {
                        logger.debug("click")
                        if (this.isSelected) {
                            controller.startDDESession()
                            graphic = getImageResource(20.0, 40.0, "toggle-on_s.png")
                            flog("Открыт канал связи с MapInfo")
                        }
                        else {
                            controller.stopDDESession()
                            graphic = getImageResource(20.0, 40.0, "toggle-off_s.png")
                            flog("Канал связи с MapInfo закрыт")
                        }
                    }
                    enableWhen { enableFieldsTrigger }
                }
            }
        }
    }
}