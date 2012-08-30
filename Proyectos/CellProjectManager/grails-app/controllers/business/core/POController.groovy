package business.core

import org.springframework.dao.DataIntegrityViolationException

class POController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [POInstanceList: PO.list(params), POInstanceTotal: PO.count()]
    }

    def create() {
        session.setAttribute("poSelectedTF",true)
        [POInstance: new PO(params)]
    }

    def save() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        def POInstance = new PO(solicitud: solicitudDeTareaSelected) 
        POInstance.properties = params
        def f = request.getFile('archivo')
        if(!f.empty) {
            POInstance.nombreArchivo = f.getOriginalFilename()
            POInstance.archivo = f.inputStream.bytes
        }    else {
            flash.message = 'file cannot be empty'          
        }
        if (!POInstance.save(flush: true)) {
            render(view: "create", model: [POInstance: POInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'PO.label', default: 'PO'), POInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def show() {
        session.setAttribute("poSelectedTF",true)
        def POInstance = PO.get(params.id)
        if (!POInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        [POInstance: POInstance]
    }

    def edit() {
        session.setAttribute("poSelectedTF",true)
        def POInstance = PO.get(params.id)
        if (!POInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        [POInstance: POInstance]
    }

    def update() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        def POInstance = PO.get(params.id)
        
        if (!POInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (POInstance.version > version) {
                POInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'PO.label', default: 'PO')] as Object[],
                          "Another user has updated this PO while you were editing")
                render(view: "edit", model: [POInstance: POInstance])
                return
            }
        }

        POInstance.properties = params
        def f = request.getFile('archivo')
        if(!f.empty) {
            POInstance.nombreArchivo = f.getOriginalFilename()
            POInstance.archivo = f.inputStream.bytes
        }   
        if (!POInstance.save(flush: true)) {
            render(view: "edit", model: [POInstance: POInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'PO.label', default: 'PO'), POInstance.id])
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
        def POInstance = PO.get(params.id)
        if (!POInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "list")
            return
        }

        try {
            POInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            if(isSolicitudCreate){ 
                redirect(controller: "solicitudDeTarea", action: "create")
            }else{
                redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
            }
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'PO.label', default: 'PO'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def downloadFile(){
        def POInstance = PO.get(params.id)   

        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=${POInstance.nombreArchivo}")
        response.outputStream << POInstance.archivo
   
    }
}
