package business.tarea

class UnidadMedida {

    static hasMany = [ materiales:MaterialDeTarea]
    String nombre
    
    static constraints = {
        nombre(blank: false, unique: true)        
        materiales()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
