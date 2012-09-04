package business.tarea
import business.documento.*
import business.herramienta.*
class TipoTarea {

    static belongsTo = [ tarea: Tarea]        
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        tarea(nullable: true)
    }
    
    @Override String toString() {
	return getNombre()
    }
}
