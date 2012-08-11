package business.tarea

import business.core.Pago

class SolicitudDeTarea {

    static hasMany = [tareasPorSitio:TareasPorSitio]
    static belongsTo = [pago: Pago]
    Date fechaAlta
    
    static constraints = {
        fechaAlta(black:true)
    }
}
