package roslesinforg.porokhin.geobaseeditor.view

import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import roslesinforg.porokhin.geobaseeditor.view.constructors.ViewConstructor
import roslesinforg.porokhin.geobaseeditor.view.constructors.ViewDeconstructor
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

    fun EventTarget.loadProgressBar(width: Double = 50.0, height: Double = 50.0): ImageView {
        return imageview(Image("/gui/loading.gif")){
            fitHeight = height
            fitWidth = width
        }
    }

    @Suppress("unchecked_cast")
    fun <V: GeoBaseEditorView>construct(constructor: ViewConstructor<V>){
        constructor.construct(this as V)
    }

    @Suppress("unchecked_cast")
    fun <V: GeoBaseEditorView>deconstruct(constructor: ViewDeconstructor<V>){
        constructor.deconstruct(this as V)
    }
}