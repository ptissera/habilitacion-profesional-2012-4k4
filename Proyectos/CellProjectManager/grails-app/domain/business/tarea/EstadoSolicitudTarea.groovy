package business.tarea

class EstadoSolicitudTarea{
    
    static hasMany = [solicitudes: SolicitudDeTarea]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        solicitudes()
    }
    
    @Override String toString() {
	return getNombre()
    }
   
}
