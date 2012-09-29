<%@ page import="business.solicitud.SolicitudDeViaticos" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-solicitudDeViaticos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="edit-solicitudDeViaticos" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${solicitudDeViaticosInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${solicitudDeViaticosInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${solicitudDeViaticosInstance?.id}" />
				<g:hiddenField name="version" value="${solicitudDeViaticosInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="updateAceptar" value="Aceptar" />
					<g:actionSubmit class="delete" action="updateRechazar" value="Rechazar" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
