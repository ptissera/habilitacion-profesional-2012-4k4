package business.tarea

class TipoEquipoDeTarea {

    static hasMany = [equipos: EquipoDeTarea]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)  
        equipos()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
