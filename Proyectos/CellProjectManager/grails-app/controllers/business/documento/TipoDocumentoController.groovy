package business.documento

import org.springframework.dao.DataIntegrityViolationException

class TipoDocumentoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoDocumentoInstanceList: TipoDocumento.list(params), tipoDocumentoInstanceTotal: TipoDocumento.count()]
    }

    def create() {
        [tipoDocumentoInstance: new TipoDocumento(params)]
    }

    def save() {
        def tipoDocumentoInstance = new TipoDocumento(params)
        if (!tipoDocumentoInstance.save(flush: true)) {
            render(view: "create", model: [tipoDocumentoInstance: tipoDocumentoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), tipoDocumentoInstance.id])
        redirect(action: "show", id: tipoDocumentoInstance.id)
    }

    def show() {
        def tipoDocumentoInstance = TipoDocumento.get(params.id)
        if (!tipoDocumentoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        [tipoDocumentoInstance: tipoDocumentoInstance]
    }

    def edit() {
        def tipoDocumentoInstance = TipoDocumento.get(params.id)
        if (!tipoDocumentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        [tipoDocumentoInstance: tipoDocumentoInstance]
    }

    def update() {
        def tipoDocumentoInstance = TipoDocumento.get(params.id)
        if (!tipoDocumentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tipoDocumentoInstance.version > version) {
                tipoDocumentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoDocumento.label', default: 'TipoDocumento')] as Object[],
                          "Another user has updated this TipoDocumento while you were editing")
                render(view: "edit", model: [tipoDocumentoInstance: tipoDocumentoInstance])
                return
            }
        }

        tipoDocumentoInstance.properties = params

        if (!tipoDocumentoInstance.save(flush: true)) {
            render(view: "edit", model: [tipoDocumentoInstance: tipoDocumentoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), tipoDocumentoInstance.id])
        redirect(action: "show", id: tipoDocumentoInstance.id)
    }

    def delete() {
        def tipoDocumentoInstance = TipoDocumento.get(params.id)
        if (!tipoDocumentoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tipoDocumentoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
