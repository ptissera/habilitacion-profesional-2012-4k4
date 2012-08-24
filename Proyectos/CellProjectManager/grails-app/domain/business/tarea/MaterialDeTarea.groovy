package business.tarea

class MaterialDeTarea {

    static belongsTo = [ tareasPorSitio: TareasPorSitio, unidad: UnidadMedida]
    int cantidad
    String descripcion
    boolean esDeCliente
    
    static constraints = {        
        descripcion(blank: false)  
        cantidad(min:1)  
        esDeCliente()        
        unidad()
        tareasPorSitio()
    }
    
    @Override String toString() {
	return getDescripcion()
    }
}
