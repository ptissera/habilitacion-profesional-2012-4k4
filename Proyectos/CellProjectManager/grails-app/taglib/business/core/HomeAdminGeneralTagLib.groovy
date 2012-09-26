package business.core
import business.cuadrillas.DocumentacionIntegranteCuadrilla

class HomeAdminGeneralTagLib {
    def vencimientos = {
        out << "<table>"
        out << "	<thead>"
        out << "       <tr>"
        out << "           <th> Desde </th>"
        out << "           <th> Hasta </th>"
        out << "           <th> Descripcion </th>"
        out << "           <th> Tipo </th>"
        out << "           <th> Integrante </th>"
        out << "           <th> Estado </th>"
        out << "       </tr>"
        out << "	</thead>"
        out << "   <tbody>"
        def documentacionIntegranteCuadrillaInstanceList = DocumentacionIntegranteCuadrilla.list()
        documentacionIntegranteCuadrillaInstanceList.eachWithIndex() { documentacionIntegranteCuadrillaInstance, i ->
            out << """<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">"""
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
    }
}
