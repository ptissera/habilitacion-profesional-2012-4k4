package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class EstadoSolicitudTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [estadoSolicitudTareaInstanceList: EstadoSolicitudTarea.list(params), estadoSolicitudTareaInstanceTotal: EstadoSolicitudTarea.count()]
    }

    def create() {
        [estadoSolicitudTareaInstance: new EstadoSolicitudTarea(params)]
    }

    def save() {
        def estadoSolicitudTareaInstance = new EstadoSolicitudTarea(params)
        if (!estadoSolicitudTareaInstance.save(flush: true)) {
            render(view: "create", model: [estadoSolicitudTareaInstance: estadoSolicitudTareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), estadoSolicitudTareaInstance.id])
        redirect(action: "show", id: estadoSolicitudTareaInstance.id)
    }

    def show() {
        def estadoSolicitudTareaInstance = EstadoSolicitudTarea.get(params.id)
        if (!estadoSolicitudTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), params.id])
            redirect(action: "list")
            return
        }

        [estadoSolicitudTareaInstance: estadoSolicitudTareaInstance]
    }

    def edit() {
        def estadoSolicitudTareaInstance = EstadoSolicitudTarea.get(params.id)
        if (!estadoSolicitudTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), params.id])
            redirect(action: "list")
            return
        }

        [estadoSolicitudTareaInstance: estadoSolicitudTareaInstance]
    }

    def update() {
        def estadoSolicitudTareaInstance = EstadoSolicitudTarea.get(params.id)
        if (!estadoSolicitudTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (estadoSolicitudTareaInstance.version > version) {
                estadoSolicitudTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea')] as Object[],
                          "Another user has updated this EstadoSolicitudTarea while you were editing")
                render(view: "edit", model: [estadoSolicitudTareaInstance: estadoSolicitudTareaInstance])
                return
            }
        }

        estadoSolicitudTareaInstance.properties = params

        if (!estadoSolicitudTareaInstance.save(flush: true)) {
            render(view: "edit", model: [estadoSolicitudTareaInstance: estadoSolicitudTareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), estadoSolicitudTareaInstance.id])
        redirect(action: "show", id: estadoSolicitudTareaInstance.id)
    }

    def delete() {
        def estadoSolicitudTareaInstance = EstadoSolicitudTarea.get(params.id)
        if (!estadoSolicitudTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            estadoSolicitudTareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
