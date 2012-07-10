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
        def documentacionEmpleadoInstance= new DocumentacionEmpleado(params)
        documentacionEmpleadoInstance.setEmpleado(empleadoInstance)
        session.setAttribute("documentacionEmpleadoSelectedTF",new Boolean(true));
        [documentacionEmpleadoInstance: documentacionEmpleadoInstance, empleadoInstance: empleadoInstance]
    }

    def save() {
        def empleadoInstance=(Empleado)session.getAttribute("empleadoSelected")        
        params.empleado.id=empleadoInstance.id
        def documentacionEmpleadoInstance = new DocumentacionEmpleado(params)        
        if (!documentacionEmpleadoInstance.save(flush: true)) {            
            render(view: "create", model: [documentacionEmpleadoInstance: documentacionEmpleadoInstance, empleadoInstance: empleadoInstance])
            return
        }
        
        empleadoInstance.addToDocumentacion(documentacionEmpleadoInstance)
        flash.message = message(code: 'default.created.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), documentacionEmpleadoInstance.id])
        redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
    }

    def show() {
        def empleadoInstance=(Empleado)session.getAttribute("empleadoSelected") 
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
            return
        }
        session.setAttribute("documentacionEmpleadoSelectedTF",new Boolean(true));
        [documentacionEmpleadoInstance: documentacionEmpleadoInstance, empleadoInstance: empleadoInstance]
    }

    def edit() {
        def empleadoInstance=(Empleado)session.getAttribute("empleadoSelected") 
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
            return
        }

        [documentacionEmpleadoInstance: documentacionEmpleadoInstance, empleadoInstance: empleadoInstance]
    }

    def update() {
        def empleadoInstance=(Empleado)session.getAttribute("empleadoSelected") 
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (documentacionEmpleadoInstance.version > version) {
                documentacionEmpleadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado')] as Object[],
                          "Another user has updated this DocumentacionEmpleado while you were editing")
                render(view: "edit", model: [documentacionEmpleadoInstance: documentacionEmpleadoInstance, empleadoInstance: empleadoInstance])
                return
            }
        }

        documentacionEmpleadoInstance.properties = params

        if (!documentacionEmpleadoInstance.save(flush: true)) {
            render(view: "edit", model: [documentacionEmpleadoInstance: documentacionEmpleadoInstance, empleadoInstance: empleadoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), documentacionEmpleadoInstance.id])
        redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
    }

    def delete() {
        def empleadoInstance=(Empleado)session.getAttribute("empleadoSelected") 
        session.setAttribute("documentacionEmpleadoSelectedTF",new Boolean(true));
        def documentacionEmpleadoInstance = DocumentacionEmpleado.get(params.id)
        if (!documentacionEmpleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
            return
        }

        try {
            documentacionEmpleadoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado'), params.id])
            redirect(action: "show",controller: "empleado", id: empleadoInstance.id)
        }
    }
}
