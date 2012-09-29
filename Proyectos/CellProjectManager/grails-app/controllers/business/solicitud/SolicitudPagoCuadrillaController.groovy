package business.solicitud

import org.springframework.dao.DataIntegrityViolationException
import business.tarea.SolicitudDeTarea
import business.solicitud.EstadoSolicitudPagoCuadrilla

class SolicitudPagoCuadrillaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [solicitudPagoCuadrillaInstanceList: SolicitudPagoCuadrilla.list(params), solicitudPagoCuadrillaInstanceTotal: SolicitudPagoCuadrilla.count()]
    }

    def create() {
        session.setAttribute("solicitudPagoCuadrillaSelectedTF",true)        
        [solicitudPagoCuadrillaInstance: new SolicitudPagoCuadrilla(params)]
    }

    def save() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        def solicitudPagoCuadrillaInstance = new SolicitudPagoCuadrilla( solicitud: solicitudDeTareaSelected, fechaCreacion: new Date(), estado:EstadoSolicitudPagoCuadrilla.findByNombre('Pendiente'))
        solicitudPagoCuadrillaInstance.properties = params
        if (!solicitudPagoCuadrillaInstance.save(flush: true)) {
            render(view: "create", model: [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), solicitudPagoCuadrillaInstance.id])
        redirect(action: "show", controller: "solicitudDeTarea", id: solicitudDeTareaSelected.id)
    }

    def show() {
        def solicitudPagoCuadrillaInstance = SolicitudPagoCuadrilla.get(params.id)
        if (!solicitudPagoCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance]
    }

    def edit() {
        def solicitudPagoCuadrillaInstance = SolicitudPagoCuadrilla.get(params.id)        
        if (!solicitudPagoCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }
        solicitudPagoCuadrillaInstance.fechaPago = new Date()
        [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance]
    }

    def updateAceptar() {
        def solicitudPagoCuadrillaInstance = SolicitudPagoCuadrilla.get(params.id)
        if (!solicitudPagoCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (solicitudPagoCuadrillaInstance.version > version) {
                solicitudPagoCuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla')] as Object[],
                          "Another user has updated this SolicitudPagoCuadrilla while you were editing")
                render(view: "edit", model: [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance])
                return
            }
        }

        solicitudPagoCuadrillaInstance.properties = params
        solicitudPagoCuadrillaInstance.estado=EstadoSolicitudPagoCuadrilla.findByNombre('Aprobada')
        if (!solicitudPagoCuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance])
            return
        }

	flash.message = "Solicitud de Pago a Cuadrilla Aprobada"
        redirect(uri:"/")
    }
    
    def updateRechazar() {
        def solicitudPagoCuadrillaInstance = SolicitudPagoCuadrilla.get(params.id)
        if (!solicitudPagoCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (solicitudPagoCuadrillaInstance.version > version) {
                solicitudPagoCuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla')] as Object[],
                          "Another user has updated this SolicitudPagoCuadrilla while you were editing")
                render(view: "edit", model: [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance])
                return
            }
        }

        solicitudPagoCuadrillaInstance.properties = params
        solicitudPagoCuadrillaInstance.estado=EstadoSolicitudPagoCuadrilla.findByNombre('Rechazada')
        if (!solicitudPagoCuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance])
            return
        }

	flash.message = "Solicitud de Pago a Cuadrilla Rechazada"
        redirect(uri:"/")
    }

    def delete() {
        def solicitudPagoCuadrillaInstance = SolicitudPagoCuadrilla.get(params.id)
        if (!solicitudPagoCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        try {
            solicitudPagoCuadrillaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
