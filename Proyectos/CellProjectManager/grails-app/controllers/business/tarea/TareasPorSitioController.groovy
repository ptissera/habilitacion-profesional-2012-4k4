package business.tarea

import org.springframework.dao.DataIntegrityViolationException

class TareasPorSitioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tareasPorSitioInstanceList: TareasPorSitio.list(params), tareasPorSitioInstanceTotal: TareasPorSitio.count()]
    }

    def create() {
        def tareasPorSitioInstance = new TareasPorSitio(params)
        session.setAttribute("tareaSelected",tareasPorSitioInstance)
        [tareasPorSitioInstance: tareasPorSitioInstance]
    }

    def save() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
       }
              
        def tareasPorSitioInstance = new TareasPorSitio(solicitudDeTarea: solicitudDeTareaSelected) 
        tareasPorSitioInstance.properties = params
        if (!tareasPorSitioInstance.save(flush: true)) {
            render(view: "create", model: [tareasPorSitioInstance: tareasPorSitioInstance])
            return
        }
       
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def show() {
        def tareasPorSitioInstance = TareasPorSitio.get(params.id)
        if (!tareasPorSitioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio'), params.id])
            redirect(action: "list")
            return
        }
session.setAttribute("tareaSelected",tareasPorSitioInstance)
        [tareasPorSitioInstance: tareasPorSitioInstance]
    }

    def edit() {
        def tareasPorSitioInstance = TareasPorSitio.get(params.id)
        if (!tareasPorSitioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio'), params.id])
            redirect(action: "list")
            return
        }
session.setAttribute("tareaSelected",tareasPorSitioInstance)
        [tareasPorSitioInstance: tareasPorSitioInstance]
    }

    def update() {
         
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
       }
       
        def tareasPorSitioInstance = TareasPorSitio.get(params.id)
        if (!tareasPorSitioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tareasPorSitioInstance.version > version) {
                tareasPorSitioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')] as Object[],
                          "Another user has updated this TareasPorSitio while you were editing")
                render(view: "edit", model: [tareasPorSitioInstance: tareasPorSitioInstance])
                return
            }
        }

        tareasPorSitioInstance.properties = params

        if (!tareasPorSitioInstance.save(flush: true)) {
            render(view: "edit", model: [tareasPorSitioInstance: tareasPorSitioInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio'), tareasPorSitioInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def delete() {
         def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
       }
        def tareasPorSitioInstance = TareasPorSitio.get(params.id)
        if (!tareasPorSitioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio'), params.id])
            redirect(action: "list")
            return
        }

        try {
            tareasPorSitioInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio'), params.id])
             if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
