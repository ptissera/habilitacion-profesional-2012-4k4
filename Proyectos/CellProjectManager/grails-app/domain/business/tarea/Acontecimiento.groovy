package business.tarea

class Acontecimiento {
    static belongsTo = [ tipoAcontecimiento: TipoAcontecimiento, tarea: Tarea]        
    Date  fechaCreacion
    String descripcion
    
    static constraints = {
        fechaCreacion(blank: false)
        descripcion(blank: false)
        tipoAcontecimiento(nullable: false)
        tarea()
    }
    
    @Override String toString() {
	return getDescripcion()
    }
}
