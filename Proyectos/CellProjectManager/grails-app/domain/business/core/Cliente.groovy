package business.core

class Cliente {
    
    static hasMany = [ licitaciones:Proyecto ]
    String razonSocial
    String cuit
    String direccion
    String email
    String telefono
    String contactoNombre
    String contactoTelefono
    String contactoEmail
    
    static constraints = {
        razonSocial(size:3..50, unique: true)
        cuit(black:false, unique: true)
        telefono(black:false)
        direccion(black:false)
        email(email:true, blank: false)
        contactoNombre(black:false)
        contactoTelefono(black:false)
        contactoEmail(email:true, black:false)
        licitaciones()
    }
    
       @Override String toString() {
		return getRazonSocial()
	}
}
