package support.secure

class Role {
    
    static hasMany = [people: User]

   /** description */
   String description
   /** ROLE String */
   String authority

   static constraints = {
      authority(blank: false, unique: true)
      description()
   }
   
    @Override String toString() {
		return getDescription()
	}
}
