package business.core

import business.tarea.TareasPorSitio

class PermisoAcceso {

    static belongsTo = [tarea: TareasPorSitio]
    
    Date fechaDesde
    Date fechaHasta
    String nombreArchivo
    byte[] archivo
    
    static constraints = {        
        fechaDesde(blank: false)
        fechaHasta(blank: false)
        nombreArchivo(blank: true)
        archivo(blank: true)         
    }
}
