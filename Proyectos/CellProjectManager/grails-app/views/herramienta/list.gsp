
<%@ page import="business.herramienta.Herramienta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'herramienta.label', default: 'Herramienta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-herramienta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-herramienta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numeroDeSerie" title="${message(code: 'herramienta.numeroDeSerie.label', default: 'Numero De Serie')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'herramienta.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'herramienta.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="herramienta.estado.label" default="Estado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${herramientaInstanceList}" status="i" var="herramientaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${herramientaInstance.id}">${fieldValue(bean: herramientaInstance, field: "numeroDeSerie")}</g:link></td>
					
						<td>${fieldValue(bean: herramientaInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: herramientaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: herramientaInstance, field: "estado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${herramientaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
