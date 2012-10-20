package business.tarea
import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TipoAcontecimientoController {
      static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoAcontecimientoInstanceList: TipoAcontecimiento.list(params), tipoAcontecimientoInstanceTotal: TipoAcontecimiento.count()]
    }

    def create() {
        [tipoAcontecimientoInstance: new TipoAcontecimiento(params)]
    }

    def save() {
        def tipoAcontecimientoInstance = new TipoAcontecimiento(params)
        if (!tipoAcontecimientoInstance.save(flush: true)) {
            render(view: "create", model: [tipoAcontecimientoInstance: tipoAcontecimientoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), tipoAcontecimientoInstance.id])
        redirect(action: "show", id: tipoAcontecimientoInstance.id)
    }

    def show() {
        def tipoAcontecimientoInstance = TipoAcontecimiento.get(params.id)
        if (!tipoAcontecimientoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        [tipoAcontecimientoInstance: tipoAcontecimientoInstance]
    }

    def edit() {
        def tipoAcontecimientoInstance = TipoAcontecimiento.get(params.id)
        if (!tipoAcontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        [tipoAcontecimientoInstance: tipoAcontecimientoInstance]
    }

    def update() {
        def tipoAcontecimientoInstance = TipoAcontecimiento.get(params.id)
        if (!tipoAcontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tipoAcontecimientoInstance.version > version) {
                tipoAcontecimientoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento')] as Object[],
                          "Another user has updated this TipoAcontecimiento while you were editing")
                render(view: "edit", model: [tipoAcontecimientoInstance: tipoAcontecimientoInstance])
                return
            }
        }

        tipoAcontecimientoInstance.properties = params

        if (!tipoAcontecimientoInstance.save(flush: true)) {
            render(view: "edit", model: [tipoAcontecimientoInstance: tipoAcontecimientoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), tipoAcontecimientoInstance.id])
        redirect(action: "show", id: tipoAcontecimientoInstance.id)
    }

    def delete() {
        def tipoAcontecimientoInstance = TipoAcontecimiento.get(params.id)
        if (!tipoAcontecimientoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tipoAcontecimientoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'TipoAcontecimiento.label', default: 'TipoAcontecimiento'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def rest(){
         switch(request.method)
        {
            case 'GET':
                doGetRest()
                break;
        }  
        
    }
    
    def doGetRest (){
        def tipoAcontecimiento = TipoAcontecimiento.getAll()
      
        if (tipoAcontecimiento) {
                render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }, 'acontecimientos': $tipoAcontecimiento}") as JSON
                
            }
        else{
                response.status=200
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            }
        
    }
}

