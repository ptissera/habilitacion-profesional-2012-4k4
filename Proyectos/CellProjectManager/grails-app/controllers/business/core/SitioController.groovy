package business.core

import org.springframework.dao.DataIntegrityViolationException

class SitioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sitioInstanceList: Sitio.list(params), sitioInstanceTotal: Sitio.count()]
    }

    def create() {
        [sitioInstance: new Sitio(params)]
    }

    def save() {
        def sitioInstance = new Sitio(params)
        if (!sitioInstance.save(flush: true)) {
            render(view: "create", model: [sitioInstance: sitioInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'sitio.label', default: 'Sitio'), sitioInstance.id])
        redirect(action: "show", id: sitioInstance.id)
    }

    def show() {
        def sitioInstance = Sitio.get(params.id)
        if (!sitioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'sitio.label', default: 'Sitio'), params.id])
            redirect(action: "list")
            return
        }

        [sitioInstance: sitioInstance]
    }

    def edit() {
        def sitioInstance = Sitio.get(params.id)
        if (!sitioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sitio.label', default: 'Sitio'), params.id])
            redirect(action: "list")
            return
        }

        [sitioInstance: sitioInstance]
    }

    def update() {
        def sitioInstance = Sitio.get(params.id)
        if (!sitioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sitio.label', default: 'Sitio'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (sitioInstance.version > version) {
                sitioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'sitio.label', default: 'Sitio')] as Object[],
                          "Another user has updated this Sitio while you were editing")
                render(view: "edit", model: [sitioInstance: sitioInstance])
                return
            }
        }

        sitioInstance.properties = params

        if (!sitioInstance.save(flush: true)) {
            render(view: "edit", model: [sitioInstance: sitioInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'sitio.label', default: 'Sitio'), sitioInstance.id])
        redirect(action: "show", id: sitioInstance.id)
    }

    def delete() {
        def sitioInstance = Sitio.get(params.id)
        if (!sitioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'sitio.label', default: 'Sitio'), params.id])
            redirect(action: "list")
            return
        }

        try {
            sitioInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'sitio.label', default: 'Sitio'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sitio.label', default: 'Sitio'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
