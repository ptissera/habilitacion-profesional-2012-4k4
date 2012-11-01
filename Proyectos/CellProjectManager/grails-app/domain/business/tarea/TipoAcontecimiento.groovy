package business.tarea


class TipoAcontecimiento {

    static hasMany = [ acontecimientos: Acontecimiento]        
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
    }
    
    @Override String toString() {
	return getNombre()
    }
}
