package business.reporte
import business.solicitud.*

class ReporteSolicitudViaticoController {

    def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        flash.message = null
        [estadoViaticoListInstance: EstadoSolicitudDeViaticos.list()]
    }
    
    def step2(){
        flash.message = null
        def estadoViaticoListInstance = params.estadoViaticoIds.collect { EstadoSolicitudDeViaticos.get(it) }        
        if(!estadoViaticoListInstance){
            flash.message = "Debe seleccionar al menos un Estado"
            render(view: "step1", model: [estadoViaticoListInstance: EstadoSolicitudDeViaticos.list()])
        } else {
            session.estados = estadoViaticoListInstance
        }
        
    }
    
    def step3() {
        flash.message = null
        def estadoViaticoListInstance = session.estados
        def resultReport = []
        def datos = [] 
        def fechaCreacion_mayorQue = params.fechaCreacion_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_mayorQue_value):null
        def fechaCreacion_menorQue = params.fechaCreacion_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaCreacion_menorQue_value): null
        def fechaPago_mayorQue = params.fechaPago_mayorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaPago_mayorQue_value):null
        def fechaPago_menorQue = params.fechaPago_menorQue_value ? new Date().parse("dd/MM/yyyy", params.fechaPago_menorQue_value):null
        
        def montoDesde = null    
        def montoHasta = null    
        if (params.montoDesde)
            montoDesde = params.montoDesde.toFloat()
        if (params.montoHasta)
            montoHasta = params.montoHasta.toFloat()
        
        session.reporte_montoHasta = montoHasta
        session.reporte_montoDesde = montoDesde        
        
        
        if(fechaCreacion_mayorQue){
            session.reporteViatico_fechaCreacion_mayorQue = fechaCreacion_mayorQue.format("dd/MM/yyyy")
        }
        if(fechaCreacion_menorQue){
            session.reporteViatico_fechaCreacion_menorQue = fechaCreacion_menorQue.format("dd/MM/yyyy")
        }
        if(fechaPago_mayorQue){
            session.reporteViatico_fechaPago_mayorQue = fechaPago_mayorQue.format("dd/MM/yyyy")
        }
        if(fechaPago_menorQue){
            session.reporteViatico_fechaPago_menorQue = fechaPago_menorQue.format("dd/MM/yyyy")
        }
        
       
        def estadosNombre = ""
        def fechaCreacionEditada = null
        def fechaPagoEditada = null
        
        def busqueda=[]
        
        for (j in estadoViaticoListInstance)
        {
            estadosNombre += " ${j.nombre} \n"
            busqueda = SolicitudDeViaticos.findAllByEstado(j)
            
            busqueda.each{
               if ( (fechaCreacion_mayorQue !=null ? fechaCreacion_mayorQue <= it.fechaCreacion : true)
                    && (fechaCreacion_menorQue !=null ? fechaCreacion_menorQue >= it.fechaCreacion : true)
                    && ( it.fechaPago == null || (fechaPago_mayorQue !=null ? fechaPago_mayorQue <= it.fechaPago : true) )
                    && ( it.fechaPago == null || (fechaPago_menorQue !=null ? fechaPago_menorQue >= it.fechaPago : true))
                    && (montoDesde !=null ? montoDesde <= it.monto : true)
                    && (montoHasta !=null ? montoHasta >= it.monto : true)
                 ){                            
                     resultReport << it
                     if (it.fechaCreacion)
                        fechaCreacionEditada = it.fechaCreacion.format("dd/MM/yyyy")
                     if (it.fechaPago)
                        fechaPagoEditada = it.fechaPago.format("dd/MM/yyyy")
                      datos << [estado: it.estado, fechaCreacion: fechaCreacionEditada, fechaPago: fechaPagoEditada, monto: " \$  ${it.monto.toString()}" ]
                  }
             }
        }
           
         session.estadosNombre = estadosNombre
         session.resultReport = datos
        [SolicitudViaticoInstanceList: resultReport]    
    }
   
    def reporte () {
        
        params.estados = session.estadosNombre
        params.estadosProyecto = session.report_estadosProyecto        
        if(session.reporteViatico_fechaCreacion_mayorQue)
            params.fechaCreacion_mayorQue = session.reporteViatico_fechaCreacion_mayorQue
        else
            params.fechaCreacion_mayorQue = "Todas las fechas"
        if(session.reporteViatico_fechaCreacion_menorQue)     
            params.fechaCreacion_menorQue =  session.reporteViatico_fechaCreacion_menorQue
        else
            params.fechaCreacion_menorQue = "Todas las fechas"
        if(session.reporteViatico_fechaPago_mayorQue)    
            params.fechaPago_mayorQue = session.reporteViatico_fechaPago_mayorQue
        else
            params.fechaPago_mayorQue ="Todas las fechas"
        if(session.reporteViatico_fechaPago_menorQue)    
            params.fechaPago_menorQue = session.reporteViatico_fechaPago_menorQue
        else
            params.fechaPago_menorQue ="Todas las fechas"
        if(session.reporte_montoDesde)    
            params.montoDesde = " \$  ${session.reporte_montoDesde}" 
        else
            params.montoDesde = "Todos los montos"
        if(session.reporte_montoHasta)    
            params.montoHasta = " \$  ${session.reporte_montoHasta}"
        else
         params.montoHasta =  "Todos los montos"
    
        def datos = session.resultReport            
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
     }
    
}
