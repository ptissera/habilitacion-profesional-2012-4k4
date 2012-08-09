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
        if(diffDate.days >=0 ){
            return ["-1","Vencido"]    
        } else if(diffDate.days < 0  && diffDate.days >= tipoDocumento.getDiaAntesVencimiento()){
            return ["0","Actualizar"]    
        }else{
            return ["1","Sin Problema"]    
        }
        
    }
    
    @Override String toString() {
        return getTipoDocumento() +", "+ checkVencimiento()
    }
}
