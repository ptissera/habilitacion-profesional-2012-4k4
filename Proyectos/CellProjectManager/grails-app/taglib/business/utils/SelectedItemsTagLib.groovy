package business.utils

import business.cuadrillas.*

class SelectedItemsTagLib {
    
    def selectedItems = {
        
        if(session.usuario!=null){
            Cuadrilla cuadrillaSelected=(Cuadrilla)session.getAttribute("cuadrillaSelected")
            IntegranteCuadrilla integranteCuadrillaSelected=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected")
            Boolean documentacionIntegranteCuadrillaSelectedTF=(Boolean)session.getAttribute("documentacionIntegranteCuadrillaSelectedTF")
            Boolean historialCuadrillaSelectedTF=(Boolean)session.getAttribute("historialCuadrillaSelectedTF")
            if(cuadrillaSelected && (integranteCuadrillaSelected || historialCuadrillaSelectedTF)){
                out << "<div class='selectedItem' role='navigation'><ul>"
//                if(cuadrillaSelected){
//                    out << "<li><a class='home' href='${createLink(uri: '/')}'>Principal</a></li>"
//                }
                if(cuadrillaSelected && (integranteCuadrillaSelected || historialCuadrillaSelectedTF)){
                    out << "<li>"
                    //out << "<a class='cuadrilla' href='#'>"+cuadrillaSelected.getNombre()+"</a>"
                    out << """${link(class: "cuadrilla", action: "show", controller: "cuadrilla", id: cuadrillaSelected.id){cuadrillaSelected}}"""
                    out << "</li>"
                }
                if(integranteCuadrillaSelected && documentacionIntegranteCuadrillaSelectedTF){
                    out << "<li>"
                    out << """${link(class: "operario", action: "show", controller: "integranteCuadrilla", id: integranteCuadrillaSelected.id){integranteCuadrillaSelected}}"""
                    out << "</li>"                                        
                }
                out << "</ul></div>"
            }
        }
    }
}



//				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
