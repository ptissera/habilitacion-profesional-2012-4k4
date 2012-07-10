
<%@ page import="business.cuadrillas.DocumentacionEmpleado" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-documentacionEmpleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="back" action="show" controller="empleado" id="${empleadoInstance?.id}">Regresar</g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>                
		<div id="show-documentacionEmpleado" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list documentacionEmpleado">
			
				<g:if test="${documentacionEmpleadoInstance?.tipoDocumento}">
				<li class="fieldcontain">
					<span id="tipoDocumento-label" class="property-label"><g:message code="documentacionEmpleado.tipoDocumento.label" default="Tipo Documento" /></span>
					
						<span class="property-value" aria-labelledby="tipoDocumento-label"><g:link controller="tipoDocumentacionEmpleado" action="show" id="${documentacionEmpleadoInstance?.tipoDocumento?.id}">${documentacionEmpleadoInstance?.tipoDocumento?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentacionEmpleadoInstance?.vigenciaDesde}">
				<li class="fieldcontain">
					<span id="vigenciaDesde-label" class="property-label"><g:message code="documentacionEmpleado.vigenciaDesde.label" default="Vigencia Desde" /></span>
					
						<span class="property-value" aria-labelledby="vigenciaDesde-label"><g:formatDate format="dd/MM/yyyy" date="${documentacionEmpleadoInstance?.vigenciaDesde}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentacionEmpleadoInstance?.vigenciaHasta}">
				<li class="fieldcontain">
					<span id="vigenciaHasta-label" class="property-label"><g:message code="documentacionEmpleado.vigenciaHasta.label" default="Vigencia Hasta" /></span>
					
						<span class="property-value" aria-labelledby="vigenciaHasta-label"><g:formatDate format="dd/MM/yyyy" date="${documentacionEmpleadoInstance?.vigenciaHasta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentacionEmpleadoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="documentacionEmpleado.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${documentacionEmpleadoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
								
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${documentacionEmpleadoInstance?.id}" />
					<g:link class="edit" action="edit" id="${documentacionEmpleadoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
