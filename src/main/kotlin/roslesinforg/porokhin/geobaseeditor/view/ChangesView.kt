package roslesinforg.porokhin.geobaseeditor.view

import com.sun.javafx.property.adapter.PropertyDescriptor
import javafx.beans.property.Property
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.NodeOrientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.Font
import javafx.scene.text.Font.font
import javafx.scene.text.Text
import javafx.scene.text.TextFlow
import javafx.util.Callback
import javafx.util.StringConverter
import roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController
import roslesinforg.porokhin.geobaseeditor.view.viewmodels.AreaModel
import roslesinforg.porokhin.filecomparator.ComparingResult.Companion.toToken
import roslesinforg.porokhin.filecomparator.FileComparator
import roslesinforg.porokhin.filecomparator.MSWordResult
import roslesinforg.porokhin.filecomparator.service.ComparedLine
import roslesinforg.porokhin.filecomparator.service.ComparedPair
import roslesinforg.porokhin.filecomparator.service.LineType
import tornadofx.*
import tornadofx.adapters.toTornadoFXFeatures
import java.io.File
import java.io.FileOutputStream
import org.apache.logging.log4j.kotlin.logger

class ChangesView : GeoBaseEditorView("My View") { //todo test after replace areas
    private val logger = logger()
    val controller = find(roslesinforg.porokhin.geobaseeditor.GeoBaseEditorController::class, MainView.AppScope)
    var table: TableView<ComparedPair>? = null
    val difResult: MutableList<ComparedPair> = mutableListOf()
    var progress: ImageView? = null
    val task = runAsync {
        val res = controller.diff()
        logger.debug("End task")
        res
    } ui{
        fillTable(it)
        progress!!.isVisible = false
    }

    override val root = stackpane {

        flowpane {
            useMaxSize = true
            padding = Insets(3.0, 0.0, 3.0, 0.0)
            val tNumber = "Строка"
            val tBefore = "До"
            val tAfter = "После"
            buttonbar{
                maxHeight = 20.0
                buttonMinWidth = 26.0
                prefWidth = 450.0
                nodeOrientation = NodeOrientation.RIGHT_TO_LEFT
                style{
                    backgroundColor += c("#696966")
                }
                val word = addNewButton("Word.png", "Экспортировать в MS Word"){
                    val file = chooseFile("Сохранение", filters = emptyArray(), mode = FileChooserMode.Save)
                    if (file.isEmpty()) return@addNewButton
                    val title = "Лесничество: ${controller.location?.forestry},  участок: ${controller.location?.subForestry}"
                    //val path = "D:/my/wordout.docx"
                    val path = file[0].path + ".docx"
                    val fos = FileOutputStream(path)
                    MSWordResult(difResult, title, tableColLineNumber = tNumber, tableColLine1 = tBefore, tableColLine2 = tAfter).get().write(fos)
                    fos.flush()
                    fos.close()
                    logger.debug("docx file created")
                }
                word.apply {
                    enableWhen { difResult.isNotEmpty().toProperty() }
                }

            }
            //if (difResult.isEmpty()) information("", "Нет изменений", ButtonType.OK, owner = primaryStage, title = ""){}
            table = tableview(emptyList<ComparedPair>().toObservable()){
                prefWidth = 450.0
                smartResize()
                column(tNumber, ComparedPair::lineNumber)
                column<ComparedPair, String>("<>"){
                    it.tableColumn.maxWidth = 36.0
                    it.value.first.type.toToken().toProperty()
                }
                column(tBefore, ComparedLine::class){
                    useMaxSize = true
                    cellValueFactory = Callback { it.value.first.toProperty() }
                    cellFactory = ChangingCellFactory(16.0) //todo calculate rowHeight
                }
                column<ComparedPair, String>("<>"){
                    it.tableColumn.maxWidth = 36.0
                    it.value.second.type.toToken().toProperty()
                }
                column(tAfter, ComparedLine::class){
                    useMaxSize = true
                    cellValueFactory = Callback { it.value.second.toProperty() }
                    cellFactory = ChangingCellFactory(16.0)  //todo calculate rowHeight
                }
            }
            logger.debug("End construct")
        }
        progress = this.loadProgressBar()
    }

    private fun fillTable(difResult: ObservableList<ComparedPair>){
        table!!.items = difResult
        this.difResult.addAll(difResult)
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
                    var lastOperatedIdx = -1
                   /* item.changedIndexes.forEach { pair ->
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
                    }*/

                    valueArr.forEachIndexed { index, c ->
                        if (index > lastOperatedIdx){
                            val cIdx = item.changedIndexes.find { index in it.first..it.second }
                            if (cIdx == null){
                                sb.append(c)
                            }else{
                                if (sb.isNotEmpty()) texts.add(Text(sb.toString()))
                                sb.clear()
                                texts.add(Text(value.substring(cIdx.first, cIdx.second + 1)).apply {
                                    fill = Color.RED
                                    font = font("Verdana")
                                })
                                lastOperatedIdx = cIdx.second
                            }
                        }

                    }
                    if (sb.isNotEmpty()) texts.add(Text(sb.toString()))


                    graphic = TextFlow(*texts.toTypedArray()).apply {
                        style {
                            prefHeight = Dimension(rowHeight, tornadofx.Dimension.LinearUnits.px)
                        }
                    }
                    contentDisplay = ContentDisplay.GRAPHIC_ONLY

                }
            }
        }

    }


}

