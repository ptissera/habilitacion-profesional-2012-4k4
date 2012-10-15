package business.tarea
import business.core.Sitio
import business.core.PermisoAcceso
import business.herramienta.PrestamoHerramienta
import business.documento.Documento

class Tarea {

    static belongsTo = [estado: EstadoTarea, sitio: Sitio, solicitudDeTarea: SolicitudDeTarea, tipoTarea: TipoTarea]
    static hasMany = [ permisos: PermisoAcceso, materialDeTarea: MaterialDeTarea, equipoDeTarea: EquipoDeTarea, acontecimientos: Acontecimiento]
    
    int ordenEjecucion
    Date fechaInicio
    Date fechaInicioReal
    Date fechaFin
    Date fechaFinReal
    
    String documentoDeIngenieria
    byte[] archivo
    String observaciones
    
    static constraints = {
        ordenEjecucion(min:1)
        fechaInicio(blank:false, validator: {date, obj -> obj.id ? true : date - new Date() >= 0})        
        fechaFin(blank: false, validator: {date, obj -> date!=null ? date - obj.fechaInicio >= 0 : true})
        fechaInicioReal(blank:true, nullable: true)        
        fechaFinReal(blank:true, nullable: true, validator: {date, obj -> date!=null ? date - obj.fechaInicioReal >= 0 : true})        
        
        sitio()
        tipoTarea()
        estado()
        documentoDeIngenieria(blank: true, nullable:true)
        archivo(maxSize: 50000000, nullable:true)   
        solicitudDeTarea()
        materialDeTarea()
        equipoDeTarea()
        permisos()
        acontecimientos()
    }
    
    @Override String toString() {
        return getSitio()
    }
}
