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
        session.setAttribute("equipoDeTareaSelectedTF",true)
        [equipoDeTareaInstance: new EquipoDeTarea(params)]
    }

    def save() {
        def tareaSelected = session.getAttribute("tareaSelected")
        def equipoDeTareaInstance = new EquipoDeTarea(tarea: tareaSelected)
        equipoDeTareaInstance.properties = params
        if (!equipoDeTareaInstance.save(flush: true)) {
            render(view: "create", model: [equipoDeTareaInstance: equipoDeTareaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), equipoDeTareaInstance.id])
        redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
    }

    def show() {
        session.setAttribute("equipoDeTareaSelectedTF",true)
        def equipoDeTareaInstance = EquipoDeTarea.get(params.id)
        if (!equipoDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        [equipoDeTareaInstance: equipoDeTareaInstance]
    }

    def edit() {
        session.setAttribute("equipoDeTareaSelectedTF",true)
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
        def tareaSelected = session.getAttribute("tareaSelected")
        redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
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
            def tareaSelected = session.getAttribute("tareaSelected")
            redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
