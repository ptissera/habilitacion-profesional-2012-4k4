package support.secure

class Rol {
    
    static hasMany = [usuarios: Usuario]

   /** description */
   String descripcion
   /** ROLE String */
   String nombre

   static constraints = {
      nombre(blank: false, unique: true)
      descripcion()
      usuarios()
   }
   
    @Override String toString() {
		return getDescripcion()
	}
}
