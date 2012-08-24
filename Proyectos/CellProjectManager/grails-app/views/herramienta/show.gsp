
<%@ page import="business.herramienta.Herramienta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'herramienta.label', default: 'Herramienta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-herramienta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-herramienta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list herramienta">
			
				<g:if test="${herramientaInstance?.numeroDeSerie}">
				<li class="fieldcontain">
					<span id="numeroDeSerie-label" class="property-label"><g:message code="herramienta.numeroDeSerie.label" default="Numero De Serie" /></span>
					
						<span class="property-value" aria-labelledby="numeroDeSerie-label"><g:fieldValue bean="${herramientaInstance}" field="numeroDeSerie"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${herramientaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="herramienta.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${herramientaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${herramientaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="herramienta.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${herramientaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${herramientaInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="herramienta.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:link controller="estadoHerramienta" action="show" id="${herramientaInstance?.estado?.id}">${herramientaInstance?.estado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${herramientaInstance?.prestamos}">
				<li class="fieldcontain">
					<span id="prestamos-label" class="property-label"><g:message code="herramienta.prestamos.label" default="Prestamos" /></span>
					
						<g:each in="${herramientaInstance.prestamos}" var="p">
						<span class="property-value" aria-labelledby="prestamos-label"><g:link controller="prestamoHerramienta" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${herramientaInstance?.id}" />
					<g:link class="edit" action="edit" id="${herramientaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
