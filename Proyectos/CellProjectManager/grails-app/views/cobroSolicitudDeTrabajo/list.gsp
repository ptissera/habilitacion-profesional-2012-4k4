
<%@ page import="business.solicitud.CobroSolicitudDeTrabajo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cobroSolicitudDeTrabajo.label', default: 'CobroSolicitudDeTrabajo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cobroSolicitudDeTrabajo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cobroSolicitudDeTrabajo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="cobroSolicitudDeTrabajo.solicitud.label" default="Solicitud" /></th>
					
						<g:sortableColumn property="fechaCobro" title="${message(code: 'cobroSolicitudDeTrabajo.fechaCobro.label', default: 'Fecha Cobro')}" />
					
						<g:sortableColumn property="monto" title="${message(code: 'cobroSolicitudDeTrabajo.monto.label', default: 'Monto')}" />
					
						<g:sortableColumn property="observaciones" title="${message(code: 'cobroSolicitudDeTrabajo.observaciones.label', default: 'Observaciones')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cobroSolicitudDeTrabajoInstanceList}" status="i" var="cobroSolicitudDeTrabajoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cobroSolicitudDeTrabajoInstance.id}">${fieldValue(bean: cobroSolicitudDeTrabajoInstance, field: "solicitud")}</g:link></td>
					
						<td><g:formatDate date="${cobroSolicitudDeTrabajoInstance.fechaCobro}" /></td>
					
						<td>${fieldValue(bean: cobroSolicitudDeTrabajoInstance, field: "monto")}</td>
					
						<td>${fieldValue(bean: cobroSolicitudDeTrabajoInstance, field: "observaciones")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cobroSolicitudDeTrabajoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
