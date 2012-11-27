
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title> 
      <g:javascript>
    function marcarTodos(name) {
     boxes = document.getElementsByName(name);
     for (i = 0; i < boxes.length; i++)
     if (!boxes[i].disabled)
   		{	boxes[i].checked = !boxes[i].checked ; }
     }
  </g:javascript>
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
                                                   <g:checkBox name="todos" value="${false}" onclick="marcarTodos('estadoViaticoIds')"/>
                                                  <g:message code="acontecimiento.todos.label" default="Todos" />  
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
