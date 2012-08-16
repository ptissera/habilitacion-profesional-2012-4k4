
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proyecto.label', default: 'Proyecto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-proyecto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>		
		<div id="list-proyecto" class="content scaffold-list" role="main">
			<h1>Seleccionar Proyecto</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="licitacion" title="${message(code: 'proyecto.licitacion.label', default: 'Licitacion')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'proyecto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'proyecto.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fechaCreacion" title="${message(code: 'proyecto.fechaCreacion.label', default: 'Fecha Creacion')}" />
					
						<g:sortableColumn property="fechaInicio" title="${message(code: 'proyecto.fechaInicio.label', default: 'Fecha Inicio')}" />
					
						<g:sortableColumn property="fechaFin" title="${message(code: 'proyecto.fechaFin.label', default: 'Fecha Fin')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${proyectoInstanceList}" status="i" var="proyectoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="selectedProject" id="${proyectoInstance.id}" onclick="return confirm('¿Confirma Seleccion de proyecto?');">${fieldValue(bean: proyectoInstance, field: "licitacion")}</g:link></td>
					
						<td>${fieldValue(bean: proyectoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: proyectoInstance, field: "descripcion")}</td>
					
						<td><g:formatDate  format="dd/MM/yyyy" date="${proyectoInstance.fechaCreacion}" /></td>
					
						<td><g:formatDate  format="dd/MM/yyyy" date="${proyectoInstance.fechaInicio}" /></td>
					
						<td><g:formatDate  format="dd/MM/yyyy" date="${proyectoInstance.fechaFin}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${proyectoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
