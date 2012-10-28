
<%@ page import="business.tarea.*" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Tareas - Paso 2/5</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step3" >
        <ol class="property-list">
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label"><g:message code="sitio.nombre.label" default="Seleccione Sitio" /></span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:checkBoxList name="sitiosIds" from="${sitiosTareasListInstance}" value="${sitiosTareasListInstance}" optionKey="id"/>
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
