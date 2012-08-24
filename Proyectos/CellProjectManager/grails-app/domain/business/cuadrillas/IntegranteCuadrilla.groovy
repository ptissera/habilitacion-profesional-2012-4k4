package business.cuadrillas

class IntegranteCuadrilla {
    
    static hasMany = [ documentacion:DocumentacionIntegranteCuadrilla ]
    static belongsTo = [cuadrilla:Cuadrilla]
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
        fechaAlta(black: false)
        fechaBaja(black:true, nullable:true)
    }
    
     def checkDocumentacion(){
        int codigo = 4
        getDocumentacion().each{ it ->
            if(it.checkVencimiento()<codigo){
                codigo=it.checkVencimiento();
            }
        }        
        return codigo
    }
    
    def estadoDocumentacionIcon(){
        switch (checkDocumentacion()){
            case 1: return "/images/estados/rojo_1.png"
            case 2: return "/images/estados/amarillo_1.png"
            case 3: return "/images/estados/verde_1.png"
            default: return ""
        }
    }
   
    
    @Override String toString() {
		return getApellido() + ", " + getNombre()
	}
}
