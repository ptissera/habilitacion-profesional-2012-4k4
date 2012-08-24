
<%@ page import="business.tarea.TareasPorSitio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tareasPorSitio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tareasPorSitio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="ordenEjecucion" title="${message(code: 'tareasPorSitio.ordenEjecucion.label', default: 'Orden Ejecucion')}" />
					
						<g:sortableColumn property="fechaInicio" title="${message(code: 'tareasPorSitio.fechaInicio.label', default: 'Fecha Inicio')}" />
					
						<th><g:message code="tareasPorSitio.sitio.label" default="Sitio" /></th>
					
						<th><g:message code="tareasPorSitio.tarea.label" default="Tarea" /></th>
					
						<th><g:message code="tareasPorSitio.estado.label" default="Estado" /></th>
					
						<th><g:message code="tareasPorSitio.solicitudDeTarea.label" default="Solicitud De Tarea" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tareasPorSitioInstanceList}" status="i" var="tareasPorSitioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tareasPorSitioInstance.id}">${fieldValue(bean: tareasPorSitioInstance, field: "ordenEjecucion")}</g:link></td>
					
						<td><g:formatDate date="${tareasPorSitioInstance.fechaInicio}" /></td>
					
						<td>${fieldValue(bean: tareasPorSitioInstance, field: "sitio")}</td>
					
						<td>${fieldValue(bean: tareasPorSitioInstance, field: "tarea")}</td>
					
						<td>${fieldValue(bean: tareasPorSitioInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: tareasPorSitioInstance, field: "solicitudDeTarea")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tareasPorSitioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
