package business.utils

class MainMenuTagLib {

    def menuPrincipal = {
        startMainMenu()
        
        tituloItemMenu("Proyectos & Tareas", true)  
	subItemMenu([["proyecto","Gestor de Proyectos"],
                ["proyecto","Seleccionar Proyectos","selectList"],
                ["proyecto","Asignar Proyectos","asignProject"],
                ["solicitudDeTarea","Gestor de solicitud de tarea"],
                ["solicitudDeTarea","Crear Solicitud de Tarea","create"]])
                              	
	tituloItemMenu("Soporte Usuarios", false)
        subItemMenu([["usuario","Usuarios"],
                ["rol","Roles"]])
        
        tituloItemMenu("Soporte Sitio", false)
	subItemMenu([["cliente","Clientes"],              
                ["tarea","Tareas"],
                ["sitio","Sitios"],
                ["provincia","Provincias"]])
        
        tituloItemMenu("Cuadrilla", false)  
        subItemMenu([["cuadrilla","Cuadrillas"],
                ["tipoDocumentacionIntegranteCuadrilla","Tipos de Documentaciones"]])
			
	finishMainMenu()
    }
    
    def startMainMenu(){
        
        out << "<div id='status' role='complementary'>\n"
	out << "   <ul id='accordion'>\n"   
    }
    
    def finishMainMenu(){
        out << "   </ul>\n"
        out << "</div>\n"
     
    }
    
    def tituloItemMenu(titulo,isFirst){
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
