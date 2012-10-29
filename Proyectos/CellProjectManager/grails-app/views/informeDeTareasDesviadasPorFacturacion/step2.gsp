
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Informe de tareas desviadas por Facturacion</title>  
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Informe de tareas desviadas por Facturacion - Paso 2/2</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Proyecto</th>
            <th>Cliente</th>
            <th>Monto de la Solicitud</th>
            <th>Porcentaje Desvio</th>
            <th>Cuadrilla</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${datos}" status="i" var="dato">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${dato.proyecto}</td>
            <td>${dato.cliente}</td>
            <td>${dato.montoSolicitud}</td>
            <td>${dato.porcentajeDesvio}</td>
            <td>${dato.cuadrilla}</td>
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
