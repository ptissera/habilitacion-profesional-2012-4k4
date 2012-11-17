
<%@ page import="business.tarea.*" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Acontecimientos - Paso 3/5</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step4" >
        <ol class="property-list">
        <span id="nombre-label" class="property-label"><g:message code="acontecimiento.tarea.tipoTarea.label" default="Seleccione Tipo de Tarea" /></span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:checkBoxList name="tipoTareaIds" from="${tipoTareaListInstance}" value="${tipoTareaListInstance}" optionKey="id"/>
        </span>
        </ol>
        <fieldset class="buttons">
          <g:submitButton name="siguiente" class="siguiente" value="Siguiente" />
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
