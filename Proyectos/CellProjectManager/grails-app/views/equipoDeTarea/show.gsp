
<%@ page import="business.tarea.EquipoDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-equipoDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-equipoDeTarea" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list equipoDeTarea">
			
				<g:if test="${equipoDeTareaInstance?.numeroSerie}">
				<li class="fieldcontain">
					<span id="numeroSerie-label" class="property-label"><g:message code="equipoDeTarea.numeroSerie.label" default="Numero Serie" /></span>
					
						<span class="property-value" aria-labelledby="numeroSerie-label"><g:fieldValue bean="${equipoDeTareaInstance}" field="numeroSerie"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${equipoDeTareaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="equipoDeTarea.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${equipoDeTareaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${equipoDeTareaInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="equipoDeTarea.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:link controller="tipoEquipoDeTarea" action="show" id="${equipoDeTareaInstance?.tipo?.id}">${equipoDeTareaInstance?.tipo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
							
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${equipoDeTareaInstance?.id}" />
					<g:link class="edit" action="edit" id="${equipoDeTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
