package business.cuadrillas

class Cuadrilla {
    
    static hasMany = [ operarios:Empleado ]
    String nombre
    String estado
    
    static constraints = {
        nombre(black:false, unique: true)
        estado()
        operarios()
    }
    
}
