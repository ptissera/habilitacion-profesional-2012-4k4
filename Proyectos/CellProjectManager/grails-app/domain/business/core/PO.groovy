package business.core
import business.tarea.SolicitudDeTarea

class PO {
static belongsTo = [solicitud: SolicitudDeTarea ]
    
    Date fechaRecibida
    Float monto
    Boolean esExtra
    String nombreArchivo
    byte[] archivo
    
    
    static constraints = {
        fechaRecibida(blank: false)
        monto(blank: false)
        esExtra(blank: false)        
        nombreArchivo(blank: true)
        archivo(maxSize: 50000000)          
        solicitud(blank: true, nullable: true)
    }
    
    
    @Override String toString() {
	return '$' + getMonto()
    }
}
