package business.herramienta

import org.springframework.dao.DataIntegrityViolationException

class EstadoHerramientaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [estadoHerramientaInstanceList: EstadoHerramienta.list(params), estadoHerramientaInstanceTotal: EstadoHerramienta.count()]
    }

    def create() {
        [estadoHerramientaInstance: new EstadoHerramienta(params)]
    }

    def save() {
        def estadoHerramientaInstance = new EstadoHerramienta(params)
        if (!estadoHerramientaInstance.save(flush: true)) {
            render(view: "create", model: [estadoHerramientaInstance: estadoHerramientaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), estadoHerramientaInstance.id])
        redirect(action: "show", id: estadoHerramientaInstance.id)
    }

    def show() {
        def estadoHerramientaInstance = EstadoHerramienta.get(params.id)
        if (!estadoHerramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        [estadoHerramientaInstance: estadoHerramientaInstance]
    }

    def edit() {
        def estadoHerramientaInstance = EstadoHerramienta.get(params.id)
        if (!estadoHerramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        [estadoHerramientaInstance: estadoHerramientaInstance]
    }

    def update() {
        def estadoHerramientaInstance = EstadoHerramienta.get(params.id)
        if (!estadoHerramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (estadoHerramientaInstance.version > version) {
                estadoHerramientaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta')] as Object[],
                          "Another user has updated this EstadoHerramienta while you were editing")
                render(view: "edit", model: [estadoHerramientaInstance: estadoHerramientaInstance])
                return
            }
        }

        estadoHerramientaInstance.properties = params

        if (!estadoHerramientaInstance.save(flush: true)) {
            render(view: "edit", model: [estadoHerramientaInstance: estadoHerramientaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), estadoHerramientaInstance.id])
        redirect(action: "show", id: estadoHerramientaInstance.id)
    }

    def delete() {
        def estadoHerramientaInstance = EstadoHerramienta.get(params.id)
        if (!estadoHerramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        try {
            estadoHerramientaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
