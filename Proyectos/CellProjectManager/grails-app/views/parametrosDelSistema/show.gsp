
<%@ page import="support.tool.ParametrosDelSistema" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-parametrosDelSistema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>			
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>			
			</ul>
		</div>
		<div id="show-parametrosDelSistema" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list parametrosDelSistema">
			
				<g:if test="${parametrosDelSistemaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="parametrosDelSistema.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${parametrosDelSistemaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${parametrosDelSistemaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="parametrosDelSistema.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${parametrosDelSistemaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${parametrosDelSistemaInstance?.valor}">
				<li class="fieldcontain">
					<span id="valor-label" class="property-label"><g:message code="parametrosDelSistema.valor.label" default="Valor" /></span>
					
						<span class="property-value" aria-labelledby="valor-label"><g:fieldValue bean="${parametrosDelSistemaInstance}" field="valor"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${parametrosDelSistemaInstance?.id}" />
					<g:link class="edit" action="edit" id="${parametrosDelSistemaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>					
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
