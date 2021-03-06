
<%@ page import="business.tarea.TipoTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoTarea.label', default: 'TipoTarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoTarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoTarea">
			
				<g:if test="${tipoTareaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="tipoTarea.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${tipoTareaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoTareaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipoTarea.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoTareaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
                          
                          
				<li class="fieldcontain">
					<span id="requierePermisoDeAcceso-label" class="property-label"><g:message code="tipoTarea.requierePermisoDeAcceso.label" default="Requiere Permiso De Acceso" /></span>
					
						<span class="property-value" aria-labelledby="requierePermisoDeAcceso-label">
                                                  <g:checkBox name="requiereIngenieria" value="${tipoTareaInstance?.requierePermisoDeAcceso}"  /></span>
					
				</li>
			
                          
                          
				<li class="fieldcontain">
					<span id="requiereIngenieria-label" class="property-label"><g:message code="tipoTarea.requiereIngenieria.label" default="Requiere Ingenieria" /></span>
					
						<span class="property-value" aria-labelledby="requiereIngenieria-label">
                                                  <g:checkBox name="requiereIngenieria" value="${tipoTareaInstance?.requiereIngenieria}"  /></span>
					
				</li>
			
		
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoTareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
