
<%@ page import="business.documento.Documento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-documento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list documento">
			
				<g:if test="${documentoInstance?.tarea}">
				<li class="fieldcontain">
					<span id="tarea-label" class="property-label"><g:message code="documento.tarea.label" default="Tarea" /></span>
					
						<span class="property-value" aria-labelledby="tarea-label"><g:link controller="tareasPorSitio" action="show" id="${documentoInstance?.tarea?.id}">${documentoInstance?.tarea?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="documento.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${documentoInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.fechaRealizado}">
				<li class="fieldcontain">
					<span id="fechaRealizado-label" class="property-label"><g:message code="documento.fechaRealizado.label" default="Fecha Realizado" /></span>
					
						<span class="property-value" aria-labelledby="fechaRealizado-label"><g:formatDate date="${documentoInstance?.fechaRealizado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.fechaEnviado}">
				<li class="fieldcontain">
					<span id="fechaEnviado-label" class="property-label"><g:message code="documento.fechaEnviado.label" default="Fecha Enviado" /></span>
					
						<span class="property-value" aria-labelledby="fechaEnviado-label"><g:formatDate date="${documentoInstance?.fechaEnviado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.fechaAprobado}">
				<li class="fieldcontain">
					<span id="fechaAprobado-label" class="property-label"><g:message code="documento.fechaAprobado.label" default="Fecha Aprobado" /></span>
					
						<span class="property-value" aria-labelledby="fechaAprobado-label"><g:formatDate date="${documentoInstance?.fechaAprobado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.nombreArchivo}">
				<li class="fieldcontain">
					<span id="nombreArchivo-label" class="property-label"><g:message code="documento.nombreArchivo.label" default="Nombre Archivo" /></span>
					
						<span class="property-value" aria-labelledby="nombreArchivo-label"><g:fieldValue bean="${documentoInstance}" field="nombreArchivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.archivo}">
				<li class="fieldcontain">
					<span id="archivo-label" class="property-label"><g:message code="documento.archivo.label" default="Archivo" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="documento.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:link controller="estadoDocumento" action="show" id="${documentoInstance?.estado?.id}">${documentoInstance?.estado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${documentoInstance?.id}" />
					<g:link class="edit" action="edit" id="${documentoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
