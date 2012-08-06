import support.secure.*
import business.cuadrillas.*
import business.core.*

class BootStrap {
    
    def init = { 
        initRolAndUsuarios()
        initTipos()
        initCliente()
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
        def tipoDocIntCuadrilla1=TipoDocumentacionIntegranteCuadrilla.findByNombre('Seguro de vida')
        if(!tipoDocIntCuadrilla1){
            tipoDocIntCuadrilla1 = new TipoDocumentacionIntegranteCuadrilla(nombre: 'Seguro de vida', 
                descripcion: 'Seguro de vida obligatorio ley 3323', diaAntesVencimiento: 15)
            tipoDocIntCuadrilla1.save(flush: true, insert: true)
        }
        
        def tipoDocIntCuadrilla2=TipoDocumentacionIntegranteCuadrilla.findByNombre('Apto medico')
        if(!tipoDocIntCuadrilla2){
            tipoDocIntCuadrilla2 = new TipoDocumentacionIntegranteCuadrilla(nombre: 'Apto medico', 
                descripcion: 'Certificado Medico apto para trabajos en altura', diaAntesVencimiento: 7)
            tipoDocIntCuadrilla2.save(flush: true, insert: true)
        }
        
    }
    
    def initEstados(){
        def estadoCuadrilla1=EstadoCuadrilla.findByNombre('Sin Asignacion')
        if(!estadoCuadrilla1){
            estadoCuadrilla1 = new EstadoCuadrilla(nombre: 'Sin Asignacion', 
                descripcion: 'Cuadrilla no asignada')
            estadoCuadrilla1.save(flush: true, insert: true)
        }
        
        def estadoCuadrilla2=EstadoCuadrilla.findByNombre('Asignada')
        if(!estadoCuadrilla2){
            estadoCuadrilla2 = new EstadoCuadrilla(nombre: 'Asignada', 
                descripcion: 'Cuadrilla asignada')
            estadoCuadrilla2.save(flush: true, insert: true)
        }
        
    }
    
    def initCliente(){
        def cliente1=Cliente.findByRazonSocial('Claro SA')
        if(!cliente1){
            cliente1 = new Cliente(razonSocial: 'Claro SA', 
                telefono: '334443444', email: 'claro@claro.com', direccion: 'Av Sabatini 650, Cordoba Capital',
            cuit: '34-2344432344-3', nombreContacto: 'Luis Chamorro', telefonoContacto: '1232133323', 
            emailContacto: 'luis.chamorro@claro.com')
            cliente1.save(flush: true, insert: true)
        }
    }
    
    def destroy = {
    }
}
