package roslesinforg.porokhin.geobaseeditor.view.constructors

import CLASS_SELECT_BTN_ACTIVE
import javafx.scene.control.ToggleButton
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import roslesinforg.porokhin.geobaseeditor.view.MainView
import tornadofx.action
import tornadofx.enableWhen

class SelectionsF10Constructor: ViewConstructor<MainView> {
    override fun construct(view: MainView) {
        with(view){
            selectionsF10.addAll(listOf(
                selectBtn1 to selection1,
                selectBtn2 to selection2,
                selectBtn3 to selection3,
                selectBtn4 to selection4,
                selectBtn5 to selection5,
                selectBtn6 to selection6,
                selectBtn7 to selection7,
                selectBtn8 to selection8,
                selectBtn9 to selection9,
                selectBtn10 to selection10,
            ))

            selectionsF10.forEach { pair ->
                val btn = pair.first
                btn.apply {
                    action {
                        if (isSelected){
                            pair.second.isVisible = true
                            btn.styleClass.add(CLASS_SELECT_BTN_ACTIVE)
                            selectionsF10.forEach { if(it.first != btn) {
                                it.first.deselect()
                                if (it.second != pair.second) it.second.isVisible = false
                            }}
                        } else{
                            pair.second.isVisible = false
                            btn.styleClass.remove(CLASS_SELECT_BTN_ACTIVE)
                        }
                    }

                }
                btn.enableWhen { enableFieldsTrigger }

                btn.setOnDragDetected {
                    selectionsF10.forEach { it.first.deselect() }
                    btn.startDragAndDrop(TransferMode.MOVE).apply {
                        dragView = pair.second.snapshot(null, null)
                        setContent(ClipboardContent().apply { putString(selectionsF10.indexOf(btn).toString()) })
                    }

                }
                btn.setOnDragDropped {
                    val dragboard = it.dragboard
                    if (!dragboard.hasString()) return@setOnDragDropped
                    val oldIdx = dragboard.string.toInt()
                    val newIdx = selectionsF10.indexOf(btn)
                    val elements = model.item.field10.forestElements
                    val replacingElement = elements[oldIdx]
                    elements.removeAt(oldIdx)
                    elements.add(newIdx, replacingElement)
                    it.isDropCompleted = true
                    it.consume()
                    model.bindF10()
                }
                btn.setOnDragOver {
                    if (selectionsF10.indexOf(btn) != it.dragboard.string.toInt()){
                        it.acceptTransferModes(TransferMode.MOVE)
                        it.consume()
                    }

                }
                btn.setOnDragEntered {
                    selectionsF10[selectionsF10.indexOf(btn)].second.isVisible = true
                }
                btn.setOnDragExited {
                    selectionsF10[selectionsF10.indexOf(btn)].second.isVisible = false
                }

            }
        }
    }

    private fun ToggleButton.deselect(){
        isSelected = false
        styleClass.remove(CLASS_SELECT_BTN_ACTIVE)
    }
}