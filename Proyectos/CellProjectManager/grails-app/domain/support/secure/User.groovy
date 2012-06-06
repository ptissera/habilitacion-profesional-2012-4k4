package support.secure

class User {
    
   static belongsTo = [authorities: Role]
   String userName
   String nombre
   String apellido
   String password
   boolean enabled
   String email


   static constraints = {
      userName(blank: false, unique: true)
      password(blank: false, password: true)
      nombre(blank: false)
      apellido(blank: false)        
      email(email:true, blank: false)
      authorities()
      enabled()
   }   
   
}
