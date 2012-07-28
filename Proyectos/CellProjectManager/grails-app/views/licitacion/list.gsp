
<%@ page import="business.core.Licitacion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'licitacion.label', default: 'Licitacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-licitacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-licitacion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'licitacion.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'licitacion.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="licitacion.cliente.label" default="Cliente" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${licitacionInstanceList}" status="i" var="licitacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${licitacionInstance.id}">${fieldValue(bean: licitacionInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: licitacionInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: licitacionInstance, field: "cliente")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${licitacionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
