
<%@ page import="business.herramienta.PrestamoHerramienta" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-prestamoHerramienta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>

      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>

    </ul>
  </div>

  <div id="show-prestamoHerramienta" class="content scaffold-show" role="main">
    <g:form >
    <h1>Registrar Devolucion de Prestamo</h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list prestamoHerramienta">

      <g:if test="${prestamoHerramientaInstance?.fechaPrestamo}">
        <li class="fieldcontain">
          <span id="fechaPrestamo-label" class="property-label"><g:message code="prestamoHerramienta.fechaPrestamo.label" default="Fecha Prestamo" /></span>

          <span class="property-value" aria-labelledby="fechaPrestamo-label"><g:formatDate date="${prestamoHerramientaInstance?.fechaPrestamo}" /></span>

        </li>
      </g:if>

      <g:if test="${prestamoHerramientaInstance?.fechaDevolucion}">
        <li class="fieldcontain">
          <span id="fechaDevolucion-label" class="property-label"><g:message code="prestamoHerramienta.fechaDevolucion.label" default="Fecha Devolucion" /></span>

          <span class="property-value" aria-labelledby="fechaDevolucion-label"><g:formatDate date="${prestamoHerramientaInstance?.fechaDevolucion}" /></span>

        </li>
      </g:if>


      <li class="fieldcontain">
        <span id="fechaDevolucionReal-label" class="property-label"><g:message code="prestamoHerramienta.fechaDevolucionReal.label" default="Fecha Devolucion Real" /></span>
        <span class="property-value" aria-labelledby="fechaDevolucionReal-label">
          <g:fecha name="fechaDevolucionReal" precision="day"  value="${prestamoHerramientaInstance?.fechaDevolucionReal}" default="none" noSelection="['': '']" />
        </span>
      </li>


      <g:if test="${prestamoHerramientaInstance?.herramienta}">
        <li class="fieldcontain">
          <span id="herramienta-label" class="property-label"><g:message code="prestamoHerramienta.herramienta.label" default="Herramienta" /></span>

          <span class="property-value" aria-labelledby="herramienta-label">${prestamoHerramientaInstance?.herramienta?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${prestamoHerramientaInstance?.cuadrilla}">
        <li class="fieldcontain">
          <span id="cuadrilla-label" class="property-label"><g:message code="prestamoHerramienta.cuadrilla.label" default="Cuadrilla" /></span>

          <span class="property-value" aria-labelledby="cuadrilla-label">${prestamoHerramientaInstance?.cuadrilla?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${prestamoHerramientaInstance?.descripcion}">
        <li class="fieldcontain">
          <span id="descripcion-label" class="property-label"><g:message code="prestamoHerramienta.descripcion.label" default="Descripcion" /></span>

          <span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${prestamoHerramientaInstance}" field="descripcion"/></span>

        </li>
      </g:if>

    </ol>

    <fieldset class="buttons">
      <g:hiddenField name="id" value="${prestamoHerramientaInstance?.id}" />
      <g:actionSubmit class="save" action="guardarDevolucion"  value="Actualizar" />
    </fieldset>
</g:form>
</div>
</body>
</html>
