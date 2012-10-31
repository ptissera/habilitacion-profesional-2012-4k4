package business.herramienta

import org.springframework.dao.DataIntegrityViolationException

class PrestamoHerramientaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [prestamoHerramientaInstanceList: PrestamoHerramienta.list(params), prestamoHerramientaInstanceTotal: PrestamoHerramienta.count()]
    }

    def create() {
        session.setAttribute("prestamosSelectedTF",true)
        [prestamoHerramientaInstance: new PrestamoHerramienta(params)]
    }

    def save() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        def prestamoHerramientaInstance = new PrestamoHerramienta(solicitud: solicitudDeTareaSelected, cuadrilla: solicitudDeTareaSelected.cuadrila) 
        prestamoHerramientaInstance.properties = params
        if (!prestamoHerramientaInstance.save(flush: true)) {
            render(view: "create", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), prestamoHerramientaInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def show() {
        session.setAttribute("prestamosSelectedTF",true)
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        [prestamoHerramientaInstance: prestamoHerramientaInstance]
    }

    def edit() {
        session.setAttribute("prestamosSelectedTF",true)
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        [prestamoHerramientaInstance: prestamoHerramientaInstance]
    }

    def update() {
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (prestamoHerramientaInstance.version > version) {
                prestamoHerramientaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta')] as Object[],
                          "Another user has updated this PrestamoHerramienta while you were editing")
                render(view: "edit", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
                return
            }
        }

        prestamoHerramientaInstance.properties = params

        if (!prestamoHerramientaInstance.save(flush: true)) {
            render(view: "edit", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
            return
        }

	flash.message = message(code: 'default.updated.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), prestamoHerramientaInstance.id])
        
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def delete() {
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        try {
            prestamoHerramientaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
              def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
            if(isSolicitudCreate){ 
                redirect(controller: "solicitudDeTarea", action: "create")
            }else{
                redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
            }
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
