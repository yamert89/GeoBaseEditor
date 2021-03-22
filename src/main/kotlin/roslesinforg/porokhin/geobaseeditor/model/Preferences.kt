package roslesinforg.porokhin.geobaseeditor.model

import FILTERING
import javafx.beans.property.SimpleBooleanProperty
import roslesinforg.porokhin.geobaseeditor.view.MainView
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.util.*

object Preferences { //todo replace to jar
    private val file = File("${System.getProperty("user.home")}/geobaseeditor.prefs")
    private val props = Properties()
    val filtering = SimpleBooleanProperty()


    init {
        if (file.exists()){
            with(props){
                load(FileReader(file))
                filtering.value = getProperty(FILTERING, "true").toBoolean() == true
            }
        } else file.createNewFile()
    }

    fun savePrefs(){
        props[FILTERING] = filtering.toString()
        props.store(FileOutputStream(file), "GeoBaseEditor user preferences")
    }
}