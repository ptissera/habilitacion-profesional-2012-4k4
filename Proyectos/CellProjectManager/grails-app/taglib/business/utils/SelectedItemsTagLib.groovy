package business.utils

import business.cuadrillas.*

class SelectedItemsTagLib {
    
    def selectedItems = {
        
        if(session.user!=null){
            Cuadrilla cuadrillaSelected=(Cuadrilla)session.getAttribute("cuadrillaSelected")
            Empleado empleadoSelected=(Empleado)session.getAttribute("empleadoSelected")
            Boolean documentacionEmpleadoSelectedTF=(Boolean)session.getAttribute("documentacionEmpleadoSelectedTF")
            if(cuadrillaSelected && empleadoSelected){
                out << "<div class='selectedItem' role='navigation'><ul>"
//                if(cuadrillaSelected){
//                    out << "<li><a class='home' href='${createLink(uri: '/')}'>Principal</a></li>"
//                }
                if(cuadrillaSelected && empleadoSelected){
                    out << "<li>"
                    //out << "<a class='cuadrilla' href='#'>"+cuadrillaSelected.getNombre()+"</a>"
                    out << """${link(class: "cuadrilla", action: "show", controller: "cuadrilla", id: cuadrillaSelected.id){cuadrillaSelected.getNombre()}}"""
                    out << "</li>"
                }
                if(empleadoSelected && documentacionEmpleadoSelectedTF){
                    out << "<li>"
                    out << """${link(class: "operario", action: "show", controller: "empleado", id: empleadoSelected.id){empleadoSelected.getNombre() +", "+ empleadoSelected.getApellido()}}"""
                    out << "</li>"                                        
                }
                out << "</ul></div>"
            }
        }
    }
}



//				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
