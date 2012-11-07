package business.core
import support.secure.Usuario
import business.tarea.*

class Proyecto {
    static hasMany = [ solicitudes:SolicitudDeTarea]
    static belongsTo = [cliente: Cliente, usuario: Usuario, estadoProyecto: EstadoProyecto]
    static fetchMode = [solicitudes:"eager", cliente:"eager"] 
    String licitacion
    String descripcion
    String nombre
    Date fechaCreacion
    Date fechaInicio
    Date fechaFin
    
    
    static constraints = {
        licitacion(blank:false, unique: true)        
        nombre(blank:false, unique: true)
        descripcion(blank:false)
        fechaCreacion(blank:false, validator: {date, obj -> obj.id ? true : date - new Date() >= 0})
        fechaInicio(blank:true,  validator: {date, obj -> obj.id ? true : (date!=null?date - obj.fechaCreacion >= 0:true)})
        fechaFin(blank:true, validator: {date, obj -> obj.id ? true : (date!=null?date - obj.fechaInicio >= 0:true)} )
        estadoProyecto(blank:false, nullable:false)
        licitacion()
        usuario(nullable:true)
        solicitudes()
    }
    
    
    @Override String toString() {
        return getNombre()
    }
}
