package business.utils

class MainMenuTagLib {

    def menuPrincipal = {
        if(session.usuario){
            
            startMainMenu()
            if(session.usuario.isSuperUser()){
                tituloItemMenu("Proyectos & Tareas")  
                subItemMenu([["proyecto","Gestor de Proyectos"],
                        ["proyecto","Seleccionar Proyectos","selectList"],
                        ["proyecto","Asignar Proyectos","asignProject"],
                        ["solicitudDeTarea","Gestor de solicitud de tarea"],
                        ["solicitudDeTarea","Crear Solicitud de Tarea","create"]])
                              	
                tituloItemMenu("Soporte Usuarios")
                subItemMenu([["usuario","Usuarios"],
                        ["rol","Roles"]])
        
                tituloItemMenu("Soporte Sitio")
                subItemMenu([["cliente","Clientes"],              
                        ["tipoTarea","Tipo De Tarea"],
                        ["sitio","Sitios"],
                        ["provincia","Provincias"],
                        ["tipoAcontecimiento","Tipo de Acontecimiento"]])
            }
            if(session.usuario.isSuperUser() || session.usuario.isAdminGeneral()){ 
                tituloItemMenu("Cuadrilla")  
                subItemMenu([["cuadrilla","Cuadrillas"],
                        ["tipoDocumentacionIntegranteCuadrilla","Tipos de Documentaciones"]])            
            
                tituloItemMenu("Finanzas")  
                subItemMenu([["cobroSolicitudDeTrabajo","Cobros"],
                        ["solicitudDeViaticos","Solicitudes De Viaticos"],
                        ["solicitudPagoCuadrilla","Solicitudes De Pagos"]])
                
                tituloItemMenu("Reportes")  
                subItemMenu([["reporteDocumentacionIntegranteCuadrilla","Documentacion Operarios"],
                             ["reporteProyecto","Proyectos"],
                             ["reporteTarea","Tareas"]
                        ])
                
                tituloItemMenu("Informes")  
                subItemMenu([["informeDeTareasDesviadasPorFacturacion","Tareas desviadas por Facturacion"]
                        ])
                
                tituloItemMenu("Parametros Del Sitio")  
                subItemMenu([["parametrosDelSistema","Parametrizaciones"]])
            }
            finishMainMenu()
        }
    }
    
    def startMainMenu(){
        
        out << "<div id='status' role='complementary'>\n"
	out << "   <ul id='accordion'>\n"   
    }
    
    def finishMainMenu(){
        out << "   </ul>\n"
        out << "</div>\n"
     
    }
    
    def tituloItemMenu(titulo){
        out << " <li>$titulo</li>\n"     
    }
    
    def subItemMenu(datos){
        out << "<ul>\n"
        datos.each { it ->    
            String controlador = it[0]
            String descripcion = it[1]
            if(it.size == 3){
                String vista = it[2]
                out << "<li>" + """${link(controller:"$controlador",action:"$vista"){descripcion}}""" + "</li>\n"
            }else{
                out << "<li>" + """${link(controller:"$controlador"){descripcion}}""" + "</li>\n"
            }
        }
        out << "</ul>\n"
    }
}
