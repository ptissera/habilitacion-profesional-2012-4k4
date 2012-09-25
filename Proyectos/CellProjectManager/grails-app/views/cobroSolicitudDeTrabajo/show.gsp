
<%@ page import="business.solicitud.CobroSolicitudDeTrabajo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cobroSolicitudDeTrabajo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cobroSolicitudDeTrabajo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cobroSolicitudDeTrabajo">
			
				<g:if test="${cobroSolicitudDeTrabajoInstance?.solicitud}">
				<li class="fieldcontain">
					<span id="solicitud-label" class="property-label"><g:message code="cobroSolicitudDeTrabajo.solicitud.label" default="Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="solicitud-label"><g:link controller="solicitudDeTarea" action="show" id="${cobroSolicitudDeTrabajoInstance?.solicitud?.id}">${cobroSolicitudDeTrabajoInstance?.solicitud?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroSolicitudDeTrabajoInstance?.fechaCobro}">
				<li class="fieldcontain">
					<span id="fechaCobro-label" class="property-label"><g:message code="cobroSolicitudDeTrabajo.fechaCobro.label" default="Fecha Cobro" /></span>
					
						<span class="property-value" aria-labelledby="fechaCobro-label"><g:formatDate date="${cobroSolicitudDeTrabajoInstance?.fechaCobro}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroSolicitudDeTrabajoInstance?.monto}">
				<li class="fieldcontain">
					<span id="monto-label" class="property-label"><g:message code="cobroSolicitudDeTrabajo.monto.label" default="Monto" /></span>
					
						<span class="property-value" aria-labelledby="monto-label"><g:fieldValue bean="${cobroSolicitudDeTrabajoInstance}" field="monto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroSolicitudDeTrabajoInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="cobroSolicitudDeTrabajo.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${cobroSolicitudDeTrabajoInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cobroSolicitudDeTrabajoInstance?.id}" />
					<g:link class="edit" action="edit" id="${cobroSolicitudDeTrabajoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
