package business.documento
import business.tarea.TareasPorSitio
class Documento {

    static belongsTo = [estado: EstadoDocumento, tareasPorSitio: TareasPorSitio ]
    String observaciones
    Date fechaRealizado
    Date fechaEnviado
    Date fechaAprobado
    String nombreArchivo
    byte[] archivo
    
    
    static constraints = {
        tareasPorSitio()
        observaciones(blank: false)
        fechaRealizado(blank: true, nullable: true)
        fechaEnviado(blank: true, nullable: true)
        fechaAprobado(blank: true, nullable: true)
        nombreArchivo(blank: true)
        archivo(blank: true)        
        estado()
        
    }
    
    
    @Override String toString() {
        
	return  getTipo() == null ? getTipo() + " (" + getEstado() + ")" : ""
    }
}
