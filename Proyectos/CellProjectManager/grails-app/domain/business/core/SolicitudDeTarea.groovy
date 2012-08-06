package business.core

class SolicitudDeTarea {

    static hasMany = [ tareasPorSitio:TareasPorSitio]
    static belongsTo = [pago: Pago]
    Date fechaAlta
    
    static constraints = {
        fechaAlta(black:true)
    }
}
