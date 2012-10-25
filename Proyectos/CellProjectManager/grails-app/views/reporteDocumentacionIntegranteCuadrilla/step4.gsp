
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Reporte Documentacion Operarios</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Integrante</th>
            <th>Tipo Documento</th>
            <th>Vigencia Desde</th>
            <th>Vigencia Hasta</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${documentacionIntegranteCuadrillaInstanceList}" status="i" var="documentacionIntegranteCuadrillaInstance">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "integrante")}</td>
            <td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "tipoDocumento")}</td>
            <td><g:formatDate format="dd/MM/yyyy" date="${documentacionIntegranteCuadrillaInstance.vigenciaDesde}" /></td>
          <td><g:formatDate format="dd/MM/yyyy" date="${documentacionIntegranteCuadrillaInstance.vigenciaHasta}" /></td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
 <fieldset class="buttons">
        <g:jasperReport  from="documentacionIntegranteCuadrillaInstanceList" jasper="DocumentacionIntegranteCuadrillaReport" format="PDF" name="Reporte" action="reporte" controller="ReporteDocumentacionIntegranteCuadrilla"/>        
      </fieldset>
  </body>
</html>
