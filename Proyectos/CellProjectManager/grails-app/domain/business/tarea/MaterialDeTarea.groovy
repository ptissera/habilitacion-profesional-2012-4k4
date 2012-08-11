package business.tarea

class MaterialDeTarea {

    static belongsTo = [ tarea: Tarea, unidad: UnidadMedida]
    int cantidad
    String descripcion
    boolean esDeCliente
    
    static constraints = {        
        descripcion(blank: false)  
        cantidad(min:1)  
        esDeCliente()        
        unidad()
        tarea()
    }
    
    @Override String toString() {
	return getDescripcion()
    }
}
