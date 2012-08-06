package business.cuadrillas
import business.core.*

class Cuadrilla {
    
    static belongsTo = [estadoCuadrilla: EstadoCuadrilla]
    static hasMany = [ operarios:IntegranteCuadrilla, historialDeCambios: HistorialCuadrilla, prestatmosHerramientas: PrestamoHerramienta]
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
    
    @Override String toString() {
		return getNombre()
	}
}
