
<%@ page import="business.solicitud.SolicitudPagoCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudPagoCuadrilla.label', default: 'SolicitudPagoCuadrilla')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudPagoCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudPagoCuadrilla" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudPagoCuadrilla.solicitud.label" default="Solicitud" /></th>
					
						<g:sortableColumn property="fechaCreacion" title="${message(code: 'solicitudPagoCuadrilla.fechaCreacion.label', default: 'Fecha Creacion')}" />
					
						<g:sortableColumn property="fechaPago" title="${message(code: 'solicitudPagoCuadrilla.fechaPago.label', default: 'Fecha Pago')}" />
					
						<g:sortableColumn property="porcentaje" title="${message(code: 'solicitudPagoCuadrilla.porcentaje.label', default: 'Porcentaje')}" />
					
						<g:sortableColumn property="monto" title="${message(code: 'solicitudPagoCuadrilla.monto.label', default: 'Monto')}" />
					
						<g:sortableColumn property="observaciones" title="${message(code: 'solicitudPagoCuadrilla.observaciones.label', default: 'Observaciones')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudPagoCuadrillaInstanceList}" status="i" var="solicitudPagoCuadrillaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudPagoCuadrillaInstance.id}">${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "solicitud")}</g:link></td>
					
						<td><g:formatDate date="${solicitudPagoCuadrillaInstance.fechaCreacion}" /></td>
					
						<td><g:formatDate date="${solicitudPagoCuadrillaInstance.fechaPago}" /></td>
					
						<td>${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "porcentaje")}</td>
					
						<td>${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "monto")}</td>
					
						<td>${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "observaciones")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudPagoCuadrillaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
