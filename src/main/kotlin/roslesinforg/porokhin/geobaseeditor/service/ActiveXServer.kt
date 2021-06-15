package roslesinforg.porokhin.geobaseeditor.service

import com.jacob.activeX.ActiveXComponent
import com.jacob.com.Dispatch
import com.jacob.com.Variant

class ActiveXServer {
    fun connect(){
        try {
            val xl = ActiveXComponent("MapInfo.Application")
            val xlo: Dispatch = xl.getObject()
            try {
                System.out.println("version=" + xl.getProperty("Version"))
                println("version=" + Dispatch.get(xlo, "Version"))
                Dispatch.call(xlo, "Open Table \"d:\\my\\!!test\\VID03_POL\"")
                xl.invoke("Open Table \"d:\\my\\!!test\\VID03_POL\"")
                /*xl.setProperty("Visible", Variant(true))
                val workbooks: Dispatch= xl.getProperty("Workbooks").toDispatch()
                val workbook: Dispatch = Dispatch.get(workbooks, "Add").toDispatch()
                val sheet: Dispatch = Dispatch.get(workbook, "ActiveSheet").toDispatch()
                val a1: Dispatch =
                    Dispatch.invoke(sheet, "Range", Dispatch.Get, arrayOf<Any>("A1"), IntArray(1)).toDispatch()
                val a2: Dispatch =
                    Dispatch.invoke(sheet, "Range", Dispatch.Get, arrayOf<Any>("A2"), IntArray(1)).toDispatch()
                Dispatch.put(a1, "Value", "123.456")
                Dispatch.put(a2, "Formula", "=A1*2")
                println("a1 from excel:" + Dispatch.get(a1, "Value"))
                println("a2 from excel:" + Dispatch.get(a2, "Value"))
                val f = Variant(false)
                Dispatch.call(workbook, "Close", f)*/
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //xl.invoke("Quit", Variant())
            }

            println(xl.programId)


        }catch (e: Exception){e.printStackTrace()}

    }
}