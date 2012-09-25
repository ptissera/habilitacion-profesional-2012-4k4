package business.solicitud
import business.tarea.SolicitudDeTarea

class CobroSolicitudDeTrabajo {

     static belongsTo = [solicitud: SolicitudDeTarea]
    
    Date fechaCobro    
    Float monto
    String observaciones
    
    static constraints = {
        solicitud()
        fechaCobro(blank: false)
        monto(blank: true, nullable: true)
        observaciones(blank: true, nullable: true, maxSize:500)        
    }
    
     @Override String toString() {
	return getFechaCobro()
    }
}
