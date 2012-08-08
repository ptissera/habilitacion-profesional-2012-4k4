package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class DocumentacionIntegranteCuadrillaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [documentacionIntegranteCuadrillaInstanceList: DocumentacionIntegranteCuadrilla.list(params), documentacionIntegranteCuadrillaInstanceTotal: DocumentacionIntegranteCuadrilla.count()]
    }

    def create() {
        def integranteCuadrillaInstance=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected")  
        def documentacionIntegranteCuadrillaInstance= new DocumentacionIntegranteCuadrilla(params)
        documentacionIntegranteCuadrillaInstance.setIntegrante(integranteCuadrillaInstance)
        session.setAttribute("documentacionIntegranteCuadrillaSelectedTF",new Boolean(true))
        [documentacionIntegranteCuadrillaInstance: documentacionIntegranteCuadrillaInstance, integranteCuadrillaInstance: integranteCuadrillaInstance]        
    }

    def save() {
        def integranteCuadrillaInstance=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected")        
        params.integrante.id=integranteCuadrillaInstance.id
        def documentacionIntegranteCuadrillaInstance = new DocumentacionIntegranteCuadrilla(params)
        if (!documentacionIntegranteCuadrillaInstance.save(flush: true)) {
            render(view: "create", model: [documentacionIntegranteCuadrillaInstance: documentacionIntegranteCuadrillaInstance])
            return
        }
        integranteCuadrillaInstance.addToDocumentacion(documentacionIntegranteCuadrillaInstance)
	flash.message = message(code: 'default.created.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), documentacionIntegranteCuadrillaInstance.id])
        redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
    }

    def show() {
        def integranteCuadrillaInstance=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected") 
        def documentacionIntegranteCuadrillaInstance = DocumentacionIntegranteCuadrilla.get(params.id)
        if (!documentacionIntegranteCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
            return
        }
        session.setAttribute("documentacionIntegranteCuadrillaSelectedTF",new Boolean(true));
        [documentacionIntegranteCuadrillaInstance: documentacionIntegranteCuadrillaInstance, integranteCuadrillaInstance: integranteCuadrillaInstance]
    }

    def edit() {
        def integranteCuadrillaInstance=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected") 
        def documentacionIntegranteCuadrillaInstance = DocumentacionIntegranteCuadrilla.get(params.id)
        if (!documentacionIntegranteCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
            return
        }

        [documentacionIntegranteCuadrillaInstance: documentacionIntegranteCuadrillaInstance, integranteCuadrillaInstance: integranteCuadrillaInstance]
    }

    def update() {
        def integranteCuadrillaInstance=(IntegranteCuadrilla)session.getAttribute("integranteCuadrillaSelected") 
        def documentacionIntegranteCuadrillaInstance = DocumentacionIntegranteCuadrilla.get(params.id)
        if (!documentacionIntegranteCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (documentacionIntegranteCuadrillaInstance.version > version) {
                documentacionIntegranteCuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla')] as Object[],
                          "Another user has updated this DocumentacionIntegranteCuadrilla while you were editing")
                render(view: "edit", model: [documentacionIntegranteCuadrillaInstance: documentacionIntegranteCuadrillaInstance, integranteCuadrillaInstance: integranteCuadrillaInstance])
                return
            }
        }

        documentacionIntegranteCuadrillaInstance.properties = params

        if (!documentacionIntegranteCuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [documentacionIntegranteCuadrillaInstance: documentacionIntegranteCuadrillaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), documentacionIntegranteCuadrillaInstance.id])
        redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
    }

    def delete() {
        def documentacionIntegranteCuadrillaInstance = DocumentacionIntegranteCuadrilla.get(params.id)
        if (!documentacionIntegranteCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
            return
        }

        try {
            documentacionIntegranteCuadrillaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "integranteCuadrilla", id: documentacionIntegranteCuadrillaInstance.id)
        }
    }
}
