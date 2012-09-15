import support.secure.*

import business.cuadrillas.TipoDocumentacionIntegranteCuadrilla
import business.cuadrillas.TipoDocumentoIdentificacion
import business.cuadrillas.EstadoCuadrilla
import business.cuadrillas.Cuadrilla
import business.cuadrillas.IntegranteCuadrilla
import business.herramienta.EstadoHerramienta
import business.herramienta.Herramienta
import business.core.EstadoProyecto
import business.core.Provincia
import business.core.Cliente
import business.core.Sitio
import business.tarea.EstadoSolicitudTarea
import business.tarea.EstadoTarea
import business.tarea.Tarea
import business.tarea.TipoTarea
import business.tarea.TipoEquipoDeTarea
import business.tarea.TipoMaterialDeTarea
import business.tarea.UnidadMedida
import business.documento.TipoDocumento
import business.documento.EstadoDocumento

class BootStrap {
    
    def init = { 
        initRolAndUsuarios()
        initTipos()
        initEstados()
        initCuadrilla()
        initCliente()
        initProvincia()
        initSitios()
        initTareas()
        initHerramientas()
        initUnidadesMedida()
    }
    
    def initRolAndUsuarios(){
        
        def rol=Rol.findByNombre('ROLE_ADMIN')
        if(!rol){
            rol = new Rol(nombre: 'ROLE_ADMIN', descripcion: 'Administrador')
            rol.save(flush: true, insert: true)
        }
        
         def rol_adminGral=Rol.findByNombre('ROLE_ADMIN_GENERAL')
        if(!rol_adminGral){
            rol_adminGral = new Rol(nombre: 'ROLE_ADMIN_GENERAL', descripcion: 'Administracion General')
            rol_adminGral.save(flush: true, insert: true)
        }
        
        def mariana=Usuario.findByNombreUsuario('mtissera')
        if(!mariana){
            mariana = new Usuario(nombreUsuario: 'mtissera', nombre: 'Mariana', apellido: 'Tissera', clave: '123',
                enabled: true, email: 'mtissera@coming.com')
            mariana.setRol(rol_adminGral)
            mariana.save(flush: true, insert: true)
        }
        
        def rol_adminProy=Rol.findByNombre('ROLE_ADMIN_PROYECTO')
        if(!rol_adminProy){
            rol_adminProy = new Rol(nombre: 'ROLE_ADMIN_PROYECTO', descripcion: 'Administracion Proyecto')
            rol_adminProy.save(flush: true, insert: true)
        }
        
         def sebastian=Usuario.findByNombreUsuario('shernandez')
        if(!sebastian){
            sebastian = new Usuario(nombreUsuario: 'shernandez', nombre: 'Sebastian', apellido: 'Hernandez', clave: '123',
                enabled: true, email: 'shernandez@coming.com')
            sebastian.setRol(rol_adminProy)
            sebastian.save(flush: true, insert: true)
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
        
        if(!TipoEquipoDeTarea.findByNombre('Medicio')){
            new TipoEquipoDeTarea(nombre: 'Medicio', 
                descripcion: 'Medicio').save(flush: true, insert: true)
        }
        
        if(!TipoEquipoDeTarea.findByNombre('Calibracion')){
            new TipoEquipoDeTarea(nombre: 'Calibracion', 
                descripcion: 'Calibracion').save(flush: true, insert: true)
        }
        
        if(!TipoMaterialDeTarea.findByNombre('Coaxil 5mm')){
            new TipoMaterialDeTarea(nombre: 'Coaxil 5mm', 
                descripcion: 'Coaxil 5mm').save(flush: true, insert: true)
        }
        
        if(!TipoMaterialDeTarea.findByNombre('Coaxil 9mm')){
            new TipoMaterialDeTarea(nombre: 'Coaxil 9mm', 
                descripcion: 'Coaxil 9mm').save(flush: true, insert: true)
        }
        
        if(!TipoMaterialDeTarea.findByNombre('UTP cat5')){
            new TipoMaterialDeTarea(nombre: 'UTP cat5', 
                descripcion: 'UTP cat5').save(flush: true, insert: true)
        }
        
        if(!TipoDocumentoIdentificacion.findByNombre('DU')){
            new TipoDocumentoIdentificacion(nombre: 'DU').save(flush: true, insert: true)
        }
        if(!TipoDocumentoIdentificacion.findByNombre('PASAPORTE')){
            new TipoDocumentoIdentificacion(nombre: 'PASAPORTE').save(flush: true, insert: true)
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
        
        if(!EstadoProyecto.findByNombre('Cancelado')){
            new EstadoProyecto(nombre: 'Cancelado', 
                descripcion: 'Cancelado').save(flush: true, insert: true)
        }
         
        if(!EstadoProyecto.findByNombre('Pendiente Cancelacion')){
            new EstadoProyecto(nombre: 'Pendiente Cancelacion', 
                descripcion: 'Pendiente Cancelacion').save(flush: true, insert: true)
        }
        
        if(!EstadoProyecto.findByNombre('Cerrado')){
            new EstadoProyecto(nombre: 'Cerrado', 
                descripcion: 'Cerrado').save(flush: true, insert: true)
        }
        
       
        if(!EstadoSolicitudTarea .findByNombre('Creada')){
            new EstadoSolicitudTarea(nombre: 'Creada', 
                descripcion: 'Creada').save(flush: true, insert: true)
        }
        
        if(!EstadoSolicitudTarea.findByNombre('Pendiente Conformidad')){
            new EstadoSolicitudTarea(nombre: 'Pendiente Conformidad', 
                descripcion: 'Pendiente Conformidad').save(flush: true, insert: true)
        }
        
        if(!EstadoSolicitudTarea.findByNombre('Cancelada')){
            new EstadoSolicitudTarea(nombre: 'Cancelada', 
                descripcion: 'Cancelada').save(flush: true, insert: true)
        }
        
        if(!EstadoSolicitudTarea.findByNombre('Cerrada')){
            new EstadoSolicitudTarea(nombre: 'Cerrada', 
                descripcion: 'Cerrada').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('Suspendida')){
            new EstadoTarea(nombre: 'Suspendida', 
                descripcion: 'Suspendida').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('En Ejecucion')){
            new EstadoTarea(nombre: 'En Ejecucion', 
                descripcion: 'En Ejecucion').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('Creada')){
            new EstadoTarea(nombre: 'Creada', 
                descripcion: 'Creada').save(flush: true, insert: true)
        }
        
        if(!EstadoTarea.findByNombre('Cancelada')){
            new EstadoTarea(nombre: 'Cancelada', 
                descripcion: 'Cancelada').save(flush: true, insert: true)
        }
        
         if(!EstadoTarea.findByNombre('Resuelta')){
            new EstadoTarea(nombre: 'Resuelta', 
                descripcion: 'Resuelta').save(flush: true, insert: true)
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
    
    def initCuadrilla(){
        def cuadrilla = Cuadrilla.findByNombre('Perez')
        if(!cuadrilla){
            cuadrilla = new Cuadrilla(nombre: 'Perez', descripcion: 'Cuadrilla de Perez',
                propia: true, estadoCuadrilla: EstadoCuadrilla.findByNombre('Sin Asignacion'))
            cuadrilla.save(flush: true, insert: true)
        }
                        
        if(!IntegranteCuadrilla.findByApellido('Perez')){
            new IntegranteCuadrilla(nombre: 'Juan', apellido: 'Perez',documento: '345455534', tipoDocumento: TipoDocumentoIdentificacion.findByNombre('DU'),
                legajo: '234', telefono: '23444434', fechaAlta: new Date(), 
                cuadrilla: Cuadrilla.findByNombre('Perez')).save(flush: true, insert: true)
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
        
    def initSitios(){
        
        if(!Sitio.findByNombre('Golf villa Allende')){
            new Sitio(nombre: 'Golf villa Allende', direccion: 'Villa Allende Golf',
                latitud: '423423434', longitud: '42234333', provincia: Provincia.findByNombre('CORDOBA')).save()
        }
    
        if(!Sitio.findByNombre('Centro villa Allende')){
            new Sitio(nombre: 'Centro villa Allende', direccion: 'Av Los olivares 1772 - villa Allende',
                latitud: '423423434', longitud: '42234333', provincia: Provincia.findByNombre('CORDOBA')).save(flush: true, insert: true)
        }
        
    }
    
    def initTareas(){
        
        if(!TipoTarea.findByNombre('Swap Energía')){
            new TipoTarea(nombre: 'Swap Energía', descripcion: 'Swap Energía').save()
        }
        if(!TipoTarea.findByNombre('Energía Generadores')){
            new TipoTarea(nombre: 'Energía Generadores', descripcion: 'Energía Generadores').save()
        }
        if(!TipoTarea.findByNombre('Expansiones')){
            new TipoTarea(nombre: 'Expansiones', descripcion: 'Expansiones').save()
        }
    
        if(!TipoTarea.findByNombre('Montaje de BTS')){
            new TipoTarea(nombre: 'Montaje de BTS', descripcion: 'Montaje de BTS').save(flush: true, insert: true)
        }
        
    }
    
    def initHerramientas(){
        
        if(!Herramienta.findByNumeroDeSerie('NB23234443')){
            new Herramienta(numeroDeSerie: 'NB23234443', nombre: 'Consola SONY', descripcion: 'consola monitor', 
                estado: EstadoHerramienta.findByNombre('Libre')).save()
        }
    }
        
    def initUnidadesMedida() {
            
        if(!UnidadMedida.findByNombre('Metro')){
            new UnidadMedida(nombre: 'Metro').save(flush: true, insert: true)
        }
        
        if(!UnidadMedida.findByNombre('Unidad')){
            new UnidadMedida(nombre: 'Unidad').save(flush: true, insert: true)
        }
        
        if(!UnidadMedida.findByNombre('CM')){
            new UnidadMedida(nombre: 'CM').save(flush: true, insert: true)
        }
        
        if(!UnidadMedida.findByNombre('Gr')){
            new UnidadMedida(nombre: 'Gr').save(flush: true, insert: true)
        }
        
    }
    
    def destroy = {
    }
}
