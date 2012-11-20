package business.informe

import business.cuadrillas.TipoDocumentacionIntegranteCuadrilla
import business.cuadrillas.HistorialVencimientosDocumentacion

class InformeDeVencimientosDeDocumentosController {

    
    def index() { 
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        [tipoDocumentacionesListInstance: TipoDocumentacionIntegranteCuadrilla.list()]
    }
    
    def step2(){
        
        
        def tipoDocumentacionesListInstance = params.tipoDocumentacionesIds.collect { TipoDocumentacionIntegranteCuadrilla.get(it) }        
        if(!tipoDocumentacionesListInstance){
            flash.message = "Debe seleccionar al menos un tipo de Documento"
            render(view: "step1", model: [tipoDocumentacionesListInstance: TipoDocumentacionIntegranteCuadrilla.list()])
        }else{
            
            
            
            def keysTipoDocumentosMap = [:]     
            def tiposDocumentos = ""
            tipoDocumentacionesListInstance.each{
                tiposDocumentos +="${it.nombre}\n"
                def key = "${it.id}"
                keysTipoDocumentosMap[key] = [nombre: it.nombre]
            }
            
            session.informe_tipos_documentos = tiposDocumentos
            
            def desde = params.desde ? new Date().parse("dd/MM/yyyy", params.desde_value) : null
            if(desde){
                session.informe_desde = desde.format("dd/MM/yyyy")
            }
            
            def hasta = params.hasta ? new Date().parse("dd/MM/yyyy", params.hasta_value) : null
            if(hasta){
                session.informe_hasta = hasta.format("dd/MM/yyyy")
            }
            
            def datos = [:] 
            def totalCount = 0
            HistorialVencimientosDocumentacion.getAll().each{ hist->
                def key = "${hist.tipoDocumentoId}"
                if((desde !=null ? desde <= hist.fecha : true) 
                    && (hasta !=null ? hasta >= hist.fecha : true)
                    && keysTipoDocumentosMap.getAt(key)){
                    totalCount++
                    if(datos.getAt(key)){
                        datos[key].totalTiposDocs++
                    }else{
                        datos[key] = [tipoDocumento: keysTipoDocumentosMap.getAt(key).nombre,                            
                            porcentaje: 0 ,
                            totalTiposDocs: 1]
                    }                    
                }
            }
            
            def datosInforme = []
            datos.each{dato->
                dato.value.porcentaje = new Double(dato.value.totalTiposDocs / totalCount * 100).round(2)
                datosInforme << dato.value
            }        
            session.resultReport = datosInforme
            [datos: datosInforme]
        }                
    }
        
    def reporte={
        def datos = session.resultReport            
        println "\n\n\n\n  datos = ${datos} \n\n\n\n\n"
        params.tiposDocumentos = session.informe_tipos_documentos
        params.fechaDesde = session.informe_desde
        params.fechaHasta = session.informe_hasta
        
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
    
    
}
