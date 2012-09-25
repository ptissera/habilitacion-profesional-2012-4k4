
<%@ page import="business.solicitud.SolicitudDeViaticos" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDeViaticos.label', default: 'SolicitudDeViaticos')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudDeViaticos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudDeViaticos" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudDeViaticos.solicitud.label" default="Solicitud" /></th>
					
						<g:sortableColumn property="fechaCreacion" title="${message(code: 'solicitudDeViaticos.fechaCreacion.label', default: 'Fecha Creacion')}" />
					
						<g:sortableColumn property="fechaPago" title="${message(code: 'solicitudDeViaticos.fechaPago.label', default: 'Fecha Pago')}" />
					
						<g:sortableColumn property="monto" title="${message(code: 'solicitudDeViaticos.monto.label', default: 'Monto')}" />
					
						<g:sortableColumn property="observaciones" title="${message(code: 'solicitudDeViaticos.observaciones.label', default: 'Observaciones')}" />
					
						<th><g:message code="solicitudDeViaticos.estado.label" default="Estado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudDeViaticosInstanceList}" status="i" var="solicitudDeViaticosInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudDeViaticosInstance.id}">${fieldValue(bean: solicitudDeViaticosInstance, field: "solicitud")}</g:link></td>
					
						<td><g:formatDate date="${solicitudDeViaticosInstance.fechaCreacion}" /></td>
					
						<td><g:formatDate date="${solicitudDeViaticosInstance.fechaPago}" /></td>
					
						<td>${fieldValue(bean: solicitudDeViaticosInstance, field: "monto")}</td>
					
						<td>${fieldValue(bean: solicitudDeViaticosInstance, field: "observaciones")}</td>
					
						<td>${fieldValue(bean: solicitudDeViaticosInstance, field: "estado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudDeViaticosInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
