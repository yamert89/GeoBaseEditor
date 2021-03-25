package roslesinforg.porokhin.geobaseeditor.model

import AUTOSELECT
import FILTERING
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


    init {
        if (file.exists()){
            with(props){
                load(FileReader(file))
                filtering.value = getProperty(FILTERING, "true").toBoolean()
                autoSelect.value = getProperty(AUTOSELECT, "true").toBoolean()
            }
        } else file.createNewFile()
    }

    fun savePrefs(){
        props[FILTERING] = filtering.value.toString()
        props[AUTOSELECT] = autoSelect.value.toString()
        props.store(FileOutputStream(file), "GeoBaseEditor user preferences")
    }
}