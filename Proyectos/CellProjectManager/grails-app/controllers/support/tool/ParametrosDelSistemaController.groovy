package support.tool

import org.springframework.dao.DataIntegrityViolationException

class ParametrosDelSistemaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [parametrosDelSistemaInstanceList: ParametrosDelSistema.list(params), parametrosDelSistemaInstanceTotal: ParametrosDelSistema.count()]
    }

    def create() {
        [parametrosDelSistemaInstance: new ParametrosDelSistema(params)]
    }

    def save() {
        def parametrosDelSistemaInstance = new ParametrosDelSistema(params)
        if (!parametrosDelSistemaInstance.save(flush: true)) {
            render(view: "create", model: [parametrosDelSistemaInstance: parametrosDelSistemaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), parametrosDelSistemaInstance.id])
        redirect(action: "show", id: parametrosDelSistemaInstance.id)
    }

    def show() {
        def parametrosDelSistemaInstance = ParametrosDelSistema.get(params.id)
        if (!parametrosDelSistemaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), params.id])
            redirect(action: "list")
            return
        }

        [parametrosDelSistemaInstance: parametrosDelSistemaInstance]
    }

    def edit() {
        def parametrosDelSistemaInstance = ParametrosDelSistema.get(params.id)
        if (!parametrosDelSistemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), params.id])
            redirect(action: "list")
            return
        }

        [parametrosDelSistemaInstance: parametrosDelSistemaInstance]
    }

    def update() {
        def parametrosDelSistemaInstance = ParametrosDelSistema.get(params.id)
        if (!parametrosDelSistemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (parametrosDelSistemaInstance.version > version) {
                parametrosDelSistemaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema')] as Object[],
                          "Another user has updated this ParametrosDelSistema while you were editing")
                render(view: "edit", model: [parametrosDelSistemaInstance: parametrosDelSistemaInstance])
                return
            }
        }

        parametrosDelSistemaInstance.properties = params

        if (!parametrosDelSistemaInstance.save(flush: true)) {
            render(view: "edit", model: [parametrosDelSistemaInstance: parametrosDelSistemaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), parametrosDelSistemaInstance.id])
        redirect(action: "show", id: parametrosDelSistemaInstance.id)
    }

    def delete() {
        def parametrosDelSistemaInstance = ParametrosDelSistema.get(params.id)
        if (!parametrosDelSistemaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), params.id])
            redirect(action: "list")
            return
        }

        try {
            parametrosDelSistemaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
