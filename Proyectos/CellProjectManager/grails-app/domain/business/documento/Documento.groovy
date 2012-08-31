package business.documento
import business.tarea.SolicitudDeTarea
class Documento {

    static belongsTo = [estado: EstadoDocumento, solicitudDeTarea: SolicitudDeTarea, tipo: TipoDocumento]
    String observaciones
    Date fechaRealizado
    Date fechaEnviado
    Date fechaAprobado
    String nombreArchivo
    byte[] archivo
    
    
    static constraints = {
        solicitudDeTarea()
        tipo()
        observaciones(blank: false)
        fechaRealizado(blank: true, nullable: true)
        fechaEnviado(blank: true, nullable: true)
        fechaAprobado(blank: true, nullable: true)
        nombreArchivo(blank: true)
        archivo(maxSize: 50000000)        
        estado()
        
    }
    
    
    @Override String toString() {        
	return  getTipo()
    }
}
