package business.reporte

import business.cuadrillas.*


class ReporteDocumentacionIntegranteCuadrillaController {

    def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        [cuadrillasListInstance: Cuadrilla.list()]
    }
    
    def step2(){
        def cuadrillasListInstance = params.cuadrillaIds.collect { Cuadrilla.get(it) }        
        if(!cuadrillasListInstance){
            flash.message = "Debe seleccionar al menos una cuadrilla"
            render(view: "step1", model: [cuadrillasListInstance: cuadrillasListInstance])
        }else{
            def integrantesCuadrillasListInstance = []
            cuadrillasListInstance.collect{it.operarios.each{operario ->
                    integrantesCuadrillasListInstance << operario
                }
            }
            flash.integrantesCuadrillasListInstance = integrantesCuadrillasListInstance
            [integrantesCuadrillasListInstance: integrantesCuadrillasListInstance]
        }
    }
    
    def step3(){
        
        def integrantesCuadrillaListSelectedInstance = params.integrantesCuadrillasIds.collect { IntegranteCuadrilla.get(it) }        
        if(!integrantesCuadrillaListSelectedInstance){
            flash.integrantesCuadrillasListInstance = flash.integrantesCuadrillasListInstance
            flash.message = "Debe seleccionar al menos un integrante de cuadrilla"
            render(view: "step2", model: [integrantesCuadrillasListInstance: flash.integrantesCuadrillasListInstance])
        }else{
            session.integrantesCuadrillaListSelectedInstance = integrantesCuadrillaListSelectedInstance
        }
    }
    
    def step4(){
        def tiposDocumentosListSelectedInstance=params.tiposDocumentosIds.collect{TipoDocumentacionIntegranteCuadrilla.get(it)}
        if(!tiposDocumentosListSelectedInstance){            
            flash.message = "Debe seleccionar al menos un tipo de documento"
            render(view: "step3")
        }else{
            def integrantesCuadrillaListSelectedInstance = session.integrantesCuadrillaListSelectedInstance
            def resultReport = []
            def datos = [] 
            def tiposDocumentos = ""
            def vigenciaDesde_mayorQue = params.vigenciaDesde_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.vigenciaDesde_mayorQue_value):null
            def vigenciaDesde_menorQue = params.vigenciaDesde_menorQue_value ? new Date().parse("dd/MM/yyyy", params.vigenciaDesde_menorQue_value): null
            def vigenciaHasta_mayorQue = params.vigenciaHasta_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.vigenciaHasta_mayorQue_value):null
            def vigenciaHasta_menorQue = params.vigenciaHasta_menorQue_value ? new Date().parse("dd/MM/yyyy", params.vigenciaHasta_menorQue_value):null
            
             
            if(vigenciaDesde_mayorQue){
                session.reporte_vigenciaDesde_mayorQue = vigenciaDesde_mayorQue.format("dd/MM/yyyy")
            }
            if(vigenciaDesde_menorQue){
                session.reporte_vigenciaDesde_menorQue = vigenciaDesde_menorQue.format("dd/MM/yyyy")
            }
            if(vigenciaHasta_mayorQue){
                session.reporte_vigenciaHasta_mayorQue = vigenciaHasta_mayorQue.format("dd/MM/yyyy")
            }
            if(vigenciaHasta_menorQue){
                session.reporte_vigenciaHasta_menorQue = vigenciaHasta_menorQue.format("dd/MM/yyyy")
            }
            
            
            tiposDocumentosListSelectedInstance.each{
                tiposDocumentos += "${it.nombre} \n"
            }
            session.reporte_tiposDocumentos = tiposDocumentos
            
            integrantesCuadrillaListSelectedInstance.each{ intCuad->                 
                
                def integranteCuadrilla = IntegranteCuadrilla.get(intCuad.id)
                integranteCuadrilla.documentacion.each{doc->                     
                    
                    tiposDocumentosListSelectedInstance.each{                            
                        if(it==doc.tipoDocumento 
                            && (vigenciaDesde_mayorQue !=null ? vigenciaDesde_mayorQue <= doc.vigenciaDesde : true)
                            && (vigenciaDesde_menorQue !=null ? vigenciaDesde_menorQue >= doc.vigenciaDesde : true)
                            && (vigenciaHasta_mayorQue !=null ? vigenciaHasta_mayorQue <= doc.vigenciaHasta : true)
                            && (vigenciaHasta_menorQue !=null ? vigenciaHasta_menorQue >= doc.vigenciaHasta : true)){                            
                            resultReport << doc
                            datos << [integrante: doc.integrante, tipoDocumento: doc.tipoDocumento, vigenciaDesde: doc.vigenciaDesde, vigenciaHasta: doc.vigenciaHasta]
                            
                        }
                    }
                }
            }            
            
            session.resultReport = datos
            [documentacionIntegranteCuadrillaInstanceList: resultReport]
        }
    }
    
    def reporte={
        
        def datos = session.resultReport            
        params.tiposDocumentos = session.reporte_tiposDocumentos 
        params.vigenciaDesde_mayorQue = session.reporte_vigenciaDesde_mayorQue         
        params.vigenciaDesde_menorQue = session.reporte_vigenciaDesde_menorQue         
        params.vigenciaHasta_mayorQue =  session.reporte_vigenciaHasta_mayorQue         
        params.vigenciaHasta_menorQue =  session.reporte_vigenciaHasta_menorQue
                
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
    

}
