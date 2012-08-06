package business.cuadrillas

class DocumentacionIntegranteCuadrilla {
    static belongsTo = [tipoDocumento: TipoDocumentacionIntegranteCuadrilla, integrante: IntegranteCuadrilla]
    Date vigenciaDesde
    Date vigenciaHasta
    String descripcion
    
    static constraints = {        
        vigenciaDesde(black:false)
        vigenciaHasta(black:false)
        descripcion(black:false)
        tipoDocumento()
        integrante()
    }
    
    def String toString() {
        return getTipoDocumento()
    }
}
