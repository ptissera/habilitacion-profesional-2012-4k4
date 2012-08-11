
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proyecto.label', default: 'Proyecto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-proyecto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-proyecto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list proyecto">
			
				<g:if test="${proyectoInstance?.licitacion}">
				<li class="fieldcontain">
					<span id="licitacion-label" class="property-label"><g:message code="proyecto.licitacion.label" default="Licitacion" /></span>
					
						<span class="property-value" aria-labelledby="licitacion-label"><g:fieldValue bean="${proyectoInstance}" field="licitacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="proyecto.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${proyectoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="proyecto.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${proyectoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.fechaCreacion}">
				<li class="fieldcontain">
					<span id="fechaCreacion-label" class="property-label"><g:message code="proyecto.fechaCreacion.label" default="Fecha Creacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreacion-label"><g:formatDate date="${proyectoInstance?.fechaCreacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.fechaInicio}">
				<li class="fieldcontain">
					<span id="fechaInicio-label" class="property-label"><g:message code="proyecto.fechaInicio.label" default="Fecha Inicio" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate date="${proyectoInstance?.fechaInicio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.fechaFin}">
				<li class="fieldcontain">
					<span id="fechaFin-label" class="property-label"><g:message code="proyecto.fechaFin.label" default="Fecha Fin" /></span>
					
						<span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate date="${proyectoInstance?.fechaFin}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.estadoProyecto}">
				<li class="fieldcontain">
					<span id="estadoProyecto-label" class="property-label"><g:message code="proyecto.estadoProyecto.label" default="Estado Proyecto" /></span>
					
						<span class="property-value" aria-labelledby="estadoProyecto-label"><g:link controller="estadoProyecto" action="show" id="${proyectoInstance?.estadoProyecto?.id}">${proyectoInstance?.estadoProyecto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="proyecto.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="show" id="${proyectoInstance?.usuario?.id}">${proyectoInstance?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.solicitudes}">
				<li class="fieldcontain">
					<span id="solicitudes-label" class="property-label"><g:message code="proyecto.solicitudes.label" default="Solicitudes" /></span>
					
						<g:each in="${proyectoInstance.solicitudes}" var="s">
						<span class="property-value" aria-labelledby="solicitudes-label"><g:link controller="solicitudDeTarea" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${proyectoInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="proyecto.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="cliente" action="show" id="${proyectoInstance?.cliente?.id}">${proyectoInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${proyectoInstance?.id}" />
					<g:link class="edit" action="edit" id="${proyectoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
