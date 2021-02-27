package roslesinforg.geobaseeditor.view

import com.sun.javafx.property.adapter.PropertyDescriptor
import javafx.beans.property.Property
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ContentDisplay
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
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
import tornadofx.adapters.toTornadoFXFeatures

class ChangesView : View("My View") {
    val controller = find(GeoBaseEditorController::class)
    override val root = flowpane {
        useMaxSize = true
        tableview(controller.diff()){
            prefWidth = 400.0
            column<ComparedPair, String>("<>"){
                it.tableColumn.maxWidth = 40.0
                it.value.first.type.toToken().toProperty()
            }
            column("До", ComparedLine::class){
                cellValueFactory = Callback { it.value.first.toProperty() }
                cellFactory = ChangingCellFactory(16.0) //todo calculate rowHeight
            }
            column<ComparedPair, String>("<>"){
                it.tableColumn.maxWidth = 40.0
                it.value.second.type.toToken().toProperty()
            }
            column("После", ComparedLine::class){
                cellValueFactory = Callback { it.value.second.toProperty() }
                cellFactory = ChangingCellFactory(16.0)  //todo calculate rowHeight


            }
        }
    }

    class ChangingCellFactory(private val rowHeight: Double): Callback<TableColumn<ComparedPair, ComparedLine>, TableCell<ComparedPair, ComparedLine>>{
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
                    if (item.changedIndexes.isEmpty()){
                        contentDisplay = ContentDisplay.TEXT_ONLY
                        text = item.value
                        return
                    }
                    val texts = mutableListOf<Text>()
                    val sb = StringBuilder()
                    val valueArr = value.toCharArray()
                    var lastOperatedIdx = 0
                    item.changedIndexes.forEach { pair ->
                        for (index in lastOperatedIdx..valueArr.lastIndex){
                            if (index !in pair.first..pair.second) sb.append(valueArr[index])
                            else{
                                lastOperatedIdx = index
                                break
                            }
                        }
                        if (sb.isNotEmpty()) texts.add(Text(sb.toString()))
                        sb.clear()
                        texts.add(Text(value.substring(pair.first, pair.second)).apply {
                            fill = Color.RED
                            font = font("Verdana")
                        })
                    }
                    graphic = TextFlow(*texts.toTypedArray()).apply {
                        style {
                            prefHeight = Dimension(rowHeight, tornadofx.Dimension.LinearUnits.px)
                        }
                        onHover {
                            println("${this.height} | ${this.prefHeight} | ${this.maxHeight} || ${this.minHeight}")
                        }
                    }
                    contentDisplay = ContentDisplay.GRAPHIC_ONLY

                }
            }
        }

    }


}

