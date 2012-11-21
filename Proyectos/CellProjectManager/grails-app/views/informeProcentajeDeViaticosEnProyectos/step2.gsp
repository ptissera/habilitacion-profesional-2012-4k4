
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Informe Procentaje De Viaticos En Proyectos</title>  
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Informe Procentaje De Viaticos En Proyectos - Paso 2/2</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Proyecto</th>
            <th>Porcentaje %</th>            
            <th>$ Total Viaticos</th>
            <th>$ Total Proyecto</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${datos}" status="i" var="dato">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${dato.proyecto}</td>
            <td>${dato.porcentaje} %</td>
            <td>$ ${dato.totalViaticos}</td>
            <td>$ ${dato.totalProyecto}</td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
    <fieldset class="buttons">
      <g:jasperReport  from="datos" jasper="ProcentajeDeViaticosEnProyectosInform" format="PDF" name="Informe" action="reporte" controller="InformeProcentajeDeViaticosEnProyectos"/>        
    </fieldset>
  </body>
</html>
