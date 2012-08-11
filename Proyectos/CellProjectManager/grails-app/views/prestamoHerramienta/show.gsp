
<%@ page import="business.herramienta.PrestamoHerramienta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-prestamoHerramienta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-prestamoHerramienta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list prestamoHerramienta">
			
				<g:if test="${prestamoHerramientaInstance?.fechaPrestamo}">
				<li class="fieldcontain">
					<span id="fechaPrestamo-label" class="property-label"><g:message code="prestamoHerramienta.fechaPrestamo.label" default="Fecha Prestamo" /></span>
					
						<span class="property-value" aria-labelledby="fechaPrestamo-label"><g:formatDate date="${prestamoHerramientaInstance?.fechaPrestamo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoHerramientaInstance?.fechaDevolucion}">
				<li class="fieldcontain">
					<span id="fechaDevolucion-label" class="property-label"><g:message code="prestamoHerramienta.fechaDevolucion.label" default="Fecha Devolucion" /></span>
					
						<span class="property-value" aria-labelledby="fechaDevolucion-label"><g:formatDate date="${prestamoHerramientaInstance?.fechaDevolucion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoHerramientaInstance?.herramienta}">
				<li class="fieldcontain">
					<span id="herramienta-label" class="property-label"><g:message code="prestamoHerramienta.herramienta.label" default="Herramienta" /></span>
					
						<span class="property-value" aria-labelledby="herramienta-label"><g:link controller="herramienta" action="show" id="${prestamoHerramientaInstance?.herramienta?.id}">${prestamoHerramientaInstance?.herramienta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoHerramientaInstance?.cuadrilla}">
				<li class="fieldcontain">
					<span id="cuadrilla-label" class="property-label"><g:message code="prestamoHerramienta.cuadrilla.label" default="Cuadrilla" /></span>
					
						<span class="property-value" aria-labelledby="cuadrilla-label"><g:link controller="cuadrilla" action="show" id="${prestamoHerramientaInstance?.cuadrilla?.id}">${prestamoHerramientaInstance?.cuadrilla?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoHerramientaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="prestamoHerramienta.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${prestamoHerramientaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${prestamoHerramientaInstance?.id}" />
					<g:link class="edit" action="edit" id="${prestamoHerramientaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
