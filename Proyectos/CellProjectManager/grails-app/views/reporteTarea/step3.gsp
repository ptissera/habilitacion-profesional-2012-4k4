
<%@ page import="business.tarea.*" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Tareas - Paso 3/5</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step4" >
        <ol class="property-list">
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label"><g:message code="tarea.tipoTarea.label" default="Seleccione Tipo de Tarea" /></span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:checkBoxList name="tiposIds" from="${tiposTareasListInstance}" value="${tiposTareasListInstance}" optionKey="id"/>
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