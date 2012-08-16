package business.utils

import business.cuadrillas.*
import business.core.Proyecto

class SelectedItemsTagLib {
    
    def selectedItems = {
        
        if(session.usuario!=null){
            Proyecto proyectoSelected=(Proyecto)session.getAttribute("proyectoSelected")
            Cuadrilla cuadrillaSelected=(Cuadrilla)session.getAttribute("cuadrillaSelected")
            IntegranteCuadrilla integranteCuadrillaSelected=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected")
            Boolean documentacionIntegranteCuadrillaSelectedTF=(Boolean)session.getAttribute("documentacionIntegranteCuadrillaSelectedTF")
            Boolean historialCuadrillaSelectedTF=(Boolean)session.getAttribute("historialCuadrillaSelectedTF")

            out << "<div class='selectedItem' role='navigation'><ul>"
            out << "<li><a class='home' href='${createLink(uri: '/')}'>Principal</a></li>"
            if(proyectoSelected){
                out << "<li>"
                out << """${link(class: "proyecto", action: "show", controller: "proyecto", id: proyectoSelected.id){proyectoSelected}}"""
                out << "</li>"
            }
            if(cuadrillaSelected && (integranteCuadrillaSelected || historialCuadrillaSelectedTF)){
                out << "<li>"
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