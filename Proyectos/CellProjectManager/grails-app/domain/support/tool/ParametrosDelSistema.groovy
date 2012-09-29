package support.tool

class ParametrosDelSistema {

    String nombre
    String descripcion
    String valor
    
    static constraints = {
        nombre(blank:false)
        descripcion(blank:false)
        valor(blank:false)
    }    
    
    @Override String toString() {
        return getNombre()
    }
       
}
