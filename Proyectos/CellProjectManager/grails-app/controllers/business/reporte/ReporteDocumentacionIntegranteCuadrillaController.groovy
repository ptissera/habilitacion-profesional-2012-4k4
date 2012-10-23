package business.reporte

import business.cuadrillas.Cuadrilla

class ReporteDocumentacionIntegranteCuadrillaController {

    def index() { 
     def cuadrillasListInstance = Cuadrilla.getAll()
     render(view: "step1", model: [cuadrillasListInstance: cuadrillasListInstance])
    }
    
    def step1(){
        def cuadrillasListInstance = params.cuadrillaIds.collect { Cuadrilla.get(it) }        
        if(!cuadrillasListInstance){
            flash.message = "Debe seleccionar al menos una cuadrilla"
            render(view: "step1", model: [cuadrillasListInstance: cuadrillasListInstance])
        }else{
            def integrantesCuadrillasListInstance = []
            cuadrillasListInstance.collect{it.operarios.each{operario ->
                integrantesCuadrillasListInstance << operario}}
            render(view: "step2", model: [integrantesCuadrillasListInstance: integrantesCuadrillasListInstance])
        }
    }
    
    def step2(){
        render(view: "step3")
    }
    
    def step3(){}
}
