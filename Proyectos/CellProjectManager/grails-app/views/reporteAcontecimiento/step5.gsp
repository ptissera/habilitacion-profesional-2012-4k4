
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Reporte Proyectos - Paso 5/5</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Tipo Acontecimiento</th>
            <th>Creador</th>
            <th>Tipo Tarea</th>
            <th>Fecha Creacion</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${acontecimientoInstanceList}" status="i" var="acontecimiento">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: acontecimiento, field: "tipoAcontecimiento")}</td>
            <td>${fieldValue(bean: acontecimiento, field: "creador")}</td>
            <td>${fieldValue(bean: acontecimiento, field: "tarea.tipoTarea")}</td>
            <td><g:formatDate format="dd/MM/yyyy" date="${acontecimiento.fechaCreacion}" /></td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
 <fieldset class="buttons">
        <g:jasperReport  from="acontecimientoInstanceList" jasper="AcontecimientoReport" format="PDF" name="Reporte" action="reporte" controller="ReporteAcontecimiento"/>        
      </fieldset>
  </body>
</html>
