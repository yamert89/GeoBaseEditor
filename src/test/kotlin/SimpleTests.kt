import com.pretty_tools.dde.client.DDEClientConversation
import tornadofx.isFloat

fun main() {
    dde()
    //val str = "2.5f"
    //print(str.isFloat())
}
fun dde(){
    val client = DDEClientConversation()
    client.connect("MapInfo Professional", "System")
    print(client.request("Version"))
}