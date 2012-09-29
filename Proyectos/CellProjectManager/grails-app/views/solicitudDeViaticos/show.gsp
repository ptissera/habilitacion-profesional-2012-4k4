
<%@ page import="business.solicitud.SolicitudDeViaticos" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudDeViaticos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudDeViaticos" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudDeViaticos">
			
				<g:if test="${solicitudDeViaticosInstance?.solicitud}">
				<li class="fieldcontain">
					<span id="solicitud-label" class="property-label"><g:message code="solicitudDeViaticos.solicitud.label" default="Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="solicitud-label">${solicitudDeViaticosInstance?.solicitud?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeViaticosInstance?.fechaCreacion}">
				<li class="fieldcontain">
					<span id="fechaCreacion-label" class="property-label"><g:message code="solicitudDeViaticos.fechaCreacion.label" default="Fecha Creacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreacion-label"><g:formatDate format="dd/MM/yyyy" date="${solicitudDeViaticosInstance?.fechaCreacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeViaticosInstance?.fechaPago}">
				<li class="fieldcontain">
					<span id="fechaPago-label" class="property-label"><g:message code="solicitudDeViaticos.fechaPago.label" default="Fecha Pago" /></span>
					
						<span class="property-value" aria-labelledby="fechaPago-label"><g:formatDate format="dd/MM/yyyy" date="${solicitudDeViaticosInstance?.fechaPago}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeViaticosInstance?.monto}">
				<li class="fieldcontain">
					<span id="monto-label" class="property-label"><g:message code="solicitudDeViaticos.monto.label" default="Monto" /></span>
					
						<span class="property-value" aria-labelledby="monto-label"><g:fieldValue bean="${solicitudDeViaticosInstance}" field="monto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeViaticosInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudDeViaticos.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${solicitudDeViaticosInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeViaticosInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="solicitudDeViaticos.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label">${solicitudDeViaticosInstance?.estado?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudDeViaticosInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudDeViaticosInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>					
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
