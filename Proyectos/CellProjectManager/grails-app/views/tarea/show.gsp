
<%@ page import="business.tarea.Tarea" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'tarea.label', default: 'Tarea')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-tarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="show-tarea" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list tarea">

      <g:if test="${tareaInstance?.ordenEjecucion}">
        <li class="fieldcontain">
          <span id="ordenEjecucion-label" class="property-label"><g:message code="tarea.ordenEjecucion.label" default="Orden Ejecucion" /></span>

          <span class="property-value" aria-labelledby="ordenEjecucion-label"><g:fieldValue bean="${tareaInstance}" field="ordenEjecucion"/></span>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.fechaInicio}">
        <li class="fieldcontain">
          <span id="fechaInicio-label" class="property-label"><g:message code="tarea.fechaInicio.label" default="Fecha Inicio" /></span>

          <span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate format="dd/MM/yyyy" date="${tareaInstance?.fechaInicio}" /></span>

        </li>
      </g:if>
      
      <g:if test="${tareaInstance?.fechaInicioReal}">
        <li class="fieldcontain">
          <span id="fechaInicioReal-label" class="property-label"><g:message code="tarea.fechaInicioReal.label" default="Fecha Inicio Real" /></span>

          <span class="property-value" aria-labelledby="fechaInicioReal-label"><g:formatDate format="dd/MM/yyyy" date="${tareaInstance?.fechaInicioReal}" /></span>

        </li>
      </g:if>
      
      <g:if test="${tareaInstance?.fechaFin}">
        <li class="fieldcontain">
          <span id="fechaFin-label" class="property-label"><g:message code="tarea.fechaFin.label" default="Fecha Fin" /></span>

          <span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate format="dd/MM/yyyy" date="${tareaInstance?.fechaFin}" /></span>

        </li>
      </g:if>
      
      <g:if test="${tareaInstance?.fechaFinReal}">
        <li class="fieldcontain">
          <span id="fechaFinReal-label" class="property-label"><g:message code="tarea.fechaFinReal.label" default="Fecha Fin Real" /></span>

          <span class="property-value" aria-labelledby="fechaFinReal-label"><g:formatDate format="dd/MM/yyyy" date="${tareaInstance?.fechaFinReal}" /></span>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.sitio}">
        <li class="fieldcontain">
          <span id="sitio-label" class="property-label"><g:message code="tarea.sitio.label" default="Sitio" /></span>

          <span class="property-value" aria-labelledby="sitio-label">${tareaInstance?.sitio?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.tipoTarea}">
        <li class="fieldcontain">
          <span id="tarea-label" class="property-label"><g:message code="tarea.tipoTarea.label" default="Tarea" /></span>

          <span class="property-value" aria-labelledby="tarea-label">${tareaInstance?.tipoTarea?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.estado}">
        <li class="fieldcontain">
          <span id="estado-label" class="property-label"><g:message code="tarea.estado.label" default="Estado" /></span>

          <span class="property-value" aria-labelledby="estado-label">${tareaInstance?.estado?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.materialDeTarea}">
        <li class="fieldcontain">
          <span id="materialDeTarea-label" class="property-label"><g:message code="tarea.materialDeTarea.label" default="Material De Tarea" /></span>
          <table>
            <thead>
              <tr>
            <g:sortableColumn property="descripcion" title="${message(code: 'materialDeTarea.descripcion.label', default: 'Descripcion')}" />
            <g:sortableColumn property="cantidad" title="${message(code: 'materialDeTarea.cantidad.label', default: 'Cantidad')}" />
            <g:sortableColumn property="esDeCliente" title="${message(code: 'materialDeTarea.esDeCliente.label', default: 'Es De Cliente')}" />
            <th><g:message code="materialDeTarea.unidad.label" default="Unidad" /></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${tareaInstance.materialDeTarea}" status="i" var="materialDeTareaInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: materialDeTareaInstance, field: "descripcion")}</td>
                <td>${fieldValue(bean: materialDeTareaInstance, field: "cantidad")}</td>
                <td><g:formatBoolean boolean="${materialDeTareaInstance.esDeCliente}" /></td>
              <td>${fieldValue(bean: materialDeTareaInstance, field: "unidad")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </li>
      </g:if>

      <g:if test="${tareaInstance?.equipoDeTarea}">
        <li class="fieldcontain">
          <span id="equipoDeTarea-label" class="property-label"><g:message code="tarea.equipoDeTarea.label" default="Equipo De Tarea" /></span>

          <table>
            <thead>
              <tr>
            <g:sortableColumn property="numeroSerie" title="${message(code: 'equipoDeTarea.numeroSerie.label', default: 'Numero Serie')}" />
            <g:sortableColumn property="descripcion" title="${message(code: 'equipoDeTarea.descripcion.label', default: 'Descripcion')}" />
            <th><g:message code="equipoDeTarea.tipo.label" default="Tipo" /></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${tareaInstance.equipoDeTarea}" status="i" var="equipoDeTareaInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: equipoDeTareaInstance, field: "numeroSerie")}</td>
                <td>${fieldValue(bean: equipoDeTareaInstance, field: "descripcion")}</td>
                <td>${fieldValue(bean: equipoDeTareaInstance, field: "tipo")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.permisos}">
        <li class="fieldcontain">
          <span id="permisos-label" class="property-label"><g:message code="tarea.permisos.label" default="Permisos" /></span>

          <table>
            <thead>
              <tr>					
            <g:sortableColumn property="fechaDesde" title="${message(code: 'permisoAcceso.fechaDesde.label', default: 'Fecha Desde')}" />					
            <g:sortableColumn property="fechaHasta" title="${message(code: 'permisoAcceso.fechaHasta.label', default: 'Fecha Hasta')}" />					
            <g:sortableColumn property="nombreArchivo" title="${message(code: 'permisoAcceso.nombreArchivo.label', default: 'Archivo')}" />										
            </tr>
            </thead>
            <tbody>
            <g:each in="${tareaInstance.permisos}" status="i" var="permisoAccesoInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                <td><g:formatDate format="dd/MM/yyyy" date="${permisoAccesoInstance.fechaDesde}" /></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${permisoAccesoInstance.fechaHasta}" /></td>					
              <td>${fieldValue(bean: permisoAccesoInstance, field: "nombreArchivo")}</td>					
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.documentoDeIngenieria}">
        <li class="fieldcontain">
          <span id="documentoDeIngenieria-label" class="property-label"><g:message code="documento.documentoDeIngenieria.label" default="Documento De Ingenieria" /></span>

          <span class="property-value" aria-labelledby="documentoDeIngenieria-label">
            <g:link action="downloadFile" id="${tareaInstance?.id}"> <g:fieldValue bean="${tareaInstance}" field="documentoDeIngenieria"/></g:link>
          </span>

        </li>
      </g:if>

      <g:if test="${tareaInstance?.observaciones}">
        <li class="fieldcontain">
          <span id="observaciones-label" class="property-label"><g:message code="tarea.observaciones.label" default="Observaciones" /></span>

          <span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${tareaInstance}" field="observaciones"/></span>

        </li>
      </g:if>

    </ol>
    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${tareaInstance?.id}" />
        <g:link class="edit" action="edit" id="${tareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
      </fieldset>
    </g:form>
  </div>
</body>
</html>
