package business.cuadrillas
import business.core.Tipo

class TipoDocumentacionIntegranteCuadrilla extends Tipo{
    
   static hasMany = [documentacionIntegranteCuadrilla: DocumentacionIntegranteCuadrilla]
   int diaAntesVencimiento

   static constraints = {
      nombre(blank: false, unique: true)
      descripcion(blank: false)
      diaAntesVencimiento(blank: false, range:1..30)
      documentacionIntegranteCuadrilla()
   }      
    
}
