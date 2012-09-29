<%@ page import="business.solicitud.SolicitudPagoCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-solicitudPagoCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="edit-solicitudPagoCuadrilla" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${solicitudPagoCuadrillaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${solicitudPagoCuadrillaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${solicitudPagoCuadrillaInstance?.id}" />
				<g:hiddenField name="version" value="${solicitudPagoCuadrillaInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="updateAceptar" value="Aprobar" />
					<g:actionSubmit class="delete" action="updateRechazar" value="Rechazar" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
