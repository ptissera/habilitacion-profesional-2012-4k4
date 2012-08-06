package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class TipoDocumentacionIntegranteCuadrillaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoDocumentacionIntegranteCuadrillaInstanceList: TipoDocumentacionIntegranteCuadrilla.list(params), tipoDocumentacionIntegranteCuadrillaInstanceTotal: TipoDocumentacionIntegranteCuadrilla.count()]
    }

    def create() {
        [tipoDocumentacionIntegranteCuadrillaInstance: new TipoDocumentacionIntegranteCuadrilla(params)]
    }

    def save() {
        def tipoDocumentacionIntegranteCuadrillaInstance = new TipoDocumentacionIntegranteCuadrilla(params)
        if (!tipoDocumentacionIntegranteCuadrillaInstance.save(flush: true)) {
            render(view: "create", model: [tipoDocumentacionIntegranteCuadrillaInstance: tipoDocumentacionIntegranteCuadrillaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), tipoDocumentacionIntegranteCuadrillaInstance.id])
        redirect(action: "show", id: tipoDocumentacionIntegranteCuadrillaInstance.id)
    }

    def show() {
        def tipoDocumentacionIntegranteCuadrillaInstance = TipoDocumentacionIntegranteCuadrilla.get(params.id)
        if (!tipoDocumentacionIntegranteCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        [tipoDocumentacionIntegranteCuadrillaInstance: tipoDocumentacionIntegranteCuadrillaInstance]
    }

    def edit() {
        def tipoDocumentacionIntegranteCuadrillaInstance = TipoDocumentacionIntegranteCuadrilla.get(params.id)
        if (!tipoDocumentacionIntegranteCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        [tipoDocumentacionIntegranteCuadrillaInstance: tipoDocumentacionIntegranteCuadrillaInstance]
    }

    def update() {
        def tipoDocumentacionIntegranteCuadrillaInstance = TipoDocumentacionIntegranteCuadrilla.get(params.id)
        if (!tipoDocumentacionIntegranteCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tipoDocumentacionIntegranteCuadrillaInstance.version > version) {
                tipoDocumentacionIntegranteCuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla')] as Object[],
                          "Another user has updated this TipoDocumentacionIntegranteCuadrilla while you were editing")
                render(view: "edit", model: [tipoDocumentacionIntegranteCuadrillaInstance: tipoDocumentacionIntegranteCuadrillaInstance])
                return
            }
        }

        tipoDocumentacionIntegranteCuadrillaInstance.properties = params

        if (!tipoDocumentacionIntegranteCuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [tipoDocumentacionIntegranteCuadrillaInstance: tipoDocumentacionIntegranteCuadrillaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), tipoDocumentacionIntegranteCuadrillaInstance.id])
        redirect(action: "show", id: tipoDocumentacionIntegranteCuadrillaInstance.id)
    }

    def delete() {
        def tipoDocumentacionIntegranteCuadrillaInstance = TipoDocumentacionIntegranteCuadrilla.get(params.id)
        if (!tipoDocumentacionIntegranteCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tipoDocumentacionIntegranteCuadrillaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
