
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Reporte Proyectos - Paso 4/4</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Estado</th>
            <th>Cliente</th>
            <th>Fecha Creacion</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${proyectoInstanceList}" status="i" var="proyecto">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: proyecto, field: "estadoProyecto")}</td>
            <td>${fieldValue(bean: proyecto, field: "cliente")}</td>
            <td><g:formatDate format="dd/MM/yyyy" date="${proyecto.fechaCreacion}" /></td>
            <td><g:formatDate format="dd/MM/yyyy" date="${proyecto.fechaInicio}" /></td>
            <td><g:formatDate format="dd/MM/yyyy" date="${proyecto.fechaFin}" /></td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
 <fieldset class="buttons">
        <g:jasperReport  from="proyectoInstanceList" jasper="proyectoReport" format="PDF" name="Reporte" action="reporte" controller="ReporteProyecto"/>        
      </fieldset>
  </body>
</html>
