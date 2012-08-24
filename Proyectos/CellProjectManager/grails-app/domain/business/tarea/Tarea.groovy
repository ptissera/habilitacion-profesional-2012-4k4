package business.tarea
import business.documento.*
import business.herramienta.*
class Tarea {

    static belongsTo = [ tareasPorSitio: TareasPorSitio]        
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        tareasPorSitio(nullable: true)
    }
    
    @Override String toString() {
	return getNombre()
    }
}
