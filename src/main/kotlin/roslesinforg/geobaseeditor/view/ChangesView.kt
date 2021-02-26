package roslesinforg.geobaseeditor.view

import javafx.beans.property.Property
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ContentDisplay
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Border
import javafx.scene.layout.BorderStroke
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
import java.awt.Color
import java.awt.Dimension
import javax.swing.UIManager.getFont

class ChangesView : View("My View") {
    val controller = find(GeoBaseEditorController::class)
    override val root = flowpane {
        useMaxSize = true
        tableview(controller.diff()){
            //prefWidth = this.width
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
       /* text("text"){
            maxHeight = 30.0
            background = Background(BackgroundFill(Paint.valueOf("#ff9f15"), null, null))
            *//*style { backgroundColor += c(333, 222, 444)
                borderWidth += box(tornadofx.Dimension(4.0, tornadofx.Dimension.LinearUnits.px))
            }*//*
        }
        textflow{
            maxHeight = 30.0
            background = Background(BackgroundFill(Paint.valueOf("#5f5f5f"), null, null))
            text("1")
            text("2")
            text("3")
        }*/

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
                        if (sb.isNotEmpty()) texts.add(Text(sb.toString()).apply { maxHeight = tableRow?.height ?: 15.0  })
                        sb.clear()
                        texts.add(Text(value.substring(pair.first, pair.second)).apply {
                            fill = c(230, 145, 124)
                            font = font("Verdana")
                            stroke  = javafx.scene.paint.Color.GREEN
                            style = "-fx-border-width: 2; -fx-border-color: #000;"
                            /*style(true){
                                borderWidth += box(tornadofx.Dimension(3.0, tornadofx.Dimension.LinearUnits.px))
                                borderColor += box(javafx.scene.paint.Color.RED)
                            }*/
                            //prefHeight = 10.0
                            //maxHeight = tableRow?.height ?: 15.0
                        })
                    }
                    graphic = TextFlow(*texts.toTypedArray()).apply {
                       /* height = tableRow?.height ?: 15.0*/ //fixme - illegal access exc
                    }
                    //text = item.value
                    contentDisplay = ContentDisplay.GRAPHIC_ONLY
                    maxHeight = tableRow?.height ?: 15.0
                }
            }
        }

    }


}

