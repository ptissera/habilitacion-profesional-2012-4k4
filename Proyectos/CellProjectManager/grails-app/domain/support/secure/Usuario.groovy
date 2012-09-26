package support.secure

class Usuario {
    
    static belongsTo = [rol: Rol]
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

    @Override String toString() {
        return getApellido() + ", " + getNombre()
    }
       
}
