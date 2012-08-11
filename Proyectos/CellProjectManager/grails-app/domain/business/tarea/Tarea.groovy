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
        descripcion(blank:false)
        monto(blank:false)
        observaciones(blank:true, nullable:true)
        fechaAlta(blank:false)
        estado()
        tipo()
        tareaSitio()
    }
}
