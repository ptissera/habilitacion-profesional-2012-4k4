
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Informe de Vencimientos de Documentos</title>  
  </head>
  <body>

    <div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
      <h1>Informe de Vencimientos de Documentos - Paso 2/2</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>
            <th>Tipo de Documentacion</th>
            <th>% / Total</th>            
            <th>Cant. Docs.</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${datos}" status="i" var="dato">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${dato.tipoDocumento}</td>
            <td>${dato.porcentaje}</td>
            <td>${dato.totalTiposDocs}</td>
          </tr>
        </g:each>
        </tbody>
      </table>          
    </div>
 <fieldset class="buttons">
        <g:jasperReport  from="datos" jasper="VencimientosDeDocumentosInform" format="PDF" name="Informe" action="reporte" controller="InformeDeVencimientosDeDocumentos"/>        
      </fieldset>
  </body>
</html>
