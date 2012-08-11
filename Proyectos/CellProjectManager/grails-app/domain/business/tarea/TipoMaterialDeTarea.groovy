package business.tarea

class TipoMaterialDeTarea {

    static hasMany = [materiales: MaterialDeTarea]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)  
        materiales()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
