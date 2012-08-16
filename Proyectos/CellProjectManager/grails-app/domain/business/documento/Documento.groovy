package business.documento

class Documento {

    static belongsTo = [estado: EstadoDocumento, tipo: TipoDocumento]
    String observaciones
    Date fechaRealizado
    Date fechaEnviado
    Date fechaAprobado
    File archivo
    
    
    static constraints = {
        tipo()
        observaciones(blank: false)
        fechaRealizado(blank: true, nullable: true)
        fechaEnviado(blank: true, nullable: true)
        fechaAprobado(blank: true, nullable: true)
        archivo(blank: true, nullable: true)
        estado()
    }
    
    
    @Override String toString() {
	return getTipo() +" - "+ getEstado()
    }
}
