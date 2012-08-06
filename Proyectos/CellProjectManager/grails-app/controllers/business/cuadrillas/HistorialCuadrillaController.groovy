package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class HistorialCuadrillaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [historialCuadrillaInstanceList: HistorialCuadrilla.list(params), historialCuadrillaInstanceTotal: HistorialCuadrilla.count()]
    }

    def create() {
        [historialCuadrillaInstance: new HistorialCuadrilla(params)]
    }

    def save() {
        def historialCuadrillaInstance = new HistorialCuadrilla(params)
        if (!historialCuadrillaInstance.save(flush: true)) {
            render(view: "create", model: [historialCuadrillaInstance: historialCuadrillaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), historialCuadrillaInstance.id])
        redirect(action: "show", id: historialCuadrillaInstance.id)
    }

    def show() {
        def historialCuadrillaInstance = HistorialCuadrilla.get(params.id)
        if (!historialCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        [historialCuadrillaInstance: historialCuadrillaInstance]
    }

    def edit() {
        def historialCuadrillaInstance = HistorialCuadrilla.get(params.id)
        if (!historialCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        [historialCuadrillaInstance: historialCuadrillaInstance]
    }

    def update() {
        def historialCuadrillaInstance = HistorialCuadrilla.get(params.id)
        if (!historialCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (historialCuadrillaInstance.version > version) {
                historialCuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla')] as Object[],
                          "Another user has updated this HistorialCuadrilla while you were editing")
                render(view: "edit", model: [historialCuadrillaInstance: historialCuadrillaInstance])
                return
            }
        }

        historialCuadrillaInstance.properties = params

        if (!historialCuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [historialCuadrillaInstance: historialCuadrillaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), historialCuadrillaInstance.id])
        redirect(action: "show", id: historialCuadrillaInstance.id)
    }

    def delete() {
        def historialCuadrillaInstance = HistorialCuadrilla.get(params.id)
        if (!historialCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        try {
            historialCuadrillaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
