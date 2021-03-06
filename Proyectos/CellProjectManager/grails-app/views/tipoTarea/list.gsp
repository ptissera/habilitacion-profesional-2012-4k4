
<%@ page import="business.tarea.TipoTarea" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoTarea.label', default: 'TipoTarea')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoTarea" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'tipoTarea.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tipoTarea.descripcion.label', default: 'Descripcion')}" />
                                                
                                                <g:sortableColumn property="descripcion" title="${message(code: 'tipoTarea.requierePermisoDeAcceso.label', default: 'Requiere Permiso De Acceso')}" />
                                                
                                                <g:sortableColumn property="descripcion" title="${message(code: 'tipoTarea.requiereIngenieria.label', default: 'Requiere Ingenieria')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoTareaInstanceList}" status="i" var="tipoTareaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoTareaInstance.id}">${fieldValue(bean: tipoTareaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: tipoTareaInstance, field: "descripcion")}</td>
                                                
                                                <td><g:formatBoolean boolean="${tipoTareaInstance.requierePermisoDeAcceso}" /></td>
                                                
                                                <td><g:formatBoolean boolean="${tipoTareaInstance.requiereIngenieria}" /></td>
										
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoTareaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
