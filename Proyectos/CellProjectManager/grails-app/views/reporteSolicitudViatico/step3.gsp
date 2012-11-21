
<%@ page import="business.solicitud.*" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Reporte de Solicitudes de Viaticos - Paso 3/3</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Estado</th>
            <th>Fecha Creacion</th>
            <th>Fecha Pago</th>
            <th>Monto</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${SolicitudViaticoInstanceList}" status="i" var="SolicitudDeViaticos">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: SolicitudDeViaticos, field: "estado")}</td>
            <td><g:formatDate format="dd/MM/yyyy" date="${SolicitudDeViaticos.fechaCreacion}" /></td>
            <td><g:formatDate format="dd/MM/yyyy" date="${SolicitudDeViaticos.fechaPago}" /></td>
            <td>${fieldValue(bean: SolicitudDeViaticos, field: "monto")}</td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
 <fieldset class="buttons">
        <g:jasperReport  from="SolicitudViaticoInstanceList" jasper="SolicitudViaticoReport" format="PDF" name="Reporte" action="reporte" controller="ReporteSolicitudViatico"/>        
      </fieldset>
  </body>
</html>
