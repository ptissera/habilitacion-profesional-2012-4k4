package business.cuadrillas

class Empleado {
    List documentacion = new ArrayList()
    static hasMany = [ documentacion:DocumentacionEmpleado ]    
    String du
    String nombre
    String apellido
    String legajo
    String telefono
    Date fechaAlta
    Date fechaBaja
    static constraints = {
        du(black:false, unique:true)
        nombre(black:false)
        apellido(black:false)
        legajo(black:false, unique:true)
        telefono(black:false)        
    }
    def getExpandableDocumentacionList() {
        return LazyList.decorate(documentacion,FactoryUtils.instantiateFactory(DocumentacionEmpleado.class))
    }
    
     def String toString() {
        return apellido +", "+ nombre
    }
}
