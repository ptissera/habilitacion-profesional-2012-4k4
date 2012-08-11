
<%@ page import="business.core.Cliente" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cliente.label', default: 'Cliente')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cliente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cliente" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cliente">
			
				<g:if test="${clienteInstance?.razonSocial}">
				<li class="fieldcontain">
					<span id="razonSocial-label" class="property-label"><g:message code="cliente.razonSocial.label" default="Razon Social" /></span>
					
						<span class="property-value" aria-labelledby="razonSocial-label"><g:fieldValue bean="${clienteInstance}" field="razonSocial"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.cuit}">
				<li class="fieldcontain">
					<span id="cuit-label" class="property-label"><g:message code="cliente.cuit.label" default="Cuit" /></span>
					
						<span class="property-value" aria-labelledby="cuit-label"><g:fieldValue bean="${clienteInstance}" field="cuit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="cliente.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${clienteInstance}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.direccion}">
				<li class="fieldcontain">
					<span id="direccion-label" class="property-label"><g:message code="cliente.direccion.label" default="Direccion" /></span>
					
						<span class="property-value" aria-labelledby="direccion-label"><g:fieldValue bean="${clienteInstance}" field="direccion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="cliente.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${clienteInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.contactoNombre}">
				<li class="fieldcontain">
					<span id="contactoNombre-label" class="property-label"><g:message code="cliente.contactoNombre.label" default="Contacto Nombre" /></span>
					
						<span class="property-value" aria-labelledby="contactoNombre-label"><g:fieldValue bean="${clienteInstance}" field="contactoNombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.contactoTelefono}">
				<li class="fieldcontain">
					<span id="contactoTelefono-label" class="property-label"><g:message code="cliente.contactoTelefono.label" default="Contacto Telefono" /></span>
					
						<span class="property-value" aria-labelledby="contactoTelefono-label"><g:fieldValue bean="${clienteInstance}" field="contactoTelefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.contactoEmail}">
				<li class="fieldcontain">
					<span id="contactoEmail-label" class="property-label"><g:message code="cliente.contactoEmail.label" default="Contacto Email" /></span>
					
						<span class="property-value" aria-labelledby="contactoEmail-label"><g:fieldValue bean="${clienteInstance}" field="contactoEmail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${clienteInstance?.licitaciones}">
				<li class="fieldcontain">
					<span id="licitaciones-label" class="property-label"><g:message code="cliente.licitaciones.label" default="Licitaciones" /></span>
					
						<g:each in="${clienteInstance.licitaciones}" var="l">
						<span class="property-value" aria-labelledby="licitaciones-label"><g:link controller="proyecto" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${clienteInstance?.id}" />
					<g:link class="edit" action="edit" id="${clienteInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
