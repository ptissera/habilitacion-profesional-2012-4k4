
<%@ page import="business.tarea.EquipoDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-equipoDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-equipoDeTarea" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numeroSerie" title="${message(code: 'equipoDeTarea.numeroSerie.label', default: 'Numero Serie')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'equipoDeTarea.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="equipoDeTarea.tipo.label" default="Tipo" /></th>
					
						<th><g:message code="equipoDeTarea.tarea.label" default="Tareas Por Sitio" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${equipoDeTareaInstanceList}" status="i" var="equipoDeTareaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${equipoDeTareaInstance.id}">${fieldValue(bean: equipoDeTareaInstance, field: "numeroSerie")}</g:link></td>
					
						<td>${fieldValue(bean: equipoDeTareaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: equipoDeTareaInstance, field: "tipo")}</td>
					
						<td>${fieldValue(bean: equipoDeTareaInstance, field: "tarea")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${equipoDeTareaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
