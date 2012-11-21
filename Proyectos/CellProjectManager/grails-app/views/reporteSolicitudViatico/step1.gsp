
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>  
  </head>
  <body>
    

    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte de Solicitudes de Viaticos - Paso 1/3</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
  <g:form action="step2" >
    <ol class="property-list">
      <li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="estadoSolicitudViatico.nombre.label" default="Seleccione estado de solicitud de Viatico" /></span>
						<span class="property-value" aria-labelledby="nombre-label">
                                                  <g:checkBoxList name="estadoViaticoIds" from="${estadoViaticoListInstance}" value="${estadoViaticoListInstance}" optionKey="id"/>
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
