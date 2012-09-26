package support.secure

class AuthorizeController {

    def index() {
        //		redirect(action: "login")
    }
	
    def home = 
    {
        [ "cuadrillaSelected",
         "solicitudDeTareaCreate",
         "integranteCuadrillaSelected",
         "documentacionIntegranteCuadrillaSelectedTF",
         "historialCuadrillaSelectedTF",
        "solicitudDeTareaSelected",
        "tareaSelected",
        "equipoDeTareaSelectedTF",
        "materialDeTareaSelectedTF",
        "permisoAccesoSelectedTF",
        "documentoSelectedTF",
        "poSelectedTF",
        "prestamosSelectedTF",
        "solicitudPagoCuadrillaSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
        
        def solicitudDeTareaCreate = session.getAttribute("solicitudDeTareaCreate")
        if (solicitudDeTareaCreate){
            solicitudDeTareaCreate.delete(flush: true)
        }
        session.setAttribute("solicitudDeTareaCreate",null)
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
            session.usuario.rol = usuario.rol                  
        }else{
            flash.message = "Lo sentimos, ${params.nombreUsuario}. Intente de nuevo por favor."     
        }
    }
  
    def logout = {
        flash.message = "Adios ${session.usuario} !!"
        session.usuario = null
        ["solicitudDeTareaCreate",
         "proyectoSelected",
         "cuadrillaSelected",
         "solicitudDeTareaCreate",
         "integranteCuadrillaSelected",
         "documentacionIntegranteCuadrillaSelectedTF",
         "historialCuadrillaSelectedTF",
        "solicitudDeTareaSelected",
        "tareaSelected",
        "equipoDeTareaSelectedTF",
        "materialDeTareaSelectedTF",
        "permisoAccesoSelectedTF",
        "documentoSelectedTF",
        "poSelectedTF",
        "prestamosSelectedTF",
        "solicitudPagoCuadrillaSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
        redirect(action: "login")
    }  
}
