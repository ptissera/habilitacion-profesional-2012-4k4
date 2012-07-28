package business.core

class Cliente {
    
    static hasMany = [ licitaciones:Licitacion ]
    String razonSocial
    String cuit
    String direccion
    String telefono
    String contactoNombre
    String contactoTelefono
    
    static constraints = {
        razonSocial(black:false, unique: true)
        cuit(black:false, unique: true)
        telefono(black:false)
        direccion(black:false)
        email(email:true,black:false)
        contactoNombre(black:false)
        contactoTelefono(black:false)
        contactoEmail(email:true ,black:false)
        licitaciones()
    }
}
