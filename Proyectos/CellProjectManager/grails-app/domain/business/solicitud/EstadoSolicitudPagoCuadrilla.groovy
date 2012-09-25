package business.solicitud

class EstadoSolicitudPagoCuadrilla {

     static hasMany = [solicitudPagoCuadrilla: SolicitudPagoCuadrilla]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        solicitudPagoCuadrilla()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
