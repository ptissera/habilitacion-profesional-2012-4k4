
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuadrilla.label', default: 'Cuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>				
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                                <li><g:link class="list" action="list" controller="historialCuadrilla" id="${cuadrillaInstance?.id}">Historial Cuadrilla</g:link></li>
			</ul>
		</div>
		<div id="show-cuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cuadrilla">
			
				<g:if test="${cuadrillaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="cuadrilla.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${cuadrillaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="cuadrilla.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${cuadrillaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.propia}">
				<li class="fieldcontain">
					<span id="propia-label" class="property-label"><g:message code="cuadrilla.propia.label" default="Propia" /></span>
					
						<span class="property-value" aria-labelledby="propia-label"><g:checkBox name="propia" value="${cuadrillaInstance?.propia}"  style="display: block"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.estadoCuadrilla}">
				<li class="fieldcontain">
					<span id="estadoCuadrilla-label" class="property-label"><g:message code="cuadrilla.estadoCuadrilla.label" default="Estado Cuadrilla" /></span>
					
						<span class="property-value" aria-labelledby="estadoCuadrilla-label"><g:link controller="estadoCuadrilla" action="show" id="${cuadrillaInstance?.estadoCuadrilla?.id}">${cuadrillaInstance?.estadoCuadrilla?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
		      <g:if test="${cuadrillaInstance?.operarios}">
                        <li class="fieldcontain">
                          <table>
                                  <thead>
                                          <tr><g:message code="cuadrilla.operarios.label" default="Operarios" /></tr>
                                          <tr>					
                                                  <g:sortableColumn property="du" title="${message(code: 'empleado.du.label', default: 'Du')}" />					
                                                  <g:sortableColumn property="nombre" title="${message(code: 'empleado.nombre.label', default: 'Nombre')}" />					
                                                  <g:sortableColumn property="apellido" title="${message(code: 'empleado.apellido.label', default: 'Apellido')}" />					
                                                  <g:sortableColumn property="legajo" title="${message(code: 'empleado.legajo.label', default: 'Legajo')}" />					
                                                  <g:sortableColumn property="telefono" title="${message(code: 'empleado.telefono.label', default: 'Telefono')}" />					
                                                  <g:sortableColumn property="telefono" title="Estado" />
                                          </tr>
                                  </thead>
                                  <tbody>
                                  <g:each in="${cuadrillaInstance?.operarios}" status="i" var="integranteCuadrillaInstance">
                                          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                                                  <td><g:link action="show" controller="integranteCuadrilla" id="${integranteCuadrillaInstance.id}">${fieldValue(bean: integranteCuadrillaInstance, field: "du")}</g:link></td>					
                                                  <td>${fieldValue(bean: integranteCuadrillaInstance, field: "nombre")}</td>					
                                                  <td>${fieldValue(bean: integranteCuadrillaInstance, field: "apellido")}</td>					
                                                  <td>${fieldValue(bean: integranteCuadrillaInstance, field: "legajo")}</td>					
                                                  <td>${fieldValue(bean: integranteCuadrillaInstance, field: "telefono")}</td>					
                                                  <td><g:img uri="${integranteCuadrillaInstance.estadoDocumentacionIcon()}" /></td>
                                          </tr>
                                  </g:each>
                                  </tbody>
                          </table>
                        </li>
                      </g:if>

			
			</ol>
			<g:form>
				<fieldset class="buttons_add">					
                                        <g:hiddenField name="id" value="${cuadrillaInstance?.id}" />
                                        <g:link class="add" action="create" controller="integranteCuadrilla"><g:message code="default.add.label" args="[message(code: 'empleado.label', default: 'Operario')]"/></g:link>
				</fieldset>
                          <fieldset class="buttons">					                                       
                                        <g:link class="edit" action="edit" id="${cuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />                                        
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
