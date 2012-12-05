package business.documento

import org.springframework.dao.DataIntegrityViolationException
import business.tarea.EstadoSolicitudTarea
import business.tarea.SolicitudDeTarea

class DocumentoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [documentoInstanceList: Documento.list(params), documentoInstanceTotal: Documento.count()]
    }

    def create() {
        session.setAttribute("documentoSelectedTF",true)
        [documentoInstance: new Documento(params)]
    }

    def save() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        def documentoInstance = new Documento(solicitudDeTarea: solicitudDeTareaSelected)
        documentoInstance.properties = params
        def f = request.getFile('uploadArchivo')
        if(!f.empty) {
            documentoInstance.nombreArchivo = f.getOriginalFilename()
            documentoInstance.archivo = f.inputStream.bytes
        }    else {
            flash.message = 'file cannot be empty'          
        }
        
        documentoInstance.estado = EstadoDocumento.findByNombre("Creado")
        documentoInstance.fechaRealizado = new Date()
        
        if (!documentoInstance.save(flush: true)) {
            render(view: "create", model: [documentoInstance: documentoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }
    
    def show() {
        session.setAttribute("documentoSelectedTF",true)
        def documentoInstance = Documento.get(params.id)
        
        
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "list")
            return
        }

        [documentoInstance: documentoInstance]
    }

    def edit() {
        session.setAttribute("documentoSelectedTF",true)
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "list")
            return
        }

        [documentoInstance: documentoInstance]
    }

    def update() {
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (documentoInstance.version > version) {
                documentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'documento.label', default: 'Documento')] as Object[],
                          "Another user has updated this Documento while you were editing")
                render(view: "edit", model: [documentoInstance: documentoInstance])
                return
            }
        }

        documentoInstance.properties = params
        def f = request.getFile('uploadArchivo')
        if(!f.empty) {
            documentoInstance.documentoDeIngenieria = f.getOriginalFilename()
            documentoInstance.archivo = f.inputStream.bytes
        }    else {
            flash.message = 'file cannot be empty'          
        }
        if (!documentoInstance.save(flush: true)) {
            render(view: "edit", model: [documentoInstance: documentoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
       
        if(solicitudDeTareaSelected.estado.id == EstadoSolicitudTarea.findByNombre('En Ejecucion').id){
            def estadoAceptado = EstadoDocumento.findByNombre('Aceptado')
            def areAllAcepted = true
            solicitudDeTareaSelected = SolicitudDeTarea.get(solicitudDeTareaSelected.id)
            solicitudDeTareaSelected.documentos.each{
                if(it.estado!=estadoAceptado){
                    areAllAcepted=false
                }
            }
            if(areAllAcepted){
                solicitudDeTareaSelected.estado = EstadoSolicitudTarea.findByNombre('Pendiente Cobro')
                solicitudDeTareaSelected.save(flush: true)
            }            
        }
        
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def delete() {
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "list")
            return
        }

        try {
            documentoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
            boolean isSolicitudCreate = true
            if(!solicitudDeTareaSelected){
                isSolicitudCreate = false
                solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
            }
            if(isSolicitudCreate){ 
                redirect(controller: "solicitudDeTarea", action: "create")
            }else{
                redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
            }
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    def downloadFile(){
        def documentoInstance = Documento.get(params.id)   

        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=${documentoInstance.nombreArchivo}")
        response.outputStream << documentoInstance.archivo
   
    }
    
    def aprobarDocumento(){
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "show")
            return
        }
        
        if (documentoInstance.estado.id != EstadoDocumento.findByNombre("Enviado").id){
            flash.error = "El documento debe estar en estado Enviado"
            redirect(action: "show")
            return
        }
        
        
        documentoInstance.estado = EstadoDocumento.findByNombre("Aprobado")
        documentoInstance.fechaAprobado = new Date()            
        documentoInstance.save(flush: true)
       
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")        
        verificarEstadoSolicitudTarea(solicitudDeTareaSelected)  
        redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        
        
    }
    
    def desaprobarDocumento(){
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "show")
            return
        }
        documentoInstance.estado = EstadoDocumento.findByNombre("Incompleto")
            
        if (documentoInstance.save(flush: true))
        {  flash.message = message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
               
        }
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }
    
    def verificarEstadoSolicitudTarea(solicitudDeTarea) {
        def solicitud = SolicitudDeTarea.get(solicitudDeTarea.id)
        boolean tieneDocumentacionOK = true
        def estadoAprobado = EstadoDocumento.findByNombre("Aprobado")
        def estadoSolicitudPendienteConformidad = EstadoSolicitudTarea.findByNombre("Pendiente Conformidad")
        solicitudDeTarea.documentos.each{
            if (it.estado.id != estadoAprobado.id )
            tieneDocumentacionOK = false
        }
        if (tieneDocumentacionOK && solicitud.estado.id == estadoSolicitudPendienteConformidad.id)
        {
            solicitud.estado = EstadoSolicitudTarea.findByNombre("Pendiente Cobro")
            solicitud.save(flush:true)            
        }
        
    }
}
