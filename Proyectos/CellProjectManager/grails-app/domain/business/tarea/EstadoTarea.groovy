package business.tarea

class EstadoTarea {

    static hasMany = [tarea: Tarea]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        tarea()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
