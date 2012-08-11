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
    
    def checkVencimiento(){
        def diffDate = getVigenciaHasta() - new Date()
        return (diffDate <=0 ? 1:(diffDate > 0  && diffDate <= tipoDocumento.getDiaAntesVencimiento()?2:3))        
    }
    
    def estadoDocumento(){
        switch (checkVencimiento()){
            case 1: return "Vencido"
            case 2: return "Necesita Actualizar"
            case 3: return "Actualizado"
            default: return ""
        }
    }
    
    @Override String toString() {
        return getTipoDocumento() +", "+ checkVencimiento()
    }
}
