package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class TipoDocumentacionEmpleadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoDocumentacionEmpleadoInstanceList: TipoDocumentacionEmpleado.list(params), tipoDocumentacionEmpleadoInstanceTotal: TipoDocumentacionEmpleado.count()]
    }

    def create() {
        [tipoDocumentacionEmpleadoInstance: new TipoDocumentacionEmpleado(params)]
    }

    def save() {
        def tipoDocumentacionEmpleadoInstance = new TipoDocumentacionEmpleado(params)
        if (!tipoDocumentacionEmpleadoInstance.save(flush: true)) {
            render(view: "create", model: [tipoDocumentacionEmpleadoInstance: tipoDocumentacionEmpleadoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), tipoDocumentacionEmpleadoInstance.id])
        redirect(action: "show", id: tipoDocumentacionEmpleadoInstance.id)
    }

    def show() {
        def tipoDocumentacionEmpleadoInstance = TipoDocumentacionEmpleado.get(params.id)
        if (!tipoDocumentacionEmpleadoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        [tipoDocumentacionEmpleadoInstance: tipoDocumentacionEmpleadoInstance]
    }

    def edit() {
        def tipoDocumentacionEmpleadoInstance = TipoDocumentacionEmpleado.get(params.id)
        if (!tipoDocumentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        [tipoDocumentacionEmpleadoInstance: tipoDocumentacionEmpleadoInstance]
    }

    def update() {
        def tipoDocumentacionEmpleadoInstance = TipoDocumentacionEmpleado.get(params.id)
        if (!tipoDocumentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tipoDocumentacionEmpleadoInstance.version > version) {
                tipoDocumentacionEmpleadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado')] as Object[],
                          "Another user has updated this TipoDocumentacionEmpleado while you were editing")
                render(view: "edit", model: [tipoDocumentacionEmpleadoInstance: tipoDocumentacionEmpleadoInstance])
                return
            }
        }

        tipoDocumentacionEmpleadoInstance.properties = params

        if (!tipoDocumentacionEmpleadoInstance.save(flush: true)) {
            render(view: "edit", model: [tipoDocumentacionEmpleadoInstance: tipoDocumentacionEmpleadoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), tipoDocumentacionEmpleadoInstance.id])
        redirect(action: "show", id: tipoDocumentacionEmpleadoInstance.id)
    }

    def delete() {
        def tipoDocumentacionEmpleadoInstance = TipoDocumentacionEmpleado.get(params.id)
        if (!tipoDocumentacionEmpleadoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tipoDocumentacionEmpleadoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
