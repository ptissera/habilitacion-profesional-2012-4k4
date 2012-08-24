package business.core

import org.springframework.dao.DataIntegrityViolationException

class POController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [POInstanceList: PO.list(params), POInstanceTotal: PO.count()]
    }

    def create() {
        [POInstance: new PO(params)]
    }

    def save() {
        def POInstance = new PO(params)
        def f = request.getFile('nombreArchivo')
        if(!f.empty) {
            POInstance.nombreArchivo = f.getOriginalFilename()
            POInstance.archivo = f.inputStream.bytes
        }    else {
	       flash.message = 'file cannot be empty'          
	    }
        if (!POInstance.save(flush: true)) {
            render(view: "create", model: [POInstance: POInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'PO.label', default: 'PO'), POInstance.id])
        redirect(action: "show", id: POInstance.id)
    }

    def show() {
        def POInstance = PO.get(params.id)
        if (!POInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        [POInstance: POInstance]
    }

    def edit() {
        def POInstance = PO.get(params.id)
        if (!POInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        [POInstance: POInstance]
    }

    def update() {
        def POInstance = PO.get(params.id)
        if (!POInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (POInstance.version > version) {
                POInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'PO.label', default: 'PO')] as Object[],
                          "Another user has updated this PO while you were editing")
                render(view: "edit", model: [POInstance: POInstance])
                return
            }
        }

        POInstance.properties = params

        if (!POInstance.save(flush: true)) {
            render(view: "edit", model: [POInstance: POInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'PO.label', default: 'PO'), POInstance.id])
        redirect(action: "show", id: POInstance.id)
    }

    def delete() {
        def POInstance = PO.get(params.id)
        if (!POInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        try {
            POInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
