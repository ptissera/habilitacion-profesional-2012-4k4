package business.core

import org.springframework.dao.DataIntegrityViolationException

class ProvinciaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [provinciaInstanceList: Provincia.list(params), provinciaInstanceTotal: Provincia.count()]
    }

    def create() {
        [provinciaInstance: new Provincia(params)]
    }

    def save() {
        def provinciaInstance = new Provincia(params)
        if (!provinciaInstance.save(flush: true)) {
            render(view: "create", model: [provinciaInstance: provinciaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'provincia.label', default: 'Provincia'), provinciaInstance.id])
        redirect(action: "show", id: provinciaInstance.id)
    }

    def show() {
        def provinciaInstance = Provincia.get(params.id)
        if (!provinciaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])
            redirect(action: "list")
            return
        }

        [provinciaInstance: provinciaInstance]
    }

    def edit() {
        def provinciaInstance = Provincia.get(params.id)
        if (!provinciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])
            redirect(action: "list")
            return
        }

        [provinciaInstance: provinciaInstance]
    }

    def update() {
        def provinciaInstance = Provincia.get(params.id)
        if (!provinciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (provinciaInstance.version > version) {
                provinciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'provincia.label', default: 'Provincia')] as Object[],
                          "Another user has updated this Provincia while you were editing")
                render(view: "edit", model: [provinciaInstance: provinciaInstance])
                return
            }
        }

        provinciaInstance.properties = params

        if (!provinciaInstance.save(flush: true)) {
            render(view: "edit", model: [provinciaInstance: provinciaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'provincia.label', default: 'Provincia'), provinciaInstance.id])
        redirect(action: "show", id: provinciaInstance.id)
    }

    def delete() {
        def provinciaInstance = Provincia.get(params.id)
        if (!provinciaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])
            redirect(action: "list")
            return
        }

        try {
            provinciaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
