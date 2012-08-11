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
        cuit(blank:false, unique: true)
        telefono(blank:false)
        direccion(blank:false)
        email(email:true, blank: false)
        contactoNombre(blank:false)
        contactoTelefono(blank:false)
        contactoEmail(email:true, blank:false)
        licitaciones()
    }
    
       @Override String toString() {
		return getRazonSocial()
	}
}
