package business.cuadrillas
import business.herramienta.PrestamoHerramienta

class Cuadrilla {
    
    static belongsTo = [estadoCuadrilla: EstadoCuadrilla]
    static hasMany = [ operarios:IntegranteCuadrilla, historialDeCambios: HistorialCuadrilla, prestatmosHerramientas: PrestamoHerramienta]
    static fetchMode = [historialDeCambios:"eager", operarios:"eager"] 
    String nombre
    String descripcion
    Boolean propia
    
    
    static constraints = {
        nombre(black:false, unique: true)
        descripcion(black:false)        
        propia()
        estadoCuadrilla()
        operarios()
        historialDeCambios()
    }
    
    def haveJefeCuadrilla(){
        def hayJefe = false
        getOperarios().each{ it ->
            if(it.esJefeCuadrilla){
                hayJefe = true
            }
        }   
        return hayJefe
    }
     
     def checkDocumentacion(){
        int codigo = 4
        getOperarios().each{ it ->
            if(it.checkDocumentacion()<codigo){
                codigo=it.checkDocumentacion();
            }
        }        
        return codigo
    }
    
    def estadoDocumentacionIcon(){
        switch (checkDocumentacion()){
            case 1: return "/images/estados/rojo_1.png"
            case 2: return "/images/estados/amarillo_1.png"
            case 3: return "/images/estados/verde_1.png"
            default: return ""
        }
    }
   
    def afterInsert = {
        def historial=new HistorialCuadrilla(fecha: new Date(), cuadrilla: this)       
        historial.setDescripcion("    Cuadrilla -- Alta -- (${this.toString()})")
        historial.save()
    }
    
    @Override String toString() {
		return getNombre()
	}
}
