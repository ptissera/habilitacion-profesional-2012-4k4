
<%@ page import="business.cuadrillas.TipoDocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoDocumentacionIntegranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoDocumentacionIntegranteCuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoDocumentacionIntegranteCuadrilla">
			
				<g:if test="${tipoDocumentacionIntegranteCuadrillaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="tipoDocumentacionIntegranteCuadrilla.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${tipoDocumentacionIntegranteCuadrillaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDocumentacionIntegranteCuadrillaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipoDocumentacionIntegranteCuadrilla.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoDocumentacionIntegranteCuadrillaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDocumentacionIntegranteCuadrillaInstance?.diaAntesVencimiento}">
				<li class="fieldcontain">
					<span id="diaAntesVencimiento-label" class="property-label"><g:message code="tipoDocumentacionIntegranteCuadrilla.diaAntesVencimiento.label" default="Dia Antes Vencimiento" /></span>
					
						<span class="property-value" aria-labelledby="diaAntesVencimiento-label"><g:fieldValue bean="${tipoDocumentacionIntegranteCuadrillaInstance}" field="diaAntesVencimiento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDocumentacionIntegranteCuadrillaInstance?.documentacionIntegranteCuadrilla}">
				<li class="fieldcontain">
					<span id="documentacionIntegranteCuadrilla-label" class="property-label"><g:message code="tipoDocumentacionIntegranteCuadrilla.documentacionIntegranteCuadrilla.label" default="Documentacion Integrante Cuadrilla" /></span>
					
						<g:each in="${tipoDocumentacionIntegranteCuadrillaInstance.documentacionIntegranteCuadrilla}" var="d">
						<span class="property-value" aria-labelledby="documentacionIntegranteCuadrilla-label"><g:link controller="documentacionIntegranteCuadrilla" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoDocumentacionIntegranteCuadrillaInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoDocumentacionIntegranteCuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
