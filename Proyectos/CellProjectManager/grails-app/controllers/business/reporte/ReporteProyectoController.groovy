package business.reporte
import  business.core.*

class ReporteProyectoController {

    def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        flash.message = null
        [estadosProyectosListInstance: EstadoProyecto.list()]
    }
    
    def step2(){
        flash.message = null
        def estadosProyectosListInstance = params.estadoProyectoIds.collect { EstadoProyecto.get(it) }        
        if(!estadosProyectosListInstance){
            flash.message = "Debe seleccionar al menos un Estado"
            render(view: "step1", model: [estadosProyectosListInstance:  EstadoProyecto.list()])
        }else{
            def estadosProyecto = ""
            estadosProyectosListInstance.each{
                estadosProyecto += "${it.nombre}\n"
            }
            session.report_estadosProyecto = estadosProyecto
            session.estadosProyectosListInstance = estadosProyectosListInstance
            [clientesProyectosListInstance: Cliente.list()]
        }
    }
    
    def step3(){
        flash.message = null
        def clientesProyectosListInstance = params.clientesIds.collect { Cliente.get(it) }        
        if(!clientesProyectosListInstance){
            flash.message = "Debe seleccionar al menos un Cliente"
            render(view: "step2", model: [clientesProyectosListInstance: Cliente.list()])
        }else{
            def clientes = ""
            clientesProyectosListInstance.each{
                clientes += "${it.razonSocial}\n"
            }
            session.report_clientes = clientes
            session.clientesProyectosListInstance = clientesProyectosListInstance
        }
    }
    
    def step4(){
        flash.message = null
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
            
        if(fechaCreacion_mayorQue){
            session.reporte_fechaCreacion_mayorQue = fechaCreacion_mayorQue.format("dd/MM/yyyy")
        }
        if(fechaCreacion_menorQue){
            session.reporte_fechaCreacion_menorQue = fechaCreacion_menorQue.format("dd/MM/yyyy")
        }
        if(fechaInicio_mayorQue){
            session.reporte_fechaInicio_mayorQue = fechaInicio_mayorQue.format("dd/MM/yyyy")
        }
        if(fechaInicio_menorQue){
            session.reporte_fechaInicio_menorQue = fechaInicio_menorQue.format("dd/MM/yyyy")
        }
        if(fechaFin_mayorQue){
            session.reporte_fechaFin_mayorQue = fechaFin_mayorQue.format("dd/MM/yyyy")
        }
        if(fechaFin_menorQue){
            session.reporte_fechaFin_menorQue = fechaFin_menorQue.format("dd/MM/yyyy")
        }
        
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
                
                def fechaCreacionEditada = null
                def fechaInicioEditada = null
                def fechaFinEditada = null
                if (it.fechaCreacion)    
                    fechaCreacionEditada = it.fechaCreacion.format("dd/MM/yyyy")
                if (it.fechaInicio)   
                    fechaInicioEditada = it.fechaInicio.format("dd/MM/yyyy")
                if (it.fechaFin)    
                    fechaFinEditada = it.fechaFin.format("dd/MM/yyyy")
                    
                datos << [estado: it.estadoProyecto, cliente: it.cliente, fechaCreacion: fechaCreacionEditada, fechaInicio: fechaInicioEditada, fechaFin: fechaFinEditada]
            }
        }
        session.resultReport = datos
        [proyectoInstanceList: resultReport]
        
    }
    
    
    def reporte={
        
        params.clientes = session.report_clientes
        params.estadosProyecto = session.report_estadosProyecto        
        if (session.reporte_fechaCreacion_mayorQue)
            params.fechaCreacion_mayorQue = session.reporte_fechaCreacion_mayorQue
        else
            params.fechaCreacion_mayorQue = "Todas las fechas"
        if(session.reporte_fechaCreacion_menorQue)    
            params.fechaCreacion_menorQue =  session.reporte_fechaCreacion_menorQue
        else
            params.fechaCreacion_menorQue = "Todas las fechas"
        if(session.reporte_fechaInicio_mayorQue)
            params.fechaInicio_mayorQue = session.reporte_fechaInicio_mayorQue
        else
            params.fechaInicio_mayorQue = "Todas las fechas"
        if(session.reporte_fechaInicio_menorQue)    
            params.fechaInicio_menorQue = session.reporte_fechaInicio_menorQue
        else
            params.fechaInicio_menorQue ="Todas las fechas"
        if(session.reporte_fechaFin_mayorQue)    
            params.fechaFin_mayorQue = session.reporte_fechaFin_mayorQue
        else
            params.fechaFin_mayorQue = "Todas las fechas"
        if(session.reporte_fechaFin_menorQue)
            params.fechaFin_menorQue = session.reporte_fechaFin_menorQue
        else
            params.fechaFin_menorQue ="Todas las fechas"
    
        def datos = session.resultReport            
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
    
}
