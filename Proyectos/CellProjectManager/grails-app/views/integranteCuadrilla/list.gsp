
<%@ page import="business.cuadrillas.IntegranteCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-integranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-integranteCuadrilla" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="du" title="${message(code: 'integranteCuadrilla.du.label', default: 'Du')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'integranteCuadrilla.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="apellido" title="${message(code: 'integranteCuadrilla.apellido.label', default: 'Apellido')}" />
					
						<g:sortableColumn property="legajo" title="${message(code: 'integranteCuadrilla.legajo.label', default: 'Legajo')}" />
					
						<g:sortableColumn property="telefono" title="${message(code: 'integranteCuadrilla.telefono.label', default: 'Telefono')}" />
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'integranteCuadrilla.fechaAlta.label', default: 'Fecha Alta')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${integranteCuadrillaInstanceList}" status="i" var="integranteCuadrillaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${integranteCuadrillaInstance.id}">${fieldValue(bean: integranteCuadrillaInstance, field: "du")}</g:link></td>
					
						<td>${fieldValue(bean: integranteCuadrillaInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: integranteCuadrillaInstance, field: "apellido")}</td>
					
						<td>${fieldValue(bean: integranteCuadrillaInstance, field: "legajo")}</td>
					
						<td>${fieldValue(bean: integranteCuadrillaInstance, field: "telefono")}</td>
					
						<td><g:formatDate date="${integranteCuadrillaInstance.fechaAlta}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${integranteCuadrillaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
