
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Reporte Tareas - Paso 5/5</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Estado</th>
            <th>Sitio</th>
            <th>Tipo Tarea</th>
            <th>Fecha Inicio Estimada</th>
            <th>Fecha Fin Estimada</th>
            <th>Fecha Inicio Real</th>
            <th>Fecha Fin Real</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${tareaInstanceList}" status="i" var="tarea">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: tarea, field: "estado")}</td>
            <td>${fieldValue(bean: tarea, field: "sitio")}</td>
            <td>${fieldValue(bean: tarea, field: "tipoTarea")}</td>
            <td><g:formatDate format="dd/MM/yyyy" date="${tarea.fechaInicio}" /></td>
            <td><g:formatDate format="dd/MM/yyyy" date="${tarea.fechaFin}" /></td>
            <td><g:formatDate format="dd/MM/yyyy" date="${tarea.fechaInicioReal}" /></td>
            <td><g:formatDate format="dd/MM/yyyy" date="${tarea.fechaFinReal}" /></td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
 <fieldset class="buttons">
        <g:jasperReport  from="tareaInstanceList" jasper="TareaReport" format="PDF" name="Reporte" action="reporte" controller="ReporteTarea"/>        
      </fieldset>
  </body>
</html>
