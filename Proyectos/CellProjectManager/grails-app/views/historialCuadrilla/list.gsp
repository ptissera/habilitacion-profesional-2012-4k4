
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
					
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${historialCuadrillaInstanceList}" status="i" var="historialCuadrillaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:formatDate format="dd/MM/yyyy" date="${historialCuadrillaInstance.fecha}" /></td>
					
						<td>${fieldValue(bean: historialCuadrillaInstance, field: "descripcion")}</td>
					
						
					
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
