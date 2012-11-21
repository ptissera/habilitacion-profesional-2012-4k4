package business.reporte
import business.solicitud.*

class ReporteSolicitudViaticoController {

    def index() {      
        redirect(action: "step1", params: params)
    }
    
    def step1(){
        [estadoViaticoListInstance: EstadoSolicitudDeViaticos.list()]
    }
    
    def step2(){
        def estadoViaticoListInstance = params.estadoViaticoIds.collect { EstadoSolicitudDeViaticos.get(it) }        
        if(!estadoViaticoListInstance){
            flash.message = "Debe seleccionar al menos un Estado"
            render(view: "step1", model: [estadoViaticoListInstance: estadoViaticoListInstance])
        } else {
            session.estados = estadoViaticoListInstance
        }
        
    }
    
    def step3() {
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
            session.reporte_fechaCreacion_mayorQue = fechaCreacion_mayorQue.format("dd/MM/yyyy")
        }
        if(fechaCreacion_menorQue){
            session.reporte_fechaCreacion_menorQue = fechaCreacion_menorQue.format("dd/MM/yyyy")
        }
        if(fechaPago_mayorQue){
            session.reporte_fechaPago_mayorQue = fechaPago_mayorQue.format("dd/MM/yyyy")
        }
        if(fechaPago_menorQue){
            session.reporte_fechaPago_menorQue = fechaPago_menorQue.format("dd/MM/yyyy")
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
                      datos << [estado: it.estado, fechaCreacion: fechaCreacionEditada, fechaPago: fechaPagoEditada, monto: it.monto]
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
        params.fechaCreacion_mayorQue = session.reporte_fechaCreacion_mayorQue
        params.fechaCreacion_menorQue =  session.reporte_fechaCreacion_menorQue
        params.fechaPago_mayorQue = session.reporte_fechaPago_mayorQue
        params.fechaPago_menorQue = session.reporte_fechaPago_menorQue
        params.montoDesde = session.reporte_montoDesde.toString()
        params.montoHasta = session.reporte_montoHasta.toString()
    
        def datos = session.resultReport            
        chain(controller: "jasper", action: "index", model: [data: datos], params:params)
     }
    
}
