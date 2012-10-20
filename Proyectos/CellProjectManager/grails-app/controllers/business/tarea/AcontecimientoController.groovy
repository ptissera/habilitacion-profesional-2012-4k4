package business.tarea
import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class AcontecimientoController {

     static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [acontecimientoInstanceList: Acontecimiento.list(params), acontecimientoInstanceTotal: Acontecimiento.count()]
    }

    def create() {
        def acontecimientoInstance = new Acontecimiento(params)
        def tareaSelected = session.getAttribute("tareaCreate")        
        if(!tareaSelected){            
            tareaSelected = session.getAttribute("tareaSelected")
        }
        session.setAttribute("acontecimientoSelected",acontecimientoInstance)
        [acontecimientoInstance: acontecimientoInstance]
    }

    def save() {
        def tareaSelected = session.getAttribute("tareaCreate")
        boolean isSolicitudCreate = true
        if(!tareaSelected){
            isSolicitudCreate = false
            tareaSelected = session.getAttribute("tareaSelected")
        }
              
        def acontecimientoInstance = new Acontecimiento(tarea: tareaSelected) 
        acontecimientoInstance.properties = params
        
        if (!acontecimientoInstance.save(flush: true)) {
            render(view: "create", model: [acontecimientoInstance: acontecimientoInstance])
            return
        }
       
        if(isSolicitudCreate){ 
            redirect(controller: "tarea", action: "edit")
        }else{
            redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
        }
    }

    def show() {
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("AcontecimientoSelected",acontecimientoInstance)
        [acontecimientoInstance: acontecimientoInstance]
    }

    def edit() {
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("AcontecimientoSelected",acontecimientoInstance)
        [acontecimientoInstance: acontecimientoInstance]
    }

    def update() {
         
        def tareaSelected = session.getAttribute("tareaCreate")
        boolean isSolicitudCreate = true
        if(!tareaSelected){
            isSolicitudCreate = false
            tareaSelected = session.getAttribute("tareaSelected")
        }
       
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (acontecimientoInstance.version > version) {
                acontecimientoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'Acontecimiento.label', default: 'Acontecimiento')] as Object[],
                          "Another user has updated this Acontecimiento while you were editing")
                render(view: "edit", model: [acontecimientoInstance: acontecimientoInstance])
                return
            }
        }

        acontecimientoInstance.properties = params
        if (!acontecimientoInstance.save(flush: true)) {
            render(view: "edit", model: [acontecimientoInstance: acontecimientoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'acontecimiento.label', default: 'Acontecimiento'), acontecimientoInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "tarea", action: "create")
        }else{
            redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
        }
    }

    def delete() {
        
        def tareaSelected = session.getAttribute("tareaCreate")
        boolean isSolicitudCreate = true
        if(!tareaSelected){
            isSolicitudCreate = false
            tareaSelected = session.getAttribute("tareaSelected")
        }
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        try {
            acontecimientoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            if(isSolicitudCreate){ 
                redirect(controller: "tarea", action: "create")
            }else{
                redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
            }
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "show", id: params.id)
        }
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
    
    def doGetRest (params){
        def tarea = Tarea.findById(params.id)
        def acontecimientoInstance = tarea ? Acontecimiento.findAllByTarea(tarea) : []
        
        if (acontecimientoInstance) {
                render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }, 'acontecimientos': $acontecimientoInstance}") as JSON
                
            }
        else{
                response.status=200
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            }
        
    }
    
    def doPostRest (params){
        def acontecimientoInstance = Acontecimiento.findById(params.id)
        acontecimientoInstance.properties= request.JSON
        if (acontecimientoInstance.save(flush:true)) {
                render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }}") as JSON
                
            }
        else{
                response.status=200
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            }
    }
}
