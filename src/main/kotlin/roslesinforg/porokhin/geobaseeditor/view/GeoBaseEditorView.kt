package roslesinforg.porokhin.geobaseeditor.view

import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import tornadofx.*

abstract class GeoBaseEditorView(title: String): View(title) {

    fun ButtonBar.addNewButton(picture: String, tooltip: String, action: () -> Unit): Button {
        ButtonBar.setButtonUniformSize(this, false)
        val btn = button{
            action {
                action()
            }
            style{
                background = null
                maxWidth = Dimension(26.0, Dimension.LinearUnits.px)
            }
            onHover {
                background = Background(BackgroundFill(c(250, 250, 0, 0.3), CornerRadii(4.0), null))
            }
            setOnMouseExited {
                background = null
            }
            tooltip(tooltip)
            graphic = getImageResource(20.0, 20.0, picture)
        }
        return btn
    }

    fun getImageResource(height: Double, width: Double, path: String): javafx.scene.image.ImageView = this.resources.imageview("/gui/$path").apply {
        fitHeight = height
        fitWidth = width
    }
}