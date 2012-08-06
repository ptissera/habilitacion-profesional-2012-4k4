package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class EstadoCuadrillaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [estadoCuadrillaInstanceList: EstadoCuadrilla.list(params), estadoCuadrillaInstanceTotal: EstadoCuadrilla.count()]
    }

    def create() {
        [estadoCuadrillaInstance: new EstadoCuadrilla(params)]
    }

    def save() {
        def estadoCuadrillaInstance = new EstadoCuadrilla(params)
        if (!estadoCuadrillaInstance.save(flush: true)) {
            render(view: "create", model: [estadoCuadrillaInstance: estadoCuadrillaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), estadoCuadrillaInstance.id])
        redirect(action: "show", id: estadoCuadrillaInstance.id)
    }

    def show() {
        def estadoCuadrillaInstance = EstadoCuadrilla.get(params.id)
        if (!estadoCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        [estadoCuadrillaInstance: estadoCuadrillaInstance]
    }

    def edit() {
        def estadoCuadrillaInstance = EstadoCuadrilla.get(params.id)
        if (!estadoCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        [estadoCuadrillaInstance: estadoCuadrillaInstance]
    }

    def update() {
        def estadoCuadrillaInstance = EstadoCuadrilla.get(params.id)
        if (!estadoCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (estadoCuadrillaInstance.version > version) {
                estadoCuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla')] as Object[],
                          "Another user has updated this EstadoCuadrilla while you were editing")
                render(view: "edit", model: [estadoCuadrillaInstance: estadoCuadrillaInstance])
                return
            }
        }

        estadoCuadrillaInstance.properties = params

        if (!estadoCuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [estadoCuadrillaInstance: estadoCuadrillaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), estadoCuadrillaInstance.id])
        redirect(action: "show", id: estadoCuadrillaInstance.id)
    }

    def delete() {
        def estadoCuadrillaInstance = EstadoCuadrilla.get(params.id)
        if (!estadoCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        try {
            estadoCuadrillaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadoCuadrilla.label', default: 'EstadoCuadrilla'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
