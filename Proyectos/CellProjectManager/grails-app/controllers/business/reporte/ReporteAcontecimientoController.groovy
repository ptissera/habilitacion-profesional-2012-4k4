package business.reporte
import  business.tarea.*
import  support.secure.*

class ReporteAcontecimientoController {

       def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        [tipoAcontecimientoListInstance: TipoAcontecimiento.list()]
    }
    
    def step2(){
        def tipoAcontecimientoListInstance = params.tipoAcontecimientoIds.collect { TipoAcontecimiento.get(it) }        
        if(!tipoAcontecimientoListInstance){
            flash.message = "Debe seleccionar al menos un tipo de Acontecimiento"
            render(view: "step1", model: [tipoAcontecimientoListInstance: tipoAcontecimientoListInstance])
        }else{
            session.tipoAcontecimientoListInstance = tipoAcontecimientoListInstance
            [creadorListInstance: Usuario.list()]
        }
    }
    
    def step3(){
        
        def creadorListInstance = params.creadorIds.collect { Usuario.get(it) }        
        if(!creadorListInstance){
            flash.message = "Debe seleccionar al menos un creador"
            render(view: "step2", model: [creadorListInstance: creadorListInstance])
        }else{
            session.creadorListInstance = creadorListInstance
            [tipoTareaListInstance: TipoTarea.list()]
        }
    }
    
    
    def step4(){
        
        def tipoTareaListInstance = params.tipoTareaIds.collect { TipoTarea.get(it) }        
        if(!tipoTareaListInstance){
            flash.message = "Debe seleccionar al menos un Tipo de Tarea"
            render(view: "step3", model: [tipoTareaListInstance: tipoTareaListInstance])
        }else{
            session.tipoTareaListInstance = tipoTareaListInstance
        }
    }
    
    def step5(){
            def tipoAcontecimientoListInstance = session.tipoAcontecimientoListInstance
            def creadorListInstance =  session.creadorListInstance
            def tipoTareaListInstance = session.tipoTareaListInstance
            def resultReport = []
            def datos = [] 
            def fechaCreacion_mayorQue = params.fechaCreacion_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_mayorQue_value):null
            def fechaCreacion_menorQue = params.fechaCreacion_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_menorQue_value):null
            session.reporte_fechaCreacion_mayorQue =  fechaCreacion_mayorQue.format("dd/MM/yyyy")           
            session.reporte_fechaCreacion_menorQue =  fechaCreacion_menorQue.format("dd/MM/yyyy")
        
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
            session.reporte_tiposDocumentos = tiposAcontecimientos    
            session.reporte_tiposDeTareas = tiposDeTareas    
            session.reporte_creadores = creadores    
            session.resultReport = datos
            [acontecimientoInstanceList: resultReport]
        
    }
    
    def reporte={
        def datos = session.resultReport   
        params.tiposAcontecimientos = session.reporte_tiposDocumentos 
        params.creadores = session.reporte_creadores
        params.tiposDeTareas = session.reporte_tiposDeTareas
        params.fechaCreacion_mayorQue = session.reporte_fechaCreacion_mayorQue         
        params.fechaCreacion_menorQue = session.reporte_fechaCreacion_menorQue    
        
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
}


