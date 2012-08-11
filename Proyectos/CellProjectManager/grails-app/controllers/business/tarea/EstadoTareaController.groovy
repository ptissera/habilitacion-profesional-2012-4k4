package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class EstadoTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [estadoTareaInstanceList: EstadoTarea.list(params), estadoTareaInstanceTotal: EstadoTarea.count()]
    }

    def create() {
        [estadoTareaInstance: new EstadoTarea(params)]
    }

    def save() {
        def estadoTareaInstance = new EstadoTarea(params)
        if (!estadoTareaInstance.save(flush: true)) {
            render(view: "create", model: [estadoTareaInstance: estadoTareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), estadoTareaInstance.id])
        redirect(action: "show", id: estadoTareaInstance.id)
    }

    def show() {
        def estadoTareaInstance = EstadoTarea.get(params.id)
        if (!estadoTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), params.id])
            redirect(action: "list")
            return
        }

        [estadoTareaInstance: estadoTareaInstance]
    }

    def edit() {
        def estadoTareaInstance = EstadoTarea.get(params.id)
        if (!estadoTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), params.id])
            redirect(action: "list")
            return
        }

        [estadoTareaInstance: estadoTareaInstance]
    }

    def update() {
        def estadoTareaInstance = EstadoTarea.get(params.id)
        if (!estadoTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (estadoTareaInstance.version > version) {
                estadoTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadoTarea.label', default: 'EstadoTarea')] as Object[],
                          "Another user has updated this EstadoTarea while you were editing")
                render(view: "edit", model: [estadoTareaInstance: estadoTareaInstance])
                return
            }
        }

        estadoTareaInstance.properties = params

        if (!estadoTareaInstance.save(flush: true)) {
            render(view: "edit", model: [estadoTareaInstance: estadoTareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), estadoTareaInstance.id])
        redirect(action: "show", id: estadoTareaInstance.id)
    }

    def delete() {
        def estadoTareaInstance = EstadoTarea.get(params.id)
        if (!estadoTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            estadoTareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadoTarea.label', default: 'EstadoTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
