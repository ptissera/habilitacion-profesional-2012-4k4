package business.utils

import business.cuadrillas.*
import business.core.Proyecto
import business.tarea.SolicitudDeTarea
import business.tarea.Tarea

class SelectedItemsTagLib {
    
    def selectedItems = {
        
        if(session.usuario!=null){
            Proyecto proyectoSelected=(Proyecto)session.getAttribute("proyectoSelected")
            SolicitudDeTarea solicitudDeTareaSelected=(SolicitudDeTarea)session.getAttribute("solicitudDeTareaSelected")
            SolicitudDeTarea solicitudDeTareaCreate=(SolicitudDeTarea)session.getAttribute("solicitudDeTareaCreate")
            Tarea tareaSelected=(Tarea)session.getAttribute("tareaSelected")
            Cuadrilla cuadrillaSelected=(Cuadrilla)session.getAttribute("cuadrillaSelected")
            IntegranteCuadrilla integranteCuadrillaSelected=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected")
            Boolean documentacionIntegranteCuadrillaSelectedTF=(Boolean)session.getAttribute("documentacionIntegranteCuadrillaSelectedTF")
            Boolean historialCuadrillaSelectedTF=(Boolean)session.getAttribute("historialCuadrillaSelectedTF")
            Boolean poSelectedTF=(Boolean)session.getAttribute("poSelectedTF")
            Boolean documentoSelectedTF=(Boolean)session.getAttribute("documentoSelectedTF")
            Boolean permisoAccesoSelectedTF=(Boolean)session.getAttribute("permisoAccesoSelectedTF")
            Boolean materialDeTareaSelectedTF=(Boolean)session.getAttribute("materialDeTareaSelectedTF")
            Boolean equipoDeTareaSelectedTF=(Boolean)session.getAttribute("equipoDeTareaSelectedTF")
            Boolean prestamosSelectedTF=(Boolean)session.getAttribute("prestamosSelectedTF")
            
            out << "<div class='selectedItem' role='navigation'><ul>"
            out << "<li>"
            out << """${link(class: "home", action: "home", controller: "Authorize"){"Principal"}}"""
            out << "</li>"
            if(proyectoSelected!=null){
                out << "<li>"
                out << """${link(class: "proyecto", action: "show", controller: "proyecto", id: proyectoSelected.id){proyectoSelected}}"""
                out << "</li>"
            }
            if((solicitudDeTareaSelected!=null || solicitudDeTareaCreate!=null) && (tareaSelected!=null || poSelectedTF || prestamosSelectedTF)){
                if(solicitudDeTareaSelected!=null){
                    out << "<li>"
                    out << """${link(class: "solicitud", action: "show", controller: "solicitudDeTarea", id: solicitudDeTareaSelected.id){'Solicitud de Tarea'}}"""
                    out << "</li>"
                } else {
                    out << "<li>"
                    out << """${link(class: "solicitud", action: "create", controller: "solicitudDeTarea", id: solicitudDeTareaCreate.id){'Solicitud de Tarea'}}"""
                    out << "</li>"
                }
            }
            if(tareaSelected!=null &&(documentoSelectedTF || permisoAccesoSelectedTF || materialDeTareaSelectedTF || equipoDeTareaSelectedTF)){
                out << "<li>"
                out << """${link(class: "tarea", action: "show", controller: "tarea", id: tareaSelected.id){tareaSelected}}"""
                out << "</li>"
            }
            if(cuadrillaSelected!=null && (integranteCuadrillaSelected!=null || historialCuadrillaSelectedTF)){
                out << "<li>"
                out << """${link(class: "cuadrilla", action: "show", controller: "cuadrilla", id: cuadrillaSelected.id){cuadrillaSelected}}"""
                out << "</li>"
            }
            if(integranteCuadrillaSelected!=null && documentacionIntegranteCuadrillaSelectedTF){
                out << "<li>"
                out << """${link(class: "operario", action: "show", controller: "integranteCuadrilla", id: integranteCuadrillaSelected.id){integranteCuadrillaSelected}}"""
                out << "</li>"                                        
            }
            out << "</ul></div>"
            
        }
    }
}