package business.herramienta

class Herramienta {

    static hasMany = [ prestamos:PrestamoHerramienta]
    static belongsTo = [estado: EstadoHerramienta]
    String numeroDeSerie
    String nombre
    String descripcion
    
    static constraints = {
        numeroDeSerie(blank: false, unique: true)
        nombre(blank: false)
        descripcion(blank: false) 
        estado()
        prestamos()
    }
    
    @Override String toString() {
	return getNumeroDeSerie() + " - " + getNombre()
    }
}
