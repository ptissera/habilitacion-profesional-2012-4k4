
<%@ page import="business.tarea.TipoMaterialDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoMaterialDeTarea.label', default: 'TipoMaterialDeTarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoMaterialDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoMaterialDeTarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoMaterialDeTarea">
			
				<g:if test="${tipoMaterialDeTareaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="tipoMaterialDeTarea.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${tipoMaterialDeTareaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoMaterialDeTareaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipoMaterialDeTarea.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoMaterialDeTareaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoMaterialDeTareaInstance?.materiales}">
				<li class="fieldcontain">
					<span id="materiales-label" class="property-label"><g:message code="tipoMaterialDeTarea.materiales.label" default="Materiales" /></span>
					
						<g:each in="${tipoMaterialDeTareaInstance.materiales}" var="m">
						<span class="property-value" aria-labelledby="materiales-label"><g:link controller="materialDeTarea" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoMaterialDeTareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoMaterialDeTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
