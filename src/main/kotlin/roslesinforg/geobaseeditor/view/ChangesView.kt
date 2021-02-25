package roslesinforg.geobaseeditor.view

import javafx.beans.property.Property
import javafx.scene.control.ContentDisplay
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.text.Font
import javafx.scene.text.Font.font
import javafx.scene.text.Text
import javafx.scene.text.TextFlow
import javafx.util.Callback
import javafx.util.StringConverter
import roslesinforg.geobaseeditor.GeoBaseEditorController
import roslesinforg.geobaseeditor.view.viewmodels.AreaModel
import roslesinforg.porokhin.filecomparator.StringResult.Companion.toToken
import roslesinforg.porokhin.filecomparator.service.ComparedLine
import roslesinforg.porokhin.filecomparator.service.ComparedPair
import roslesinforg.porokhin.filecomparator.service.LineType
import tornadofx.*
import javax.swing.UIManager.getFont

class ChangesView : View("My View") {
    val controller = find(GeoBaseEditorController::class)
    override val root = anchorpane {
        tableview(controller.diff()){
            smartResize()
            column<ComparedPair, String>("<>"){
                it.value.first.type.toToken().toProperty()
            }
            column("До", ComparedLine::class){
                cellValueFactory = Callback { it.value.first.toProperty() }
                cellFactory = ChangingCellFactory()
            }
            column<ComparedPair, String>("<>"){
                it.value.second.type.toToken().toProperty()
            }
            column("После", ComparedLine::class){
                cellValueFactory = Callback { it.value.second.toProperty() }
                cellFactory = ChangingCellFactory()
            }
        }

    }

    class ChangingCellFactory: Callback<TableColumn<ComparedPair, ComparedLine>, TableCell<ComparedPair, ComparedLine>>{
        override fun call(param: TableColumn<ComparedPair, ComparedLine>?): TableCell<ComparedPair, ComparedLine> {
           return object : TableCell<ComparedPair, ComparedLine>(){
                override fun updateItem(item: ComparedLine?, empty: Boolean) {
                    super.updateItem(item, empty)
                    if( item == null) {
                        text = null
                        graphic = null
                        return
                    }
                    val value = item.value
                    // val changedTexts = item.changedIndexes.map { pair -> Text(value.substring(pair.first, pair.second)) }
                    if (item.changedIndexes.isEmpty()){
                        contentDisplay = ContentDisplay.TEXT_ONLY
                        text = item.value
                        return
                    }
                    val texts = mutableListOf<Text>()
                    val sb = StringBuilder()
                    val valueArr = value.toCharArray()
                    item.changedIndexes.forEach { pair ->
                        valueArr.forEachIndexed{ index, char ->
                            if (index !in pair.first..pair.second) sb.append(char)
                        }
                        if (sb.isNotEmpty()) texts.add(Text(sb.toString()).apply { height = tableRow?.height ?: 15.0  })
                        sb.clear()
                        texts.add(Text(value.substring(pair.first, pair.second)).apply {
                            fill = c(230, 145, 124)
                            font = font("Verdana")
                            height = tableRow?.height ?: 15.0
                        })
                    }
                    graphic = TextFlow(*texts.toTypedArray()).apply {
                       /* height = tableRow?.height ?: 15.0*/ //fixme - illegal access exc
                    }
                    //text = item.value
                    contentDisplay = ContentDisplay.GRAPHIC_ONLY
                    height = tableRow?.height ?: 15.0
                }
            }
        }

    }


}

