
<%@ page import="business.tarea.MaterialDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialDeTarea.label', default: 'MaterialDeTarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-materialDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-materialDeTarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list materialDeTarea">
			
				<g:if test="${materialDeTareaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="materialDeTarea.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${materialDeTareaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialDeTareaInstance?.cantidad}">
				<li class="fieldcontain">
					<span id="cantidad-label" class="property-label"><g:message code="materialDeTarea.cantidad.label" default="Cantidad" /></span>
					
						<span class="property-value" aria-labelledby="cantidad-label"><g:fieldValue bean="${materialDeTareaInstance}" field="cantidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialDeTareaInstance?.esDeCliente}">
				<li class="fieldcontain">
					<span id="esDeCliente-label" class="property-label"><g:message code="materialDeTarea.esDeCliente.label" default="Es De Cliente" /></span>
					
						<span class="property-value" aria-labelledby="esDeCliente-label"><g:formatBoolean boolean="${materialDeTareaInstance?.esDeCliente}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialDeTareaInstance?.unidad}">
				<li class="fieldcontain">
					<span id="unidad-label" class="property-label"><g:message code="materialDeTarea.unidad.label" default="Unidad" /></span>
					
						<span class="property-value" aria-labelledby="unidad-label"><g:link controller="unidadMedida" action="show" id="${materialDeTareaInstance?.unidad?.id}">${materialDeTareaInstance?.unidad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialDeTareaInstance?.tareasPorSitio}">
				<li class="fieldcontain">
					<span id="tareasPorSitio-label" class="property-label"><g:message code="materialDeTarea.tareasPorSitio.label" default="Tareas Por Sitio" /></span>
					
						<span class="property-value" aria-labelledby="tareasPorSitio-label"><g:link controller="tareasPorSitio" action="show" id="${materialDeTareaInstance?.tareasPorSitio?.id}">${materialDeTareaInstance?.tareasPorSitio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${materialDeTareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${materialDeTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
