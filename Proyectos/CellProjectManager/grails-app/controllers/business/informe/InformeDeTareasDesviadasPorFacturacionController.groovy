package business.informe

import business.tarea.Tarea
import business.tarea.SolicitudDeTarea

class InformeDeTareasDesviadasPorFacturacionController {

    def index() { 
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        flash.hasta = new Date()
        flash.desde = flash.hasta - 30
        flash.monto = 0
    }
    
     
    def step2(){
        
        if(!params.monto){
            flash.message = "Debe ingresar un monto"
            render(view: "step1")
        }else{
             
            def desde = params.desde ? new Date().parse("dd/MM/yyyy", params.desde):null
            def hasta = params.hasta ? new Date().parse("dd/MM/yyyy", params.hasta): null
            def monto = params.monto
            
            
            def datos = [] 
            def solicitudes = []
            
            SolicitudDeTarea.getAll().each{ solicitud->
                if(solicitud.totalPOs()>=monto){                    
                    solicitud.tarea.each{tar->
                        if((desde !=null ? desde <= tar.fechaInicio : true)
                            && (hasta !=null ? hasta >= tar.fechaInicio : true)
                            && (desde !=null ? desde <= tar.fechaFin : true)
                            && (hasta !=null ? hasta >= tar.fechaFin : true)){
                            if(tar.fechaInicioReal && tar.fechaInicio!=tar.fechaInicioReal
                                || (tar.fechaFinReal && tar.fechaFin!=tar.fechaFinReal)){
                               
                                def diasDesvio = (tar.fechaInicioReal - tar.fechaInicio) + (tar.fechaFinReal?tar.fechaFinReal - tar.fechaFin:0)
                                def porcentajeDesvio = (tar.fechaFin - tar.fechaInicio) * diasDesvio / 100
                                datos << [proyecto: solicitud.proyecto,
                                    cliente: solicitud.proyecto.cliente, 
                                    montoSolicitud: proyecto.totalPOs(),
                                    porcentajeDesvio: porcentajeDesvio,
                                    cuadrilla: solicitud.cuadrilla]
                            }
                        }
                    }
                }                
            }
            
            session.resultReport = datos
            [datos: datos]
        }
    }
    
}
