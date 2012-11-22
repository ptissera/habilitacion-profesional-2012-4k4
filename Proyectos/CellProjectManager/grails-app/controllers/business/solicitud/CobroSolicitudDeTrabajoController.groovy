package business.solicitud

import org.springframework.dao.DataIntegrityViolationException
import business.tarea.SolicitudDeTarea
import business.tarea.EstadoSolicitudTarea

class CobroSolicitudDeTrabajoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cobroSolicitudDeTrabajoInstanceList: CobroSolicitudDeTrabajo.list(params), cobroSolicitudDeTrabajoInstanceTotal: CobroSolicitudDeTrabajo.count()]
    }

    def create() {
        
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        session.solicitudDeTareaSelected = solicitudDeTareaInstance
        def cobroSolicitudDeTrabajoInstance = new CobroSolicitudDeTrabajo(fechaCobro: new Date(), solicitud: solicitudDeTareaInstance,
            monto: solicitudDeTareaInstance.totalPorCobrar())
        
        [cobroSolicitudDeTrabajoInstance: cobroSolicitudDeTrabajoInstance]
    }

    def save() {
        def solicitudDeTareaInstance = session.solicitudDeTareaSelected
        def cobroSolicitudDeTrabajoInstance = new CobroSolicitudDeTrabajo(solicitud:solicitudDeTareaInstance)
        cobroSolicitudDeTrabajoInstance.properties = params
        if (!cobroSolicitudDeTrabajoInstance.save(flush: true)) {
            render(view: "create", model: [cobroSolicitudDeTrabajoInstance: cobroSolicitudDeTrabajoInstance])
            return
        }
        if(solicitudDeTareaInstance.totalPorCobrar()==cobroSolicitudDeTrabajoInstance.monto){
            solicitudDeTareaInstance.estado=EstadoSolicitudTarea.findByNombre("Cerrada")
            solicitudDeTareaInstance.save(flush: true)
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), cobroSolicitudDeTrabajoInstance.id])
        redirect(uri: "/")
    }

    def show() {
        def cobroSolicitudDeTrabajoInstance = CobroSolicitudDeTrabajo.get(params.id)
        if (!cobroSolicitudDeTrabajoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), params.id])
            redirect(action: "list")
            return
        }

        [cobroSolicitudDeTrabajoInstance: cobroSolicitudDeTrabajoInstance]
    }

    def edit() {
        def cobroSolicitudDeTrabajoInstance = CobroSolicitudDeTrabajo.get(params.id)
        if (!cobroSolicitudDeTrabajoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), params.id])
            redirect(action: "list")
            return
        }

        [cobroSolicitudDeTrabajoInstance: cobroSolicitudDeTrabajoInstance]
    }

    def update() {
        def cobroSolicitudDeTrabajoInstance = CobroSolicitudDeTrabajo.get(params.id)
        if (!cobroSolicitudDeTrabajoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (cobroSolicitudDeTrabajoInstance.version > version) {
                cobroSolicitudDeTrabajoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo')] as Object[],
                          "Another user has updated this CobroSolicitudDeTrabajo while you were editing")
                render(view: "edit", model: [cobroSolicitudDeTrabajoInstance: cobroSolicitudDeTrabajoInstance])
                return
            }
        }

        cobroSolicitudDeTrabajoInstance.properties = params

        if (!cobroSolicitudDeTrabajoInstance.save(flush: true)) {
            render(view: "edit", model: [cobroSolicitudDeTrabajoInstance: cobroSolicitudDeTrabajoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), cobroSolicitudDeTrabajoInstance.id])
        redirect(action: "show", id: cobroSolicitudDeTrabajoInstance.id)
    }

    def delete() {
        def cobroSolicitudDeTrabajoInstance = CobroSolicitudDeTrabajo.get(params.id)
        if (!cobroSolicitudDeTrabajoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), params.id])
            redirect(action: "list")
            return
        }

        try {
            cobroSolicitudDeTrabajoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
