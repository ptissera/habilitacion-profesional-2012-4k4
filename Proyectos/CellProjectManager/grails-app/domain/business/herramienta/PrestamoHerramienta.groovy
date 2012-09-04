package business.herramienta

import business.cuadrillas.Cuadrilla
import business.tarea.SolicitudDeTarea

class PrestamoHerramienta {

    static belongsTo = [herramienta: Herramienta, cuadrilla: Cuadrilla, solicitud: SolicitudDeTarea]
    
    Date fechaPrestamo
    Date fechaDevolucion
    String descripcion
    
    static constraints = {
        fechaPrestamo(blank: false, min: new Date())
        fechaDevolucion(black:true, nullable:true, min: new Date()) 
        herramienta()
        cuadrilla()
    }
    
    @Override String toString() {
	return getHerramienta()
    }
}
