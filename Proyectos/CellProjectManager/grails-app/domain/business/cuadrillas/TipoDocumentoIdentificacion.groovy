package business.cuadrillas

class TipoDocumentoIdentificacion {
     
    static hasMany = [integrantes: IntegranteCuadrilla]
    String nombre
    
    
    static constraints = {
        nombre(blank: false, unique: true)
        integrantes()
    }
    
    @Override String toString() {
	return getNombre()
    }
}
