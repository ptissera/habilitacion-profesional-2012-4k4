
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuadrilla.label', default: 'Cuadrilla')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>				
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cuadrilla" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'cuadrilla.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'cuadrilla.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="propia" title="${message(code: 'cuadrilla.propia.label', default: 'Propia')}" />
					
						<th><g:message code="cuadrilla.estadoCuadrilla.label" default="Estado Cuadrilla" /></th>
                                                
                                                <th><g:message code="cuadrilla.estadoCuadrilla.label" default="Estado Doc" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cuadrillaInstanceList}" status="i" var="cuadrillaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cuadrillaInstance.id}">${fieldValue(bean: cuadrillaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: cuadrillaInstance, field: "descripcion")}</td>
					
						<td><g:formatBoolean boolean="${cuadrillaInstance.propia}" /></td>
					
						<td>${fieldValue(bean: cuadrillaInstance, field: "estadoCuadrilla")}</td>
                                                
                                                <td><g:img uri="${cuadrillaInstance.estadoDocumentacionIcon()}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cuadrillaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
