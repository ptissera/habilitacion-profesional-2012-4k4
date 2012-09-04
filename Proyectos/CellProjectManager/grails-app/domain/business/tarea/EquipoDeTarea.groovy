package business.tarea

class EquipoDeTarea {

    static belongsTo = [tipo: TipoEquipoDeTarea, tarea: Tarea]
    String numeroSerie
    String descripcion
    
    static constraints = {
        numeroSerie(blank: false, unique: true)
        descripcion(blank: false)
        tipo()
        tarea()
    }
    
     @Override String toString() {
	return getDescripcion()
    }
}
