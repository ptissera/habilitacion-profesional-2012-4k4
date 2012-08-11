
<%@ page import="business.tarea.SolicitudDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudDeTarea" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'solicitudDeTarea.fechaAlta.label', default: 'Fecha Alta')}" />
					
						<th><g:message code="solicitudDeTarea.pago.label" default="Pago" /></th>
					
						<th><g:message code="solicitudDeTarea.proyecto.label" default="Proyecto" /></th>
					
						<th><g:message code="solicitudDeTarea.estado.label" default="Estado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudDeTareaInstanceList}" status="i" var="solicitudDeTareaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudDeTareaInstance.id}">${fieldValue(bean: solicitudDeTareaInstance, field: "fechaAlta")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudDeTareaInstance, field: "pago")}</td>
					
						<td>${fieldValue(bean: solicitudDeTareaInstance, field: "proyecto")}</td>
					
						<td>${fieldValue(bean: solicitudDeTareaInstance, field: "estado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudDeTareaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
