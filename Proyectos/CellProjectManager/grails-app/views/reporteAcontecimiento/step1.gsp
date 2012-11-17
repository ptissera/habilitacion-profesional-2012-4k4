
<%@ page import="business.tarea.*" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>  
  </head>
  <body>
    

    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Acontecimientos - Paso 1/5</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
  <g:form action="step2" >
    <ol class="property-list">
      <li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="acontecimiento.tipoAcontecimiento.label" default="Seleccione tipo de Acontecimiento" /></span>
						<span class="property-value" aria-labelledby="nombre-label">
                                                  <g:checkBoxList name="tipoAcontecimientoIds" from="${tipoAcontecimientoListInstance}" value="${tipoAcontecimientoListInstance}" optionKey="id"/>
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
