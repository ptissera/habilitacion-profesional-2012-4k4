<%@ page import="business.documento.Documento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-documento" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${documentoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${documentoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post"  enctype="multipart/form-data">
				<g:hiddenField name="id" value="${documentoInstance?.id}" />
				<g:hiddenField name="version" value="${documentoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
                                <fieldset class="buttons_add">
                                        <g:actionSubmit class="aprobar" action="aprobarDocumento" value="${message(code: 'default.button.aprobarDocumento.label', default: 'Aprobar')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.aprobarDocumento.confirm.message', default: 'Confirma aprobacion?')}');" />
                                        <g:actionSubmit class="cancelar" action="desaprobarDocumento" value="${message(code: 'default.button.desaprobarDocumento.label', default: 'Incompleto')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.desaprobarDocumento.confirm.message', default: 'Confirma estado incompleto?')}');" />
                                </fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
