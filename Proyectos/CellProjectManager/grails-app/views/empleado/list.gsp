
<%@ page import="business.cuadrillas.Empleado" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleado.label', default: 'Empleado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-empleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-empleado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="du" title="${message(code: 'empleado.du.label', default: 'Du')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'empleado.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="apellido" title="${message(code: 'empleado.apellido.label', default: 'Apellido')}" />
					
						<g:sortableColumn property="legajo" title="${message(code: 'empleado.legajo.label', default: 'Legajo')}" />
					
						<g:sortableColumn property="telefono" title="${message(code: 'empleado.telefono.label', default: 'Telefono')}" />
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'empleado.fechaAlta.label', default: 'Fecha Alta')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${empleadoInstanceList}" status="i" var="empleadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${empleadoInstance.id}">${fieldValue(bean: empleadoInstance, field: "du")}</g:link></td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "apellido")}</td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "legajo")}</td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "telefono")}</td>
					
						<td><g:formatDate date="${empleadoInstance.fechaAlta}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${empleadoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
