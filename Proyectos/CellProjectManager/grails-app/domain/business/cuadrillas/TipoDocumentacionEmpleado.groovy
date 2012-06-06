package business.cuadrillas

class TipoDocumentacionEmpleado {
    
    static hasMany = [people: DocumentacionEmpleado]

   String nombre
   String descripcion
   boolean requerida

   static constraints = {
      nombre(blank: false, unique: true)
      descripcion(blank: false)
      requerida()
   }
   
    @Override String toString() {
		return getDescripcion()
	}
}
