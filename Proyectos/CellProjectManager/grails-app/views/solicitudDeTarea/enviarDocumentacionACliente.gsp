
<%@ page import="business.tarea.SolicitudDeTarea" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-solicitudDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
 
  <div id="show-solicitudDeTarea" class="content scaffold-show" role="main">
    <h1>Enviar Documentos a Cliente</h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">      
      <ul class="errors" role="alert"><li>${flash.error}</li></ul>        
    </g:if>
    <g:form action="enviarEmail">
    <ol class="property-list solicitudDeTarea">

      
        <li class="fieldcontain">
          <span id="documentos-label" class="property-label"><g:message code="solicitudDeTarea.documentos.label" default="Documentos" /></span>

          <table>
            <thead>
              <tr>
                <th><g:message code="documento.tipo.label" default="Tipo" /></th>
            <g:sortableColumn property="observaciones" title="${message(code: 'documento.observaciones.label', default: 'Observaciones')}" />
            <g:sortableColumn property="fechaRealizado" title="${message(code: 'documento.fechaRealizado.label', default: 'Fecha Realizado')}" />
            <g:sortableColumn property="fechaEnviado" title="${message(code: 'documento.fechaEnviado.label', default: 'Fecha Enviado')}" />
            <g:sortableColumn property="fechaAprobado" title="${message(code: 'documento.fechaAprobado.label', default: 'Fecha Aprobado')}" />
            </tr>
            </thead>
            <tbody>
            <g:each in="${solicitudDeTareaInstance?.documentos}" status="i" var="documentoInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: documentoInstance, field: "tipo")}</td>
                <td>${fieldValue(bean: documentoInstance, field: "observaciones")}</td>
                <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaRealizado}" /></td>
              <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaEnviado}" /></td>
              <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaAprobado}" /></td>
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      
        <li class="fieldcontain">
          <span class="property-label">Email cliente</span>
          <span class="property-value">${emailCliente}</span>
        </li>
      
        <li class="fieldcontain">
          <span class="property-label">Asunto</span>
          <span class="property-value" aria-labelledby="cuadrilla-label">
            <g:textField name="asunto" value="Protocolos y CAO"/>
          </span>
        </li>
       
        <li class="fieldcontain">
          <span class="property-label">Observaciones</span>
          <span class="property-value">
            <g:textArea name="observaciones" cols="40" rows="5" maxlength="500" value=""/>
          </span>
        </li>
      
    </ol>
    
      <fieldset class="buttons">        
         <g:submitButton name="sendEmail" class="sendEmail" value="Enviar" />
           
      </fieldset>
    </g:form>
  </div>
</body>
</html>
