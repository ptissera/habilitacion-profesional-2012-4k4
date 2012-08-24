
<%@ page import="business.herramienta.EstadoHerramienta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'estadoHerramienta.label', default: 'EstadoHerramienta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-estadoHerramienta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-estadoHerramienta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list estadoHerramienta">
			
				<g:if test="${estadoHerramientaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="estadoHerramienta.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${estadoHerramientaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estadoHerramientaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="estadoHerramienta.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${estadoHerramientaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estadoHerramientaInstance?.herramientas}">
				<li class="fieldcontain">
					<span id="herramientas-label" class="property-label"><g:message code="estadoHerramienta.herramientas.label" default="Herramientas" /></span>
					
						<g:each in="${estadoHerramientaInstance.herramientas}" var="h">
						<span class="property-value" aria-labelledby="herramientas-label"><g:link controller="herramienta" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${estadoHerramientaInstance?.id}" />
					<g:link class="edit" action="edit" id="${estadoHerramientaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
