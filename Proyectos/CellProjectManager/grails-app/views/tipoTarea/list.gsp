
<%@ page import="business.tarea.TipoTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoTarea.label', default: 'TipoTarea')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoTarea" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'tipoTarea.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="complejidad" title="${message(code: 'tipoTarea.complejidad.label', default: 'Complejidad')}" />
					
						<g:sortableColumn property="duracionEstimada" title="${message(code: 'tipoTarea.duracionEstimada.label', default: 'Duracion Estimada')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tipoTarea.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoTareaInstanceList}" status="i" var="tipoTareaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoTareaInstance.id}">${fieldValue(bean: tipoTareaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: tipoTareaInstance, field: "complejidad")}</td>
					
						<td>${fieldValue(bean: tipoTareaInstance, field: "duracionEstimada")}</td>
					
						<td>${fieldValue(bean: tipoTareaInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoTareaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
