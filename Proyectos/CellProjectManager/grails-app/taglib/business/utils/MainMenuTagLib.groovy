package business.utils

class MainMenuTagLib {

    def menuPrincipal = {
        startMainMenu()
        
        tituloItemMenu("Proyectos")  
	subItemMenu([["cliente","Clientes"],                     
                     ["proyecto","Proyectos"]])
                              	
	tituloItemMenu("Soporte Usuarios")
        subItemMenu([["usuario","Usuarios"],
                     ["rol","Roles"]])
			
         tituloItemMenu("Cuadrilla")  
         subItemMenu([["cuadrilla","Cuadrillas"],
                     ["tipoDocumentacionIntegranteCuadrilla","Tipos de Documentaciones"]])
			
	finishMainMenu()
    }
    
    def startMainMenu(){
        out << "<div id='status' role='complementary'>"
    }
    
    def finishMainMenu(){
        out << "</div>"
    }
    
    def tituloItemMenu(titulo){
        out << "<h1>$titulo</h1>"
    }
    
    def subItemMenu(datos){
        out << "<ul>"
        datos.each { it ->    
            String controlador = it[0]
            String descripcion = it[1]
            out << "<li>" + """${link(controller:"$controlador"){descripcion}}""" + "</li>"
        }
        out << "</ul>"
    }
}
