package business.core

import org.springframework.dao.DataIntegrityViolationException

class EstadoProyectoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [estadoProyectoInstanceList: EstadoProyecto.list(params), estadoProyectoInstanceTotal: EstadoProyecto.count()]
    }

    def create() {
        [estadoProyectoInstance: new EstadoProyecto(params)]
    }

    def save() {
        def estadoProyectoInstance = new EstadoProyecto(params)
        if (!estadoProyectoInstance.save(flush: true)) {
            render(view: "create", model: [estadoProyectoInstance: estadoProyectoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), estadoProyectoInstance.id])
        redirect(action: "show", id: estadoProyectoInstance.id)
    }

    def show() {
        def estadoProyectoInstance = EstadoProyecto.get(params.id)
        if (!estadoProyectoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), params.id])
            redirect(action: "list")
            return
        }

        [estadoProyectoInstance: estadoProyectoInstance]
    }

    def edit() {
        def estadoProyectoInstance = EstadoProyecto.get(params.id)
        if (!estadoProyectoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), params.id])
            redirect(action: "list")
            return
        }

        [estadoProyectoInstance: estadoProyectoInstance]
    }

    def update() {
        def estadoProyectoInstance = EstadoProyecto.get(params.id)
        if (!estadoProyectoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (estadoProyectoInstance.version > version) {
                estadoProyectoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadoProyecto.label', default: 'EstadoProyecto')] as Object[],
                          "Another user has updated this EstadoProyecto while you were editing")
                render(view: "edit", model: [estadoProyectoInstance: estadoProyectoInstance])
                return
            }
        }

        estadoProyectoInstance.properties = params

        if (!estadoProyectoInstance.save(flush: true)) {
            render(view: "edit", model: [estadoProyectoInstance: estadoProyectoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), estadoProyectoInstance.id])
        redirect(action: "show", id: estadoProyectoInstance.id)
    }

    def delete() {
        def estadoProyectoInstance = EstadoProyecto.get(params.id)
        if (!estadoProyectoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), params.id])
            redirect(action: "list")
            return
        }

        try {
            estadoProyectoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadoProyecto.label', default: 'EstadoProyecto'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
