
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
					
						<g:sortableColumn property="ordenEjecucion" title="${message(code: 'tarea.ordenEjecucion.label', default: 'Orden Ejecucion')}" />
					
						<g:sortableColumn property="fechaInicio" title="${message(code: 'tarea.fechaInicio.label', default: 'Fecha Inicio')}" />
					
						<th><g:message code="tarea.sitio.label" default="Sitio" /></th>
					
						<th><g:message code="tarea.tarea.label" default="Tarea" /></th>
					
						<th><g:message code="tarea.estado.label" default="Estado" /></th>
					
						<th><g:message code="tarea.solicitudDeTarea.label" default="Solicitud De Tarea" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tareaInstanceList}" status="i" var="tareaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tareaInstance.id}">${fieldValue(bean: tareaInstance, field: "ordenEjecucion")}</g:link></td>
					
						<td><g:formatDate format="dd/MM/yyyy" date="${tareaInstance.fechaInicio}" /></td>
					
						<td>${fieldValue(bean: tareaInstance, field: "sitio")}</td>
					
						<td>${fieldValue(bean: tareaInstance, field: "tarea")}</td>
					
						<td>${fieldValue(bean: tareaInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: tareaInstance, field: "solicitudDeTarea")}</td>
					
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
