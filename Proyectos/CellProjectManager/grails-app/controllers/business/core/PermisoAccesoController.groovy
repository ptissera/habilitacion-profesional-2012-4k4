package business.core

import org.springframework.dao.DataIntegrityViolationException

class PermisoAccesoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [permisoAccesoInstanceList: PermisoAcceso.list(params), permisoAccesoInstanceTotal: PermisoAcceso.count()]
    }

    def create() {
        session.setAttribute("permisoAccesoSelectedTF",true)
        [permisoAccesoInstance: new PermisoAcceso(params)]
    }

    def save() {
        def tareaSelected = session.getAttribute("tareaSelected")
        def permisoAccesoInstance = new PermisoAcceso(tareasPorSitio: tareaSelected)
        permisoAccesoInstance.properties = params
        def f = request.getFile('archivo')
        if(!f.empty) {
            permisoAccesoInstance.nombreArchivo = f.getOriginalFilename()
            permisoAccesoInstance.archivo = f.inputStream.bytes
        }    else {
            flash.message = 'file cannot be empty'          
        }
        if (!permisoAccesoInstance.save(flush: true)) {
            render(view: "create", model: [permisoAccesoInstance: permisoAccesoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), permisoAccesoInstance.id])
        redirect(controller: "tareasPorSitio", action: "edit", id: tareaSelected.id)
    }

    def show() {
        session.setAttribute("permisoAccesoSelectedTF",true)
        def permisoAccesoInstance = PermisoAcceso.get(params.id)
        if (!permisoAccesoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), params.id])
            redirect(action: "list")
            return
        }

        [permisoAccesoInstance: permisoAccesoInstance]
    }

    def edit() {
        session.setAttribute("permisoAccesoSelectedTF",true)
        def permisoAccesoInstance = PermisoAcceso.get(params.id)
        if (!permisoAccesoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), params.id])
            redirect(action: "list")
            return
        }

        [permisoAccesoInstance: permisoAccesoInstance]
    }

    def update() {
        def permisoAccesoInstance = PermisoAcceso.get(params.id)
        if (!permisoAccesoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (permisoAccesoInstance.version > version) {
                permisoAccesoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'permisoAcceso.label', default: 'PermisoAcceso')] as Object[],
                          "Another user has updated this PermisoAcceso while you were editing")
                render(view: "edit", model: [permisoAccesoInstance: permisoAccesoInstance])
                return
            }
        }

        permisoAccesoInstance.properties = params

        if (!permisoAccesoInstance.save(flush: true)) {
            render(view: "edit", model: [permisoAccesoInstance: permisoAccesoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), permisoAccesoInstance.id])
        def tareaSelected = session.getAttribute("tareaSelected")
        redirect(controller: "tareasPorSitio", action: "edit", id: tareaSelected.id)
    }

    def delete() {
        def permisoAccesoInstance = PermisoAcceso.get(params.id)
        if (!permisoAccesoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), params.id])
            redirect(action: "list")
            return
        }

        try {
            permisoAccesoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), params.id])
            def tareaSelected = session.getAttribute("tareaSelected")
            redirect(controller: "tareasPorSitio", action: "edit", id: tareaSelected.id)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
