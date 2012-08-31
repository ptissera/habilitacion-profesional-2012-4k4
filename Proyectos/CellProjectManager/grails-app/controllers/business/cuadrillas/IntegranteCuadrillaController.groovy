package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class IntegranteCuadrillaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        cleanSelected() 
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [integranteCuadrillaInstanceList: IntegranteCuadrilla.list(params), integranteCuadrillaInstanceTotal: IntegranteCuadrilla.count()]
    }

    def create() {
        cleanSelected() 
        def integranteCuadrillaInstance=new IntegranteCuadrilla(params)        
        session.setAttribute("integranteCuadrillaSelected",integranteCuadrillaInstance);
        [integranteCuadrillaInstance: new IntegranteCuadrilla(params), cuadrilaInstance: session.getAttribute("cuadrillaSelected")]
    }

    def save() {
        def cuadrilaInstance=session.getAttribute("cuadrillaSelected")    
        def integranteCuadrillaInstance = new IntegranteCuadrilla(params)
        integranteCuadrillaInstance.setFechaAlta(new Date())
        integranteCuadrillaInstance.setCuadrilla(cuadrilaInstance)
        if (!integranteCuadrillaInstance.save(flush: true)) {
            render(view: "create", model: [integranteCuadrillaInstance: integranteCuadrillaInstance])
            return
        }
        def historial=new HistorialCuadrilla(fecha: new Date(), cuadrilla: cuadrilaInstance)       
        historial.setDescripcion("    IntegranteCuadrilla -- Alta -- ($integranteCuadrillaInstance)")
        historial.save()
		flash.message = message(code: 'default.created.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), integranteCuadrillaInstance.id])
        redirect(action: "show",controller: "integranteCuadrilla", id: integranteCuadrillaInstance.id)
    }

    def show() {
        cleanSelected() 
        def integranteCuadrillaInstance = IntegranteCuadrilla.get(params.id)
        session.setAttribute("integranteCuadrillaSelected",integranteCuadrillaInstance);
        if (!integranteCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), params.id])
            redirect(action: "list")
            return
        }
        
        
        [integranteCuadrillaInstance: integranteCuadrillaInstance]
    }

    def edit() {
        cleanSelected() 
        def integranteCuadrillaInstance = IntegranteCuadrilla.get(params.id)
        if (!integranteCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), params.id])
            redirect(action: "list")
            return
        }        
        [integranteCuadrillaInstance: integranteCuadrillaInstance]
    }

    def update() {
        def integranteCuadrillaInstance = IntegranteCuadrilla.get(params.id)
        if (!integranteCuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (integranteCuadrillaInstance.version > version) {
                integranteCuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla')] as Object[],
                          "Another user has updated this IntegranteCuadrilla while you were editing")
                render(view: "edit", model: [integranteCuadrillaInstance: integranteCuadrillaInstance])
                return
            }
        }

        integranteCuadrillaInstance.properties = params

        if (!integranteCuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [integranteCuadrillaInstance: integranteCuadrillaInstance])
            return
        }       
        
		flash.message = message(code: 'default.updated.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), integranteCuadrillaInstance.id])
        redirect(action: "show",controller: "integranteCuadrilla", id: integranteCuadrillaInstance.id)
    }

    def delete() {
        def integranteCuadrillaInstance = IntegranteCuadrilla.get(params.id)       
        
        def cuadrilaInstance=session.getAttribute("cuadrillaSelected") 
        def historial=new HistorialCuadrilla(fecha: new Date(), cuadrilla: cuadrilaInstance)       
        historial.setDescripcion("    IntegranteCuadrilla -- Baja -- ($integranteCuadrillaInstance)")
        historial.save()
        if (!integranteCuadrillaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "cuadrilla", id: cuadrilaInstance.id)
            return
        }

        try {
            integranteCuadrillaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), params.id])
            redirect(action: "show",controller: "integranteCuadrilla", id: integranteCuadrillaInstance.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla'), params.id])
           redirect(action: "show",controller: "integranteCuadrilla", id: integranteCuadrillaInstance.id)
        }
    }
    
     def cleanSelected() 
    {
         [ "integranteCuadrillaSelected",
        "documentacionIntegranteCuadrillaSelectedTF",
        "historialCuadrillaSelectedTF"].each{ name ->
            session.setAttribute(name , null)
        }
    }
}
