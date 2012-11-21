package business.informe

import business.core.Proyecto
import business.tarea.SolicitudDeTarea

class InformeProcentajeDeViaticosEnProyectosController {

   
    def index() { 
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        flash.porcentaje = 0
    }
     
    def step2(){
        
        if(!params.porcentaje){
            flash.message = "Debe ingresar un porcentaje"
            render(view: "step1")
        }else{           
            Float porcentaje = new Float(params.porcentaje)            
            session.informe_porcentaje = porcentaje
              
            def datos = [:] 
            Proyecto.list().each{proyecto ->                
                proyecto.solicitudes.each{                     
                    def solicitud = SolicitudDeTarea.get(it.id)
                    def key = "${proyecto.id}"
                    if(datos.getAt(key)){
                        datos[key].totalViaticos += solicitud.totalViaticos()
                        datos[key].totalProyecto += solicitud.totalPOs()
                    }else{
                        datos[key] = [
                            proyecto: proyecto.toString(),
                            porcentaje: 0,
                            totalViaticos: solicitud.totalViaticos(),
                            totalProyecto: solicitud.totalPOs()
                        ]
                    }
                }                
            }
            
            def datosInforme = []
            datos.each{dato->
                dato.value.porcentaje = new Double(dato.value.totalViaticos * 100 / dato.value.totalProyecto).round(2)
                if(dato.value.porcentaje >= porcentaje){
                    datosInforme << dato.value
                }
            }    
           
            session.resultReport = datosInforme
            [datos: datosInforme]
        }
    }
    
        
    def reporte={
        def datos = session.resultReport            
        println "datos = ${datos}"
        params.porcentaje = session.informe_porcentaje
        
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
    
}
