import support.secure.*

import business.cuadrillas.TipoDocumentacionIntegranteCuadrilla
import business.cuadrillas.EstadoCuadrilla
import business.herramienta.EstadoHerramienta
import business.core.EstadoProyecto
import business.core.Provincia
import business.core.Cliente
import business.tarea.EstadoSolicitudTarea
import business.tarea.EstadoTarea
import business.tarea.TipoEquipoDeTarea
import business.tarea.TipoMaterialDeTarea
import business.documento.TipoDocumento
import business.documento.EstadoDocumento

class BootStrap {
    
    def init = { 
        initRolAndUsuarios()
        initTipos()
        initEstados()
        initCliente()
        initProvincia()
    }
    
    def initRolAndUsuarios(){
        
        def rol=Rol.findByNombre('ROLE_ADMIN')
        if(!rol){
            rol = new Rol(nombre: 'ROLE_ADMIN', descripcion: 'Administrador')
            rol.save(flush: true, insert: true)
        }
        
        def pablo=Usuario.findByNombreUsuario('ptissera')
        if(!pablo){
            pablo = new Usuario(nombreUsuario: 'ptissera', nombre: 'Pablo', apellido: 'Tissera', clave: '123',
                enabled: true, email: 'ptissera@coming.com')
            pablo.setRol(rol)
            pablo.save(flush: true, insert: true)
        }
        
        def carlos=Usuario.findByNombreUsuario('ctrepat')
        if(!carlos){
            carlos = new Usuario(nombreUsuario: 'ctrepat', nombre: 'Carlos', apellido: 'Trepat', clave: '123',
                enabled: true, email: 'ctrepat@coming.com')
            carlos.setRol(rol)                
            carlos.save(flush: true, insert: true)
        }        
        
        def mariano=Usuario.findByNombreUsuario('mgava')
        if(!mariano){
            mariano = new Usuario(nombreUsuario: 'mgava', nombre: 'Mariano', apellido: 'Gava', clave: '123',
                enabled: true, email: 'mgava@coming.com')
            mariano.setRol(rol)                
            mariano.save(flush: true, insert: true)
        }   
        
        def demian=Usuario.findByNombreUsuario('dodasso')
        if(!demian){
            demian = new Usuario(nombreUsuario: 'dodasso', nombre: 'Demian', apellido: 'Odasso', clave: '123',
                enabled: true, email: 'dodasso@coming.com')
            demian.setRol(rol)                
            demian.save(flush: true, insert: true)
        }   
        
        def javier=Usuario.findByNombreUsuario('jbrizuela')
        if(!javier){
            javier = new Usuario(nombreUsuario: 'jbrizuela', nombre: 'Javier', apellido: 'Brizuela', clave: '123',
                enabled: true, email: 'jbrizuela@coming.com')
            javier.setRol(rol)                
            javier.save(flush: true, insert: true)
        }   
        
        def mguillen=Usuario.findByNombreUsuario('mguillen')
        if(!mguillen){
            mguillen = new Usuario(nombreUsuario: 'mguillen', nombre: 'Mariano', apellido: 'Guillen', clave: '123',
                enabled: true, email: 'mguillen@coming.com')
            mguillen.setRol(rol)                
            mguillen.save(flush: true, insert: true)
        }   
    }
    
    def initTipos(){
        
        if(!TipoDocumentacionIntegranteCuadrilla.findByNombre('Seguro de vida')){
            new TipoDocumentacionIntegranteCuadrilla(nombre: 'Seguro de vida', 
                descripcion: 'Seguro de vida obligatorio ley 3323', 
                diaAntesVencimiento: 15).save(flush: true, insert: true)
        }        
        
        if(!TipoDocumentacionIntegranteCuadrilla.findByNombre('Apto medico')){
            new TipoDocumentacionIntegranteCuadrilla(nombre: 'Apto medico', 
                descripcion: 'Certificado Medico apto para trabajos en altura', 
                diaAntesVencimiento: 7).save(flush: true, insert: true)
        }
        
        if(!TipoDocumento.findByNombre('Protocolos')){
            new TipoDocumento(nombre: 'Protocolos', 
                descripcion: 'Protocolos contemplados en el trabajo').save(flush: true, insert: true)
        }
        
        if(!TipoDocumento.findByNombre('CAO')){
            new TipoDocumento(nombre: 'CAO', 
                descripcion: 'Conformidad De Obra').save(flush: true, insert: true)
        }
        
    }
    
