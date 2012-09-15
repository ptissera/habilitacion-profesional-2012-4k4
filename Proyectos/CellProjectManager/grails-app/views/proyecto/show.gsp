
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'proyecto.label', default: 'Proyecto')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-proyecto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>				
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="show-proyecto" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list proyecto">

      <g:if test="${proyectoInstance?.cliente}">
        <li class="fieldcontain">
          <span id="cliente-label" class="property-label"><g:message code="proyecto.cliente.label" default="Cliente" /></span>

          <span class="property-value" aria-labelledby="cliente-label">${proyectoInstance?.cliente?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.licitacion}">
        <li class="fieldcontain">
          <span id="licitacion-label" class="property-label"><g:message code="proyecto.licitacion.label" default="Licitacion" /></span>

          <span class="property-value" aria-labelledby="licitacion-label"><g:fieldValue bean="${proyectoInstance}" field="licitacion"/></span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.nombre}">
        <li class="fieldcontain">
          <span id="nombre-label" class="property-label"><g:message code="proyecto.nombre.label" default="Nombre" /></span>

          <span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${proyectoInstance}" field="nombre"/></span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.descripcion}">
        <li class="fieldcontain">
          <span id="descripcion-label" class="property-label"><g:message code="proyecto.descripcion.label" default="Descripcion" /></span>

          <span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${proyectoInstance}" field="descripcion"/></span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.fechaCreacion}">
        <li class="fieldcontain">
          <span id="fechaCreacion-label" class="property-label"><g:message code="proyecto.fechaCreacion.label" default="Fecha Creacion" /></span>

          <span class="property-value" aria-labelledby="fechaCreacion-label"><g:formatDate  format="dd/MM/yyyy" date="${proyectoInstance?.fechaCreacion}" /></span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.fechaInicio}">
        <li class="fieldcontain">
          <span id="fechaInicio-label" class="property-label"><g:message code="proyecto.fechaInicio.label" default="Fecha Inicio" /></span>

          <span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate  format="dd/MM/yyyy" date="${proyectoInstance?.fechaInicio}" /></span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.fechaFin}">
        <li class="fieldcontain">
          <span id="fechaFin-label" class="property-label"><g:message code="proyecto.fechaFin.label" default="Fecha Fin" /></span>

          <span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate  format="dd/MM/yyyy" date="${proyectoInstance?.fechaFin}" /></span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.estadoProyecto}">
        <li class="fieldcontain">
          <span id="estadoProyecto-label" class="property-label"><g:message code="proyecto.estadoProyecto.label" default="Estado Proyecto" /></span>

          <span class="property-value" aria-labelledby="estadoProyecto-label">${proyectoInstance?.estadoProyecto?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.usuario}">
        <li class="fieldcontain">
          <span id="usuario-label" class="property-label"><g:message code="proyecto.usuario.label" default="Administrador" /></span>

          <span class="property-value" aria-labelledby="usuario-label">${proyectoInstance?.usuario?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${proyectoInstance?.solicitudes}">
        <li class="fieldcontain">
          <span id="solicitudes-label" class="property-label"><g:message code="proyecto.solicitudes.label" default="Solicitudes" /></span>					
          <table>
            <thead>
              <tr>					
            <g:sortableColumn property="fechaAlta" title="${message(code: 'solicitudDeTarea.fechaAlta.label', default: 'Fecha Alta')}" />											
            <th><g:message code="solicitudDeTarea.cuadrilla.label" default="Cuadrilla" /></th>					
            <th><g:message code="solicitudDeTarea.estado.label" default="Estado" /></th>					
            </tr>
            </thead>
            <tbody>
            <g:each in="${proyectoInstance.solicitudes}" status="i" var="solicitudDeTareaInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                <td><g:link action="show" controller="solicitudDeTarea" id="${solicitudDeTareaInstance.id}"><g:formatDate format="dd/MM/yyyy" date="${solicitudDeTareaInstance.fechaAlta}" /></g:link></td>
              <td>${fieldValue(bean: solicitudDeTareaInstance, field: "cuadrilla")}</td>					
              <td>${fieldValue(bean: solicitudDeTareaInstance, field: "estado")}</td>					
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>



    </ol>
    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${proyectoInstance?.id}" />
        <g:link class="edit" action="edit" id="${proyectoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        <g:actionSubmit class="cerrar" action="closeProject" value="${message(code: 'default.button.cerrarProyecto.label', default: 'Cerrar')}" onclick="return confirm('${message(code: 'default.button.cerrarProyecto.confirm.message', default: 'Esta seguro que desea cerrar el Proyecto?')}');" />
      </fieldset>
    </g:form>
  </div>
</body>
</html>
