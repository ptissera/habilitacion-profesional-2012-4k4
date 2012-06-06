package business.cuadrillas

class Cuadrilla {
    List operarios = new ArrayList()
    static hasMany = [ operarios:Empleado ]
    String nombre
    String estado
    
    static constraints = {
        nombre(black:false, unique: true)
        estado()
    }
    
     def getExpandableOperariosList() {
        return LazyList.decorate(operarios,FactoryUtils.instantiateFactory(Empleado.class))
    }
}
