package business.tarea

class TipoTarea{
    
    static hasMany = [ tareas:Tarea]
    String nombre
    int complejidad
    int duracionEstimada
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        complejidad(range:1..10)
        duracionEstimada(range:1..100)
        descripcion(blank: false)  
        tareas()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
