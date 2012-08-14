package business.core

class Provincia {
    static hasMany = [ sitios:Sitio]  
    String nombre
    static constraints = {
        nombre(blank:false, unique:true)
        sitios()
    }
    @Override String toString() {
	return getNombre()
    }
}
