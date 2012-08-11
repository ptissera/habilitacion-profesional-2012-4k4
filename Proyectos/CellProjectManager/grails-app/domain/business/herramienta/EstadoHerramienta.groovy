package business.herramienta

class EstadoHerramienta{

    static hasMany = [herramientas: Herramienta]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false) 
        herramientas()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
