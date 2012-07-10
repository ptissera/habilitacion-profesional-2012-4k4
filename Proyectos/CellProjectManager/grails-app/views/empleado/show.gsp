
<%@ page import="business.cuadrillas.Empleado" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'empleado.label', default: 'Empleado')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-empleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="back" action="show" controller="empleado" id="${empleadoInstance?.id}">Regresar</g:link></li>      
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>  
  <div id="show-empleado" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list empleado">

      <g:if test="${empleadoInstance?.du}">
        <li class="fieldcontain">
          <span id="du-label" class="property-label"><g:message code="empleado.du.label" default="Du" /></span>

          <span class="property-value" aria-labelledby="du-label"><g:fieldValue bean="${empleadoInstance}" field="du"/></span>

        </li>
      </g:if>

      <g:if test="${empleadoInstance?.nombre}">
        <li class="fieldcontain">
          <span id="nombre-label" class="property-label"><g:message code="empleado.nombre.label" default="Nombre" /></span>

          <span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${empleadoInstance}" field="nombre"/></span>

        </li>
      </g:if>

      <g:if test="${empleadoInstance?.apellido}">
        <li class="fieldcontain">
          <span id="apellido-label" class="property-label"><g:message code="empleado.apellido.label" default="Apellido" /></span>

          <span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${empleadoInstance}" field="apellido"/></span>

        </li>
      </g:if>

      <g:if test="${empleadoInstance?.legajo}">
        <li class="fieldcontain">
          <span id="legajo-label" class="property-label"><g:message code="empleado.legajo.label" default="Legajo" /></span>

          <span class="property-value" aria-labelledby="legajo-label"><g:fieldValue bean="${empleadoInstance}" field="legajo"/></span>

        </li>
      </g:if>

      <g:if test="${empleadoInstance?.telefono}">
        <li class="fieldcontain">
          <span id="telefono-label" class="property-label"><g:message code="empleado.telefono.label" default="Telefono" /></span>

          <span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${empleadoInstance}" field="telefono"/></span>

        </li>
      </g:if>

      <g:if test="${empleadoInstance?.documentacion}">
        <li class="fieldcontain">
          
          <table>
            <thead>
              <tr><g:message code="empleado.documentacion.label" default="Documentacion" /></tr>
              <tr>              
                <th><g:message code="documentacionEmpleado.tipoDocumento.label" default="Tipo Documento" /></th>					
            <g:sortableColumn property="vigenciaDesde" title="${message(code: 'documentacionEmpleado.vigenciaDesde.label', default: 'Vigencia Desde')}" />					
            <g:sortableColumn property="vigenciaHasta" title="${message(code: 'documentacionEmpleado.vigenciaHasta.label', default: 'Vigencia Hasta')}" />					
            <g:sortableColumn property="descripcion" title="${message(code: 'documentacionEmpleado.descripcion.label', default: 'Descripcion')}" />									
            </tr>
            </thead>
            <tbody>
            <g:each in="${empleadoInstance?.documentacion}" status="i" var="documentacionEmpleadoInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                <td><g:link action="show" controller="documentacionEmpleado" id="${documentacionEmpleadoInstance.id}">${fieldValue(bean: documentacionEmpleadoInstance, field: "tipoDocumento")}</g:link></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${documentacionEmpleadoInstance.vigenciaDesde}" /></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${documentacionEmpleadoInstance.vigenciaHasta}" /></td>					
              <td>${fieldValue(bean: documentacionEmpleadoInstance, field: "descripcion")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>

    </ol>
    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${empleadoInstance?.id}" />
        <g:link class="edit" action="edit" id="${empleadoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        <g:link class="add" controller="documentacionEmpleado" action="create" id="${empleadoInstance?.id}"><g:message code="default.add.label" args="[message(code: 'tipoDocumentacionEmpleado.label', default: 'Tipo Documentacion')]"/></g:link>
      </fieldset>
    </g:form>
  </div>
</body>
</html>
