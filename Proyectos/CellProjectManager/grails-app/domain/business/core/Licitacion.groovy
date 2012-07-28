package business.core

class Licitacion {
    static belongsTo = [cliente: Cliente]
    static hasMany = [ proyectos:Proyecto]
    String nombre
    String descripcion
    
    static constraints = {
        nombre(black:false, unique: true)
        descripcion(black:false)
        cliente()
        proyectos()
    }
}
