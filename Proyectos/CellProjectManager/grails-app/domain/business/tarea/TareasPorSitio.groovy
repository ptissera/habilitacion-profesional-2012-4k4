package business.tarea
import business.core.Sitio
import business.core.PermisoAcceso
import business.herramienta.PrestamoHerramienta
import business.documento.Documento

class TareasPorSitio {

    static belongsTo = [estado: EstadoTarea, sitio: Sitio, solicitudDeTarea: SolicitudDeTarea, tarea: Tarea]
    static hasMany = [ permisos: PermisoAcceso, materialDeTarea: MaterialDeTarea, equipoDeTarea: EquipoDeTarea]
    
    int ordenEjecucion
    Date fechaInicio
    String documentoDeIngenieria
    byte[] archivo
    String observaciones
    
    static constraints = {
        ordenEjecucion(min:1)
        fechaInicio(blank:false, min: new Date())
        sitio()
        tarea()
        estado()
        documentoDeIngenieria(blank: true)
        archivo(maxSize: 50000000)   
        solicitudDeTarea()
        materialDeTarea()
        equipoDeTarea()
        permisos()
    }
    
    @Override String toString() {
		return getSitio()
	}
}
