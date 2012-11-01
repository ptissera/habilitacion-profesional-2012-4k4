package business.core
import business.cuadrillas.DocumentacionIntegranteCuadrilla
import business.solicitud.*
import business.tarea.SolicitudDeTarea
import business.tarea.EstadoSolicitudTarea

class HomeAdminGeneralTagLib {
    
    def homeAdminGeneral = {        
        def solicitudDeViaticosInstanceList = EstadoSolicitudDeViaticos.findByNombre('Pendiente').solicitudDeViaticos        
        def solicitudPagoCuadrillaInstanceList = EstadoSolicitudPagoCuadrilla.findByNombre('Pendiente').solicitudPagoCuadrilla
        def documentacionIntegranteCuadrillaInstanceList = DocumentacionIntegranteCuadrilla.list()
        def solicitudDeTareaInstanceList = EstadoSolicitudTarea.findByNombre('Pendiente Cobro').solicitudes
        
        def documentacionIntegranteCuadrillaAuxList = []
        documentacionIntegranteCuadrillaInstanceList.each{
            if(it.checkVencimiento()<3){
                documentacionIntegranteCuadrillaAuxList << it
            }
        }
                
        def masLargo = false
        if(solicitudDeViaticosInstanceList || solicitudPagoCuadrillaInstanceList || documentacionIntegranteCuadrillaAuxList || documentacionIntegranteCuadrillaInstanceList ){
            out << "<table style='border: 0px; background-color: white;' class='none;'>"
            if(solicitudDeViaticosInstanceList || solicitudPagoCuadrillaInstanceList){
                if(solicitudDeViaticosInstanceList && solicitudPagoCuadrillaInstanceList){
                    out << "<tr style='background-color: white;'><td style='background-color: white;'>"
                    viaticos(solicitudDeViaticosInstanceList)
                    out << "</td><td>"
                    pagos(solicitudPagoCuadrillaInstanceList)
                    out << "</td></tr>"
                } else if(solicitudDeViaticosInstanceList){
                    out << "<tr style='background-color: white;'><td style='background-color: white;'>"
                    viaticos(solicitudDeViaticosInstanceList)
                    out << "</td></tr>"
                } else {
                    out << "<tr style='background-color: white;'><td style='background-color: white;'>"
                    pagos(solicitudPagoCuadrillaInstanceList)
                    out << "</td></tr>"
                }
            }else{
                masLargo = true   
            }
            
            if(documentacionIntegranteCuadrillaAuxList){
                
                if(solicitudDeViaticosInstanceList && solicitudPagoCuadrillaInstanceList){
                    out << "<tr style='background-color: white;' ><td colspan='2' style='background-color: white;'>"
                } else {
                    out << "<tr style='background-color: white;'><td style='background-color: white;'>"
                }
                vencimientos(documentacionIntegranteCuadrillaAuxList)
                out << "</td></tr>"
            }else{
                masLargo = true   
            }
            
            
            if(solicitudDeTareaInstanceList.size() > 0){
                
                if(solicitudDeViaticosInstanceList && solicitudPagoCuadrillaInstanceList){
                    out << "<tr style='background-color: white;'><td colspan='2' style='background-color: white;'>"
                } else {
                    out << "<tr style='background-color: white;'><td style='background-color: white;'>"
                }
                cobros(solicitudDeTareaInstanceList)
                out << "</td></tr>"
            }else{
                masLargo = true   
            }
            
            out <<"</table>"
        } else {
            out << "<div style='height: 300px;'></div>"
            masLargo = true
        }
        if(masLargo == true){
            out << "<div style='height: 200px;'></div>"
        }
    }
  
    def vencimientos(documentacionIntegranteCuadrillaInstanceList) {
        out << "<table cellspacing='0' cellpadding='0' style='padding: 0px; spacing: 0px; margin: 10px 0px 0px 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; text-align: center; padding: 0px; spacing: 10px; margin: 0px; border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;background-color: #CFDF78; font-weight: bold;'>"
        out << "Vencimientos"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <thead>"
        out << "       <tr>"
        out << "           <th> Desde </th>"
        out << "           <th> Hasta </th>"
        out << "           <th> Descripcion </th>"
        out << "           <th> Tipo </th>"
        out << "           <th> Integrante </th>"
        out << "           <th> Estado </th>"
        out << "       </tr>"
        out << "    </thead>"
        out << "</table>"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='overflow: auto;height: 150px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <tbody>"
        
        documentacionIntegranteCuadrillaInstanceList.eachWithIndex() { documentacionIntegranteCuadrillaInstance, i ->
            
            out << """<tr class="${(i % 2) == 0 ? 'even' : 'odd'}"  style='padding: 0px; spacing: 0px; margin: 0px;'>"""
            out <<   "<td>" + """${link(controller:"documentacionIntegranteCuadrilla",action:"editFromHome",id: documentacionIntegranteCuadrillaInstance.id){documentacionIntegranteCuadrillaInstance.vigenciaDesde.format('dd/MM/yyyy')}}""" + "</td>"                
            out <<   "<td>${documentacionIntegranteCuadrillaInstance.vigenciaHasta.format('dd/MM/yyyy')}</td>"
            out <<   "<td>${documentacionIntegranteCuadrillaInstance.descripcion}</td>"
            out <<   "<td>${documentacionIntegranteCuadrillaInstance.tipoDocumento}</td>"
            out <<   "<td>${documentacionIntegranteCuadrillaInstance.integrante}</td>"
            out <<   "<td><img src=${createLinkTo(dir: "", file: documentacionIntegranteCuadrillaInstance.estadoDocumentoIcon())} width='20px' /></td>"					
            out << "</tr>"
        }
	out << "   </tbody>"
        out << "</table>"	
        out << "</div>"
        out << "</td></tr></table>"
    }
    
