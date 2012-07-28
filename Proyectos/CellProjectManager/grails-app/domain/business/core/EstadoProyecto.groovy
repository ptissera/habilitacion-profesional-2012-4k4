package business.core

class EstadoProyecto {
    static hasMany = [ proyectos:Proyecto]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(blank: false, unique: true)
        descripcion(blank: false)
    }
}
