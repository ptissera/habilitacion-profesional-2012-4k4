
<%@ page import="business.documento.Documento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-documento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="documento.tarea.label" default="Tarea" /></th>
					
						<g:sortableColumn property="observaciones" title="${message(code: 'documento.observaciones.label', default: 'Observaciones')}" />
					
						<g:sortableColumn property="fechaRealizado" title="${message(code: 'documento.fechaRealizado.label', default: 'Fecha Realizado')}" />
					
						<g:sortableColumn property="fechaEnviado" title="${message(code: 'documento.fechaEnviado.label', default: 'Fecha Enviado')}" />
					
						<g:sortableColumn property="fechaAprobado" title="${message(code: 'documento.fechaAprobado.label', default: 'Fecha Aprobado')}" />
					
						<g:sortableColumn property="nombreArchivo" title="${message(code: 'documento.nombreArchivo.label', default: 'Nombre Archivo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentoInstanceList}" status="i" var="documentoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentoInstance.id}">${fieldValue(bean: documentoInstance, field: "tarea")}</g:link></td>
					
						<td>${fieldValue(bean: documentoInstance, field: "observaciones")}</td>
					
						<td><g:formatDate date="${documentoInstance.fechaRealizado}" /></td>
					
						<td><g:formatDate date="${documentoInstance.fechaEnviado}" /></td>
					
						<td><g:formatDate date="${documentoInstance.fechaAprobado}" /></td>
					
						<td>${fieldValue(bean: documentoInstance, field: "nombreArchivo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
