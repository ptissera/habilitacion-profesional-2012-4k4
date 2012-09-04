package business.cuadrillas

class DocumentacionIntegranteCuadrilla {
    static belongsTo = [tipoDocumento: TipoDocumentacionIntegranteCuadrilla, integrante: IntegranteCuadrilla]
    Date vigenciaDesde
    Date vigenciaHasta
    String descripcion
    
    static constraints = {        
        vigenciaDesde(black:false, min: new Date())
        vigenciaHasta(black:false, min: new Date())
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
    
    def estadoDocumentoIcon(){
        switch (checkVencimiento()){
            case 1: return "/images/estados/rojo_1.png"
            case 2: return "/images/estados/amarillo_1.png"
            case 3: return "/images/estados/verde_1.png"
            default: return ""
        }
    }
    
    @Override String toString() {
        return getTipoDocumento()
    }
}
