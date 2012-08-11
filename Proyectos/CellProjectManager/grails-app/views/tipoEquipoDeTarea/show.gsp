
<%@ page import="business.tarea.TipoEquipoDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoEquipoDeTarea.label', default: 'TipoEquipoDeTarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoEquipoDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoEquipoDeTarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoEquipoDeTarea">
			
				<g:if test="${tipoEquipoDeTareaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="tipoEquipoDeTarea.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${tipoEquipoDeTareaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoEquipoDeTareaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipoEquipoDeTarea.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoEquipoDeTareaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoEquipoDeTareaInstance?.equipos}">
				<li class="fieldcontain">
					<span id="equipos-label" class="property-label"><g:message code="tipoEquipoDeTarea.equipos.label" default="Equipos" /></span>
					
						<g:each in="${tipoEquipoDeTareaInstance.equipos}" var="e">
						<span class="property-value" aria-labelledby="equipos-label"><g:link controller="equipoDeTarea" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoEquipoDeTareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoEquipoDeTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
