package business.documento

import org.springframework.dao.DataIntegrityViolationException

class EstadoDocumentoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [estadoDocumentoInstanceList: EstadoDocumento.list(params), estadoDocumentoInstanceTotal: EstadoDocumento.count()]
    }

    def create() {
        [estadoDocumentoInstance: new EstadoDocumento(params)]
    }

    def save() {
        def estadoDocumentoInstance = new EstadoDocumento(params)
        if (!estadoDocumentoInstance.save(flush: true)) {
            render(view: "create", model: [estadoDocumentoInstance: estadoDocumentoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), estadoDocumentoInstance.id])
        redirect(action: "show", id: estadoDocumentoInstance.id)
    }

    def show() {
        def estadoDocumentoInstance = EstadoDocumento.get(params.id)
        if (!estadoDocumentoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        [estadoDocumentoInstance: estadoDocumentoInstance]
    }

    def edit() {
        def estadoDocumentoInstance = EstadoDocumento.get(params.id)
        if (!estadoDocumentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        [estadoDocumentoInstance: estadoDocumentoInstance]
    }

    def update() {
        def estadoDocumentoInstance = EstadoDocumento.get(params.id)
        if (!estadoDocumentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (estadoDocumentoInstance.version > version) {
                estadoDocumentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadoDocumento.label', default: 'EstadoDocumento')] as Object[],
                          "Another user has updated this EstadoDocumento while you were editing")
                render(view: "edit", model: [estadoDocumentoInstance: estadoDocumentoInstance])
                return
            }
        }

        estadoDocumentoInstance.properties = params

        if (!estadoDocumentoInstance.save(flush: true)) {
            render(view: "edit", model: [estadoDocumentoInstance: estadoDocumentoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), estadoDocumentoInstance.id])
        redirect(action: "show", id: estadoDocumentoInstance.id)
    }

    def delete() {
        def estadoDocumentoInstance = EstadoDocumento.get(params.id)
        if (!estadoDocumentoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        try {
            estadoDocumentoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadoDocumento.label', default: 'EstadoDocumento'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
