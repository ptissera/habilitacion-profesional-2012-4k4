package business.core
import support.secure.Usuario
import business.tarea.SolicitudDeTarea

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
        licitacion(black:false, unique: true)        
        nombre(black:false, unique: true)
        descripcion(black:false)
        fechaCreacion(black:false)
        fechaInicio(black:true, nullable:true)
        fechaFin(black:true, nullable:true)
        estadoProyecto(black:false)
        licitacion()
        usuario(black:true, nullable:true)
        solicitudes()
    }
    
    
       @Override String toString() {
		return getNombre()
	}
}
