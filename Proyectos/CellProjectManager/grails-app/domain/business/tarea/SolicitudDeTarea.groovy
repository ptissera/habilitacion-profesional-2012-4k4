package business.tarea

import business.solicitud.CobroSolicitudDeTrabajo
import business.solicitud.SolicitudDeViaticos
import business.solicitud.SolicitudPagoCuadrilla
import business.core.PO
import business.core.Proyecto
import business.cuadrillas.Cuadrilla
import business.herramienta.PrestamoHerramienta
import business.documento.Documento

class SolicitudDeTarea {

    
    static hasMany = [tarea: Tarea, pos: PO, prestamos: PrestamoHerramienta, documentos: Documento, 
        viaticos: SolicitudDeViaticos, pagos: SolicitudPagoCuadrilla, cobros: CobroSolicitudDeTrabajo]
    static belongsTo = [proyecto: Proyecto, estado: EstadoSolicitudTarea, cuadrilla: Cuadrilla]
    static fetchMode = [cuadrilla:"eager", proyecto: "eager"] 
    Date fechaAlta
    
    static constraints = {
        fechaAlta(blank:true, validator: {date, obj -> obj.id ? true : date - new Date() >= 0})
        proyecto()
        cuadrilla()
        estado()
        tarea()
        pos()          
        prestamos()
        documentos()
        viaticos()
        pagos()
        cobros()
    }
     
    @Override String toString() {
        return getId() + " - " + getProyecto()
    }
    
    def hasEstadoCreada(){              
        return estado != null ? estado.getNombre() == "Creada" : false
    }
    
     def hasEstadoEnEjecucion(){              
        return estado != null ? estado.getNombre() == "En Ejecucion" : false
    }
    
    def hasDocumentos(){              
        return documentos != null ? documentos.size() > 0 : false
    }
    
    def totalPorCobrar(){              
         
        Float totalACobrar = 0
        if(estado != null ? estado.getNombre() == "Pendiente Cobro" : false){
                        
            Float totalPOs = 0
            Float totalCobros = 0            
            
            pos.each{
                totalPOs += it.monto
            }
            
            if(cobros){
                cobros.each{                    
                    totalCobros += it.monto                    
                }
            }
            
            totalACobrar = totalPOs - totalCobros            
        }
        return totalACobrar
    }
    
    def totalPOs(){              
                    
        Float totalPOs = 0            
        pos.each{
            totalPOs += it.monto
        }
        
        return totalPOs
    }
    
    def totalViaticos(){              
                    
        Float totalViaticos = 0            
        viaticos.each{
            totalViaticos += it.monto
        }
        
        return totalViaticos
    }
}
