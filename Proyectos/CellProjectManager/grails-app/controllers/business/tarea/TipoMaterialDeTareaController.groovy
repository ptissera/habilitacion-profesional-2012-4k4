package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class TipoMaterialDeTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoMaterialDeTareaInstanceList: TipoMaterialDeTarea.list(params), tipoMaterialDeTareaInstanceTotal: TipoMaterialDeTarea.count()]
    }

    def create() {
        [tipoMaterialDeTareaInstance: new TipoMaterialDeTarea(params)]
    }

    def save() {
        def tipoMaterialDeTareaInstance = new TipoMaterialDeTarea(params)
        if (!tipoMaterialDeTareaInstance.save(flush: true)) {
            render(view: "create", model: [tipoMaterialDeTareaInstance: tipoMaterialDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), tipoMaterialDeTareaInstance.id])
        redirect(action: "show", id: tipoMaterialDeTareaInstance.id)
    }

    def show() {
        def tipoMaterialDeTareaInstance = TipoMaterialDeTarea.get(params.id)
        if (!tipoMaterialDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [tipoMaterialDeTareaInstance: tipoMaterialDeTareaInstance]
    }

    def edit() {
        def tipoMaterialDeTareaInstance = TipoMaterialDeTarea.get(params.id)
        if (!tipoMaterialDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [tipoMaterialDeTareaInstance: tipoMaterialDeTareaInstance]
    }

    def update() {
        def tipoMaterialDeTareaInstance = TipoMaterialDeTarea.get(params.id)
        if (!tipoMaterialDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tipoMaterialDeTareaInstance.version > version) {
                tipoMaterialDeTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea')] as Object[],
                          "Another user has updated this TipoMaterialDeTarea while you were editing")
                render(view: "edit", model: [tipoMaterialDeTareaInstance: tipoMaterialDeTareaInstance])
                return
            }
        }

        tipoMaterialDeTareaInstance.properties = params

        if (!tipoMaterialDeTareaInstance.save(flush: true)) {
            render(view: "edit", model: [tipoMaterialDeTareaInstance: tipoMaterialDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), tipoMaterialDeTareaInstance.id])
        redirect(action: "show", id: tipoMaterialDeTareaInstance.id)
    }

    def delete() {
        def tipoMaterialDeTareaInstance = TipoMaterialDeTarea.get(params.id)
        if (!tipoMaterialDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tipoMaterialDeTareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
