
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
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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

      <g:if test="${cuadrillaInstance?.estado}">
        <li class="fieldcontain">
          <span id="estado-label" class="property-label"><g:message code="cuadrilla.estado.label" default="Estado" /></span>

          <span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${cuadrillaInstance}" field="estado"/></span>

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
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'empleado.fechaAlta.label', default: 'Fecha Alta')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cuadrillaInstance?.operarios}" status="i" var="empleadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${empleadoInstance.id}">${fieldValue(bean: empleadoInstance, field: "du")}</g:link></td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "apellido")}</td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "legajo")}</td>
					
						<td>${fieldValue(bean: empleadoInstance, field: "telefono")}</td>
					
						<td><g:formatDate date="${empleadoInstance.fechaAlta}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
          
        </li>
      </g:if>

    </ol>
    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${cuadrillaInstance?.id}" />
        <g:link class="edit" action="edit" id="${cuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        <g:link class="add" action="create"><g:message code="default.add.label" args="[message(code: 'empleado.label', default: 'Operario')]"/></g:link>
      </fieldset>
    </g:form>
  </div>
</body>
</html>
