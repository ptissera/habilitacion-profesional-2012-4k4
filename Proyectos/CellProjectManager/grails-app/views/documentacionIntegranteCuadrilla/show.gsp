
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-documentacionIntegranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>				
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-documentacionIntegranteCuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list documentacionIntegranteCuadrilla">
			
				<g:if test="${documentacionIntegranteCuadrillaInstance?.vigenciaDesde}">
				<li class="fieldcontain">
					<span id="vigenciaDesde-label" class="property-label"><g:message code="documentacionIntegranteCuadrilla.vigenciaDesde.label" default="Vigencia Desde" /></span>
					
						<span class="property-value" aria-labelledby="vigenciaDesde-label"><g:formatDate date="${documentacionIntegranteCuadrillaInstance?.vigenciaDesde}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentacionIntegranteCuadrillaInstance?.vigenciaHasta}">
				<li class="fieldcontain">
					<span id="vigenciaHasta-label" class="property-label"><g:message code="documentacionIntegranteCuadrilla.vigenciaHasta.label" default="Vigencia Hasta" /></span>
					
						<span class="property-value" aria-labelledby="vigenciaHasta-label"><g:formatDate date="${documentacionIntegranteCuadrillaInstance?.vigenciaHasta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentacionIntegranteCuadrillaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="documentacionIntegranteCuadrilla.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${documentacionIntegranteCuadrillaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentacionIntegranteCuadrillaInstance?.tipoDocumento}">
				<li class="fieldcontain">
					<span id="tipoDocumento-label" class="property-label"><g:message code="documentacionIntegranteCuadrilla.tipoDocumento.label" default="Tipo Documento" /></span>
					
						<span class="property-value" aria-labelledby="tipoDocumento-label"><g:link controller="tipoDocumentacionIntegranteCuadrilla" action="show" id="${documentacionIntegranteCuadrillaInstance?.tipoDocumento?.id}">${documentacionIntegranteCuadrillaInstance?.tipoDocumento?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
							
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${documentacionIntegranteCuadrillaInstance?.id}" />
					<g:link class="edit" action="edit" id="${documentacionIntegranteCuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
