
<%@ page import="business.tarea.TareasPorSitio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tareasPorSitio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tareasPorSitio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tareasPorSitio">
			
				<g:if test="${tareasPorSitioInstance?.ordenEjecucion}">
				<li class="fieldcontain">
					<span id="ordenEjecucion-label" class="property-label"><g:message code="tareasPorSitio.ordenEjecucion.label" default="Orden Ejecucion" /></span>
					
						<span class="property-value" aria-labelledby="ordenEjecucion-label"><g:fieldValue bean="${tareasPorSitioInstance}" field="ordenEjecucion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.fechaInicio}">
				<li class="fieldcontain">
					<span id="fechaInicio-label" class="property-label"><g:message code="tareasPorSitio.fechaInicio.label" default="Fecha Inicio" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate date="${tareasPorSitioInstance?.fechaInicio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.sitio}">
				<li class="fieldcontain">
					<span id="sitio-label" class="property-label"><g:message code="tareasPorSitio.sitio.label" default="Sitio" /></span>
					
						<span class="property-value" aria-labelledby="sitio-label"><g:link controller="sitio" action="show" id="${tareasPorSitioInstance?.sitio?.id}">${tareasPorSitioInstance?.sitio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.tarea}">
				<li class="fieldcontain">
					<span id="tarea-label" class="property-label"><g:message code="tareasPorSitio.tarea.label" default="Tarea" /></span>
					
						<span class="property-value" aria-labelledby="tarea-label"><g:link controller="tarea" action="show" id="${tareasPorSitioInstance?.tarea?.id}">${tareasPorSitioInstance?.tarea?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.solicitudDeTarea}">
				<li class="fieldcontain">
					<span id="solicitudDeTarea-label" class="property-label"><g:message code="tareasPorSitio.solicitudDeTarea.label" default="Solicitud De Tarea" /></span>
					
						<span class="property-value" aria-labelledby="solicitudDeTarea-label"><g:link controller="solicitudDeTarea" action="show" id="${tareasPorSitioInstance?.solicitudDeTarea?.id}">${tareasPorSitioInstance?.solicitudDeTarea?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.herramientas}">
				<li class="fieldcontain">
					<span id="herramientas-label" class="property-label"><g:message code="tareasPorSitio.herramientas.label" default="Herramientas" /></span>
					
						<g:each in="${tareasPorSitioInstance.herramientas}" var="h">
						<span class="property-value" aria-labelledby="herramientas-label"><g:link controller="prestamoHerramienta" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.permisos}">
				<li class="fieldcontain">
					<span id="permisos-label" class="property-label"><g:message code="tareasPorSitio.permisos.label" default="Permisos" /></span>
					
						<g:each in="${tareasPorSitioInstance.permisos}" var="p">
						<span class="property-value" aria-labelledby="permisos-label"><g:link controller="permisoAcceso" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tareasPorSitioInstance?.id}" />
					<g:link class="edit" action="edit" id="${tareasPorSitioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>