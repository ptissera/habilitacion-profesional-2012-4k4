package business.cuadrillas

class HistorialCuadrilla {

    static belongsTo = [cuadrilla:Cuadrilla]
    Date fecha
    String descripcion
    
    static constraints = {
        fecha(blank: false)
        descripcion(blank: false)
        cuadrilla()
    }
}
