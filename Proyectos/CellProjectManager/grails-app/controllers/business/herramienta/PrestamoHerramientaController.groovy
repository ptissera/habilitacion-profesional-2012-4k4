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
        [prestamoHerramientaInstance: new PrestamoHerramienta(params)]
    }

    def save() {
        def prestamoHerramientaInstance = new PrestamoHerramienta(params)
        if (!prestamoHerramientaInstance.save(flush: true)) {
            render(view: "create", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), prestamoHerramientaInstance.id])
        redirect(action: "show", id: prestamoHerramientaInstance.id)
    }

    def show() {
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        [prestamoHerramientaInstance: prestamoHerramientaInstance]
    }

    def edit() {
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
        redirect(action: "show", id: prestamoHerramientaInstance.id)
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
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
