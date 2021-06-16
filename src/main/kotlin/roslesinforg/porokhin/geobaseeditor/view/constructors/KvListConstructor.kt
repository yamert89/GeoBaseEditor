package roslesinforg.porokhin.geobaseeditor.view.constructors

import CLASS_SELECT_BTN_ACTIVE
import javafx.beans.property.Property
import javafx.scene.control.ButtonType
import javafx.scene.control.TableRow
import javafx.scene.effect.DropShadow
import javafx.scene.input.*
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import roslesinforg.porokhin.areatypes.Area
import roslesinforg.porokhin.areatypes.GeneralTypes
import roslesinforg.porokhin.geobaseeditor.view.MainView
import tornadofx.*

class KvListConstructor: ViewConstructor<MainView> {
    override fun construct(view: MainView) {
        with(view){
            borderPane.apply {
                left {
                    kv_list = tableview(controller.areas){

                        model.rebindOnChange(this){ area ->
                            if (area == null) return@rebindOnChange

                            if (!enableFieldsTrigger.value) {
                                enableFieldsTrigger.value = true
                            } else controller.paint()

                            val message = validationHelper.hRangsAndProportionsChecking( listOf(
                                fHrang1 to fProportion1,
                                fHrang2 to fProportion2,
                                fHrang3 to fProportion3,
                                fHrang4 to fProportion4,
                                fHrang5 to fProportion5,
                                fHrang6 to fProportion6,
                                fHrang7 to fProportion7,
                                fHrang8 to fProportion8,
                                fHrang9 to fProportion9,
                                fHrang10 to fProportion10)
                            )
                            var notice = ""
                            if (!validationContext.validate()) notice += "Имеются некорректно заполненные поля"
                            if (message.isNotEmpty()) notice += "\n$message"
                            if (notice.isNotEmpty()) {
                                error("Внимание", notice, ButtonType.OK, title = "Ошибка")
                                validationHelper.failedAreas.add(item.id)
                            } else validationHelper.failedAreas.remove(item.id)

                            selectionsF10.forEach {
                                it.second.isVisible = false
                                it.first.styleClass.remove(CLASS_SELECT_BTN_ACTIVE)
                            }

                            commit()
                            item = area
                            println("Selection kv: ${item.kv} vid: ${item.field1.number}")

                        }

                        shortcut(KeyCodeCombination(KeyCode.SUBTRACT)){
                            val selected = kv_list.selectionModel.selectedItem
                            controller.removeArea(selected)
                        }
                        shortcut(KeyCodeCombination(KeyCode.ADD)){
                            logger.trace("selected item: ${kv_list.selectedItem}")
                            if (kv_list.selectedItem == null) return@shortcut
                            logger.trace("area copy")
                            controller.copyArea(kv_list.selectedItem!!)
                            kv_list.selectionModel.select(kv_list.selectionModel.selectedIndex + 1)
                        }
                        shortcut(KeyCodeCombination(KeyCode.ADD, KeyCombination.SHIFT_DOWN)){
                            logger.trace("add empty item: ${kv_list.selectedItem}")
                            if (kv_list.selectedItem == null) return@shortcut
                            controller.newEmptyArea(kv_list.selectedItem!!)
                            kv_list.selectionModel.select(kv_list.selectionModel.selectedIndex + 1)
                        }

                        isEditable = true
                        prefWidth = 130.0

                        readonlyColumn("Кв", Area::kv){
                            style{
                                textAlignment = TextAlignment.CENTER
                                fontWeight = FontWeight.BOLD
                            }
                        }
                        column<Area, Int>("Выд"){
                            it.value.field1.number.toProperty() as Property<Int>
                        }.makeEditable().apply {
                            this.setOnEditCommit {
                                model.field1Model.numberProperty.value = it.newValue
                            }
                        }
                        column("ЦНЛ", Area::categoryProtection){
                            style{ textAlignment = TextAlignment.CENTER}
                            setOnEditCommit {
                                if (GeneralTypes.categoryProtection[it.newValue] == it.newValue.toString()) {
                                    error(header = "Ошибка", content = "Некорректное значение")
                                    editModel.rollbackSelected()
                                }
                            }
                        }.makeEditable()

                        setRowFactory {
                            val row = TableRow<Area>()
                            val selectableEffect = DropShadow(3.0,  0.0, 0.4, c("#000", 0.9))
                            val fakeEffect = DropShadow(0.0, c("#000", 0.0))
                            val format = DataFormat.lookupMimeType("application/x-java-serialized-object") ?: DataFormat("application/x-java-serialized-object")
                            row.setOnDragDetected {
                                val index = row.index
                                if(row.isEmpty) return@setOnDragDetected
                                val dragboard = row.startDragAndDrop(TransferMode.MOVE)
                                dragboard.dragView = row.snapshot(null, null)
                                val cc = ClipboardContent()
                                cc[format] = index
                                dragboard.setContent(cc)
                                it.consume()
                                println("drag detected")
                            }

                            row.setOnDragOver {
                                val db = it.dragboard
                                if (!db.hasContent(format)) return@setOnDragOver
                                if (row.index != db.getContent(format) as Int){
                                    it.acceptTransferModes(TransferMode.COPY, TransferMode.MOVE)
                                    it.consume()
                                }
                            }
                            row.setOnDragDropped {
                                val db = it.dragboard
                                if(!db.hasContent(format)) return@setOnDragDropped
                                val dragIndex: Int = db.getContent(format) as Int
                                val area = controller.areas.removeAt(dragIndex)
                                val dropIndex = if (row.isEmpty) kv_list.items.size else
                                    if (row.index < dragIndex) row.index else row.index - 1
                                controller.areas.add(dropIndex, area)
                                it.isDropCompleted = true

                                selectionModel.select(dropIndex)
                                it.consume()

                            }
                            row.setOnDragEntered {
                                row.style {
                                    effect = selectableEffect
                                }
                            }
                            row.setOnDragExited {
                                row.style {
                                    effect = fakeEffect
                                }
                            }
                            row
                        }
                        smartResize()
                    }
                }
            }
        }
    }
}