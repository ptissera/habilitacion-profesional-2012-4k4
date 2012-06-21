package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class EmpleadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [empleadoInstanceList: Empleado.list(params), empleadoInstanceTotal: Empleado.count()]
    }

    def create() {
        [empleadoInstance: new Empleado(params)]
    }

    def save() {
        def empleadoInstance = new Empleado(params)
        if (!empleadoInstance.save(flush: true)) {
            render(view: "create", model: [empleadoInstance: empleadoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleadoInstance.id])
        redirect(action: "show", id: empleadoInstance.id)
    }

    def show() {
        def empleadoInstance = Empleado.get(params.id)
        
        if (!empleadoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("empleadoSelected",empleadoInstance);
        [empleadoInstance: empleadoInstance]
    }

    def edit() {
        def empleadoInstance = Empleado.get(params.id)
        if (!empleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
            return
        }

        [empleadoInstance: empleadoInstance]
    }

    def update() {
        def empleadoInstance = Empleado.get(params.id)
        if (!empleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (empleadoInstance.version > version) {
                empleadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'empleado.label', default: 'Empleado')] as Object[],
                          "Another user has updated this Empleado while you were editing")
                render(view: "edit", model: [empleadoInstance: empleadoInstance])
                return
            }
        }

        empleadoInstance.properties = params

        if (!empleadoInstance.save(flush: true)) {
            render(view: "edit", model: [empleadoInstance: empleadoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleadoInstance.id])
        redirect(action: "show", id: empleadoInstance.id)
    }

    def delete() {
        def empleadoInstance = Empleado.get(params.id)
        if (!empleadoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
            return
        }

        try {
            empleadoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
