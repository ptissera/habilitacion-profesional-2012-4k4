package business.herramienta

import org.springframework.dao.DataIntegrityViolationException

class HerramientaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [herramientaInstanceList: Herramienta.list(params), herramientaInstanceTotal: Herramienta.count()]
    }

    def create() {
        [herramientaInstance: new Herramienta(params)]
    }

    def save() {
        def herramientaInstance = new Herramienta(params)
        if (!herramientaInstance.save(flush: true)) {
            render(view: "create", model: [herramientaInstance: herramientaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), herramientaInstance.id])
        redirect(action: "show", id: herramientaInstance.id)
    }

    def show() {
        def herramientaInstance = Herramienta.get(params.id)
        if (!herramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), params.id])
            redirect(action: "list")
            return
        }

        [herramientaInstance: herramientaInstance]
    }

    def edit() {
        def herramientaInstance = Herramienta.get(params.id)
        if (!herramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), params.id])
            redirect(action: "list")
            return
        }

        [herramientaInstance: herramientaInstance]
    }

    def update() {
        def herramientaInstance = Herramienta.get(params.id)
        if (!herramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (herramientaInstance.version > version) {
                herramientaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'herramienta.label', default: 'Herramienta')] as Object[],
                          "Another user has updated this Herramienta while you were editing")
                render(view: "edit", model: [herramientaInstance: herramientaInstance])
                return
            }
        }

        herramientaInstance.properties = params

        if (!herramientaInstance.save(flush: true)) {
            render(view: "edit", model: [herramientaInstance: herramientaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), herramientaInstance.id])
        redirect(action: "show", id: herramientaInstance.id)
    }

    def delete() {
        def herramientaInstance = Herramienta.get(params.id)
        if (!herramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), params.id])
            redirect(action: "list")
            return
        }

        try {
            herramientaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'herramienta.label', default: 'Herramienta'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
