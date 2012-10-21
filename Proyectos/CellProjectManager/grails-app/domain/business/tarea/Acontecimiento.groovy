package business.tarea
import support.secure.Usuario

class Acontecimiento {
    static belongsTo = [ tipoAcontecimiento: TipoAcontecimiento, tarea: Tarea, creador: Usuario]        
    Date  fechaCreacion
    String descripcion
    
    static constraints = {
        fechaCreacion(blank: false)
        descripcion(blank: false)
        tipoAcontecimiento(nullable: false)
        tarea()
        creador()
    }
    
    @Override String toString() {
	return getDescripcion()
    }
}
