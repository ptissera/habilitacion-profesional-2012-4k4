package business.tarea
import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import support.secure.Usuario

class AcontecimientoController {

     static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [acontecimientoInstanceList: Acontecimiento.list(params), acontecimientoInstanceTotal: Acontecimiento.count()]
    }

    def create() {
        def acontecimientoInstance = new Acontecimiento(params)
        def tareaSelected = session.getAttribute("tareaCreate")        
        if(!tareaSelected){            
            tareaSelected = session.getAttribute("tareaSelected")
        }
        session.setAttribute("acontecimientoSelected",acontecimientoInstance)
        [acontecimientoInstance: acontecimientoInstance]
    }

    def save() {
        def tareaSelected = session.getAttribute("tareaCreate")
        boolean isSolicitudCreate = true
        if(!tareaSelected){
            isSolicitudCreate = false
            tareaSelected = session.getAttribute("tareaSelected")
        }
              
        def acontecimientoInstance = new Acontecimiento(tarea: tareaSelected) 
        acontecimientoInstance.properties = params
        
        if (!acontecimientoInstance.save(flush: true)) {
            render(view: "create", model: [acontecimientoInstance: acontecimientoInstance])
            return
        }
       
        if(isSolicitudCreate){ 
            redirect(controller: "tarea", action: "edit")
        }else{
            redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
        }
    }

    def show() {
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("AcontecimientoSelected",acontecimientoInstance)
        [acontecimientoInstance: acontecimientoInstance]
    }

    def edit() {
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("AcontecimientoSelected",acontecimientoInstance)
        [acontecimientoInstance: acontecimientoInstance]
    }

    def update() {
         
        def tareaSelected = session.getAttribute("tareaCreate")
        boolean isSolicitudCreate = true
        if(!tareaSelected){
            isSolicitudCreate = false
            tareaSelected = session.getAttribute("tareaSelected")
        }
       
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (acontecimientoInstance.version > version) {
                acontecimientoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'Acontecimiento.label', default: 'Acontecimiento')] as Object[],
                          "Another user has updated this Acontecimiento while you were editing")
                render(view: "edit", model: [acontecimientoInstance: acontecimientoInstance])
                return
            }
        }

        acontecimientoInstance.properties = params
        if (!acontecimientoInstance.save(flush: true)) {
            render(view: "edit", model: [acontecimientoInstance: acontecimientoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'acontecimiento.label', default: 'Acontecimiento'), acontecimientoInstance.id])
        if(isSolicitudCreate){ 
            redirect(controller: "tarea", action: "create")
        }else{
            redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
        }
    }

    def delete() {
        
        def tareaSelected = session.getAttribute("tareaCreate")
        boolean isSolicitudCreate = true
        if(!tareaSelected){
            isSolicitudCreate = false
            tareaSelected = session.getAttribute("tareaSelected")
        }
        def acontecimientoInstance = Acontecimiento.get(params.id)
        if (!acontecimientoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "list")
            return
        }

        try {
            acontecimientoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            if(isSolicitudCreate){ 
                redirect(controller: "tarea", action: "create")
            }else{
                redirect(controller: "tarea", action: "edit", id: tareaSelected.id)
            }
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'Acontecimiento.label', default: 'Acontecimiento'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
     def rest(){
         switch(request.method)
        {
            case 'GET':
                doGetRest(params)
                break;
                
            case 'POST':
                doPostRest(params)
                break;

            case 'PUT':
                doPutRest(params)
                break;
                
        }  
        
    }
    
    def doGetRest (params){
        def acontecimientos = Tarea.findAllById(params.id).acontecimientos
        def respuesta
        def id
        def nombreTipo        
        def creador
        def fechaCreacion
        def descripcion
        def i=0
        boolean esPrimetasLinea = true
        acontecimientos.each{
                   boolean finLista = false
                   while (!finLista)              
                   {
                    id = it.id[i]
                    nombreTipo = it.tipoAcontecimiento[i].nombre
                    creador = it.creador[i].nombreUsuario
                    fechaCreacion = it.fechaCreacion[i].format("ddMMyyyy")
                    descripcion = it.descripcion[i]
                    if (esPrimetasLinea)
                    respuesta = "{ id: $id, nombreTipo: '$nombreTipo', creadorPor: '$creador', fechaCreacion: '$fechaCreacion', descripcion: '$descripcion' } "
                    else
                    respuesta = respuesta +  " , " + "{ id: $id, nombreTipo: '$nombreTipo', creadoPor: '$creador', fechaCreacion: '$fechaCreacion', descripcion: '$descripcion' } "               
                    esPrimetasLinea=false
                    i++
                    if (it.id[i] == null)
                      finLista = true
                   }

            }
            
       if (respuesta) {
                render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }, 'acontecimientos': [$respuesta]}") as JSON
                
         }
        response.status=200
        render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
        
    }
    
    def doPutRest (params){
        def acontecimientoInstance = Acontecimiento.findById(params.id)
        def objetoJSON = request.JSON
        acontecimientoInstance.descripcion = objetoJSON.descripcion
        acontecimientoInstance.tipoAcontecimiento = TipoAcontecimiento.findByNombre(objetoJSON.nombreTipo)
        acontecimientoInstance.creador = Usuario.findByNombreUsuario(objetoJSON.creadoPor)
        if (objetoJSON.fechaCreacion != "")
         acontecimientoInstance.fechaCreacion = new Date().parse("ddMMyyyy", objetoJSON.fechaCreacion) 
        
        if (acontecimientoInstance.save(flush:true)) {
                render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }}") as JSON
                
            }
        else{
                response.status=200
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            }
    }
    
    def doPostRest (params){
        def objetoJSON = request.JSON
        if (new Acontecimiento(tarea: Tarea.findById(params.id), descripcion: objetoJSON.descripcion, tipoAcontecimiento: TipoAcontecimiento.findByNombre(objetoJSON.nombreTipo), creador: Usuario.findByNombreUsuario(objetoJSON.creadoPor), fechaCreacion: new Date().parse("ddMMyyyy", objetoJSON.fechaCreacion)).save(flush:true)) {
                render  JSON.parse("{ error: { codigo: 0, descripcion: 'Exito' }}") as JSON
                
            }
        else{
                response.status=200
                render JSON.parse("{ error: { codigo: 1, descripcion: 'Fallo' }}") as JSON
            }
    }
}
