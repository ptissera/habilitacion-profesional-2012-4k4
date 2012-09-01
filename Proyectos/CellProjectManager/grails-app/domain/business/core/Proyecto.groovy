package business.core
import support.secure.Usuario
import business.tarea.*

class Proyecto {
    
    static hasMany = [ solicitudes:SolicitudDeTarea]
    static belongsTo = [cliente: Cliente, usuario: Usuario, estadoProyecto: EstadoProyecto]
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
        fechaCreacion(blank:false)
        fechaInicio(blank:true, nullable:true)
        fechaFin(blank:true, nullable:true)
        estadoProyecto(blank:false, nullable:false)
        licitacion()
        usuario(nullable:true)
        solicitudes()
    }
    
    
       @Override String toString() {
		return getNombre()
	}
}
