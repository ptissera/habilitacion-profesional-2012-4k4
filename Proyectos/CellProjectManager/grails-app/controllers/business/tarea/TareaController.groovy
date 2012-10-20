package business.tarea
import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import support.secure.*
import business.cuadrillas.*
import business.core.Sitio

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
    {   //def tareas = Tarea.get(params.id)
      //  def tareas = Tarea.executeQuery("select t.id, t.sitio_id, s.id from tarea t, solicitud_de_tarea s where t.solicitud_de_tarea_id = s.id and t.sitio_id = 1 and s.cuadrilla_id = 1; ")
        
        def usuario = Usuario.findById(params.idCuadrilla)
        if (usuario.isJefeCuadrilla())
        {
            def integranteCuadrilla = usuario ? IntegranteCuadrilla.findByUsuario(usuario) : []
            def cuadrilla = integranteCuadrilla.cuadrilla.id ? Cuadrilla.findById(integranteCuadrilla.cuadrilla.id) : []
            def solicitudesTareas = cuadrilla ? SolicitudDeTarea.findAllByCuadrilla(cuadrilla) : []
            // por cada solicitud que este en ejecucion
            def tareas = solicitudesTareas ? Tarea.findAllBySolicitudDeTarea(solicitudesTareas) : []
            def sitio = Sitio.findById(params.idSitio)
            def respuesta = sitio ? tareas.findAllBySitio(sitio) : []
            /*
            for( i in tareas ) {  
             respuesta = respuesta + "${i.findBySitio(sitio)}  "  
            } */
            
                       //respuesta = respuesta + sitio ? tareas[i].findBySitio(sitio) : []
            
             
            render respuesta as JSON
            if (respuesta) {
                    response.status =200
                    render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }}") as JSON
                }
            else{
                    response.status=200
                    render  JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
                }
        }
        else
        {
            response.status =200
            render  JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON 
        }
    }
    
    private void doPostRest(params)
    {   def tarea = Tarea.get(params.id)
            tarea.properties = request.JSON
      
        if (tarea.save(flush: true)) {
                render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }}") as JSON
                
            }
        else{
                response.status=200
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            }
    }
}
