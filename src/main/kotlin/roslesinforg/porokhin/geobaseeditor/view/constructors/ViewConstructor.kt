package roslesinforg.porokhin.geobaseeditor.view.constructors

import roslesinforg.porokhin.geobaseeditor.view.GeoBaseEditorView
import javax.xml.soap.Node

interface ViewConstructor<V: GeoBaseEditorView> {
    fun construct(view: V)

}

interface ViewDeconstructor<V: GeoBaseEditorView>{
    fun deconstruct(view: V)
}