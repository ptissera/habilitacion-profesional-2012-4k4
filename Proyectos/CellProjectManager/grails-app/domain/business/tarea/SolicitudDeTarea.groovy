package business.tarea


import business.core.Pago
import business.core.PO
import business.core.Proyecto
import business.cuadrillas.Cuadrilla
import business.herramienta.PrestamoHerramienta

class SolicitudDeTarea {

    static hasMany = [pagos: Pago, tareasPorSitio: TareasPorSitio, pos: PO, prestamos: PrestamoHerramienta]
    static belongsTo = [proyecto: Proyecto, estado: EstadoSolicitudTarea, cuadrilla: Cuadrilla]
    
    Date fechaAlta
    
    static constraints = {
        fechaAlta(blank:true)
        proyecto()
        cuadrilla()
        estado()
        tareasPorSitio()
        pos()        
        pagos()                
        prestamos()
    }
}
