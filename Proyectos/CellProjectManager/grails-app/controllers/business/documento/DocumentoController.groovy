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
        [documentoInstance: new Documento(params)]
    }

    def save() {
        def documentoInstance = new Documento(params)
        def f = request.getFile('nombreArchivo')
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
        redirect(action: "show", id: documentoInstance.id)
    }
    
    def upload = {
		def f = request.getFile('nombreArchivo')
	    if(!f.empty) {
	      flash.message = 'Your file has been uploaded'
		  new File( grailsApplication.config.images.location.toString() ).mkdirs()
		  f.transferTo( new File( grailsApplication.config.images.location.toString() + File.separatorChar + f.getOriginalFilename() ) )								             			     	
		}    
	    else {
	       flash.message = 'file cannot be empty'
	    }
	
	}

    def show() {
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "list")
            return
        }

        [documentoInstance: documentoInstance]
    }

    def edit() {
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
        redirect(action: "show", id: documentoInstance.id)
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
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
