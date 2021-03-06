
<%@ page import="business.cuadrillas.TipoDocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoDocumentacionIntegranteCuadrilla.label', default: 'TipoDocumentacionIntegranteCuadrilla')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoDocumentacionIntegranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoDocumentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'tipoDocumentacionIntegranteCuadrilla.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tipoDocumentacionIntegranteCuadrilla.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="diaAntesVencimiento" title="${message(code: 'tipoDocumentacionIntegranteCuadrilla.diaAntesVencimiento.label', default: 'Dia Antes Vencimiento')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoDocumentacionIntegranteCuadrillaInstanceList}" status="i" var="tipoDocumentacionIntegranteCuadrillaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoDocumentacionIntegranteCuadrillaInstance.id}">${fieldValue(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: "diaAntesVencimiento")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoDocumentacionIntegranteCuadrillaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
