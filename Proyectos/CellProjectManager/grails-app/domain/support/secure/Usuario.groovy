package support.secure

class Usuario {
    
   static belongsTo = [rol: Rol]
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
    
       @Override String toString() {
		return getApellido() + ", " + getNombre()
	}
   
}
