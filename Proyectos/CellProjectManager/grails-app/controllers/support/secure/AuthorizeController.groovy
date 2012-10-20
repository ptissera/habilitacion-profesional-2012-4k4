package support.secure
import grails.converters.JSON

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
        "solicitudPagoCuadrillaSelectedTF",
        "solicitudViaticosSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
        
        def solicitudDeTareaCreate = session.getAttribute("solicitudDeTareaCreate")
        if (solicitudDeTareaCreate){
            solicitudDeTareaCreate.delete(flush: true)
        }
        session.setAttribute("solicitudDeTareaCreate",null)
        if (session.usuario){
            redirect(uri:"/")   
        } else {
            flash.message = "La session a caducado!"
        }
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
        "solicitudPagoCuadrillaSelectedTF",
        "solicitudViaticosSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
        redirect(action: "login")
    }  
    
    def restLogin = {
        switch(request.method)
        {
            case 'GET':
                doGetRestLogin(params)
                break;
        }
    }
    
    private void doGetRestLogin(params)
    {   if (!session.usuario) 
        {
            this.authenticate()
            //falta el rol
            if(session.usuario){
                response.status =200
                render JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }, usuario: { id: $session.usuario.id , nombre: '$session.usuario.nombreUsuario'} }") as JSON
            }
            else{
                response.status=500
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            }
        }    
    }
    
    
   def restLogout = {
        switch(request.method)
        {
            case 'GET':
                doGetRestLogout(params)
                break;
        }
    }
    
    private void doGetRestLogout(params)
    {   if (session.usuario) 
        {
                session.usuario = null
                response.status=200
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            
        } 
    }
    
   
}
