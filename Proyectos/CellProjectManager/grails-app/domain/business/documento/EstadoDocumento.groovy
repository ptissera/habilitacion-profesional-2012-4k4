package business.documento

class EstadoDocumento {

    static hasMany = [documentos: Documento]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)  
        documentos()
    }
    
    @Override String toString() {
	return getNombre()
    }
}


