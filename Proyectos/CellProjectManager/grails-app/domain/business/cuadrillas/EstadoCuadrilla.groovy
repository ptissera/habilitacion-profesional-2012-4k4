package business.cuadrillas

import business.core.Estado

class EstadoCuadrilla extends Estado{

    static hasMany = [cuadrillas: Cuadrilla]
    
    static constraints = {
        cuadrillas()
    }
}
