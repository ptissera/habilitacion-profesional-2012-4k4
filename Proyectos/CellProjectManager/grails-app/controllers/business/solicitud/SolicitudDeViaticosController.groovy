package business.solicitud

import org.springframework.dao.DataIntegrityViolationException

class SolicitudDeViaticosController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [solicitudDeViaticosInstanceList: SolicitudDeViaticos.list(params), solicitudDeViaticosInstanceTotal: SolicitudDeViaticos.count()]
    }

    def create() {
        [solicitudDeViaticosInstance: new SolicitudDeViaticos(params)]
    }

    def save() {
        def solicitudDeViaticosInstance = new SolicitudDeViaticos(params)
        if (!solicitudDeViaticosInstance.save(flush: true)) {
            render(view: "create", model: [solicitudDeViaticosInstance: solicitudDeViaticosInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), solicitudDeViaticosInstance.id])
        redirect(action: "show", id: solicitudDeViaticosInstance.id)
    }

    def show() {
        def solicitudDeViaticosInstance = SolicitudDeViaticos.get(params.id)
        if (!solicitudDeViaticosInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), params.id])
            redirect(action: "list")
            return
        }

        [solicitudDeViaticosInstance: solicitudDeViaticosInstance]
    }

    def edit() {
        def solicitudDeViaticosInstance = SolicitudDeViaticos.get(params.id)
        if (!solicitudDeViaticosInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), params.id])
            redirect(action: "list")
            return
        }

        [solicitudDeViaticosInstance: solicitudDeViaticosInstance]
    }

    def update() {
        def solicitudDeViaticosInstance = SolicitudDeViaticos.get(params.id)
        if (!solicitudDeViaticosInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (solicitudDeViaticosInstance.version > version) {
                solicitudDeViaticosInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos')] as Object[],
                          "Another user has updated this SolicitudDeViaticos while you were editing")
                render(view: "edit", model: [solicitudDeViaticosInstance: solicitudDeViaticosInstance])
                return
            }
        }

        solicitudDeViaticosInstance.properties = params

        if (!solicitudDeViaticosInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDeViaticosInstance: solicitudDeViaticosInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), solicitudDeViaticosInstance.id])
        redirect(action: "show", id: solicitudDeViaticosInstance.id)
    }

    def delete() {
        def solicitudDeViaticosInstance = SolicitudDeViaticos.get(params.id)
        if (!solicitudDeViaticosInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), params.id])
            redirect(action: "list")
            return
        }

        try {
            solicitudDeViaticosInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
