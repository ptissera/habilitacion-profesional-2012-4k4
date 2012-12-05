
<%@ page import="business.documento.Documento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-documento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list documento">
			
                          <g:if test="${documentoInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="documento.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label">${documentoInstance?.tipo?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
                          
				
				<g:if test="${documentoInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="documento.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${documentoInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.fechaRealizado}">
				<li class="fieldcontain">
					<span id="fechaRealizado-label" class="property-label"><g:message code="documento.fechaRealizado.label" default="Fecha Realizado" /></span>
					
						<span class="property-value" aria-labelledby="fechaRealizado-label"><g:formatDate date="${documentoInstance?.fechaRealizado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.fechaEnviado}">
				<li class="fieldcontain">
					<span id="fechaEnviado-label" class="property-label"><g:message code="documento.fechaEnviado.label" default="Fecha Enviado" /></span>
					
						<span class="property-value" aria-labelledby="fechaEnviado-label"><g:formatDate date="${documentoInstance?.fechaEnviado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.fechaAprobado}">
				<li class="fieldcontain">
					<span id="fechaAprobado-label" class="property-label"><g:message code="documento.fechaAprobado.label" default="Fecha Aprobado" /></span>
					
						<span class="property-value" aria-labelledby="fechaAprobado-label"><g:formatDate date="${documentoInstance?.fechaAprobado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.nombreArchivo}">
				<li class="fieldcontain">
					<span id="nombreArchivo-label" class="property-label"><g:message code="documento.archivo.label" default="Archivo" /></span>
                                            
						<span class="property-value" aria-labelledby="nombreArchivo-label">
                                                  <g:link action="downloadFile" id="${documentoInstance?.id}"> <g:fieldValue bean="${documentoInstance}" field="nombreArchivo"/></g:link>
                                                </span>
					
				</li>
				</g:if>
										
				<g:if test="${documentoInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="documento.estado.label" default="Estado" /></span>
					
					        <span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${documentoInstance}" field="estado"/></span>
				</li>
				</g:if>
			
			</ol>
			<g:form>
                          <g:if test="${documentoInstance?.hasEstadoEnviado()}">
                                <fieldset class="buttons_add">
                                        <g:actionSubmit class="aprobar" action="aprobarDocumento" value="${message(code: 'default.button.aprobarDocumento.label', default: 'Aprobar')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.aprobarDocumento.confirm.message', default: 'Confirma aprobacion?')}');" />
                                        <g:actionSubmit class="cancelar" action="desaprobarDocumento" value="${message(code: 'default.button.desaprobarDocumento.label', default: 'Incompleto')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.desaprobarDocumento.confirm.message', default: 'Confirma estado incompleto?')}');" />
                                </fieldset>
                            </g:if>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${documentoInstance?.id}" />
					<g:link class="edit" action="edit" id="${documentoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
