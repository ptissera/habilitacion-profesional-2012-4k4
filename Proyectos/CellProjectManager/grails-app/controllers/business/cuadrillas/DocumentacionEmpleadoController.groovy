package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class DocumentacionEmpleadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [documentacionEmpleadoInstanceList: DocumentacionEmpleado.list(params), documentacionEmpleadoInstanceTotal: DocumentacionEmpleado.count()]
    }

    def create() {
        def empleadoInstance=session.getAttribute("empleadoSelected")              
        [documentacionEmpleadoInstance: new DocumentacionEmpleado(params), empleadoInstance: empleadoInstance]
    }

    def save() {
        def empleadoInstance=(Empleado)session.getAttribute("empleadoSelected")        
        params.empleado.id=empleadoInstance.id
        def documentacionEmpleadoInstance = new DocumentacionEmpleado(params)
        
        
        if (!documentacionEmpleadoInstance.save(flush: true)) {            
            render(view: "create", model: [documentacionEmpleadoInstance: documentacionEmpleadoInstance])
            return
        }
        
        empleadoInstance.addDocumentacionEmpleado(documentacionEmpleadoInstance)
        flash.message = message(code: 'default.created.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), documentacionEmpleadoInstance.id])
        redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
    }

    def show() {
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        [documentacionEmpleadoInstance: documentacionEmpleadoInstance]
    }

    def edit() {
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        [documentacionEmpleadoInstance: documentacionEmpleadoInstance]
    }

    def update() {
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (documentacionEmpleadoInstance.version > version) {
                documentacionEmpleadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado')] as Object[],
                          "Another user has updated this DocumentacionEmpleado while you were editing")
                render(view: "edit", model: [documentacionEmpleadoInstance: documentacionEmpleadoInstance])
                return
            }
        }

        documentacionEmpleadoInstance.properties = params

        if (!documentacionEmpleadoInstance.save(flush: true)) {
            render(view: "edit", model: [documentacionEmpleadoInstance: documentacionEmpleadoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), documentacionEmpleadoInstance.id])
        redirect(action: "show", id: documentacionEmpleadoInstance.id)
    }

    def delete() {
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "list")
            return
        }

        try {
            documentacionEmpleadoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
