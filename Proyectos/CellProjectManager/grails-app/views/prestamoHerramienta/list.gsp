
<%@ page import="business.herramienta.PrestamoHerramienta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-prestamoHerramienta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-prestamoHerramienta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fechaPrestamo" title="${message(code: 'prestamoHerramienta.fechaPrestamo.label', default: 'Fecha Prestamo')}" />
					
						<g:sortableColumn property="fechaDevolucion" title="${message(code: 'prestamoHerramienta.fechaDevolucion.label', default: 'Fecha Devolucion')}" />
					
						<th><g:message code="prestamoHerramienta.herramienta.label" default="Herramienta" /></th>
					
						<th><g:message code="prestamoHerramienta.cuadrilla.label" default="Cuadrilla" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'prestamoHerramienta.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${prestamoHerramientaInstanceList}" status="i" var="prestamoHerramientaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${prestamoHerramientaInstance.id}">${fieldValue(bean: prestamoHerramientaInstance, field: "fechaPrestamo")}</g:link></td>
					
						<td><g:formatDate date="${prestamoHerramientaInstance.fechaDevolucion}" /></td>
					
						<td>${fieldValue(bean: prestamoHerramientaInstance, field: "herramienta")}</td>
					
						<td>${fieldValue(bean: prestamoHerramientaInstance, field: "cuadrilla")}</td>
					
						<td>${fieldValue(bean: prestamoHerramientaInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${prestamoHerramientaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
