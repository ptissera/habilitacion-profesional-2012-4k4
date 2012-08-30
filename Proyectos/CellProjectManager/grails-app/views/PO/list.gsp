
<%@ page import="business.core.PO" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'PO.label', default: 'PO')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-PO" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-PO" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fechaRecibida" title="${message(code: 'PO.fechaRecibida.label', default: 'Fecha Recibida')}" />
					
						<g:sortableColumn property="monto" title="${message(code: 'PO.monto.label', default: 'Monto')}" />
					
						<g:sortableColumn property="esExtra" title="${message(code: 'PO.esExtra.label', default: 'Es Extra')}" />
															
						<g:sortableColumn property="archivo" title="${message(code: 'PO.archivo.label', default: 'Archivo')}" />
					
						<th><g:message code="PO.cobro.label" default="Cobro" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${POInstanceList}" status="i" var="POInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${POInstance.id}"><g:formatDate format="dd/MM/yyyy" date="${POInstance.fechaRecibida}" /></g:link></td>
					
						<td>${fieldValue(bean: POInstance, field: "monto")}</td>
					
						<td><g:formatBoolean boolean="${POInstance.esExtra}" /></td>
					
						<td>${fieldValue(bean: POInstance, field: "nombreArchivo")}</td>
																
						<td>${fieldValue(bean: POInstance, field: "cobro")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${POInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
