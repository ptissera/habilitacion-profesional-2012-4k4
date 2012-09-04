
<%@ page import="business.core.PermisoAcceso" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'permisoAcceso.label', default: 'PermisoAcceso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-permisoAcceso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-permisoAcceso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fechaDesde" title="${message(code: 'permisoAcceso.fechaDesde.label', default: 'Fecha Desde')}" />
					
						<g:sortableColumn property="fechaHasta" title="${message(code: 'permisoAcceso.fechaHasta.label', default: 'Fecha Hasta')}" />
					
						<g:sortableColumn property="nombreArchivo" title="${message(code: 'permisoAcceso.nombreArchivo.label', default: 'Nombre Archivo')}" />
					
						<g:sortableColumn property="archivo" title="${message(code: 'permisoAcceso.archivo.label', default: 'Archivo')}" />
					
						<th><g:message code="permisoAcceso.tarea.label" default="Tareas Por Sitio" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${permisoAccesoInstanceList}" status="i" var="permisoAccesoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${permisoAccesoInstance.id}">${fieldValue(bean: permisoAccesoInstance, field: "fechaDesde")}</g:link></td>
					
						<td><g:formatDate date="${permisoAccesoInstance.fechaHasta}" /></td>
					
						<td>${fieldValue(bean: permisoAccesoInstance, field: "nombreArchivo")}</td>
					
						<td>${fieldValue(bean: permisoAccesoInstance, field: "archivo")}</td>
					
						<td>${fieldValue(bean: permisoAccesoInstance, field: "tarea")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${permisoAccesoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