    def viaticos(solicitudDeViaticosInstanceList) {
        out << "<table cellspacing='0' cellpadding='0' style='padding: 0px; spacing: 0px; margin: 10px 0px 0px 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; text-align: center; padding: 0px; spacing: 10px; margin: 0px; border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;background-color: #CFDF78; font-weight: bold;'>"
        out << "Solicitudes de Viaticos"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <thead>"
        out << "       <tr>"
        out << "           <th width='40%'> Solicitud De tarea </th>"
        out << "           <th width='20%'> Cuadrilla </th>"
        out << "           <th width='20%'> Creacion </th>"
        out << "       </tr>"
        out << "    </thead>"
        out << "</table>"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='overflow: auto;height: 150px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <tbody>"
        
        solicitudDeViaticosInstanceList.eachWithIndex() { solicitudDeViaticosInstance, i ->
            out << """<tr class="${(i % 2) == 0 ? 'even' : 'odd'}"  style='padding: 0px; spacing: 0px; margin: 0px;'>"""
            out <<   "<td width='40%'>" + """${link(controller:"solicitudDeViaticos",action:"show",id: solicitudDeViaticosInstance.id){solicitudDeViaticosInstance.solicitud}}""" + "</td>"
            out <<   "<td width='20%'>${solicitudDeViaticosInstance.solicitud.cuadrilla}</td>"
            out <<   "<td width='20%'>${solicitudDeViaticosInstance.fechaCreacion.format('dd/MM/yyyy')}</td>"            
            out << "</tr>"
        }			
	out << "   </tbody>"
        out << "</table>"	
        out << "</div>"
        out << "</td></tr></table>"
    }
    
    def pagos(solicitudPagoCuadrillaInstanceList) {
        out << "<table cellspacing='0' cellpadding='0' style='padding: 0px; spacing: 0px; margin: 10px 0px 0px 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; text-align: center; padding: 0px; spacing: 10px; margin: 0px; border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;background-color: #CFDF78; font-weight: bold;'>"
        out << "Solicitudes de Pagos a Cuadrilla"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <thead>"
        out << "       <tr>"
        out << "           <th width='40%'> Solicitud De tarea </th>"
        out << "           <th width='20%'> Cuadrilla </th>"
        out << "           <th width='20%'> Creacion </th>"
        out << "       </tr>"
        out << "    </thead>"
        out << "</table>"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='overflow: auto;height: 150px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <tbody>"
        
        solicitudPagoCuadrillaInstanceList.eachWithIndex() { solicitudPagoCuadrillaInstance, i ->
            out << """<tr class="${(i % 2) == 0 ? 'even' : 'odd'}"  style='padding: 0px; spacing: 0px; margin: 0px;'>"""            
            out <<   "<td width='40%'>" + """${link(controller:"solicitudPagoCuadrilla",action:"show",id: solicitudPagoCuadrillaInstance.id){solicitudPagoCuadrillaInstance.solicitud}}""" + "</td>"
            out <<   "<td width='20%'>${solicitudPagoCuadrillaInstance.solicitud.cuadrilla}</td>"
            out <<   "<td width='20%'>${solicitudPagoCuadrillaInstance.fechaCreacion.format('dd/MM/yyyy')}</td>"   
            out << "</tr>"
        }			
	out << "   </tbody>"
        out << "</table>"	
        out << "</div>"
        out << "</td></tr></table>"
    }


    def cobros(solicitudDeTareaInstanceList) {
         out << "<table cellspacing='0' cellpadding='0' style='padding: 0px; spacing: 0px; margin: 10px 0px 0px 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; text-align: center; padding: 0px; spacing: 10px; margin: 0px; border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;background-color: #CFDF78; font-weight: bold;'>"
        out << "Cobros de Solicitud de Trabjos"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<tr> <td style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='height: 25px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <thead>"
        out << "       <tr>"
        out << "           <th width='80%'> Solicitud De tarea </th>"
        out << "           <th width='10%'> Monto </th>"
        
        out << "       </tr>"
        out << "    </thead>"
        out << "</table>"
        out << "</div>"
        out << "</td></tr><tr><td  style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "<div style='overflow: auto;height: 150px; width: 100%; padding: 0px; spacing: 0px; margin: 0px;border-collapse: collapse;"
        out << "border-color: #DFDFDF; border-style: solid; border-width: 1px;width: 100%;'>"
        out << "<table style='padding: 0px; spacing: 0px; margin: 0px;'>"
        out << "    <tbody>"
        
        solicitudDeTareaInstanceList.eachWithIndex() { solicitudDeTareaInstance, i ->
            out << """<tr class="${(i % 2) == 0 ? 'even' : 'odd'}"  style='padding: 0px; spacing: 0px; margin: 0px;'>"""            
            out <<   "<td width='80%'>" + """${link(controller:"cobroSolicitudDeTrabajo",action:"create",id: solicitudDeTareaInstance.id){solicitudDeTareaInstance}}""" + "</td>"
            out <<   "<td width='10%'>${solicitudDeTareaInstance.totalPorCobrar()}</td>"
            out << "</tr>"
        }			
	out << "   </tbody>"
        out << "</table>"	
        out << "</div>"
        out << "</td></tr></table>"
    }
   
}
