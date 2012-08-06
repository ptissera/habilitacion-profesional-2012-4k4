package business.utils

import business.cuadrillas.*

class SelectedItemsTagLib {
    
    def selectedItems = {
        
        if(session.user!=null){
            Cuadrilla cuadrillaSelected=(Cuadrilla)session.getAttribute("cuadrillaSelected")
            IntegranteCuadrilla integranteCuadrillaSelected=(Empleado)session.getAttribute("empleadoSelected")
            Boolean documentacionEmpleadoSelectedTF=(Boolean)session.getAttribute("documentacionEmpleadoSelectedTF")
            if(cuadrillaSelected && integranteCuadrillaSelected){
                out << "<div class='selectedItem' role='navigation'><ul>"
//                if(cuadrillaSelected){
//                    out << "<li><a class='home' href='${createLink(uri: '/')}'>Principal</a></li>"
//                }
                if(cuadrillaSelected && integranteCuadrillaSelected){
                    out << "<li>"
                    //out << "<a class='cuadrilla' href='#'>"+cuadrillaSelected.getNombre()+"</a>"
                    out << """${link(class: "cuadrilla", action: "show", controller: "cuadrilla", id: cuadrillaSelected.id){cuadrillaSelected}}"""
                    out << "</li>"
                }
                if(integranteCuadrillaSelected && documentacionEmpleadoSelectedTF){
                    out << "<li>"
                    out << """${link(class: "operario", action: "show", controller: "integranteCuadrilla", id: empleadoSelected.id){integranteCuadrillaSelected}}"""
                    out << "</li>"                                        
                }
                out << "</ul></div>"
            }
        }
    }
}



//				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
