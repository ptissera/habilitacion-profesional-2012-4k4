package business.tarea


class TipoAcontecimiento {

    static belongsTo = [ acontecimiento: Acontecimiento]        
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        acontecimiento(nullable: true)
    }
    
    @Override String toString() {
	return getNombre()
    }
}
