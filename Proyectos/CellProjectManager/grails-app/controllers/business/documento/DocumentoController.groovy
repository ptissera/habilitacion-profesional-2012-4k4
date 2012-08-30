package business.documento

import org.springframework.dao.DataIntegrityViolationException

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
        def tareaSelected = session.getAttribute("tareaSelected")
        def documentoInstance = new Documento(tareasPorSitio: tareaSelected)
        documentoInstance.properties = params
        def f = request.getFile('archivo')
        if(!f.empty) {
            documentoInstance.nombreArchivo = f.getOriginalFilename()
            documentoInstance.archivo = f.inputStream.bytes
        }    else {
	       flash.message = 'file cannot be empty'          
	    }
        if (!documentoInstance.save(flush: true)) {
            render(view: "create", model: [documentoInstance: documentoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])
        redirect(controller: "tareaPorSitio", action: "edit", id: tareaSelected.id)
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

        if (!documentoInstance.save(flush: true)) {
            render(view: "edit", model: [documentoInstance: documentoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])
        def tareaSelected = session.getAttribute("tareaSelected")
        redirect(controller: "tareaPorSitio", action: "edit", id: tareaSelected.id)
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
            def tareaSelected = session.getAttribute("tareaSelected")
        redirect(controller: "tareaPorSitio", action: "edit", id: tareaSelected.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
