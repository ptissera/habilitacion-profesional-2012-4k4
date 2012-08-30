<%@ page import="business.tarea.SolicitudDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-solicitudDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-solicitudDeTarea" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${solicitudDeTareaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${solicitudDeTareaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${solicitudDeTareaInstance?.id}" />
				<g:hiddenField name="version" value="${solicitudDeTareaInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons_add">
					<g:link class="add" controller="tareasPorSitio" action="create" params="['solicitudDeTarea.id': solicitudDeTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')])}</g:link>                                        
                                        <g:link class="add" controller="PO" action="create" params="['solicitudDeTarea.id': solicitudDeTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'PO.label', default: 'PO')])}</g:link>
                                        <g:link class="add" controller="prestamoHerramienta" action="create" params="['solicitudDeTarea.id': solicitudDeTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta')])}</g:link>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                                </fieldset>
			</g:form>
		</div>
	</body>
</html>
