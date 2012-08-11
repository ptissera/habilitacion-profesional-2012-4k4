package business.tarea

import business.core.Pago
import business.core.PO
import business.core.Proyecto
import business.cuadrillas.Cuadrilla

class SolicitudDeTarea {

    static hasMany = [tareasPorSitio: TareasPorSitio, po: PO, cuadrilla: Cuadrilla]
    static belongsTo = [pago: Pago, proyecto: Proyecto, estado: EstadoSolicitudTarea]
    
    Date fechaAlta
    
    static constraints = {
        fechaAlta(black:true)
        tareasPorSitio()
        po()
        cuadrilla()
        pago()
        proyecto()
        estado()
    }
}
