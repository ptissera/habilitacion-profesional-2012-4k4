package business.tarea

import org.springframework.dao.DataIntegrityViolationException
import business.core.Proyecto
import business.cuadrillas.Cuadrilla

class SolicitudDeTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        cleanSelected()
        Proyecto proyectoSelected=(Proyecto)session.getAttribute("proyectoSelected")
         if(proyectoSelected){
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            [solicitudDeTareaInstanceList: proyectoSelected.getSolicitudes().list(params), solicitudDeTareaInstanceTotal: proyectoSelected.getSolicitudes().count()]
        }else{
            session.setAttribute("aDondeVoy",["solicitudDeTarea","list"])
            redirect(action: "selectList", controller: "proyecto")
        }
    }

    def create() {
        cleanSelected()
        Proyecto proyectoSelected=(Proyecto)session.getAttribute("proyectoSelected")
         
        if(proyectoSelected){
            def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaCreate")      
            if(!solicitudDeTareaInstance){
                solicitudDeTareaInstance   = new SolicitudDeTarea(fechaAlta: new Date(), proyecto: proyectoSelected, cuadrilla: Cuadrilla.findByNombre('Perez'),
                estado: EstadoSolicitudTarea.findByNombre('Creada'))
                solicitudDeTareaInstance.save(flush: true)          
                session.setAttribute("solicitudDeTareaCreate",solicitudDeTareaInstance)            
            }
            solicitudDeTareaInstance = SolicitudDeTarea.get(solicitudDeTareaInstance.id)
            [solicitudDeTareaInstance: solicitudDeTareaInstance]
        }else{
            session.setAttribute("aDondeVoy",["solicitudDeTarea","create"])
            redirect(action: "selectList", controller: "proyecto")
        }
    }

    def save() {
        Proyecto proyectoSelected=(Proyecto)session.getAttribute("proyectoSelected")
        def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaCreate")  
        solicitudDeTareaInstance = SolicitudDeTarea.get(solicitudDeTareaInstance.id)
        solicitudDeTareaInstance.properties = params
                
        if (!solicitudDeTareaInstance.save(flush: true)) {
            render(view: "create", model: [solicitudDeTareaInstance: solicitudDeTareaCreateInstance])
            return
        }
        
	flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), solicitudDeTareaInstance.id])
        session.setAttribute("solicitudDeTareaCreate",null) 
        redirect(action: "show", id: solicitudDeTareaInstance.id)
    }

    def show() {
        cleanSelected()
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("solicitudDeTareaSelected", solicitudDeTareaInstance)    
        [solicitudDeTareaInstance: solicitudDeTareaInstance]
    }

    def edit() {
        cleanSelected()
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        session.setAttribute("solicitudDeTareaSelected", solicitudDeTareaInstance)  
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("solicitudDeTareaSelected", solicitudDeTareaInstance)
        [solicitudDeTareaInstance: solicitudDeTareaInstance]
    }

    def update() {
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (solicitudDeTareaInstance.version > version) {
                solicitudDeTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')] as Object[],
                          "Another user has updated this SolicitudDeTarea while you were editing")
                render(view: "edit", model: [solicitudDeTareaInstance: solicitudDeTareaInstance])
                return
            }
        }

        solicitudDeTareaInstance.properties = params

        if (!solicitudDeTareaInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDeTareaInstance: solicitudDeTareaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), solicitudDeTareaInstance.id])
        redirect(action: "show", id: solicitudDeTareaInstance.id)
    }

    def delete() {
        cleanSelected()
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            solicitudDeTareaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def cleanSelected() 
    {
         [ "tareaSelected",
        "equipoDeTareaSelectedTF",
        "materialDeTareaSelectedTF",
        "permisoAccesoSelectedTF",
        "documentoSelectedTF",
        "poSelectedTF",
         "prestamosSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
    }
}
