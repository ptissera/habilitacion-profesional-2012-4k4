package business.solicitud
import business.tarea.SolicitudDeTarea

class SolicitudPagoCuadrilla {

    static belongsTo = [solicitud: SolicitudDeTarea, estado: EstadoSolicitudPagoCuadrilla ]
    Date fechaCreacion
    Date fechaPago
    Float porcentaje
    Float monto
    String observaciones
    
    static constraints = {
        solicitud()
        fechaCreacion(blank: false)
        fechaPago(blank: true, nullable: true)
        porcentaje(blank: false, range: 0..100)
        monto(blank: true, nullable: true)
        observaciones(blank: true, nullable: true, maxSize:500)
        estado()
    }
    
     @Override String toString() {
	return getFechaCreacion()
    }
}
