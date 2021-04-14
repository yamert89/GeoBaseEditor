import javafx.scene.Parent
import tornadofx.*
import tornadofx.App

fun main() {
    launch<TestApp>()
}

class TestApp: App(TestView::class)

class TestView : View("ddd"){
    override val root = stackpane {
        prefWidth = 400.0
        prefHeight = 400.0
            combobox("def".toProperty(), mutableListOf("111", "2222", "3333").toObservable())
    }

    init {
        print("app launched")
    }

}