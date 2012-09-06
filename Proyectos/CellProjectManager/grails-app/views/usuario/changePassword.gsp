<%@ page import="support.secure.Usuario" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
  <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#edit-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
 
  <div id="edit-usuario" class="content scaffold-edit" role="main">
    <h1>Cambio de contraseña</h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${usuarioInstance}">
      <ul class="errors" role="alert">
        <g:eachError bean="${usuarioInstance}" var="error">
          <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
      </ul>
    </g:hasErrors>
    <g:form method="post" >
      <g:hiddenField name="id" value="${id}" />
      <fieldset class="form">
        <div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'clave', 'error')} required">
          <label for="clave">
            <g:message code="usuario.clave.label" default="Clave" />
            <span class="required-indicator">*</span>
          </label>
          <g:field type="password" name="clave" required="" />
        </div>
        <div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'newClave', 'error')} required">
          <label for="clave">
            <g:message code="usuario.clave.label" default="newClave" />
            <span class="required-indicator">*</span>
          </label>
          <g:field type="password" name="newClave" required="" />
        </div>
        <div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'reNewClave', 'error')} required">
          <label for="clave">
            <g:message code="usuario.clave.label" default="reNewClave" />
            <span class="required-indicator">*</span>
          </label>
          <g:field type="password" name="reNewClave" required="" />
        </div>
      </fieldset>
      <fieldset class="buttons">
        <g:actionSubmit class="save" action="updatePassword" value="${message(code: 'default.button.update.label', default: 'Update')}" />       
      </fieldset>
    </g:form>
  </div>
</body>
</html>
