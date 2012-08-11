package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class TipoTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoTareaInstanceList: TipoTarea.list(params), tipoTareaInstanceTotal: TipoTarea.count()]
    }

    def create() {
        [tipoTareaInstance: new TipoTarea(params)]
    }

    def save() {
        def tipoTareaInstance = new TipoTarea(params)
        if (!tipoTareaInstance.save(flush: true)) {
            render(view: "create", model: [tipoTareaInstance: tipoTareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), tipoTareaInstance.id])
        redirect(action: "show", id: tipoTareaInstance.id)
    }

    def show() {
        def tipoTareaInstance = TipoTarea.get(params.id)
        if (!tipoTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), params.id])
            redirect(action: "list")
            return
        }

        [tipoTareaInstance: tipoTareaInstance]
    }

    def edit() {
        def tipoTareaInstance = TipoTarea.get(params.id)
        if (!tipoTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), params.id])
            redirect(action: "list")
            return
        }

        [tipoTareaInstance: tipoTareaInstance]
    }

    def update() {
        def tipoTareaInstance = TipoTarea.get(params.id)
        if (!tipoTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tipoTareaInstance.version > version) {
                tipoTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoTarea.label', default: 'TipoTarea')] as Object[],
                          "Another user has updated this TipoTarea while you were editing")
                render(view: "edit", model: [tipoTareaInstance: tipoTareaInstance])
                return
            }
        }

        tipoTareaInstance.properties = params

        if (!tipoTareaInstance.save(flush: true)) {
            render(view: "edit", model: [tipoTareaInstance: tipoTareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), tipoTareaInstance.id])
        redirect(action: "show", id: tipoTareaInstance.id)
    }

    def delete() {
        def tipoTareaInstance = TipoTarea.get(params.id)
        if (!tipoTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tipoTareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoTarea.label', default: 'TipoTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
