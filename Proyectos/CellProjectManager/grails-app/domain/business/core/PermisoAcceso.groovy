package business.core

import business.tarea.TareasPorSitio

class PermisoAcceso {

    static belongsTo = [tareasPorSitio: TareasPorSitio]
    
    Date fechaDesde
    Date fechaHasta
    String nombreArchivo
    byte[] archivo
    
    static constraints = {        
        fechaDesde(blank: false, min: new Date() )
        fechaHasta(blank: false, min: new Date() )
        nombreArchivo(blank: true)
        archivo(maxSize: 50000000)   
        tareasPorSitio()
    }
}
