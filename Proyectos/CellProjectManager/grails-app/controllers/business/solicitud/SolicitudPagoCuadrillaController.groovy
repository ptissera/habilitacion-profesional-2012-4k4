package business.solicitud

import org.springframework.dao.DataIntegrityViolationException
import support.tool.ParametrosDelSistema
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
        def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaSelected")
        solicitudDeTareaInstance = SolicitudDeTarea.get(solicitudDeTareaInstance.id)
        if(!solicitudDeTareaInstance.pos){
            flash.error = "No se puede crear la Solicitud de Pago a Cuadrilla. La solicitud de tareas no contiene POs aun!"
            redirect(action: "show", controller: "solicitudDeTarea", id: solicitudDeTareaInstance.id)
        }else{            
            int porcentajePagado = 0
            if(solicitudDeTareaInstance.pagos){
                solicitudDeTareaInstance.pagos.each{
                    porcentajePagado += it.porcentaje
                }
            }
            
            def solicitudPagoCuadrillaInstance = new SolicitudPagoCuadrilla(fechaCreacion: new Date(), 
                solicitud:solicitudDeTareaInstance ,               
                porcentaje: (100 - porcentajePagado),
                estado:EstadoSolicitudPagoCuadrilla.findByNombre('Pendiente'))
            [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance]        
        }
    }

    def save() {
        def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaSelected")
        solicitudDeTareaInstance = SolicitudDeTarea.get(solicitudDeTareaInstance.id)
        def solicitudPagoCuadrillaInstance = new SolicitudPagoCuadrilla( solicitud: solicitudDeTareaInstance, fechaCreacion: new Date(), estado:EstadoSolicitudPagoCuadrilla.findByNombre('Pendiente'))
        solicitudPagoCuadrillaInstance.properties = params
        
        def parametroPocentajeDelTotal = ParametrosDelSistema.findByNombre('PORCENTAJE_TAREA')
        def totalPOs = 0
        solicitudDeTareaInstance.pos.each{
            totalPOs += it.monto
        }
        def montoTotal = totalPOs * Float.valueOf(parametroPocentajeDelTotal.valor) / 100
        def montoParcial = montoTotal * solicitudPagoCuadrillaInstance.porcentaje / 100
        solicitudPagoCuadrillaInstance.monto = montoParcial
        if (!solicitudPagoCuadrillaInstance.save(flush: true)) {
            render(view: "create", model: [solicitudPagoCuadrillaInstance: solicitudPagoCuadrillaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla'), solicitudPagoCuadrillaInstance.id])
        redirect(action: "show", controller: "solicitudDeTarea", id: solicitudDeTareaInstance.id)
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