    def initEstados(){        
        
        if(!EstadoCuadrilla.findByNombre('Sin Asignacion')){
            new EstadoCuadrilla(nombre: 'Sin Asignacion', 
                descripcion: 'Cuadrilla no asignada').save(flush: true, insert: true)
        }        
        
        if(!EstadoCuadrilla.findByNombre('Asignada')){
            new EstadoCuadrilla(nombre: 'Asignada', 
                descripcion: 'Cuadrilla asignada').save(flush: true, insert: true)
        }
        
        if(!EstadoHerramienta.findByNombre('Prestada')){
            new EstadoHerramienta(nombre: 'Prestada', 
                descripcion: 'Herramienta presatada alguna Cuadrilla').save(flush: true, insert: true)
        }        
        
        if(!EstadoHerramienta.findByNombre('Libre')){
            new EstadoHerramienta(nombre: 'Libre', 
                descripcion: 'Disponible para prestamo').save(flush: true, insert: true)
        }        
        
        if(!EstadoHerramienta.findByNombre('No disponible')){
            new EstadoHerramienta(nombre: 'No disponible', 
                descripcion: 'Herramienta averiada o en mantenimiento').save(flush: true, insert: true)
        }
        
        
        if(!EstadoProyecto.findByNombre('Creado')){
           new EstadoProyecto(nombre: 'Creado', 
                descripcion: 'Creado').save(flush: true, insert: true)
        }
        
        if(!EstadoProyecto.findByNombre('Activo')){
           new EstadoProyecto(nombre: 'Activo', 
                descripcion: 'Activo').save(flush: true, insert: true)
        }
        
        if(!EstadoProyecto.findByNombre('Resuelto')){
           new EstadoProyecto(nombre: 'Resuelto', 
                descripcion: 'Resuelto').save(flush: true, insert: true)
        }
        
        if(!EstadoProyecto.findByNombre('Cerrado')){
           new EstadoProyecto(nombre: 'Cerrado', 
                descripcion: 'Cerrado').save(flush: true, insert: true)
        }
        
        if(!EstadoProyecto.findByNombre('Cancelado')){
           new EstadoProyecto(nombre: 'Cancelado', 
                descripcion: 'Cancelado').save(flush: true, insert: true)
        }
        
        if(!EstadoSolicitudTarea .findByNombre('Pendiente')){
           new EstadoSolicitudTarea(nombre: 'Pendiente', 
                descripcion: 'Pendiente').save(flush: true, insert: true)
        }
        
        if(!EstadoSolicitudTarea.findByNombre('En Ejecucion')){
           new EstadoSolicitudTarea(nombre: 'En Ejecucion', 
                descripcion: 'En Ejecucion').save(flush: true, insert: true)
        }
        
        if(!EstadoSolicitudTarea.findByNombre('Resuelto')){
           new EstadoSolicitudTarea(nombre: 'Resuelto', 
                descripcion: 'Resuelto').save(flush: true, insert: true)
        }
        
        if(!EstadoSolicitudTarea.findByNombre('Cerrado')){
           new EstadoSolicitudTarea(nombre: 'Cerrado', 
                descripcion: 'Cerrado').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('Pendiente')){
           new EstadoTarea(nombre: 'Pendiente', 
                descripcion: 'Pendiente').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('En Ejecucion')){
           new EstadoTarea(nombre: 'En Ejecucion', 
                descripcion: 'En Ejecucion').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('Resuelto')){
           new EstadoTarea(nombre: 'Resuelto', 
                descripcion: 'Resuelto').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('Cerrado')){
           new EstadoTarea(nombre: 'Cerrado', 
                descripcion: 'Cerrado').save(flush: true, insert: true)
        }
        
        if(!EstadoDocumento.findByNombre('Creado')){
           new EstadoDocumento(nombre: 'Creado', 
                descripcion: 'Creado').save(flush: true, insert: true)
        }        
        
        if(!EstadoDocumento.findByNombre('Enviado')){
           new EstadoDocumento(nombre: 'Enviado', 
                descripcion: 'Enviado').save(flush: true, insert: true)
        }        
        
        if(!EstadoDocumento.findByNombre('Aceptado')){
           new EstadoDocumento(nombre: 'Aceptado', 
                descripcion: 'Aceptado').save(flush: true, insert: true)
        }                
        
        if(!EstadoDocumento.findByNombre('Rechazado')){
           new EstadoDocumento(nombre: 'Rechazado', 
                descripcion: 'Rechazado').save(flush: true, insert: true)
        }        
        
        if(!EstadoDocumento.findByNombre('Incompleto')){
           new EstadoDocumento(nombre: 'Incompleto', 
                descripcion: 'Incompleto').save(flush: true, insert: true)
        }        
    }
    
