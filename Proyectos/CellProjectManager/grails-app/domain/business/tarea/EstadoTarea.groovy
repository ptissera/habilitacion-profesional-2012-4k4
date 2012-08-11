package business.tarea

class EstadoTarea {

    static hasMany = [tareas: Tarea]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        tareas()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
