
<%@ page import="business.core.PO" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'PO.label', default: 'PO')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-PO" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-PO" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list PO">
			
				<g:if test="${POInstance?.fechaRecibida}">
				<li class="fieldcontain">
					<span id="fechaRecibida-label" class="property-label"><g:message code="PO.fechaRecibida.label" default="Fecha Recibida" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibida-label"><g:formatDate date="${POInstance?.fechaRecibida}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.monto}">
				<li class="fieldcontain">
					<span id="monto-label" class="property-label"><g:message code="PO.monto.label" default="Monto" /></span>
					
						<span class="property-value" aria-labelledby="monto-label"><g:fieldValue bean="${POInstance}" field="monto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.esExtra}">
				<li class="fieldcontain">
					<span id="esExtra-label" class="property-label"><g:message code="PO.esExtra.label" default="Es Extra" /></span>
					
						<span class="property-value" aria-labelledby="esExtra-label"><g:formatBoolean boolean="${POInstance?.esExtra}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.nombreArchivo}">
				<li class="fieldcontain">
					<span id="nombreArchivo-label" class="property-label"><g:message code="PO.nombreArchivo.label" default="Nombre Archivo" /></span>
					
						<span class="property-value" aria-labelledby="nombreArchivo-label"><g:fieldValue bean="${POInstance}" field="nombreArchivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.archivo}">
				<li class="fieldcontain">
					<span id="archivo-label" class="property-label"><g:message code="PO.archivo.label" default="Archivo" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.cobro}">
				<li class="fieldcontain">
					<span id="cobro-label" class="property-label"><g:message code="PO.cobro.label" default="Cobro" /></span>
					
						<span class="property-value" aria-labelledby="cobro-label"><g:link controller="cobro" action="show" id="${POInstance?.cobro?.id}">${POInstance?.cobro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.solicitud}">
				<li class="fieldcontain">
					<span id="solicitud-label" class="property-label"><g:message code="PO.solicitud.label" default="Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="solicitud-label"><g:link controller="solicitudDeTarea" action="show" id="${POInstance?.solicitud?.id}">${POInstance?.solicitud?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${POInstance?.id}" />
					<g:link class="edit" action="edit" id="${POInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
