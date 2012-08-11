package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class UnidadMedidaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [unidadMedidaInstanceList: UnidadMedida.list(params), unidadMedidaInstanceTotal: UnidadMedida.count()]
    }

    def create() {
        [unidadMedidaInstance: new UnidadMedida(params)]
    }

    def save() {
        def unidadMedidaInstance = new UnidadMedida(params)
        if (!unidadMedidaInstance.save(flush: true)) {
            render(view: "create", model: [unidadMedidaInstance: unidadMedidaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), unidadMedidaInstance.id])
        redirect(action: "show", id: unidadMedidaInstance.id)
    }

    def show() {
        def unidadMedidaInstance = UnidadMedida.get(params.id)
        if (!unidadMedidaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), params.id])
            redirect(action: "list")
            return
        }

        [unidadMedidaInstance: unidadMedidaInstance]
    }

    def edit() {
        def unidadMedidaInstance = UnidadMedida.get(params.id)
        if (!unidadMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), params.id])
            redirect(action: "list")
            return
        }

        [unidadMedidaInstance: unidadMedidaInstance]
    }

    def update() {
        def unidadMedidaInstance = UnidadMedida.get(params.id)
        if (!unidadMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (unidadMedidaInstance.version > version) {
                unidadMedidaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'unidadMedida.label', default: 'UnidadMedida')] as Object[],
                          "Another user has updated this UnidadMedida while you were editing")
                render(view: "edit", model: [unidadMedidaInstance: unidadMedidaInstance])
                return
            }
        }

        unidadMedidaInstance.properties = params

        if (!unidadMedidaInstance.save(flush: true)) {
            render(view: "edit", model: [unidadMedidaInstance: unidadMedidaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), unidadMedidaInstance.id])
        redirect(action: "show", id: unidadMedidaInstance.id)
    }

    def delete() {
        def unidadMedidaInstance = UnidadMedida.get(params.id)
        if (!unidadMedidaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), params.id])
            redirect(action: "list")
            return
        }

        try {
            unidadMedidaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
