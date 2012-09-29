
<%@ page import="business.solicitud.SolicitudPagoCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudPagoCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-solicitudPagoCuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudPagoCuadrilla">
			
				<g:if test="${solicitudPagoCuadrillaInstance?.solicitud}">
				<li class="fieldcontain">
					<span id="solicitud-label" class="property-label"><g:message code="solicitudPagoCuadrilla.solicitud.label" default="Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="solicitud-label">${solicitudPagoCuadrillaInstance?.solicitud?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPagoCuadrillaInstance?.fechaCreacion}">
				<li class="fieldcontain">
					<span id="fechaCreacion-label" class="property-label"><g:message code="solicitudPagoCuadrilla.fechaCreacion.label" default="Fecha Creacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreacion-label"><g:formatDate format="dd/MM/yyy" date="${solicitudPagoCuadrillaInstance?.fechaCreacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPagoCuadrillaInstance?.fechaPago}">
				<li class="fieldcontain">
					<span id="fechaPago-label" class="property-label"><g:message code="solicitudPagoCuadrilla.fechaPago.label" default="Fecha Pago" /></span>
					
						<span class="property-value" aria-labelledby="fechaPago-label"><g:formatDate format="dd/MM/yyy" date="${solicitudPagoCuadrillaInstance?.fechaPago}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPagoCuadrillaInstance?.porcentaje}">
				<li class="fieldcontain">
					<span id="porcentaje-label" class="property-label"><g:message code="solicitudPagoCuadrilla.porcentaje.label" default="Porcentaje" /></span>
					
						<span class="property-value" aria-labelledby="porcentaje-label"><g:fieldValue bean="${solicitudPagoCuadrillaInstance}" field="porcentaje"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPagoCuadrillaInstance?.monto}">
				<li class="fieldcontain">
					<span id="monto-label" class="property-label"><g:message code="solicitudPagoCuadrilla.monto.label" default="Monto" /></span>
					
						<span class="property-value" aria-labelledby="monto-label"><g:fieldValue bean="${solicitudPagoCuadrillaInstance}" field="monto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPagoCuadrillaInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudPagoCuadrilla.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${solicitudPagoCuadrillaInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPagoCuadrillaInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="solicitudPagoCuadrilla.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label">${solicitudPagoCuadrillaInstance?.estado?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudPagoCuadrillaInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudPagoCuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>					
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
