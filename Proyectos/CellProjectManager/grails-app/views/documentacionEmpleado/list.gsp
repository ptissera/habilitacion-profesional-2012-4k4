
<%@ page import="business.cuadrillas.DocumentacionEmpleado" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-documentacionEmpleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-documentacionEmpleado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="documentacionEmpleado.tipoDocumento.label" default="Tipo Documento" /></th>
					
						<g:sortableColumn property="vigenciaDesde" title="${message(code: 'documentacionEmpleado.vigenciaDesde.label', default: 'Vigencia Desde')}" />
					
						<g:sortableColumn property="vigenciaHasta" title="${message(code: 'documentacionEmpleado.vigenciaHasta.label', default: 'Vigencia Hasta')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'documentacionEmpleado.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="documentacionEmpleado.empleado.label" default="Empleado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentacionEmpleadoInstanceList}" status="i" var="documentacionEmpleadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentacionEmpleadoInstance.id}">${fieldValue(bean: documentacionEmpleadoInstance, field: "tipoDocumento")}</g:link></td>
					
						<td><g:formatDate date="${documentacionEmpleadoInstance.vigenciaDesde}" /></td>
					
						<td><g:formatDate date="${documentacionEmpleadoInstance.vigenciaHasta}" /></td>
					
						<td>${fieldValue(bean: documentacionEmpleadoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: documentacionEmpleadoInstance, field: "empleado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentacionEmpleadoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
