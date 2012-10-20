package business.cuadrillas
import support.secure.Usuario

class IntegranteCuadrilla {
    
    static hasMany = [ documentacion:DocumentacionIntegranteCuadrilla ]
    static belongsTo = [cuadrilla:Cuadrilla, tipoDocumento: TipoDocumentoIdentificacion, usuario: Usuario]
    String documento
    String nombre
    String apellido
    String legajo
    String telefono
    Date fechaAlta
    Date fechaBaja
    Boolean esJefeCuadrilla
    
    static constraints = {
        tipoDocumento()
        documento(blank:false, unique:true)
        nombre(blank:false)
        apellido(blank:false)
        legajo(blank:false, unique:true)
        telefono(blank:false)        
        fechaAlta(blank: false, validator: {date, obj -> obj.id ? true : date - new Date() >= 0})
        fechaBaja(blank:true, nullable:true, validator: {date, obj -> date!=null ? date - obj.fechaAlta >= 0 : true})
        esJefeCuadrilla()
        // falta verificar que si es el jefe de cuadrilla, debe asignarse un usuario del sistema
        usuario()
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
