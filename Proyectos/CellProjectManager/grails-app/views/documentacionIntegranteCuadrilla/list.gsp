
<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-documentacionIntegranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-documentacionIntegranteCuadrilla" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="vigenciaDesde" title="${message(code: 'documentacionIntegranteCuadrilla.vigenciaDesde.label', default: 'Vigencia Desde')}" />
					
						<g:sortableColumn property="vigenciaHasta" title="${message(code: 'documentacionIntegranteCuadrilla.vigenciaHasta.label', default: 'Vigencia Hasta')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'documentacionIntegranteCuadrilla.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="documentacionIntegranteCuadrilla.tipoDocumento.label" default="Tipo Documento" /></th>
					
						<th><g:message code="documentacionIntegranteCuadrilla.integrante.label" default="Integrante" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentacionIntegranteCuadrillaInstanceList}" status="i" var="documentacionIntegranteCuadrillaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentacionIntegranteCuadrillaInstance.id}">${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "vigenciaDesde")}</g:link></td>
					
						<td><g:formatDate format="dd/MM/yyyy" date="${documentacionIntegranteCuadrillaInstance.vigenciaHasta}" /></td>
					
						<td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "tipoDocumento")}</td>
					
						<td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "integrante")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentacionIntegranteCuadrillaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
