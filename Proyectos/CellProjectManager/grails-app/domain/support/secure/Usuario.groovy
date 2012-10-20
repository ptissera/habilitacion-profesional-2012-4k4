package support.secure
import business.cuadrillas.IntegranteCuadrilla

class Usuario {
    
    static belongsTo = [rol: Rol, integranteCuadrilla: IntegranteCuadrilla]
    static fetchMode = [rol:"eager"] 
    String nombreUsuario
    String nombre
    String apellido
    String clave
    boolean habilitado
    String email


    static constraints = {
        nombreUsuario(blank: false, unique: true)
        clave(blank: false, password: true)
        nombre(blank: false)
        apellido(blank: false)        
        email(email:true, blank: false)
        rol()
        habilitado()
        integranteCuadrilla()
    }     
     
    def isSuperUser(){
        return rol != null ? rol.getNombre() == "ROLE_ADMIN" : false
    }
   
    def isAdminGeneral(){
        return rol != null ? rol.getNombre() == "ROLE_ADMIN_GENERAL" : false
    }
   
    def isAdminProyecto(){
        return rol != null ? rol.getNombre() == "ROLE_ADMIN_PROYECTO" : false
    }
    
    def isJefeCuadrilla(){
        return rol != null ? rol.getNombre() == "ROLE_JEFE_CUADRILLA" : false
    }

    @Override String toString() {
        return getApellido() + ", " + getNombre()
    }
       
}
