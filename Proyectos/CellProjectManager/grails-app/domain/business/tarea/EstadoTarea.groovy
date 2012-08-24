package business.tarea

class EstadoTarea {

    static hasMany = [tareasPorSitio: TareasPorSitio]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        tareasPorSitio()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
