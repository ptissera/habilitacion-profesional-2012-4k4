
<%@ page import="business.tarea.MaterialDeTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialDeTarea.label', default: 'MaterialDeTarea')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-materialDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-materialDeTarea" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'materialDeTarea.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="cantidad" title="${message(code: 'materialDeTarea.cantidad.label', default: 'Cantidad')}" />
					
						<g:sortableColumn property="esDeCliente" title="${message(code: 'materialDeTarea.esDeCliente.label', default: 'Es De Cliente')}" />
					
						<th><g:message code="materialDeTarea.unidad.label" default="Unidad" /></th>
					
						<th><g:message code="materialDeTarea.tarea.label" default="Tarea" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${materialDeTareaInstanceList}" status="i" var="materialDeTareaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${materialDeTareaInstance.id}">${fieldValue(bean: materialDeTareaInstance, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: materialDeTareaInstance, field: "cantidad")}</td>
					
						<td><g:formatBoolean boolean="${materialDeTareaInstance.esDeCliente}" /></td>
					
						<td>${fieldValue(bean: materialDeTareaInstance, field: "unidad")}</td>
					
						<td>${fieldValue(bean: materialDeTareaInstance, field: "tarea")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${materialDeTareaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
