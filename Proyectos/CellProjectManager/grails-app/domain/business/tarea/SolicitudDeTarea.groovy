package business.tarea


import business.core.Pago
import business.core.PO
import business.core.Proyecto
import business.cuadrillas.Cuadrilla
import business.herramienta.PrestamoHerramienta
import business.documento.Documento

class SolicitudDeTarea {

    static hasMany = [pagos: Pago, tarea: Tarea, pos: PO, prestamos: PrestamoHerramienta, documentos: Documento]
    static belongsTo = [proyecto: Proyecto, estado: EstadoSolicitudTarea, cuadrilla: Cuadrilla]
    
    Date fechaAlta
    
    static constraints = {
        fechaAlta(blank:true, min: new Date())
        proyecto()
        cuadrilla()
        estado()
        tarea()
        pos()        
        pagos()                
        prestamos()
        documentos()
    }
}
