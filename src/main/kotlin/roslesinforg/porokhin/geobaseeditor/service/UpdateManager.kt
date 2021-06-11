package roslesinforg.porokhin.geobaseeditor.service

import com.charleskorn.kaml.Yaml
import javafx.application.Platform
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import org.apache.logging.log4j.kotlin.logger
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.Exception

class UpdateManager(private val path: Path) {
    private val logger = logger()

    fun checkVersion(): Version{
        try {
            val parser = Yaml.default
            val lastVersion = parser.decodeFromString<Version>(path.resolve("version.yml").toFile().readText())
            val currentVersion = parser.decodeFromString<Version>(javaClass.classLoader.getResource("currentversion.yml")!!.readText())
            if (currentVersion.api < lastVersion.api) return lastVersion
        }catch (e: Exception){
            logger.warn("Version checking failed")
        }
        return Version()
    }

    fun update(){
        //Files.copy(path.resolve("GeoBaseEditor.jar"), )
        //ProcessBuilder("cmd.exe", "update.bat").start()
        try {
            Runtime.getRuntime().exec("cmd /c update.bat", null, null)
            logger.trace("updated")
            Platform.exit()
        }catch (e: Exception){e.printStackTrace()}

    }
}
@Serializable
class Version{
    val api = 0
    val version = ""
    val history = mapOf<String, String>()
}