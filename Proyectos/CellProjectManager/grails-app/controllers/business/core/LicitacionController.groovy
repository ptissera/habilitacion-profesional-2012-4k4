package business.core

import org.springframework.dao.DataIntegrityViolationException

class LicitacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [licitacionInstanceList: Licitacion.list(params), licitacionInstanceTotal: Licitacion.count()]
    }

    def create() {
        [licitacionInstance: new Licitacion(params)]
    }

    def save() {
        def licitacionInstance = new Licitacion(params)
        if (!licitacionInstance.save(flush: true)) {
            render(view: "create", model: [licitacionInstance: licitacionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), licitacionInstance.id])
        redirect(action: "show", id: licitacionInstance.id)
    }

    def show() {
        def licitacionInstance = Licitacion.get(params.id)
        if (!licitacionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), params.id])
            redirect(action: "list")
            return
        }

        [licitacionInstance: licitacionInstance]
    }

    def edit() {
        def licitacionInstance = Licitacion.get(params.id)
        if (!licitacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), params.id])
            redirect(action: "list")
            return
        }

        [licitacionInstance: licitacionInstance]
    }

    def update() {
        def licitacionInstance = Licitacion.get(params.id)
        if (!licitacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (licitacionInstance.version > version) {
                licitacionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'licitacion.label', default: 'Licitacion')] as Object[],
                          "Another user has updated this Licitacion while you were editing")
                render(view: "edit", model: [licitacionInstance: licitacionInstance])
                return
            }
        }

        licitacionInstance.properties = params

        if (!licitacionInstance.save(flush: true)) {
            render(view: "edit", model: [licitacionInstance: licitacionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), licitacionInstance.id])
        redirect(action: "show", id: licitacionInstance.id)
    }

    def delete() {
        def licitacionInstance = Licitacion.get(params.id)
        if (!licitacionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), params.id])
            redirect(action: "list")
            return
        }

        try {
            licitacionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'licitacion.label', default: 'Licitacion'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
