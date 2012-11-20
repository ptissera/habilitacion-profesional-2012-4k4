package business.cuadrillas

class HistorialVencimientosDocumentacion {

    TipoDocumentacionIntegranteCuadrilla tipoDocumento
    static belongsTo = [tipoDocumento: TipoDocumentacionIntegranteCuadrilla]
    Integer idDocumentoIntegranteCuadrilla
    Date fecha
    Date fechaEnQueVencio
    
    static constraints = {
        fecha(blank: false)
        fechaEnQueVencio(blank: false)
        idDocumentoIntegranteCuadrilla(blank: false)     
        tipoDocumento(blank: false) 
    }
}
