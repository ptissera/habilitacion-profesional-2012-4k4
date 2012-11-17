package business.herramienta
import business.cuadrillas.*
import business.tarea.*

import org.springframework.dao.DataIntegrityViolationException

class PrestamoHerramientaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [prestamoHerramientaInstanceList: PrestamoHerramienta.list(params), prestamoHerramientaInstanceTotal: PrestamoHerramienta.count()]
        
    }
    
    def listPrestamosActivos() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [prestamoHerramientaInstanceList: PrestamoHerramienta.findAllByFechaDevolucionRealIsNull(), prestamoHerramientaInstanceTotal: PrestamoHerramienta.count()]
    }
    
    def registrarDevolucion(){
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
          [prestamoHerramientaInstance: prestamoHerramientaInstance]
    }

    def create() {
        session.setAttribute("prestamosSelectedTF",true)
        [prestamoHerramientaInstance: new PrestamoHerramienta(params)]
    }

    def guardarDevolucion() {
        
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        def fechaDevolucionReal = params.fechaDevolucionReal_value ? new Date().parse("dd/MM/yyyy", params.fechaDevolucionReal_value):null
        if(!fechaDevolucionReal){
            flash.message = "Debe ingresar fecha de Devolucion Real"
            render(view: "registrarDevolucion", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
            return
        }
        prestamoHerramientaInstance.fechaDevolucionReal = fechaDevolucionReal
        if (!prestamoHerramientaInstance.save(flush: true)) {
            render(view: "registrarDevolucion", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
            return
        }else {
                def herramienta = Herramienta.get(prestamoHerramientaInstance.herramientaId)
                herramienta.estado = EstadoHerramienta.findByNombre("Libre")
                herramienta.save(flush: true)
        }

        flash.message = "La devolucion fue registrada correctamente"
         redirect(controller: "prestamoHerramienta", action: "listPrestamosActivos")
        
        
    }
    def save() {
        def solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaCreate")
        boolean isSolicitudCreate = true
        if(!solicitudDeTareaSelected){
            isSolicitudCreate = false
            solicitudDeTareaSelected = session.getAttribute("solicitudDeTareaSelected")
        }
        def prestamoHerramientaInstance = new PrestamoHerramienta(solicitud: solicitudDeTareaSelected, cuadrilla: solicitudDeTareaSelected.cuadrilla) 
        prestamoHerramientaInstance.properties = params
        
        def herramienta = prestamoHerramientaInstance.herramienta
        if (herramienta.estado == EstadoHerramienta.findByNombre("Prestada") )
          {    flash.message = "La herramienta se encuentra en Prestamo"
                  render(view: "create", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
                  return
          }else{
           if (herramienta.estado == EstadoHerramienta.findByNombre("No disponible") )
              {  flash.message = "La herramienta no esta Disponible"
                  render(view: "create", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
                  return
              } 
         }
        if (!prestamoHerramientaInstance.save(flush: true)) {
            render(view: "create", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
            return
        }else {
                herramienta.estado = EstadoHerramienta.findByNombre("Prestada")
                herramienta.save(flush: true)
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), prestamoHerramientaInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "solicitudDeTarea", action: "create")
        }else{
            redirect(controller: "solicitudDeTarea", action: "edit", id: solicitudDeTareaSelected.id)
        }
    }

    def show() {
        session.setAttribute("prestamosSelectedTF",true)
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        [prestamoHerramientaInstance: prestamoHerramientaInstance]
    }

    def edit() {
        session.setAttribute("prestamosSelectedTF",true)
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        [prestamoHerramientaInstance: prestamoHerramientaInstance]
    }

    def update() {
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        
        if (!prestamoHerramientaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (prestamoHerramientaInstance.version > version) {
                prestamoHerramientaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta')] as Object[],
                          "Another user has updated this PrestamoHerramienta while you were editing")
                render(view: "edit", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
                return
            }
        }
        // si cambio la herramienta valido
         if (prestamoHerramientaInstance.herramienta != Herramienta.findById(params.herramienta.id) )
         {def herramienta = Herramienta.findById(params.herramienta.id)
         if (herramienta.estado == EstadoHerramienta.findByNombre("Prestada") )
          {    flash.message = "La herramienta se encuentra en Prestamo"
                  render(view: "edit", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
                  return
          }else{
           if (herramienta.estado == EstadoHerramienta.findByNombre("No disponible") )
              {  flash.message = "La herramienta no esta Disponible"
                  render(view: "edit", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
                  return
              } 
         }
         }
         
        //si cargo fecha de devolucion real, cambio estado a libre
        if (params.fechaDevolucionReal)
         {     def herramienta = Herramienta.findById(params.herramienta.id)
               herramienta.estado = EstadoHerramienta.findByNombre("Libre")
               herramienta.save(flush: true)
         } 
         
        
        prestamoHerramientaInstance.properties = params
        
        if (!prestamoHerramientaInstance.save(flush: true)) {
            render(view: "edit", model: [prestamoHerramientaInstance: prestamoHerramientaInstance])
            return
        } 

	flash.message = message(code: 'default.updated.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), prestamoHerramientaInstance.id])
        
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

    def delete() {
        def prestamoHerramientaInstance = PrestamoHerramienta.get(params.id)
        if (!prestamoHerramientaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "list")
            return
        }

        try {
            prestamoHerramientaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
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
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
