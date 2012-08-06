package business.cuadrillas

class TipoDocumentacionIntegranteCuadrilla{
    
   static hasMany = [documentacionIntegranteCuadrilla: DocumentacionIntegranteCuadrilla]
   String nombre
    String descripcion    
   int diaAntesVencimiento

   static constraints = {
      nombre(blank: false, unique: true)
      descripcion(blank: false)
      diaAntesVencimiento(blank: false, range:1..30)
      documentacionIntegranteCuadrilla()
   }      
   
    @Override String toString() {
	return getNombre()
    }
    
}
