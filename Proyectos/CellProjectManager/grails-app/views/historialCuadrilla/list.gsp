
<%@ page import="business.cuadrillas.HistorialCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-historialCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-historialCuadrilla" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fecha" title="${message(code: 'historialCuadrilla.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'historialCuadrilla.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="historialCuadrilla.cuadrilla.label" default="Cuadrilla" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${historialCuadrillaInstanceList}" status="i" var="historialCuadrillaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${historialCuadrillaInstance.id}">${fieldValue(bean: historialCuadrillaInstance, field: "fecha")}</g:link></td>
					
						<td>${fieldValue(bean: historialCuadrillaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: historialCuadrillaInstance, field: "cuadrilla")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${historialCuadrillaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>