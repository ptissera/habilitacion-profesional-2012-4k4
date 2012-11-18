package business.informe

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
             
            def desde = params.desde ? new Date().parse("dd/MM/yyyy", params.desde_value) : null
            def hasta = params.hasta ? new Date().parse("dd/MM/yyyy", params.hasta_value) : null
           
            
            if(desde){
                session.informe_desde = desde.format("dd/MM/yyyy")
            }
            if(hasta){
                session.informe_hasta = hasta.format("dd/MM/yyyy")
            }
            
            def datos = [] 
                        
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