    def initCliente(){
        
        if(!Cliente.findByRazonSocial('Claro SA')){
            new Cliente(razonSocial: 'Claro SA', 
                telefono: '334443444', email: 'claro@claro.com', direccion: 'Av Sabatini 650, Cordoba Capital',
            cuit: '34-2344432344-3', contactoNombre: 'Luis Chamorro', contactoTelefono: '1232133323', 
            contactoEmail: 'luis.chamorro@claro.com').save(flush: true, insert: true)
        }
        
        
        if(!Cliente.findByRazonSocial('Nokia SA')){
            new Cliente(razonSocial: 'Nokia SA', 
                telefono: '73645534', email: 'nokia@nokia.com', direccion: '27 de Abril 763, Cordoba Capital',
            cuit: '20-223444344-3', contactoNombre: 'Juan Lemperd', contactoTelefono: '144255553', 
            contactoEmail: 'juan.lemperd@nokia.com').save(flush: true, insert: true)
        }
    }
    
     def initProvincia(){
        
        if(!Provincia.findByNombre('CORDOBA')){
            new Provincia(nombre: 'CORDOBA').save()
            new Provincia(nombre: 'BUENOS AIRES').save()
            new Provincia(nombre: 'SALTA').save()
            new Provincia(nombre: 'JUJUY').save()
            new Provincia(nombre: 'TUCUMAN').save()
            new Provincia(nombre: 'CHACO').save()
            new Provincia(nombre: 'FORMOSA').save()
            new Provincia(nombre: 'ENTRE RIOS').save()
            new Provincia(nombre: 'SANTA FE').save()
            new Provincia(nombre: 'CORRIENTES').save()
            new Provincia(nombre: 'MISIONES').save()
            new Provincia(nombre: 'SANTIAGO DEL ESTERO').save()
            new Provincia(nombre: 'LA RIOJA').save()
            new Provincia(nombre: 'SAN JUAN').save()
            new Provincia(nombre: 'SAN LUIS').save()
            new Provincia(nombre: 'MENDOZA').save()
            new Provincia(nombre: 'NEUQUEN').save()
            new Provincia(nombre: 'LA PAMPA').save()
            new Provincia(nombre: 'RIO NEGRO').save()
            new Provincia(nombre: 'CHUBUT').save()
            new Provincia(nombre: 'SANTA CRUZ').save()
            new Provincia(nombre: 'TIERRA DEL FUEGO').save()
            new Provincia(nombre: 'CATAMARCA').save(flush: true, insert: true)
        }
        
    }
    
    def destroy = {
    }
}
