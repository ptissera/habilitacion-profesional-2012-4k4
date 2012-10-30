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
                                solicitudes << [proyecto: solicitud.proyecto,
                                    cliente: solicitud.proyecto.cliente, 
                                    solicitud: solicitud,
                                    montoSolicitud: proyecto.totalPOs(),
                                    porcentajeDesvio: porcentajeDesvio]
                            }
                        }
                    }
                }                
            }
            def auxSolicitudesResultMap = [:]
                       
            solicitudes.each{ solic->
                def key = "${solic.solicitud.id}"
                if(auxSolicitudesResultMap.containsKey(key)){
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
                if(auxProyectosResultMap.containsKey(key)){
                    auxProyectosResultMap[key].sumDesvio += item.porcentajeDesvio
                    auxProyectosResultMap[key].totalTareas+=item.totalTareas
                    auxProyectosResultMap[key].totalMontoProyecto+=item.montoSolicitud
                    auxProyectosResultMap[key].totalSolicitudes++
                    auxProyectosResultMap[key].porcentajeDesvio = auxSolicitudesResultMap[key].sumDesvio / auxSolicitudesResultMap[key].totalSolicitudes
                }else{
                    auxProyectosResultMap[key] = [proyecto: item.proyecto,
                        cliente: item.cliente, 
                        porcentajeDesvio: item.porcentajeDesvio,         
                        totalTareas: item.totalTareas,
                        sumDesvio: item.porcentajeDesvio,
                        totalMontoProyecto: item.montoSolicitud,
                        totalSolicitudes: 1]
                }  
                
            }
            
            auxProyectosResultMap.each{
                def item = it.value
                System.println(item)
                datos << item
            }
            
            session.resultReport = datos
            [datos: datos]
        }
    }
    
}
