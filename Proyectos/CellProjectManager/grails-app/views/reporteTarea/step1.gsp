
<%@ page import="business.tarea.*" %>
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
      <h1>Reporte Tareas - Paso 1/5</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
  <g:form action="step2" >
    <ol class="property-list">
      <li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="estadoTarea.nombre.label" default="Seleccione estado de Tarea" /></span>
						<span class="property-value" aria-labelledby="nombre-label">
        	                             <g:checkBox name="todos" value="${false}" onclick="marcarTodos('estadosTareasIds')"/>
                                             <g:message code="acontecimiento.todos.label" default="Todos" />                                             
                                                  <g:checkBoxList name="estadosTareasIds" from="${estadosTareasListInstance}" value="${estadosTareasListInstance}" optionKey="id"/>
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
