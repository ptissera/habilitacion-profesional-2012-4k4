
<%@ page import="business.core.PermisoAcceso" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'permisoAcceso.label', default: 'PermisoAcceso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-permisoAcceso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-permisoAcceso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list permisoAcceso">
			
				<g:if test="${permisoAccesoInstance?.fechaDesde}">
				<li class="fieldcontain">
					<span id="fechaDesde-label" class="property-label"><g:message code="permisoAcceso.fechaDesde.label" default="Fecha Desde" /></span>
					
						<span class="property-value" aria-labelledby="fechaDesde-label"><g:formatDate date="${permisoAccesoInstance?.fechaDesde}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${permisoAccesoInstance?.fechaHasta}">
				<li class="fieldcontain">
					<span id="fechaHasta-label" class="property-label"><g:message code="permisoAcceso.fechaHasta.label" default="Fecha Hasta" /></span>
					
						<span class="property-value" aria-labelledby="fechaHasta-label"><g:formatDate date="${permisoAccesoInstance?.fechaHasta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${permisoAccesoInstance?.nombreArchivo}">
				<li class="fieldcontain">
					<span id="archivo-label" class="property-label"><g:message code="permisoAcceso.archivo.label" default="Archivo" /></span>
					
						<span class="property-value" aria-labelledby="archivo-label"><g:fieldValue bean="${permisoAccesoInstance}" field="nombreArchivo"/></span>
					
				</li>
				</g:if>
			
				
										
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${permisoAccesoInstance?.id}" />
					<g:link class="edit" action="edit" id="${permisoAccesoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
