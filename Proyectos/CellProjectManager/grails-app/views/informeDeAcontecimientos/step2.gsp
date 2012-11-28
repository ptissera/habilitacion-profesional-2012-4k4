
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Informe de Acontecimiento</title>  
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Informe de Acontecimiento - Paso 2/2</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Fecha de Creacion</th>
            <th>Tipo de Acontecimiento</th>
            <th>% / Total</th>            
            <th>Cant. Acont.</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${datos}" status="i" var="dato">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${dato.fechaAcontecimiento}</td>
            <td>${dato.tipoAcontecimiento}</td>
            <td>${dato.porcentajeAcontecimientos}</td>
            <td>${dato.totalAcontecimientos}</td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
 <fieldset class="buttons">
        <g:jasperReport  from="datos" jasper="AcontecimientosInform" format="PDF" name="Informe" action="reporte" controller="InformeDeAcontecimientos"/>        
      </fieldset>
  </body>
</html>
