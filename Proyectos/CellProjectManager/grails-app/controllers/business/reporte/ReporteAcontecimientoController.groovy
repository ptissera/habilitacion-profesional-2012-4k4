package business.reporte
import  business.tarea.*
import  support.secure.*

class ReporteAcontecimientoController {

       def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        flash.message = null
        [tipoAcontecimientoListInstance: TipoAcontecimiento.list()]
    }
    
    def step2(){
        flash.message = null
        def tipoAcontecimientoListInstance = params.tipoAcontecimientoIds.collect { TipoAcontecimiento.get(it) }        
        if(!tipoAcontecimientoListInstance){
            flash.message = "Debe seleccionar al menos un tipo de Acontecimiento"
            render(view: "step1", model: [tipoAcontecimientoListInstance: TipoAcontecimiento.list()])
        }else{
            session.tipoAcontecimientoListInstance = tipoAcontecimientoListInstance
            [creadorListInstance: Usuario.list()]
        }
    }
    
    def step3(){
        flash.message = null
        def creadorListInstance = params.creadorIds.collect { Usuario.get(it) }        
        if(!creadorListInstance){
            flash.message = "Debe seleccionar al menos un creador"
            render(view: "step2", model: [creadorListInstance: Usuario.list()])
        }else{
            session.creadorListInstance = creadorListInstance
            [tipoTareaListInstance: TipoTarea.list()]
        }
    }
    
    
    def step4(){
        flash.message = null
        def tipoTareaListInstance = params.tipoTareaIds.collect { TipoTarea.get(it) }        
        if(!tipoTareaListInstance){
            flash.message = "Debe seleccionar al menos un Tipo de Tarea"
            render(view: "step3", model: [tipoTareaListInstance:  TipoTarea.list()])
        }else{
            session.tipoTareaListInstance = tipoTareaListInstance
        }
    }
    
    def step5(){
            flash.message = null
            def tipoAcontecimientoListInstance = session.tipoAcontecimientoListInstance
            def creadorListInstance =  session.creadorListInstance
            def tipoTareaListInstance = session.tipoTareaListInstance
            def resultReport = []
            def datos = [] 
            def fechaCreacion_mayorQue = params.fechaCreacion_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_mayorQue_value):null
            def fechaCreacion_menorQue = params.fechaCreacion_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_menorQue_value):null
            if(fechaCreacion_mayorQue)
                session.reporteAcontecimiento_fechaCreacion_mayorQue =  fechaCreacion_mayorQue.format("dd/MM/yyyy")       
            else    
                session.reporteAcontecimiento_fechaCreacion_mayorQue = null
            if(fechaCreacion_menorQue)    
                session.reporteAcontecimiento_fechaCreacion_menorQue =  fechaCreacion_menorQue.format("dd/MM/yyyy")
            else
                session.reporteAcontecimiento_fechaCreacion_menorQue = null
        
            def busqueda =[]
            def tiposAcontecimientos = ""
            def tiposDeTareas = ""
            def creadores = ""

            for (i in tipoAcontecimientoListInstance)
            {
               tiposAcontecimientos += "${i.nombre} \n"
               for (j in  creadorListInstance)
                   {  
                       creadores += "${j.apellido}, ${j.nombre} \n"
                       busqueda = Acontecimiento.findAllByTipoAcontecimientoAndCreador(i,j)
                       for (k in tipoTareaListInstance)
                       {
                       tiposDeTareas += "${k.nombre} \n"
                       busqueda.each{
                          if (it.tarea.tipoTareaId ==k.id)
                         {
                             if(  fechaCreacion_mayorQue !=null ? fechaCreacion_mayorQue <= it.fechaCreacion : true
                                && fechaCreacion_menorQue !=null ? fechaCreacion_menorQue >= it.fechaCreacion : true
                               ){                            
                                resultReport << it
                                datos << [tipoAcontecimiento: it.tipoAcontecimiento, creador: it.creador, tipoTarea: it.tarea.tipoTarea, fechaCreacion: it.fechaCreacion.format("dd/MM/yyyy")]
                            }
                         }   
                            
                      }
                      }
                  }
              }    
            session.reporteAcontecimiento_tiposDocumentos = tiposAcontecimientos    
            session.reporteAcontecimiento_tiposDeTareas = tiposDeTareas    
            session.reporteAcontecimiento_creadores = creadores    
            session.resultReport = datos
            [acontecimientoInstanceList: resultReport]
        
    }
    
    def reporte={
        def datos = session.resultReport   
        params.tiposAcontecimientos = session.reporteAcontecimiento_tiposDocumentos 
        params.creadores = session.reporteAcontecimiento_creadores
        params.tiposDeTareas = session.reporteAcontecimiento_tiposDeTareas
        if (session.reporteAcontecimiento_fechaCreacion_mayorQue)
            params.fechaCreacion_mayorQue = session.reporteAcontecimiento_fechaCreacion_mayorQue         
        else
           params.fechaCreacion_mayorQue = "Todas las fechas"
        if   (session.reporteAcontecimiento_fechaCreacion_menorQue ) 
            params.fechaCreacion_menorQue = session.reporteAcontecimiento_fechaCreacion_menorQue    
        else
            params.fechaCreacion_menorQue = "Todas las fechas"
        
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
}


