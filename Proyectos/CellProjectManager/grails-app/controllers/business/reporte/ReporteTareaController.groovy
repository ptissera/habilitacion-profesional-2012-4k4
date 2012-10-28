package business.reporte
import  business.tarea.*
import  business.core.Sitio

class ReporteTareaController {

     def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        [estadosTareasListInstance: EstadoTarea.list()]
    }
    
    def step2(){
        def estadosTareasListInstance = params.estadosTareasIds.collect { EstadoTarea.get(it) }        
        if(!estadosTareasListInstance){
            flash.message = "Debe seleccionar al menos un Estado"
            render(view: "step1", model: [estadosTareasListInstance: estadosTareasListInstance])
        }else{
            session.estadosTareasListInstance = estadosTareasListInstance
            [sitiosTareasListInstance: Sitio.list()]
        }
    }
    
    def step3(){
        
        def sitiosTareasListInstance = params.sitiosIds.collect { Sitio.get(it) }        
        if(!sitiosTareasListInstance){
            flash.message = "Debe seleccionar al menos un Sitio"
            render(view: "step2", model: [sitiosTareasListInstance: sitiosTareasListInstance])
        }else{
            session.sitiosTareasListInstance = sitiosTareasListInstance
             [tiposTareasListInstance: TipoTarea.list()]
        }
    }
    
    
    def step4(){
        
        def tiposTareasListInstance = params.tiposIds.collect { TipoTarea.get(it) }        
        if(!tiposTareasListInstance){
            flash.message = "Debe seleccionar al menos un Tipo de Tarea"
            render(view: "step3", model: [tiposTareasListInstance: tiposTareasListInstance])
        }else{
            session.tiposTareasListInstance = tiposTareasListInstance
        }
    }
    
    def step5(){
            def estadosTareasListInstance = session.estadosTareasListInstance
            def sitiosTareasListInstance =  session.sitiosTareasListInstance
            def tiposTareasListInstance = session.tiposTareasListInstance
            def resultReport = []
            def datos = [] 
            def fechaInicioEstimada_mayorQue = params.fechaInicioEstimada_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaInicioEstimada_mayorQue_value):null
            def fechaInicioEstimada_menorQue = params.fechaInicioEstimada_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaInicioEstimada_menorQue_value):null
            def fechaFinEstimada_mayorQue = params.fechaFinEstimada_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaFinEstimada_mayorQue_value):null
            def fechaFinEstimada_menorQue = params.fechaFinEstimada_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaFinEstimada_menorQue_value):null
            def fechaInicioReal_mayorQue = params.fechaInicioReal_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaInicioReal_mayorQue_value):null
            def fechaInicioReal_menorQue = params.fechaInicioReal_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaInicioReal_menorQue_value):null
            def fechaFinReal_mayorQue = params.fechaFinReal_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaFinReal_mayorQue_value):null
            def fechaFinReal_menorQue = params.fechaFinReal_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaFinReal_menorQue_value):null
            
            def busqueda =[]
             for (i in estadosTareasListInstance)
               for (j in  sitiosTareasListInstance)
                 for (k in tiposTareasListInstance)
                   {
                       busqueda = Tarea.findAllByEstadoAndSitioAndTipoTarea(i,j, k)
                       busqueda.each{
                        if(  fechaInicioEstimada_mayorQue !=null ? fechaInicioEstimada_mayorQue <= it.fechaInicio : true
                            && fechaInicioEstimada_menorQue !=null ? fechaInicioEstimada_menorQue >= it.fechaInicio : true
                            && fechaFinEstimada_mayorQue !=null ? fechaFinEstimada_mayorQue <= it.fechaFin : true
                            && fechaFinEstimada_menorQue !=null ? fechaFinEstimada_menorQue >= it.fechaFin : true
                            && fechaInicioReal_mayorQue !=null ? fechaInicioReal_mayorQue <= it.fechaInicioReal : true
                            && fechaInicioReal_menorQue !=null ? fechaInicioReal_menorQue >= it.fechaInicioReal : true
                            && fechaFinReal_mayorQue !=null ? fechaFinReal_mayorQue <= it.fechaFinReal : true
                            && fechaFinReal_menorQue !=null ? fechaFinReal_menorQue >= it.fechaFinReal : true){                            
                            
                            resultReport << it
                            datos << [estado: it.estado, sitio: it.sitio, tipo: it.tipoTarea, fechaInicioEstimada: it.fechaInicio, fechaFinEstimada: it.fechaFin, fechaInicioReal: it.fechaInicioReal, fechaFinReal: it.fechaFinReal]
                        }
                      }
                  }
                
            session.resultReport = datos
            [tareaInstanceList: resultReport]
        
    }
    
    def reporte={
        def datos = session.resultReport            
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
}
