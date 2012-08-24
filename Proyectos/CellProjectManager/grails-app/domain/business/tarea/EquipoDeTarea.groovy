package business.tarea

class EquipoDeTarea {

    static belongsTo = [tipo: TipoEquipoDeTarea, tareasPorSitio: TareasPorSitio]
    String numeroSerie
    String descripcion
    
    static constraints = {
        numeroSerie(blank: false, unique: true)
        descripcion(blank: false)
        tipo()
        tareasPorSitio()
    }
    
     @Override String toString() {
	return getTipo() +" - "+ getDescripcion()
    }
}
