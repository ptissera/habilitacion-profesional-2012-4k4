
<%@ page import="business.tarea.EstadoSolicitudTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'estadoSolicitudTarea.label', default: 'EstadoSolicitudTarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-estadoSolicitudTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-estadoSolicitudTarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list estadoSolicitudTarea">
			
				<g:if test="${estadoSolicitudTareaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="estadoSolicitudTarea.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${estadoSolicitudTareaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estadoSolicitudTareaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="estadoSolicitudTarea.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${estadoSolicitudTareaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estadoSolicitudTareaInstance?.solicitudes}">
				<li class="fieldcontain">
					<span id="solicitudes-label" class="property-label"><g:message code="estadoSolicitudTarea.solicitudes.label" default="Solicitudes" /></span>
					
						<g:each in="${estadoSolicitudTareaInstance.solicitudes}" var="s">
						<span class="property-value" aria-labelledby="solicitudes-label"><g:link controller="solicitudDeTarea" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${estadoSolicitudTareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${estadoSolicitudTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
