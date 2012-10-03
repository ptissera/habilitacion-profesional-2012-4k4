<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla')}" />
  <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#edit-documentacionIntegranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div id="edit-documentacionIntegranteCuadrilla" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${documentacionIntegranteCuadrillaInstance}">
      <ul class="errors" role="alert">
        <g:eachError bean="${documentacionIntegranteCuadrillaInstance}" var="error">
          <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
      </ul>
    </g:hasErrors>
    <g:form method="post" >
      <g:hiddenField name="id" value="${documentacionIntegranteCuadrillaInstance?.id}" />
      <g:hiddenField name="version" value="${documentacionIntegranteCuadrillaInstance?.version}" />
      <fieldset class="form">
        <g:hiddenField id="integrante" name="integrante.id" value="${documentacionIntegranteCuadrillaInstance?.integrante?.id}"  optionKey="id" />
        <div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'tipoDocumento', 'error')} ">
          <label for="tipoDocumento">
            Integrante Cuadrilla

          </label>
          ${documentacionIntegranteCuadrillaInstance?.integrante?.encodeAsHTML()}
        </div>
        <div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'tipoDocumento', 'error')} required">
          <label for="tipoDocumento">
            <g:message code="documentacionIntegranteCuadrilla.tipoDocumento.label" default="Tipo Documento" />

          </label>
          ${documentacionIntegranteCuadrillaInstance?.tipoDocumento?.encodeAsHTML()}
        </div>
        <div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'vigenciaDesde', 'error')} required">
          <label for="vigenciaDesde">
            <g:message code="documentacionIntegranteCuadrilla.vigenciaDesde.label" default="Vigencia Desde" />
            <span class="required-indicator">*</span>
          </label>
          <g:fecha name="vigenciaDesde" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaDesde}"  />
        </div>

        <div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'vigenciaHasta', 'error')} required">
          <label for="vigenciaHasta">
            <g:message code="documentacionIntegranteCuadrilla.vigenciaHasta.label" default="Vigencia Hasta" />
            <span class="required-indicator">*</span>
          </label>
          <g:fecha name="vigenciaHasta" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaHasta}"  />
        </div>

        <div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'descripcion', 'error')} ">
          <label for="descripcion">
            <g:message code="documentacionIntegranteCuadrilla.descripcion.label" default="Descripcion" />

          </label>
          <g:textField name="descripcion" value="${documentacionIntegranteCuadrillaInstance?.descripcion}"/>
        </div>
        
      </fieldset>
      <fieldset class="buttons">
        <g:actionSubmit class="save" action="updateFromHome" value="${message(code: 'default.button.update.label', default: 'Update')}" />        
      </fieldset>
    </g:form>
  </div>
</body>
</html>
