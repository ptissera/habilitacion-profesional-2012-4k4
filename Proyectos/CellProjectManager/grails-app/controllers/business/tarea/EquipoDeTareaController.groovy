package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class EquipoDeTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [equipoDeTareaInstanceList: EquipoDeTarea.list(params), equipoDeTareaInstanceTotal: EquipoDeTarea.count()]
    }

    def create() {
        [equipoDeTareaInstance: new EquipoDeTarea(params)]
    }

    def save() {
        def equipoDeTareaInstance = new EquipoDeTarea(params)
        if (!equipoDeTareaInstance.save(flush: true)) {
            render(view: "create", model: [equipoDeTareaInstance: equipoDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), equipoDeTareaInstance.id])
        redirect(action: "show", id: equipoDeTareaInstance.id)
    }

    def show() {
        def equipoDeTareaInstance = EquipoDeTarea.get(params.id)
        if (!equipoDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [equipoDeTareaInstance: equipoDeTareaInstance]
    }

    def edit() {
        def equipoDeTareaInstance = EquipoDeTarea.get(params.id)
        if (!equipoDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [equipoDeTareaInstance: equipoDeTareaInstance]
    }

    def update() {
        def equipoDeTareaInstance = EquipoDeTarea.get(params.id)
        if (!equipoDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (equipoDeTareaInstance.version > version) {
                equipoDeTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea')] as Object[],
                          "Another user has updated this EquipoDeTarea while you were editing")
                render(view: "edit", model: [equipoDeTareaInstance: equipoDeTareaInstance])
                return
            }
        }

        equipoDeTareaInstance.properties = params

        if (!equipoDeTareaInstance.save(flush: true)) {
            render(view: "edit", model: [equipoDeTareaInstance: equipoDeTareaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), equipoDeTareaInstance.id])
        redirect(action: "show", id: equipoDeTareaInstance.id)
    }

    def delete() {
        def equipoDeTareaInstance = EquipoDeTarea.get(params.id)
        if (!equipoDeTareaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            equipoDeTareaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
