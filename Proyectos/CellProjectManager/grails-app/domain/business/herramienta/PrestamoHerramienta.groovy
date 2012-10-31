package business.herramienta

import business.cuadrillas.Cuadrilla
import business.tarea.SolicitudDeTarea

class PrestamoHerramienta {

    static belongsTo = [herramienta: Herramienta, cuadrilla: Cuadrilla, solicitud: SolicitudDeTarea]
    
    Date fechaPrestamo
    Date fechaDevolucion
    Date fechaDevolucionReal    
    String descripcion
    
    static constraints = {
        fechaPrestamo(blank: false)
        fechaDevolucion(black:false, validator: {date, obj -> date!=null ? date - obj.fechaPrestamo >= 0 : true}) 
        fechaDevolucionReal(black:true, nullable: true) 
        herramienta()
        cuadrilla()
    }
    
    @Override String toString() {
	return getHerramienta()
    }
}
