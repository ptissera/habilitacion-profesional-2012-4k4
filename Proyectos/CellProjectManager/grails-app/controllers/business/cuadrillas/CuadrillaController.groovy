package business.cuadrillas

import org.springframework.dao.DataIntegrityViolationException

class CuadrillaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        cleanSelected() 
        params.max = Math.min(params.max ? params.int('max') : 10, 100)        
        [cuadrillaInstanceList: Cuadrilla.list(), cuadrillaInstanceTotal: Cuadrilla.count()]
    }

    def create() {
        cleanSelected() 
        [cuadrillaInstance: new Cuadrilla(params)]
    }

    def save() {
        def cuadrillaInstance = new Cuadrilla(params)
        cuadrillaInstance.estadoCuadrilla = EstadoCuadrilla.findByNombre('Sin Asignacion')
        if (!cuadrillaInstance.save(insert:true, flush: true)) {
            render(view: "create", model: [cuadrillaInstance: cuadrillaInstance])
            return
        }        
        def historial = new HistorialCuadrilla(fecha: new Date(), cuadrilla: this)       
        historial.setDescripcion("    Cuadrilla -- Alta -- (${cuadrillaInstance.toString()})")
        historial.save()
        flash.message = message(code: 'default.created.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), cuadrillaInstance.id])
        redirect(action: "show", id: cuadrillaInstance.id)
    }

    def show() {
        cleanSelected() 
        def cuadrillaInstance = Cuadrilla.get(params.id)
        if (!cuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("cuadrillaSelected",cuadrillaInstance);
        session.setAttribute("integranteCuadrillaSelected",null);
        session.setAttribute("historialCuadrillaSelectedTF",null);
        [cuadrillaInstance: cuadrillaInstance]
    }

    def edit() {
        cleanSelected() 
        def cuadrillaInstance = Cuadrilla.get(params.id)
        if (!cuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), params.id])
            redirect(action: "list")
            return
        }
        
        [cuadrillaInstance: cuadrillaInstance]
    }

    def update() {
        def cuadrillaInstance = Cuadrilla.get(params.id)
        if (!cuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (cuadrillaInstance.version > version) {
                cuadrillaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'cuadrilla.label', default: 'Cuadrilla')] as Object[],
                          "Another user has updated this Cuadrilla while you were editing")
                render(view: "edit", model: [cuadrillaInstance: cuadrillaInstance])
                return
            }
        }

        cuadrillaInstance.properties = params

        if (!cuadrillaInstance.save(flush: true)) {
            render(view: "edit", model: [cuadrillaInstance: cuadrillaInstance])
            return
        }
     	flash.message = message(code: 'default.updated.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), cuadrillaInstance.id])
        redirect(action: "show", id: cuadrillaInstance.id)
    }

    def delete() {
        def cuadrillaInstance = Cuadrilla.get(params.id)        
        
        if (!cuadrillaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), params.id])
            redirect(action: "list")
            return
        }

        try {
            cuadrillaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla'), params.id])
            redirect(action: "show", id: params.id)
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
