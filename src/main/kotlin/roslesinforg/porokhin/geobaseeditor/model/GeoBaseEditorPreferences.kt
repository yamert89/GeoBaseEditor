package roslesinforg.porokhin.geobaseeditor.model

import AUTOSELECT
import FILTERING
import SQUARE_CONTROL
import javafx.beans.property.SimpleBooleanProperty
import roslesinforg.porokhin.geobaseeditor.view.MainView
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.util.*

object GeoBaseEditorPreferences {
    private val file = File("${System.getProperty("user.home")}/geobaseeditor.prefs")
    private val props = Properties()
    val filtering = SimpleBooleanProperty(true)
    val autoSelect = SimpleBooleanProperty(true)
    val squareControl = SimpleBooleanProperty(false)


    init {
        if (file.exists()){
            with(props){
                load(FileReader(file))
                filtering.value = getProperty(FILTERING, "true").toBoolean()
                autoSelect.value = getProperty(AUTOSELECT, "true").toBoolean()
                squareControl.value = getProperty(SQUARE_CONTROL, "false").toBoolean()
            }
        } else file.createNewFile()
    }

    fun savePrefs(){
        props[FILTERING] = filtering.value.toString()
        props[AUTOSELECT] = autoSelect.value.toString()
        props[SQUARE_CONTROL] = squareControl.value.toString()
        props.store(FileOutputStream(file), "GeoBaseEditor user preferences")
    }
}