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
        tableview(listOf(
            Model(1, 1, 0.3), Model(2, 2, 0.9)
        ).toObservable()) {
            column("first", Model::first) {
                cellFormat {
                    style{
                        backgroundColor += c("#ff33ff")
                    }
                }
                makeEditable()
            }
            column("third", Model::third).makeEditable().useProgressBar()
            column("dd", Model::third)
            readonlyColumn("second", Model::second) {
                //makeEditable()
                cellFormat {
                    /*style(true){
                        backgroundColor += c(0, 0, 0, 0.2)
                    }*/
                }
            }

        }
    }

    init {
        print("app launched")
    }

}
class Model(var first: Int, var second: Int, var third: Double)