package business.solicitud

class EstadoSolicitudDeViaticos {

    static hasMany = [solicitudDeViaticos: SolicitudDeViaticos]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
        solicitudDeViaticos()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
