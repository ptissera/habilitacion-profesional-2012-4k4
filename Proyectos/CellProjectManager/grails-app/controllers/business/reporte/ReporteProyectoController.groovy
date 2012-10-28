package business.reporte
import  business.core.*

class ReporteProyectoController {

   def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        [estadosProyectosListInstance: EstadoProyecto.list()]
    }
    
    def step2(){
        def estadosProyectosListInstance = params.estadoProyectoIds.collect { EstadoProyecto.get(it) }        
        if(!estadosProyectosListInstance){
            flash.message = "Debe seleccionar al menos un Estado"
            render(view: "step1", model: [estadosProyectosListInstance: estadosProyectosListInstance])
        }else{
            session.estadosProyectosListInstance = estadosProyectosListInstance
            [clientesProyectosListInstance: Cliente.list()]
        }
    }
    
    def step3(){
        
        def clientesProyectosListInstance = params.clientesIds.collect { Cliente.get(it) }        
        if(!clientesProyectosListInstance){
            flash.message = "Debe seleccionar al menos un Cliente"
            render(view: "step2", model: [clientesProyectosListInstance: clientesProyectosListInstance])
        }else{
            session.clientesProyectosListInstance = clientesProyectosListInstance
        }
    }
    
    def step4(){
            def clientesProyectosListInstance = session.clientesProyectosListInstance
            def estadosProyectosListInstance =  session.estadosProyectosListInstance
            def resultReport = []
            def datos = [] 
            def fechaCreacion_mayorQue = params.fechaCreacion_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_mayorQue_value):null
            def fechaCreacion_menorQue = params.fechaCreacion_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_menorQue_value): null
            def fechaInicio_mayorQue = params.fechaInicio_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaInicio_mayorQue_value):null
            def fechaInicio_menorQue = params.fechaInicio_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaInicio_menorQue_value):null
            def fechaFin_mayorQue = params.fechaFin_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaFin_mayorQue_value):null
            def fechaFin_menorQue = params.fechaFin_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaFin_menorQue_value):null
            
            def busqueda=[]
            for ( i in clientesProyectosListInstance)     
               for (j in estadosProyectosListInstance)
               {
                   busqueda = Proyecto.findAllByEstadoProyectoAndCliente(j,i)
                   busqueda.each{
                            if (                      
                             (fechaCreacion_mayorQue !=null ? fechaCreacion_mayorQue <= it.fechaCreacion : true)
                            && (fechaCreacion_menorQue !=null ? fechaCreacion_menorQue >= it.fechaCreacion : true)
                            && (fechaInicio_mayorQue !=null ? fechaInicio_mayorQue <= it.fechaInicio : true)
                            && (fechaInicio_menorQue !=null ? fechaInicio_menorQue >= it.fechaInicio : true)
                            && (fechaFin_mayorQue !=null ? fechaFin_mayorQue <= it.fechaFin : true)
                            && (fechaFin_menorQue !=null ? fechaFin_menorQue >= it.fechaFin : true)){                            
                            }
                            resultReport << it
                            datos << [estado: it.estadoProyecto, cliente: it.cliente, fechaCreacion: it.fechaCreacion, fechaInicio: it.fechaInicio, fechaFin: it.fechaFin]
                        }
                    }
            session.resultReport = datos
            [proyectoInstanceList: resultReport]
        
    }
    
    def reporte={
        def datos = session.resultReport            
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
    
}
