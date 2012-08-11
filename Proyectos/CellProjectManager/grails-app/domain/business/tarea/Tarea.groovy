package business.tarea
import business.documento.*
import business.herramienta.*
class Tarea {

    static belongsTo = [ estado: EstadoTarea, tipo: TipoTarea, tareaSitio: TareasPorSitio]
    static hasMany = [ documentos:Documento, materiales: MaterialDeTarea, equipos: EquipoDeTarea]   
    
    String descripcion
    float monto
    String observaciones
    Date fechaAlta
    
    static constraints = {
        descripcion(black:false)
        monto(black:false)
        observaciones(black:true, nullable:true)
        fechaAlta(black:false)
        estado()
        tipo()
        tareaSitio()
    }
}
