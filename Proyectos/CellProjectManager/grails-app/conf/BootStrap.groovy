import support.secure.*

class BootStrap {
    
    def init = { 
        def role=Role.findByAuthority('ROLE_ADMIN')
        if(!role){
            role = new Role(authority: 'ROLE_ADMIN', description: 'Administrador')
            role.save(flush: true, insert: true)}
        
        def pablo=User.findByUserName('ptissera')
        if(!pablo){
            pablo = new User(userName: 'ptissera', nombre: 'Pablo', apellido: 'Tissera', password: '123',
                enabled: true, email: 'ptissera@coming.com')
            pablo.setAuthorities(role)
            pablo.save(flush: true, insert: true)
        }
        
        def carlos=User.findByUserName('ctrepat')
        if(!carlos){
            carlos = new User(userName: 'ctrepat', nombre: 'Carlos', apellido: 'Trepat', password: '123',
                enabled: true, email: 'ctrepat@coming.com')
            carlos.setAuthorities(role)                
            carlos.save(flush: true, insert: true)
        }        
        
        def mariano=User.findByUserName('mgava')
        if(!mariano){
            mariano = new User(userName: 'mgava', nombre: 'Mariano', apellido: 'Gava', password: '123',
                enabled: true, email: 'mgava@coming.com')
            mariano.setAuthorities(role)                
            mariano.save(flush: true, insert: true)
        }   
        
        def demian=User.findByUserName('dodasso')
        if(!demian){
            demian = new User(userName: 'dodasso', nombre: 'Demian', apellido: 'Odasso', password: '123',
                enabled: true, email: 'dodasso@coming.com')
            demian.setAuthorities(role)                
            demian.save(flush: true, insert: true)
        }   
        
        def javier=User.findByUserName('jbrizuela')
        if(!javier){
            javier = new User(userName: 'jbrizuela', nombre: 'Javier', apellido: 'Brizuela', password: '123',
                enabled: true, email: 'jbrizuela@coming.com')
            javier.setAuthorities(role)                
            javier.save(flush: true, insert: true)
        }   
    }
    
    def destroy = {
    }
}
