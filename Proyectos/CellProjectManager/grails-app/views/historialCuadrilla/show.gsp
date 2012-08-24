
<%@ page import="business.cuadrillas.HistorialCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-historialCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-historialCuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list historialCuadrilla">
			
				<g:if test="${historialCuadrillaInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="historialCuadrilla.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${historialCuadrillaInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${historialCuadrillaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="historialCuadrilla.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${historialCuadrillaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${historialCuadrillaInstance?.cuadrilla}">
				<li class="fieldcontain">
					<span id="cuadrilla-label" class="property-label"><g:message code="historialCuadrilla.cuadrilla.label" default="Cuadrilla" /></span>
					
						<span class="property-value" aria-labelledby="cuadrilla-label"><g:link controller="cuadrilla" action="show" id="${historialCuadrillaInstance?.cuadrilla?.id}">${historialCuadrillaInstance?.cuadrilla?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${historialCuadrillaInstance?.id}" />
					<g:link class="edit" action="edit" id="${historialCuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
