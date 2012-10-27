
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Documentacion Operarios - Paso 2/4</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step3" >
        <ol class="property-list">
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label"><g:message code="provincia.nombre.label" default="Seleccione Integrantes" /></span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:checkBoxList name="integrantesCuadrillasIds" from="${integrantesCuadrillasListInstance}" value="${integrantesCuadrillasListInstance}" optionKey="id"/>
            </span>
          </li>
        </ol>
        <fieldset class="buttons">
          <g:submitButton name="siguiente" class="siguiente" value="Siguiente" />
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
