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
            
           if (fechaInicioEstimada_mayorQue)
                session.reporteInicioEstimado_mayorQue = fechaInicioEstimada_mayorQue.format("dd/MM/yyyy")
           if (fechaInicioEstimada_menorQue)     
                session.reporteInicioEstimado_menorQue = fechaInicioEstimada_menorQue.format("dd/MM/yyyy")
           if (fechaFinEstimada_mayorQue)     
                session.reporteFinEstimado_mayorQue    = fechaFinEstimada_mayorQue.format("dd/MM/yyyy")
           if (fechaFinEstimada_menorQue)
                session.reporteFinEstimado_menorQue    = fechaFinEstimada_menorQue.format("dd/MM/yyyy")
           if (fechaInicioReal_mayorQue)     
                session.reporteInicioReal_mayorQue     = fechaInicioReal_mayorQue.format("dd/MM/yyyy")
           if (fechaInicioReal_menorQue)     
               session.reporteInicioReal_menorQue     = fechaInicioReal_menorQue.format("dd/MM/yyyy")
           if (fechaFinReal_mayorQue)    
               session.reporteFinReal_mayorQue        = fechaFinReal_mayorQue.format("dd/MM/yyyy")
           if (fechaFinReal_menorQue)    
               session.reporteFinReal_menorQue        = fechaFinReal_menorQue.format("dd/MM/yyyy")
        
           def estadosTareas = ""
           def tiposTareas = ""
           def sitios = ""
           def fechaInicioEditada = null
           def fechaFinEditada = null
           def fechaInicioRealEditada = null
           def fechaFinRealEditada = null
                    
            def busqueda =[]
             for (i in estadosTareasListInstance)
             { estadosTareas += " ${i.nombre}  \n"
               for (j in  sitiosTareasListInstance)
               { sitios += "${j.nombre}  \n"
                 for (k in tiposTareasListInstance)
                   {   tiposTareas += "${k.nombre}  \n"
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
                            
                            fechaInicioEditada = null
                            fechaFinEditada = null
                            fechaInicioRealEditada = null
                            fechaFinRealEditada = null
                            
                            if (it.fechaInicio)
                                fechaInicioEditada = it.fechaInicio.format("dd/MM/yyyy")
                            if (it.fechaFin)   
                                fechaFinEditada = it.fechaFin.format("dd/MM/yyyy")
                            if (it.fechaInicioReal)
                                fechaInicioRealEditada = it.fechaInicioReal.format("dd/MM/yyyy")
                            if (it.fechaFinReal)   
                                fechaFinRealEditada = it.fechaFinReal.format("dd/MM/yyyy")    
                            
                            datos << [estado: it.estado, sitio: it.sitio, tipo: it.tipoTarea, fechaInicioEstimada: fechaInicioEditada, fechaFinEstimada: fechaFinEditada, fechaInicioReal: fechaInicioRealEditada, fechaFinReal: fechaFinRealEditada]
                        }
                      }
                  }
                }  
             }     
                
            session.reporteEstadosTareas = estadosTareas
            session.reporteTiposDeTareas = tiposTareas
            session.reporteSitios = sitios
            session.resultReport = datos
            [tareaInstanceList: resultReport]
        
    }
    
    def reporte={
        
        def datos = session.resultReport 
        params.estadosTareas = session.reporteEstadosTareas
        params.tiposDeTareas = session.reporteTiposDeTareas
        params.sitios = session.reporteSitios
        params.inicioEstimado_mayorQue = session.reporteInicioEstimado_mayorQue
        params.inicioEstimado_menorQue = session.reporteInicioEstimado_menorQue
        params.finEstimado_mayorQue = session.reporteFinEstimado_mayorQue
        params.finEstimado_menorQue = session.reporteFinEstimado_menorQue
        params.inicioReal_mayorQue = session.reporteInicioReal_mayorQue
        params.inicioReal_menorQue = session.reporteInicioReal_menorQue
        params.finReal_mayorQue = session.reporteFinReal_mayorQue
        params.finReal_menorQue = session.reporteFinReal_menorQue
        
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
}
