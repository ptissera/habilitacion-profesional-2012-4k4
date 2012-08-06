
<%@ page import="business.cuadrillas.IntegranteCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-integranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-integranteCuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list integranteCuadrilla">
			
				<g:if test="${integranteCuadrillaInstance?.du}">
				<li class="fieldcontain">
					<span id="du-label" class="property-label"><g:message code="integranteCuadrilla.du.label" default="Du" /></span>
					
						<span class="property-value" aria-labelledby="du-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="du"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="integranteCuadrilla.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.apellido}">
				<li class="fieldcontain">
					<span id="apellido-label" class="property-label"><g:message code="integranteCuadrilla.apellido.label" default="Apellido" /></span>
					
						<span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="apellido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.legajo}">
				<li class="fieldcontain">
					<span id="legajo-label" class="property-label"><g:message code="integranteCuadrilla.legajo.label" default="Legajo" /></span>
					
						<span class="property-value" aria-labelledby="legajo-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="legajo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="integranteCuadrilla.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="integranteCuadrilla.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${integranteCuadrillaInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.fechaBaja}">
				<li class="fieldcontain">
					<span id="fechaBaja-label" class="property-label"><g:message code="integranteCuadrilla.fechaBaja.label" default="Fecha Baja" /></span>
					
						<span class="property-value" aria-labelledby="fechaBaja-label"><g:formatDate date="${integranteCuadrillaInstance?.fechaBaja}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.cuadrilla}">
				<li class="fieldcontain">
					<span id="cuadrilla-label" class="property-label"><g:message code="integranteCuadrilla.cuadrilla.label" default="Cuadrilla" /></span>
					
						<span class="property-value" aria-labelledby="cuadrilla-label"><g:link controller="cuadrilla" action="show" id="${integranteCuadrillaInstance?.cuadrilla?.id}">${integranteCuadrillaInstance?.cuadrilla?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.documentacion}">
				<li class="fieldcontain">
					<span id="documentacion-label" class="property-label"><g:message code="integranteCuadrilla.documentacion.label" default="Documentacion" /></span>
					
						<g:each in="${integranteCuadrillaInstance.documentacion}" var="d">
						<span class="property-value" aria-labelledby="documentacion-label"><g:link controller="documentacionIntegranteCuadrilla" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${integranteCuadrillaInstance?.id}" />
					<g:link class="edit" action="edit" id="${integranteCuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
