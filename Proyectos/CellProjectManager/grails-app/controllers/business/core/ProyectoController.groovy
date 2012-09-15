package business.core

import support.secure.Usuario

import org.springframework.dao.DataIntegrityViolationException

class ProyectoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proyectoInstanceList: Proyecto.list(params), proyectoInstanceTotal: Proyecto.count()]
    }

    def selectedProject() {
        session.setAttribute("proyectoSelected", Proyecto.get(params.id))
        
        if(session.getAttribute("aDondeVoy")) {            
            def donde = session.getAttribute("aDondeVoy")            
            session.setAttribute("aDondeVoy",null)            
            redirect(controller: donde[0], action: donde[1])
        } else {
            redirect(uri:"/")
        }
    }
    
    def selectList() {
        def solicitudDeTareaCreate = session.getAttribute("solicitudDeTareaCreate")
        if (solicitudDeTareaCreate){
            solicitudDeTareaCreate.delete(flush: true)
        }
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proyectoInstanceList: Proyecto.list(params), proyectoInstanceTotal: Proyecto.count()]
    }
    
    def asignProject() {
        def solicitudDeTareaCreate = session.getAttribute("solicitudDeTareaCreate")
        if (solicitudDeTareaCreate){
            solicitudDeTareaCreate.delete(flush: true)
        }
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proyectoInstanceList: Proyecto.list(params), proyectoInstanceTotal: Proyecto.count()]
        
    }
    
    def userForProject = {
	    def proyectoInstance = Proyecto.get(params.idProyecto)	
        proyectoInstance.usuario = Usuario.get(params.idUsuario)    
        proyectoInstance.estadoProyecto = EstadoProyecto.findByNombre('Activo')
        proyectoInstance.save(flush: true)
        render "Proyecto ${proyectoInstance} asignado a ${proyectoInstance.usuario}"        
    }
    
    def create() {
        [proyectoInstance: new Proyecto(params)]
    }

    def save() {
        def proyectoInstance = new Proyecto(estadoProyecto: EstadoProyecto.findByNombre("Creado"))
        proyectoInstance.properties = params
        proyectoInstance.fechaCreacion = new Date()
        if (!proyectoInstance.save(flush: true)) {
            render(view: "create", model: [proyectoInstance: proyectoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), proyectoInstance.id])
        redirect(action: "show", id: proyectoInstance.id)
    }

    def show() {
        def proyectoInstance = Proyecto.get(params.id)
        if (!proyectoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), params.id])
            redirect(action: "list")
            return
        }

        [proyectoInstance: proyectoInstance]
    }

    def edit() {
        def proyectoInstance = Proyecto.get(params.id)
        if (!proyectoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), params.id])
            redirect(action: "list")
            return
        }

        [proyectoInstance: proyectoInstance]
    }

    def update() {
        def proyectoInstance = Proyecto.get(params.id)
        if (!proyectoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (proyectoInstance.version > version) {
                proyectoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proyecto.label', default: 'Proyecto')] as Object[],
                          "Another user has updated this Proyecto while you were editing")
                render(view: "edit", model: [proyectoInstance: proyectoInstance])
                return
            }
        }

        proyectoInstance.properties = params

        if (!proyectoInstance.save(flush: true)) {
            render(view: "edit", model: [proyectoInstance: proyectoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), proyectoInstance.id])
        redirect(action: "show", id: proyectoInstance.id)
    }

    def delete() {
        def proyectoInstance = Proyecto.get(params.id)
        if (!proyectoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), params.id])
            redirect(action: "list")
            return
        }

        try {
            proyectoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
