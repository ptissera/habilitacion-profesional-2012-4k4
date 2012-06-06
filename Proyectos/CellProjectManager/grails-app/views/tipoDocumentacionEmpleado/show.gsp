
<%@ page import="business.secondary.TipoDocumentacionEmpleado" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoDocumentacionEmpleado.label', default: 'TipoDocumentacionEmpleado')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoDocumentacionEmpleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoDocumentacionEmpleado" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoDocumentacionEmpleado">
			
				<g:if test="${tipoDocumentacionEmpleadoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="tipoDocumentacionEmpleado.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${tipoDocumentacionEmpleadoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDocumentacionEmpleadoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipoDocumentacionEmpleado.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoDocumentacionEmpleadoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDocumentacionEmpleadoInstance?.requerida}">
				<li class="fieldcontain">
					<span id="requerida-label" class="property-label"><g:message code="tipoDocumentacionEmpleado.requerida.label" default="Requerida" /></span>
					
						<span class="property-value" aria-labelledby="requerida-label"><g:formatBoolean boolean="${tipoDocumentacionEmpleadoInstance?.requerida}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDocumentacionEmpleadoInstance?.people}">
				<li class="fieldcontain">
					<span id="people-label" class="property-label"><g:message code="tipoDocumentacionEmpleado.people.label" default="People" /></span>
					
						<g:each in="${tipoDocumentacionEmpleadoInstance.people}" var="p">
						<span class="property-value" aria-labelledby="people-label"><g:link controller="documentacionEmpleado" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoDocumentacionEmpleadoInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoDocumentacionEmpleadoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
