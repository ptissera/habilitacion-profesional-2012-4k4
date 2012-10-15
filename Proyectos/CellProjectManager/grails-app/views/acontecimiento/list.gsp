
<%@ page import="business.tarea.Acontecimiento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'acontecimiento.label', default: 'Acontecimiento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-acontecimiento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-acontecimiento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fechaCreacion" title="${message(code: 'acontecimiento.fechaCreacion.label', default: 'Fecha Creacion')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'acontecimiento.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="acontecimiento.tipoAcontecimiento.label" default="Tipo Acontecimiento" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${acontecimientoInstanceList}" status="i" var="acontecimientoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${acontecimientoInstance.id}">${fieldValue(bean: acontecimientoInstance, field: "fechaCreacion")}</g:link></td>
					
						<td>${fieldValue(bean: acontecimientoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: acontecimientoInstance, field: "tipoAcontecimiento")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${acontecimientoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
