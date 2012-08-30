<%@ page import="business.tarea.TareasPorSitio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-tareasPorSitio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
			</ul>
		</div>
		<div id="create-tareasPorSitio" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${tareasPorSitioInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${tareasPorSitioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
                               <fieldset class="buttons_add">
                                        <g:link class="add" controller="documento" action="create" params="['tareasPorSitio.id': tareasPorSitioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'documento.label', default: 'Documento')])}</g:link>
                                        <g:link class="add" controller="materialDeTarea" action="create" params="['tareasPorSitio.id': tareasPorSitioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea')])}</g:link>
                                        <g:link class="add" controller="equipoDeTarea" action="create" params="['tareasPorSitio.id': tareasPorSitioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea')])}</g:link>
                                        <g:link class="add" controller="permisoAcceso" action="create" params="['tareasPorSitio.id': tareasPorSitioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso')])}</g:link>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
