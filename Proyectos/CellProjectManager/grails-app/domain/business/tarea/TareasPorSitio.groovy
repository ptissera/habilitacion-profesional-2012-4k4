package business.tarea
import business.core.Sitio
import business.core.PermisoAcceso
import business.herramienta.PrestamoHerramienta

class TareasPorSitio {

    static belongsTo = [ sitio: Sitio, solicitudDeTarea: SolicitudDeTarea, tarea: Tarea]
    static hasMany = [ herramientas: PrestamoHerramienta, permisos: PermisoAcceso]
    
    int ordenEjecucion
    Date fechaInicio
    
    static constraints = {
        ordenEjecucion(min:1)
        fechaInicio(blank:false)
        sitio()
        tarea()
        solicitudDeTarea()
        herramientas()
        permisos()
    }
    
    @Override String toString() {
		return getSitio()
	}
}
