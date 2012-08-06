package business.cuadrillas

class EstadoCuadrilla {

    static hasMany = [cuadrillas: Cuadrilla]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)  
        cuadrillas()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
