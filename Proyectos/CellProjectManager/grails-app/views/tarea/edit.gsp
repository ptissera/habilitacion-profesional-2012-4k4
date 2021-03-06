<%@ page import="business.tarea.Tarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tarea.label', default: 'Tarea')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-tarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-tarea" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${tareaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${tareaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post"  enctype="multipart/form-data">
				<g:hiddenField name="id" value="${tareaInstance?.id}" />
				<g:hiddenField name="version" value="${tareaInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
                                <fieldset class="buttons_add">                                        
                                        <g:link class="add" controller="materialDeTarea" action="create" params="['tarea.id': tareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'materialDeTarea.label', default: 'MaterialDeTarea')])}</g:link>
                                        <g:link class="add" controller="equipoDeTarea" action="create" params="['tarea.id': tareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea')])}</g:link>
                                        <g:link class="add" controller="permisoAcceso" action="create" params="['tarea.id': tareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'permisoAcceso.label', default: 'PermisoAcceso')])}</g:link>                                        
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </fieldset>
                                        
			</g:form>
		</div>
	</body>
</html>
