
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
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate format="dd/MM/yyyy" date="${tareasPorSitioInstance?.fechaInicio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.sitio}">
				<li class="fieldcontain">
					<span id="sitio-label" class="property-label"><g:message code="tareasPorSitio.sitio.label" default="Sitio" /></span>
					
						<span class="property-value" aria-labelledby="sitio-label">${tareasPorSitioInstance?.sitio?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.tarea}">
				<li class="fieldcontain">
					<span id="tarea-label" class="property-label"><g:message code="tareasPorSitio.tarea.label" default="Tarea" /></span>
					
						<span class="property-value" aria-labelledby="tarea-label">${tareasPorSitioInstance?.tarea?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="tareasPorSitio.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label">${tareasPorSitioInstance?.estado?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.documentos}">
				<li class="fieldcontain">
					<span id="documentos-label" class="property-label"><g:message code="tareasPorSitio.documentos.label" default="Documentos" /></span>
					
						<g:each in="${tareasPorSitioInstance.documentos}" var="d">
						<span class="property-value" aria-labelledby="documentos-label"><g:link controller="documento" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
							
				<g:if test="${tareasPorSitioInstance?.materialDeTarea}">
				<li class="fieldcontain">
					<span id="materialDeTarea-label" class="property-label"><g:message code="tareasPorSitio.materialDeTarea.label" default="Material De Tarea" /></span>
					
						<g:each in="${tareasPorSitioInstance.materialDeTarea}" var="m">
						<span class="property-value" aria-labelledby="materialDeTarea-label"><g:link controller="materialDeTarea" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tareasPorSitioInstance?.equipoDeTarea}">
				<li class="fieldcontain">
					<span id="equipoDeTarea-label" class="property-label"><g:message code="tareasPorSitio.equipoDeTarea.label" default="Equipo De Tarea" /></span>
					
						<g:each in="${tareasPorSitioInstance.equipoDeTarea}" var="e">
						<span class="property-value" aria-labelledby="equipoDeTarea-label"><g:link controller="equipoDeTarea" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
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
			
				<g:if test="${tareasPorSitioInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="tareasPorSitio.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${tareasPorSitioInstance}" field="observaciones"/></span>
					
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
