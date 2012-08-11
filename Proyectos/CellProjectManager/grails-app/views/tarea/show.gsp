
<%@ page import="business.tarea.Tarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tarea.label', default: 'Tarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tarea">
			
				<g:if test="${tareaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tarea.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tareaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.monto}">
				<li class="fieldcontain">
					<span id="monto-label" class="property-label"><g:message code="tarea.monto.label" default="Monto" /></span>
					
						<span class="property-value" aria-labelledby="monto-label"><g:fieldValue bean="${tareaInstance}" field="monto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="tarea.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${tareaInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="tarea.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${tareaInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="tarea.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:link controller="estadoTarea" action="show" id="${tareaInstance?.estado?.id}">${tareaInstance?.estado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="tarea.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:link controller="tipoTarea" action="show" id="${tareaInstance?.tipo?.id}">${tareaInstance?.tipo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.tareaSitio}">
				<li class="fieldcontain">
					<span id="tareaSitio-label" class="property-label"><g:message code="tarea.tareaSitio.label" default="Tarea Sitio" /></span>
					
						<span class="property-value" aria-labelledby="tareaSitio-label"><g:link controller="tareasPorSitio" action="show" id="${tareaInstance?.tareaSitio?.id}">${tareaInstance?.tareaSitio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.documentos}">
				<li class="fieldcontain">
					<span id="documentos-label" class="property-label"><g:message code="tarea.documentos.label" default="Documentos" /></span>
					
						<g:each in="${tareaInstance.documentos}" var="d">
						<span class="property-value" aria-labelledby="documentos-label"><g:link controller="documento" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.equipos}">
				<li class="fieldcontain">
					<span id="equipos-label" class="property-label"><g:message code="tarea.equipos.label" default="Equipos" /></span>
					
						<g:each in="${tareaInstance.equipos}" var="e">
						<span class="property-value" aria-labelledby="equipos-label"><g:link controller="equipoDeTarea" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tareaInstance?.materiales}">
				<li class="fieldcontain">
					<span id="materiales-label" class="property-label"><g:message code="tarea.materiales.label" default="Materiales" /></span>
					
						<g:each in="${tareaInstance.materiales}" var="m">
						<span class="property-value" aria-labelledby="materiales-label"><g:link controller="materialDeTarea" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${tareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
