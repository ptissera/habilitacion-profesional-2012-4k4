package business.core
import business.cuadrillas.DocumentacionIntegranteCuadrilla
import business.solicitud.*

class HomeAdminGeneralTagLib {
    
    def homeAdminGeneral = {
        out << "<table style='border: 0px;'><tr><td>"
        viaticos()
        out << "</td><td>"
        pagos()
        out << "</td></tr><tr><td colspan='2'>"
        vencimientos()
        out << "</td></tr></table>"
    }
  
    def vencimientos() {
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
        def documentacionIntegranteCuadrillaInstanceList = DocumentacionIntegranteCuadrilla.list()
        documentacionIntegranteCuadrillaInstanceList.eachWithIndex() { documentacionIntegranteCuadrillaInstance, i ->
            out << """<tr class="${(i % 2) == 0 ? 'even' : 'odd'}"  style='padding: 0px; spacing: 0px; margin: 0px;'>"""
            out <<   "<td>${documentacionIntegranteCuadrillaInstance.vigenciaDesde.format('dd/MM/yyyy')}</td>"
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
    
    def viaticos() {
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
        def solicitudDeViaticosInstanceList = EstadoSolicitudDeViaticos.findByNombre('Pendiente').solicitudDeViaticos
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
    
    def pagos() {
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
        def solicitudPagoCuadrillaInstanceList = SolicitudPagoCuadrilla.list()
        solicitudPagoCuadrillaInstanceList.eachWithIndex() { solicitudPagoCuadrillaInstance, i ->
            out << """<tr class="${(i % 2) == 0 ? 'even' : 'odd'}"  style='padding: 0px; spacing: 0px; margin: 0px;'>"""
            out <<   "<td width='40%'>${solicitudPagoCuadrillaInstance.solicitud}</td>"
            out <<   "<td width='20%'>${solicitudPagoCuadrillaInstance.solicitud.cuadrilla}</td>"
            out <<   "<td width='20%'>${solicitudPagoCuadrillaInstance.fechaCreacion.format('dd/MM/yyyy')}</td>"   
            out << "</tr>"
        }			
	out << "   </tbody>"
        out << "</table>"	
        out << "</div>"
        out << "</td></tr></table>"
    }
}
