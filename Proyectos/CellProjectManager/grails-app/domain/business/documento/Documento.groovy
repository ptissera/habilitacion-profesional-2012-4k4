package business.documento
import business.tarea.TareasPorSitio
class Documento {

    static belongsTo = [estado: EstadoDocumento, tareasPorSitio: TareasPorSitio, tipo: TipoDocumento]
    String observaciones
    Date fechaRealizado
    Date fechaEnviado
    Date fechaAprobado
    String nombreArchivo
    byte[] archivo
    
    
    static constraints = {
        tareasPorSitio()
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
