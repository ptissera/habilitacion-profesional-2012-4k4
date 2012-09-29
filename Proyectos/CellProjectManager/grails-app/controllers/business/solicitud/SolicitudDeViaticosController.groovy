package business.solicitud

import org.springframework.dao.DataIntegrityViolationException
import support.tool.ParametrosDelSistema
import business.tarea.SolicitudDeTarea

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
        def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaSelected")
        solicitudDeTareaInstance = SolicitudDeTarea.get(solicitudDeTareaInstance.id)
        if(!solicitudDeTareaInstance.tarea){
            flash.error = "No se puede crear la Solicitud de Viaticos. La solicitud de tareas no contiene tareas aun!"
            redirect(action: "show", controller: "solicitudDeTarea", id: solicitudDeViaticosInstance.id)
        }else{
            def parametroViaticos = ParametrosDelSistema.findByNombre('PROM_VIATICO_DIA_OPERARIO')
            def minDate 
            def maxDate 
            solicitudDeTareaInstance.tarea.each{
                if(!minDate || minDate>it.fechaInicio){
                    minDate = it.fechaInicio
                }
                if(!maxDate || maxDate<it.fechaFin){
                    maxDate = it.fechaFin
                }
            }
            def monto = (maxDate - minDate + 1) * Float.valueOf(parametroViaticos.valor) * solicitudDeTareaInstance.cuadrilla.operarios.size()
            def solicitudDeViaticosInstance = new SolicitudDeViaticos(fechaCreacion: new Date(), 
                solicitud:solicitudDeTareaInstance , 
                monto: monto, 
                estado:EstadoSolicitudDeViaticos.findByNombre('Pendiente'))
            [solicitudDeViaticosInstance: solicitudDeViaticosInstance]        
        }
      
        
    }

    def save() {
        def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaSelected")
        def solicitudDeViaticosInstance = new SolicitudDeViaticos(fechaCreacion: new Date(),
            solicitud:solicitudDeTareaInstance , 
            estado:EstadoSolicitudDeViaticos.findByNombre('Pendiente'))
        solicitudDeViaticosInstance.properties = params
        if (!solicitudDeViaticosInstance.save(flush: true)) {
            render(view: "create", model: [solicitudDeViaticosInstance: solicitudDeViaticosInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos'), solicitudDeViaticosInstance.id])
        redirect(action: "show", controller:"solicitudDeTarea", id: solicitudDeTareaInstance.id)
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
        solicitudDeViaticosInstance.fechaPago = new Date()
        [solicitudDeViaticosInstance: solicitudDeViaticosInstance]
    }

    def updateAceptar() {        
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
        solicitudDeViaticosInstance.estado=EstadoSolicitudDeViaticos.findByNombre('Aprobada')
        
        if (!solicitudDeViaticosInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDeViaticosInstance: solicitudDeViaticosInstance])
            return
        }

        flash.message = "Solicitud de Viaticos Aprobada"
        redirect(uri:"/")
    }
    
    def updateRechazar() {
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
        solicitudDeViaticosInstance.estado=EstadoSolicitudDeViaticos.findByNombre('Rechazada')
        
        if (!solicitudDeViaticosInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDeViaticosInstance: solicitudDeViaticosInstance])
            return
        }

        flash.message = "Solicitud de Viaticos Rechazada"
        redirect(uri:"/")
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
