package business.core
import business.tarea.TareasPorSitio

class Sitio {
    
    static belongsTo = [provincia: Provincia]
    static hasMany = [tareasPorSitio: TareasPorSitio]
    
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
        tareasPorSitio()
    }
    
    @Override String toString() {
	return getNombre() +" - "+getDireccion()
    }
}
