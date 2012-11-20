package business.cuadrillas

class DocumentacionIntegranteCuadrilla {
    static belongsTo = [tipoDocumento: TipoDocumentacionIntegranteCuadrilla, integrante: IntegranteCuadrilla]
    Date vigenciaDesde
    Date vigenciaHasta
    String descripcion
    
    static constraints = {        
        vigenciaDesde(black:false)
        vigenciaHasta(black:false,validator: {date, obj -> obj.id ? true : date - obj.vigenciaDesde >= 0})
        descripcion(black:false)
        tipoDocumento()
        integrante()
    }
    
    def checkVencimiento(){
        def diffDate = getVigenciaHasta() - new Date()
        def value = (diffDate <=0 ? 1:(diffDate > 0  && diffDate <= tipoDocumento.getDiaAntesVencimiento()?2:3))        
        if(value == 1){
            saveLogVencimientos()
        }
        return value;
    }
    
    def saveLogVencimientos(){
         
        def historial = HistorialVencimientosDocumentacion.findByFechaEnQueVencioAndIdDocumentoIntegranteCuadrilla(vigenciaHasta,id)
        if(!historial){
            new HistorialVencimientosDocumentacion(fecha: new Date(), 
                fechaEnQueVencio: vigenciaHasta,
                idDocumentoIntegranteCuadrilla: id,
                tipoDocumento: tipoDocumento
            ).save()            
        }
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
