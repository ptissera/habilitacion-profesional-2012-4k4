
<%@ page import="business.core.PO" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'PO.label', default: 'PO')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-PO" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-PO" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list PO">
			
				<g:if test="${POInstance?.fechaRecibida}">
				<li class="fieldcontain">
					<span id="fechaRecibida-label" class="property-label"><g:message code="PO.fechaRecibida.label" default="Fecha Recibida" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibida-label"><g:formatDate format="dd/MM/yyyy"  date="${POInstance?.fechaRecibida}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.monto}">
				<li class="fieldcontain">
					<span id="monto-label" class="property-label"><g:message code="PO.monto.label" default="Monto" /></span>
					
						<span class="property-value" aria-labelledby="monto-label"><g:fieldValue bean="${POInstance}" field="monto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.esExtra}">
				<li class="fieldcontain">
					<span id="esExtra-label" class="property-label"><g:message code="PO.esExtra.label" default="Es Extra" /></span>
					
						<span class="property-value" aria-labelledby="esExtra-label"><g:formatBoolean boolean="${POInstance?.esExtra}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${POInstance?.nombreArchivo}">
				<li class="fieldcontain">
					<span id="nombreArchivo-label" class="property-label"><g:message code="PO.archivo.label" default="Archivo" /></span>
					
						<span class="property-value" aria-labelledby="nombreArchivo-label">
                                                  <g:link action="downloadFile" id="${POInstance?.id}"> <g:fieldValue bean="${POInstance}" field="nombreArchivo"/></g:link>
                                                  
                                                </span>
					
				</li>
				</g:if>
			
				
									
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${POInstance?.id}" />
					<g:link class="edit" action="edit" id="${POInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
