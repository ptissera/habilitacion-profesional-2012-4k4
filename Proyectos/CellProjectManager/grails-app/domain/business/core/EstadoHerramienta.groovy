package business.core

class EstadoHerramienta{

    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)  
    }
    
    @Override String toString() {
	return getNombre()
    }
}
