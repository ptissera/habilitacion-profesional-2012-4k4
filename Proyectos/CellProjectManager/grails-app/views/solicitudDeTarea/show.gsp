
<%@ page import="business.tarea.SolicitudDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudDeTarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudDeTarea">
			
				<g:if test="${solicitudDeTareaInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="solicitudDeTarea.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${solicitudDeTareaInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeTareaInstance?.tareasPorSitio}">
				<li class="fieldcontain">
					<span id="tareasPorSitio-label" class="property-label"><g:message code="solicitudDeTarea.tareasPorSitio.label" default="Tareas Por Sitio" /></span>
					
						<g:each in="${solicitudDeTareaInstance.tareasPorSitio}" var="t">
						<span class="property-value" aria-labelledby="tareasPorSitio-label"><g:link controller="tareasPorSitio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeTareaInstance?.po}">
				<li class="fieldcontain">
					<span id="po-label" class="property-label"><g:message code="solicitudDeTarea.po.label" default="Po" /></span>
					
						<g:each in="${solicitudDeTareaInstance.po}" var="p">
						<span class="property-value" aria-labelledby="po-label"><g:link controller="PO" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeTareaInstance?.cuadrilla}">
				<li class="fieldcontain">
					<span id="cuadrilla-label" class="property-label"><g:message code="solicitudDeTarea.cuadrilla.label" default="Cuadrilla" /></span>
					
						<g:each in="${solicitudDeTareaInstance.cuadrilla}" var="c">
						<span class="property-value" aria-labelledby="cuadrilla-label"><g:link controller="cuadrilla" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeTareaInstance?.pago}">
				<li class="fieldcontain">
					<span id="pago-label" class="property-label"><g:message code="solicitudDeTarea.pago.label" default="Pago" /></span>
					
						<span class="property-value" aria-labelledby="pago-label"><g:link controller="pago" action="show" id="${solicitudDeTareaInstance?.pago?.id}">${solicitudDeTareaInstance?.pago?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeTareaInstance?.proyecto}">
				<li class="fieldcontain">
					<span id="proyecto-label" class="property-label"><g:message code="solicitudDeTarea.proyecto.label" default="Proyecto" /></span>
					
						<span class="property-value" aria-labelledby="proyecto-label"><g:link controller="proyecto" action="show" id="${solicitudDeTareaInstance?.proyecto?.id}">${solicitudDeTareaInstance?.proyecto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDeTareaInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="solicitudDeTarea.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:link controller="estadoSolicitudTarea" action="show" id="${solicitudDeTareaInstance?.estado?.id}">${solicitudDeTareaInstance?.estado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudDeTareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudDeTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
