package business.core

import business.tarea.Tarea

class PermisoAcceso {

    static belongsTo = [tarea: Tarea]
    
    Date fechaDesde
    Date fechaHasta
    String nombreArchivo
    byte[] archivo
    
    static constraints = {        
        fechaDesde(blank: false, validator: {date, obj -> obj.id ? true : date - new Date() >= 0} )
        fechaHasta(blank: false, validator: {date, obj -> date!=null ? date - obj.fechaDesde >= 0 : true})
        nombreArchivo(blank: true)
        archivo(maxSize: 50000000)   
        tarea()
    }
}
