package business.core
import business.tarea.Tarea

class Sitio {
    
    static belongsTo = [provincia: Provincia]
    static hasMany = [tarea: Tarea]
    
    String nombre
    String direccion
    String latitud
    String longitud
    String observaciones
    
    static constraints = {
        nombre(blank:false)
        direccion(blank:false)
        latitud(blank:true, nullable:true)
        longitud(blank:true, nullable:true)
        observaciones(blank:true, nullable:true)
        provincia()
        tarea()
    }
    
    @Override String toString() {
	return getNombre() +" - "+ getDireccion()
    }
}
