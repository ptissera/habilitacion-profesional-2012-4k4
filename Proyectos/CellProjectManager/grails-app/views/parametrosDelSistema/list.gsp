
<%@ page import="support.tool.ParametrosDelSistema" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'parametrosDelSistema.label', default: 'ParametrosDelSistema')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-parametrosDelSistema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-parametrosDelSistema" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'parametrosDelSistema.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'parametrosDelSistema.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="valor" title="${message(code: 'parametrosDelSistema.valor.label', default: 'Valor')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${parametrosDelSistemaInstanceList}" status="i" var="parametrosDelSistemaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${parametrosDelSistemaInstance.id}">${fieldValue(bean: parametrosDelSistemaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: parametrosDelSistemaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: parametrosDelSistemaInstance, field: "valor")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${parametrosDelSistemaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
