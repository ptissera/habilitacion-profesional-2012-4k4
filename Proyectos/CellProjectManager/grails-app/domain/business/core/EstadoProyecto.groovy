package business.core

class EstadoProyecto extends Estado {
    static hasMany = [ proyectos:Proyecto]
 
}
