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
             
            def desde = params.desde ? new Date().parse("dd/MM/yyyy", params.desde_value) : null
            def hasta = params.hasta ? new Date().parse("dd/MM/yyyy", params.hasta_value) : null
            Float monto = new Float(params.monto)
            
            session.informe_monto = monto
            if(desde)
            session.informe_desde = desde.format("dd/MM/yyyy")
            if(hasta)
            session.informe_hasta = hasta.format("dd/MM/yyyy")
            
            def datos = [] 
            def solicitudes = []
            
            SolicitudDeTarea.getAll().each{ solicitud->
                if(solicitud.totalPOs()>=monto){                    
                    solicitud.tarea.each{tar->
                        if(solicitud.pos){
                    
//                        if((desde !=null ? desde <= tar.fechaInicio : true)
//                            && (hasta !=null ? hasta >= tar.fechaInicio : true)
//                            && (desde !=null ? desde <= tar.fechaFin : true)
//                            && (hasta !=null ? hasta >= tar.fechaFin : true)){
//                            if(tar.fechaInicioReal && tar.fechaInicio!=tar.fechaInicioReal
//                                || (tar.fechaFinReal && tar.fechaFin!=tar.fechaFinReal)){
////                               
//                                def diasDesvio = (tar.fechaInicioReal - tar.fechaInicio) + (tar.fechaFinReal?tar.fechaFinReal - tar.fechaFin:0)
//                                def porcentajeDesvio = (tar.fechaFin - tar.fechaInicio) * diasDesvio / 100
                                
                        def porcentajeDesvio = (tar.fechaFin - tar.fechaInicio)
                        def item = [proyecto: solicitud.proyecto,
                                    cliente: solicitud.proyecto.cliente, 
                                    solicitud: solicitud,
                                    montoSolicitud: solicitud.totalPOs(),
                                    porcentajeDesvio: porcentajeDesvio]           
                                solicitudes << item
                                }
                            }
//                        }
//                    }
                }                
            }
            
            def auxSolicitudesResultMap = [:]                       
            solicitudes.each{ solic->
                def key = "${solic.solicitud.id}"
                if(auxSolicitudesResultMap.getAt(key)){
                    auxSolicitudesResultMap[key].sumDesvio += solic.porcentajeDesvio
                    auxSolicitudesResultMap[key].totalTareas++
                    auxSolicitudesResultMap[key].porcentajeDesvio = auxSolicitudesResultMap[key].sumDesvio / auxSolicitudesResultMap[key].totalTareas
                }else{
                    auxSolicitudesResultMap[key] = [proyecto: solic.proyecto,
                        cliente: solic.cliente, 
                        solicitud: solic.solicitud,
                        montoSolicitud: solic.montoSolicitud,
                        porcentajeDesvio: solic.porcentajeDesvio,
                        sumDesvio: solic.porcentajeDesvio,
                        totalTareas: 1]
                }                
            }
            
            def auxProyectosResultMap = [:]            
            auxSolicitudesResultMap.each{ solicGroup->                
                def item = solicGroup.value
                def key = "${item.proyecto.id}"
                if(auxProyectosResultMap.getAt(key)){                    
                    auxProyectosResultMap[key].sumDesvio += item.porcentajeDesvio
                    auxProyectosResultMap[key].totalTareas+=item.totalTareas
                    auxProyectosResultMap[key].totalMontoProyecto+=new Double(item.montoSolicitud).round(2)
                    auxProyectosResultMap[key].totalSolicitudes++                    
                    auxProyectosResultMap[key].porcentajeDesvio = new Double(auxProyectosResultMap[key].sumDesvio / auxProyectosResultMap[key].totalSolicitudes).round(2)
                }else{                    
                    auxProyectosResultMap[key] = [proyecto: item.proyecto,
                        cliente: item.cliente, 
                        porcentajeDesvio: new Double(item.porcentajeDesvio).round(2),         
                        totalTareas: item.totalTareas,
                        sumDesvio: item.porcentajeDesvio,
                        totalMontoProyecto: new Double(item.montoSolicitud).round(2),
                        totalSolicitudes: 1]
                }                  
            }
            
            auxProyectosResultMap.each{
                def item = it.value
                datos << item
            }
            
            session.resultReport = datos
            [datos: datos]
        }
    }
    
        
    def reporte={
        def datos = session.resultReport            
        
        params.monto = session.informe_monto
        params.fechaDesde = session.informe_desde
        params.fechaHasta = session.informe_hasta
        
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
    
}
