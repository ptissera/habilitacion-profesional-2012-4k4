package business.tarea

import org.springframework.dao.DataIntegrityViolationException
import business.core.Proyecto
import business.core.Cliente
import business.cuadrillas.Cuadrilla
import business.solicitud.*
import support.tool.ParametrosDelSistema

class SolicitudDeTareaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        cleanSelected()
        Proyecto proyectoSelected=(Proyecto)session.getAttribute("proyectoSelected")
        if(proyectoSelected){
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def solicitudDeTareaInstanceList = SolicitudDeTarea.findAllByProyecto(proyectoSelected)
            [solicitudDeTareaInstanceList: solicitudDeTareaInstanceList, solicitudDeTareaInstanceTotal: 10]
        }else{
            session.setAttribute("aDondeVoy",["solicitudDeTarea","list"])
            redirect(action: "selectList", controller: "proyecto")
        }
    }
    
    def enviarDocumentacionACliente(){
         
        def solicitudDeTareaInstance = session.solicitudDeTareaSelected
        session.enviarDocumentacionAClienteTF = true
        def proyectoInstance = session.proyectoSelected
        def clienteInstance = Cliente.get(proyectoInstance.clienteId)        
        [solicitudDeTareaInstance: solicitudDeTareaInstance, emailCliente: clienteInstance.contactoEmail]
    }

    def pasarEnEjecutacion(){      
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)   
                 
        if(solicitudDeTareaInstance.tarea){
            solicitudDeTareaInstance.tarea.each{ tarea ->
                if(tarea.tipoTarea.requierePermisoDeAcceso){
                    if(tarea.permisos){
                        def isOK = false
                        tarea.permisos.each{ permiso ->
                            if(tarea.fechaInicio >= permiso.fechaDesde && tarea.fechaFin <= permiso.fechaHasta){
                                isOK = true
                            }
                        }
                        if(!isOK){
                            flash.error = "La tarea ${tarea} contiene permisos de acceso fuera de limites del permiso de acceso"
                        }
                    }else{
                        flash.error = "La tarea ${tarea} no contiene permisos de acceso"                        
                    }
                }
                if(tarea.tipoTarea.requiereIngenieria){
                    if(!tarea.documentoDeIngenieria){
                        flash.error = "La tarea ${tarea} no contiene documento de Ingenieria"  
                    }
                }
            }
        }else{
            flash.error = "La solicitud de tarea no contiene tareas aun!!"            
        }
        
        if(!solicitudDeTareaInstance.pos){
            flash.error = "La solicitud de tarea no PO aun!!"
        }
        
        Cuadrilla cuadrilla = Cuadrilla.get(solicitudDeTareaInstance.cuadrilla.id)
        EstadoSolicitudTarea estadoEnEjecucion = EstadoSolicitudTarea.findByNombre('En Ejecucion')
        def solicitudEnEjecucion = SolicitudDeTarea.findAllByEstadoAndCuadrilla(estadoEnEjecucion, cuadrilla);
        if(solicitudEnEjecucion.size()>0){
            flash.error = "La cuadrilla ${cuadrilla} ya tiene una solicitud de tarea en ejecucion"
        }
        
        if(flash.error){
            redirect(action: "show", id: solicitudDeTareaInstance.id)            
        }else{
            solicitudDeTareaInstance.estado = estadoEnEjecucion
            solicitudDeTareaInstance.save(flush: true)
            flash.message = "Solicitud de Tarea En Ejecucion"
            redirect(action: "show", id: solicitudDeTareaInstance.id)
        }
    }
    
    def registrarSolicitudDeViaticos(){
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id) 
        
        if(!solicitudDeTareaInstance.tarea){
            flash.message = "No se puede crear la Solicitud de Viaticos. La solicitud de tareas no contiene tareas aun!"
        }else{
            def parametroViaticos = ParametrosDelSistema.findByNombre('PROM_VIATICO_DIA_OPERARIO')
            def minDate 
            def maxDate 
            solicitudDeTareaInstance.tarea.each{
                if(!minDate || minDate>it.fechaInicio){
                    minDate = it.fechaInicio
                }
                if(!maxDate || maxDate<it.fechaFin){
                    maxDate = it.fechaFin
                }
            }
            def monto = (maxDate - minDate + 1) * Float.valueOf(parametroViaticos.valor) * solicitudDeTareaInstance.cuadrilla.operarios.size()
            def solicitudDeViaticos = new SolicitudDeViaticos(fechaCreacion: new Date(), 
                solicitud:solicitudDeTareaInstance , 
                monto: monto, 
                estado:EstadoSolicitudDeViaticos.findByNombre('Pendiente'))
            solicitudDeViaticos.save(flush: true)        
            flash.message = "Nueva Solicitud de Viaticos generada"
        }
        redirect(action: "show", id: solicitudDeTareaInstance.id)
    }
    
    def create() {
        cleanSelected()
        Proyecto proyectoSelected=(Proyecto)session.getAttribute("proyectoSelected")
         
        if(proyectoSelected){
            def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaCreate")      
            if(!solicitudDeTareaInstance){
                solicitudDeTareaInstance   = new SolicitudDeTarea(fechaAlta: new Date(), proyecto: proyectoSelected, cuadrilla: Cuadrilla.findByNombre('Perez'),
                    estado: EstadoSolicitudTarea.findByNombre('Creada'))
                solicitudDeTareaInstance.save(flush: true)          
                session.setAttribute("solicitudDeTareaCreate",solicitudDeTareaInstance)            
            }
            solicitudDeTareaInstance = SolicitudDeTarea.get(solicitudDeTareaInstance.id)
            [solicitudDeTareaInstance: solicitudDeTareaInstance]
        }else{
            session.setAttribute("aDondeVoy",["solicitudDeTarea","create"])
            redirect(action: "selectList", controller: "proyecto")
        }
    }

    def save() {       
        def solicitudDeTareaInstance = session.getAttribute("solicitudDeTareaCreate")  
        solicitudDeTareaInstance = SolicitudDeTarea.get(solicitudDeTareaInstance.id)
        solicitudDeTareaInstance.properties = params
                
        if (!solicitudDeTareaInstance.save(flush: true)) {
            render(view: "create", model: [solicitudDeTareaInstance: solicitudDeTareaCreateInstance])
            return
        }
        
	flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), solicitudDeTareaInstance.id])
        session.setAttribute("solicitudDeTareaCreate",null) 
        redirect(action: "show", id: solicitudDeTareaInstance.id)
    }

    def show() {
        cleanSelected()
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("solicitudDeTareaSelected", solicitudDeTareaInstance)    
        [solicitudDeTareaInstance: solicitudDeTareaInstance]
    }

    def edit() {
        cleanSelected()
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        session.setAttribute("solicitudDeTareaSelected", solicitudDeTareaInstance)  
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }
        session.setAttribute("solicitudDeTareaSelected", solicitudDeTareaInstance)
        [solicitudDeTareaInstance: solicitudDeTareaInstance]
    }

    def update() {
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (solicitudDeTareaInstance.version > version) {
                solicitudDeTareaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')] as Object[],
                          "Another user has updated this SolicitudDeTarea while you were editing")
                render(view: "edit", model: [solicitudDeTareaInstance: solicitudDeTareaInstance])
                return
            }
        }

        solicitudDeTareaInstance.properties = params

        if (!solicitudDeTareaInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDeTareaInstance: solicitudDeTareaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), solicitudDeTareaInstance.id])
        redirect(action: "show", id: solicitudDeTareaInstance.id)
    }

    def delete() {
        cleanSelected()
        def solicitudDeTareaInstance = SolicitudDeTarea.get(params.id)
        if (!solicitudDeTareaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
            return
        }

        try {
            solicitudDeTareaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def cleanSelected() 
    {
        [ "tareaSelected",
        "equipoDeTareaSelectedTF",
        "materialDeTareaSelectedTF",
        "permisoAccesoSelectedTF",
        "documentoSelectedTF",
        "poSelectedTF",
         "prestamosSelectedTF", 
        "solicitudPagoCuadrillaSelectedTF",
        "solicitudViaticosSelectedTF",
        "enviarDocumentacionAClienteTF"].each{ name ->
            session.setAttribute(name , null)
        }
    }
}
