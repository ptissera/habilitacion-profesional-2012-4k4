package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class TareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tareaInstanceList: Tarea.list(params), tareaInstanceTotal: Tarea.count()]
    }

    def create() {
        [tareaInstance: new Tarea(params)]
    }

    def save() {
        def tareaInstance = new Tarea(params)
        if (!tareaInstance.save(flush: true)) {
            render(view: "create", model: [tareaInstance: tareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tarea.label', default: 'Tarea'), tareaInstance.id])
        redirect(action: "show", id: tareaInstance.id)
    }

    def show() {
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }

        [tareaInstance: tareaInstance]
    }

    def edit() {
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }

        [tareaInstance: tareaInstance]
    }

    def update() {
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tareaInstance.version > version) {
                tareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tarea.label', default: 'Tarea')] as Object[],
                          "Another user has updated this Tarea while you were editing")
                render(view: "edit", model: [tareaInstance: tareaInstance])
                return
            }
        }

        tareaInstance.properties = params

        if (!tareaInstance.save(flush: true)) {
            render(view: "edit", model: [tareaInstance: tareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tarea.label', default: 'Tarea'), tareaInstance.id])
        redirect(action: "show", id: tareaInstance.id)
    }

    def delete() {
        def tareaInstance = Tarea.get(params.id)
        if (!tareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
