
<%@ page import="business.tarea.Tarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tarea.label', default: 'Tarea')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tarea" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tarea.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="monto" title="${message(code: 'tarea.monto.label', default: 'Monto')}" />
					
						<g:sortableColumn property="observaciones" title="${message(code: 'tarea.observaciones.label', default: 'Observaciones')}" />
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'tarea.fechaAlta.label', default: 'Fecha Alta')}" />
					
						<th><g:message code="tarea.estado.label" default="Estado" /></th>
					
						<th><g:message code="tarea.tipo.label" default="Tipo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tareaInstanceList}" status="i" var="tareaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tareaInstance.id}">${fieldValue(bean: tareaInstance, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: tareaInstance, field: "monto")}</td>
					
						<td>${fieldValue(bean: tareaInstance, field: "observaciones")}</td>
					
						<td><g:formatDate date="${tareaInstance.fechaAlta}" /></td>
					
						<td>${fieldValue(bean: tareaInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: tareaInstance, field: "tipo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tareaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
