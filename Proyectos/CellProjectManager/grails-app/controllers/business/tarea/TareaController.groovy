package business.tarea
import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import support.secure.*
import business.cuadrillas.*
import business.core.Sitio
import java.text.SimpleDateFormat

class TareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tareaInstanceList: Tarea.list(params), tareaInstanceTotal: Tarea.count()]
    }

    def create() {
        cleanSelected()
        def tareaInstance = new Tarea(params)
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")        
        if(!solicitudDeTareaSelected){            
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        tareaInstance.ordenEjecucion = solicitudDeTareaSelected.tarea == null ? 1 : solicitudDeTareaSelected.tarea.size() + 1
        session.setAttribute("tareaSelected",tareaInstance)
        [tareaInstance: tareaInstance]
    }

    def save() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
              
        def tareaInstance = new Tarea(solicitudDeTarea: solicitudDeTareaSelected) 
        tareaInstance.properties = params
        if (tareaInstance.estado == null){ 
            tareaInstance.estado = EstadoTarea.findByNombre('Creada')
        }
        
        def f = request.getFile('uploadArchivo')
        if(!f.empty) {
            tareaInstance.documentoDeIngenieria = f.getOriginalFilename()
            tareaInstance.archivo = f.inputStream.bytes
        }    else {
            flash.message = 'file cannot be empty'          
        }
        if (!tareaInstance.save(flush: true)) {
            render(view: "create", model: [tareaInstance: tareaInstance])
            return
        }
       
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def show() {
        cleanSelected()
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("tareaSelected",tareaInstance)
        [tareaInstance: tareaInstance]
    }

    def edit() {
        cleanSelected()
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("tareaSelected",tareaInstance)
        [tareaInstance: tareaInstance]
    }

    def update() {
         
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
       
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tareaInstance.version > version) {
                tareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'tarea.label', default: 'Tarea')] as Object[],
                          "Another user has updated this Tarea while you were editing")
                render(view: "edit", model: [tareaInstance: tareaInstance])
                return
            }
        }

        tareaInstance.properties = params
        def f = request.getFile('uploadArchivo')
        if(!f.empty) {
            tareaInstance.documentoDeIngenieria = f.getOriginalFilename()
            tareaInstance.archivo = f.inputStream.bytes
        }    else {
            flash.message = 'file cannot be empty'          
        }
        if (!tareaInstance.save(flush: true)) {
            render(view: "edit", model: [tareaInstance: tareaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tarea.label', default: 'Tarea'), tareaInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def delete() {
        cleanSelected()
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tareaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            if(isSolicitudCreate){ 
                redirect(controller: "solicitudDeTarea", action: "create")
            }else{
                redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
            }
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def cleanSelected() 
    {
        [ 
        "equipoDeTareaSelectedTF",
        "materialDeTareaSelectedTF",
        "permisoAccesoSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
    }
    
    def downloadFile(){
        def tareaInstance = Tarea.get(params.id)

        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=${tareaInstance.documentoDeIngenieria}")
        response.outputStream << tareaInstance.archivo
   
    }
    
    def rest(){
        switch(request.method)
        {
            case 'GET':
            doGetRest(params)
            break;
            case 'POST':
            doPostRest(params)
            break;     
        }  
        
    }
    
    private void doGetRest(params)
    {   def usuario = Usuario.findById(params.id)
        def integranteCuadrilla = usuario ? IntegranteCuadrilla.findByUsuario(usuario) : []
        def cuadrilla = integranteCuadrilla.cuadrilla.id ? Cuadrilla.findById(integranteCuadrilla.cuadrilla.id) : []
        def estadoSolicitud = EstadoSolicitudTarea.findByNombre('En ejecucion')
        def tareas = SolicitudDeTarea.findAllByEstadoAndCuadrilla(estadoSolicitud,cuadrilla).tarea
        boolean finLista = false            
        def respuesta
        def i=0
        boolean esPrimetasLinea = true
        def id 
        def tipoTarea
        def fechaInicioEstimada
        def fechaFinEstimada        
        def fechaInicioReal
        def fechaFinReal
        def estado
        def observaciones        
        def elsitio
        tareas.each{
              while (!finLista)              
              {
                id = it.id[i]
                tipoTarea = it.tipoTarea[i].nombre
                fechaInicioEstimada = it.fechaInicio[i].format("ddMMyyyy")
                fechaFinEstimada = it.fechaFin[i].format("ddMMyyyy")
                fechaInicioReal =  it.fechaInicioReal[i] ? it.fechaInicioReal[i].format("ddMMyyyy") : ""
                fechaFinReal = it.fechaFinReal[i] ? it.fechaFinReal[i].format("ddMMyyyy") : ""
                estado= it.estado[i].nombre
                observaciones = it.observaciones[i]
                elsitio = it.sitio[i].nombre
                respuesta = (esPrimetasLinea ? "": respuesta + ", ") + "{ \"id\": $id, \"nombreSitio\": \"$elsitio\", \"nombreTipoTarea\": \"$tipoTarea\", \"fechaInicioEstimada\": \"$fechaInicioEstimada\", \"fechaFinEstimada\": \"$fechaFinEstimada\", \"fechaInicioReal\": \"$fechaInicioReal\", \"fechaFinReal\": \"$fechaFinReal\", \"estado\": \"$estado\", \"observaciones\": \"$observaciones\" } "
                esPrimetasLinea=false
                i++
                if (it.id[i] == null)
                 finLista = true
              }  
            }
            
        if (respuesta) {
                response.status =200
                render  JSON.parse("{ \"error\": { \"codigo\": 0, \"descripcion\": \"Exito\" }, \"tareas\":[$respuesta]}") as JSON
        } else {
            response.status=200
            render  JSON.parse("{ \"error\": { \"codigo\": 2, \"descripcion\": \"No hay tareas a realizar para su cuadrilla.\" }}") as JSON            
        }
    }
    
    private void doPostRest(params)
    {   def tarea = Tarea.get(params.id)
        
        def objetoJSON = request.JSON
                
        tarea.observaciones = objetoJSON.observaciones
        tarea.estado = EstadoTarea.findByNombre(objetoJSON.estado)
        if (objetoJSON.fechaFinReal != "")
            tarea.fechaFinReal = new Date().parse("ddMMyyyy", objetoJSON.fechaFinReal) 
        else 
            tarea.fechaFinReal = null;
        if (objetoJSON.fechaInicioReal != "")            
            tarea.fechaInicioReal = new Date().parse("ddMMyyyy", objetoJSON.fechaInicioReal) 
        tarea.tipoTarea = TipoTarea.findByNombre(objetoJSON.nombreTipoTarea)
        if (tarea.save(flush: true)) {
            verificarSolicitudTarea(tarea)
            render  JSON.parse("{ \"error\": { \"codigo\": 0, \"descripcion\": \"Exito\" }}") as JSON
                
        }
        response.status=200
        render JSON.parse("{ \"error\": { \"codigo\": 1, \"descripcion\": \"Fallo\" }}") as JSON
    }
    
 def verificarSolicitudTarea(tarea)   
 {
     def solicitud = SolicitudDeTarea.findById(tarea.solicitudDeTarea.id)
     def tareas = Tarea.findAllBySolicitudDeTarea(tarea.solicitudDeTarea)
     boolean finLista = false
     boolean tieneTareaActiva = false
     def i=0
     tareas.each{
                if (it.estado.nombre == "En Ejecucion")
                 tieneTareaActiva = true
            }
     if (!tieneTareaActiva){
         finLista = false
         boolean tieneTareaResuelta = true
          tareas.each{
                // puede llegar a estar alguna tarea suspendida, en ese caso la ST esta En ejecucion
                if (it.estado.nombre != "Resuelta")
                 tieneTareaResuelta = false
            }
         if (!tieneTareaResuelta) {
           solicitud.estado = EstadoSolicitudTarea.findByNombre("En Ejecucion")
           solicitud.save(flush:true)
         }  else {
           solicitud.estado = EstadoSolicitudTarea.findByNombre('Pendiente Conformidad')
           solicitud.save(flush:true)
         }
         
            
     }     
     else{
         solicitud.estado = EstadoSolicitudTarea.findByNombre("En Ejecucion")
         solicitud.save(flush:true)
     }
        
     
 }
    
}


