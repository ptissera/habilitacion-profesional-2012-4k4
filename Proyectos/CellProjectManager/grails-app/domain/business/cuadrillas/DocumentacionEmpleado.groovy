package business.cuadrillas

class DocumentacionEmpleado {
    static belongsTo = [tipoDocumento: TipoDocumentacionEmpleado, empleado: Empleado]
    Date vigenciaDesde
    Date vigenciaHasta
    String descripcion
    static constraints = {
        tipoDocumento()
        vigenciaDesde(black:false)
        vigenciaHasta(black:false)
        descripcion(black:false)
    }
    
    def String toString() {
        return tipoDocumento
    }
}
