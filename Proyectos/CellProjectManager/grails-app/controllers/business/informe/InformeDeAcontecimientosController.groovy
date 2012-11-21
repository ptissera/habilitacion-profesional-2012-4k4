package business.informe

import business.tarea.TipoAcontecimiento

class InformeDeAcontecimientosController {

   
    def index() { 
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        [tipoAcontecimientoListInstance: TipoAcontecimiento.list()]
    }
    
    def step2(){
        
        
        def tipoAcontecimientoListInstance = params.tipoAcontecimientoIds.collect { TipoAcontecimiento.get(it) }        
        if(!tipoAcontecimientoListInstance){
            flash.message = "Debe seleccionar al menos un tipo de acontecimiento"
            render(view: "step1", model: [tipoAcontecimientoListInstance: TipoAcontecimiento.list()])
        }else{
            
            
            
            def keysTipoAcontecimientoMap = [:]     
            def tiposAcontecimientos = ""
            tipoAcontecimientoListInstance.each{
                tiposAcontecimientos +="${it.nombre}\n"
                def key = "${it.id}"
                keysTipoAcontecimientoMap[key] = [tipoAcontecimientoId: it.id]
               
            }
            session.informe_tipos_acontecimientos = tiposAcontecimientos
            
            def desde = params.desde ? new Date().parse("dd/MM/yyyy", params.desde_value) : null
            if(desde){
                session.informe_desde = desde.format("dd/MM/yyyy")
            }
            
            def hasta = params.hasta ? new Date().parse("dd/MM/yyyy", params.hasta_value) : null
            if(hasta){
                session.informe_hasta = hasta.format("dd/MM/yyyy")
            }
            
            def datos = [:] 
            def totalCount = 0
            Acontecimiento.getAll().each{ acontecimiento->
                def key = "${acontecimiento.tipoAcontecimientoId}"
                if((desde !=null ? desde <= acontecimiento.fecha : true) 
                    && (hasta !=null ? hasta >= acontecimiento.fecha : true)
                    && keysTipoAcontecimientoMap..getAt(key)){
                    totalCount++
                    if(datos.getAt(key)){
                        datos[key].totalAcontecimientos++
                    }else{
                        datos[key] = [tipoAcontecimiento: acontecimiento.tipoAcontecimiento,                            
                            porcentajeAcontecimientos: 0,
                            totalAcontecimientos: 1]
                    }                    
                }
            }
             def datosInforme = []           
            datos.each{dato->
                dato.value.porcentajeAcontecimientos = new Double(dato.value.totalAcontecimientos / totalCount * 100).round(2)
                datosInforme << dato.value
            }
             session.resultReport = datosInforme
            [datos: datosInforme]
        }                
                
        
                    
           
      
    }
        
    def reporte={
        def datos = session.resultReport            
        params.tiposAcontecimientos = session.informe_tipos_acontecimientos
        params.fechaDesde = session.informe_desde
        params.fechaHasta = session.informe_hasta
        
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
    }
    
    
}
