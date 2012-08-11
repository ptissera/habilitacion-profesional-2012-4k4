package business.herramienta

import business.cuadrillas.Cuadrilla

class PrestamoHerramienta {

    static belongsTo = [herramienta: Herramienta, cuadrilla: Cuadrilla]
    
    Date fechaPrestamo
    Date fechaDevolucion
    String descripcion
    
    static constraints = {
        fechaPrestamo(blank: false)
        fechaDevolucion(black:true, nullable:true) 
        herramienta()
        cuadrilla()
    }
    
    @Override String toString() {
	return getCuadrilla() + " - " + getFechaPrestamo()
    }
}
