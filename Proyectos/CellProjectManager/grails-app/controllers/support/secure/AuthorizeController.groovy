package support.secure

class AuthorizeController {

    def index() {
        //		redirect(action: "login")
    }
	
    def home = 
    {
        ["cuadrillaSelected",
         "integranteCuadrillaSelected",
         "documentacionIntegranteCuadrillaSelectedTF",
         "historialCuadrillaSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
        
        redirect(uri:"/")    
    }
    
    def login = {    if (session.usuario) {
            redirect(uri:"/")                    
        }
    }
  
    def authenticate = {
        def usuario = Usuario.findByNombreUsuarioAndClave(params.nombreUsuario, params.clave)
        if(usuario){
            session.usuario = usuario                  
        }else{
            flash.message = "Lo sentimos, ${params.nombreUsuario}. Intente de nuevo por favor."     
        }
    }
  
    def logout = {
        flash.message = "Adios ${session.usuario} !!"
        session.usuario = null
        redirect(action: "login")
    }  
}
