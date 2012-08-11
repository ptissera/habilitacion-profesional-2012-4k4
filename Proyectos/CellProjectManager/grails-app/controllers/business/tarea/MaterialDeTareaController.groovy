package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class MaterialDeTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [materialDeTareaInstanceList: MaterialDeTarea.list(params), materialDeTareaInstanceTotal: MaterialDeTarea.count()]
    }

    def create() {
        [materialDeTareaInstance: new MaterialDeTarea(params)]
    }

    def save() {
        def materialDeTareaInstance = new MaterialDeTarea(params)
        if (!materialDeTareaInstance.save(flush: true)) {
            render(view: "create", model: [materialDeTareaInstance: materialDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), materialDeTareaInstance.id])
        redirect(action: "show", id: materialDeTareaInstance.id)
    }

    def show() {
        def materialDeTareaInstance = MaterialDeTarea.get(params.id)
        if (!materialDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [materialDeTareaInstance: materialDeTareaInstance]
    }

    def edit() {
        def materialDeTareaInstance = MaterialDeTarea.get(params.id)
        if (!materialDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [materialDeTareaInstance: materialDeTareaInstance]
    }

    def update() {
        def materialDeTareaInstance = MaterialDeTarea.get(params.id)
        if (!materialDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (materialDeTareaInstance.version > version) {
                materialDeTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea')] as Object[],
                          "Another user has updated this MaterialDeTarea while you were editing")
                render(view: "edit", model: [materialDeTareaInstance: materialDeTareaInstance])
                return
            }
        }

        materialDeTareaInstance.properties = params

        if (!materialDeTareaInstance.save(flush: true)) {
            render(view: "edit", model: [materialDeTareaInstance: materialDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), materialDeTareaInstance.id])
        redirect(action: "show", id: materialDeTareaInstance.id)
    }

    def delete() {
        def materialDeTareaInstance = MaterialDeTarea.get(params.id)
        if (!materialDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            materialDeTareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}