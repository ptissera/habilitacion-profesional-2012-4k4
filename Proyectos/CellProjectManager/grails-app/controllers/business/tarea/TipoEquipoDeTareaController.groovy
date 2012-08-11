package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class TipoEquipoDeTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoEquipoDeTareaInstanceList: TipoEquipoDeTarea.list(params), tipoEquipoDeTareaInstanceTotal: TipoEquipoDeTarea.count()]
    }

    def create() {
        [tipoEquipoDeTareaInstance: new TipoEquipoDeTarea(params)]
    }

    def save() {
        def tipoEquipoDeTareaInstance = new TipoEquipoDeTarea(params)
        if (!tipoEquipoDeTareaInstance.save(flush: true)) {
            render(view: "create", model: [tipoEquipoDeTareaInstance: tipoEquipoDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), tipoEquipoDeTareaInstance.id])
        redirect(action: "show", id: tipoEquipoDeTareaInstance.id)
    }

    def show() {
        def tipoEquipoDeTareaInstance = TipoEquipoDeTarea.get(params.id)
        if (!tipoEquipoDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [tipoEquipoDeTareaInstance: tipoEquipoDeTareaInstance]
    }

    def edit() {
        def tipoEquipoDeTareaInstance = TipoEquipoDeTarea.get(params.id)
        if (!tipoEquipoDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [tipoEquipoDeTareaInstance: tipoEquipoDeTareaInstance]
    }

    def update() {
        def tipoEquipoDeTareaInstance = TipoEquipoDeTarea.get(params.id)
        if (!tipoEquipoDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tipoEquipoDeTareaInstance.version > version) {
                tipoEquipoDeTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea')] as Object[],
                          "Another user has updated this TipoEquipoDeTarea while you were editing")
                render(view: "edit", model: [tipoEquipoDeTareaInstance: tipoEquipoDeTareaInstance])
                return
            }
        }

        tipoEquipoDeTareaInstance.properties = params

        if (!tipoEquipoDeTareaInstance.save(flush: true)) {
            render(view: "edit", model: [tipoEquipoDeTareaInstance: tipoEquipoDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), tipoEquipoDeTareaInstance.id])
        redirect(action: "show", id: tipoEquipoDeTareaInstance.id)
    }

    def delete() {
        def tipoEquipoDeTareaInstance = TipoEquipoDeTarea.get(params.id)
        if (!tipoEquipoDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tipoEquipoDeTareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
