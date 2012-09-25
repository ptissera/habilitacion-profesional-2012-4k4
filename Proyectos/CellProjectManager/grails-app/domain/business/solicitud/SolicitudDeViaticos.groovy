package business.solicitud
import business.tarea.SolicitudDeTarea

class SolicitudDeViaticos {

    static belongsTo = [solicitud: SolicitudDeTarea, estado: EstadoSolicitudDeViaticos ]
    Date fechaCreacion
    Date fechaPago
    Float monto
    String observaciones
    
    static constraints = {
        solicitud()
        fechaCreacion(blank: false)
        fechaPago(blank: true, nullable: true)
        monto(blank: true, nullable: true)
        observaciones(blank: true, nullable: true, maxSize:500)
        estado()
    }
    
     @Override String toString() {
	return getFechaCreacion()
    }
}
