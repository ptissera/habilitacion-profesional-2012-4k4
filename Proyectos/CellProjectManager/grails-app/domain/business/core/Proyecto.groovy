package business.core
import support.secure.User

class Proyecto {
    static hasMany = [ solicitudes:SolicitudDeTarea]
    static belongsTo = [licitacion: Licitacion, usuario: User, estadoProyecto: EstadoProyecto]
    String nombre
    Date fechaCreacion
    Date fechaInicio
    Date fechaFin
    
    
    static constraints = {
        nombre(black:false, unique: true)
        fechaCreacion(black:false)
        fechaInicio(black:true, nullable:true)
        fechaFin(black:true, nullable:true)
        estadoProyecto(black:false)
        licitacion()
        usuario(black:true, nullable:true)
        solicitudes()
    }
}
