<%@ page import="business.documento.Documento" %>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'tipo', 'error')} required">
  <label for="tipoDocumento">
    <g:message code="documento.tipo.label" default="Tipo" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="tipoDocumento" name="tipo.id" from="${business.documento.TipoDocumento.list()}" optionKey="id" required="" value="${documentoInstance?.tipo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'observaciones', 'error')} required">
  <label for="observaciones">
    <g:message code="documento.observaciones.label" default="Observaciones" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="observaciones" required="" value="${documentoInstance?.observaciones}"/>
</div>



<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'archivo', 'error')} required">
  <label for="archivo">
    <g:message code="documento.archivo.label" default="Archivo" />
    <span class="required-indicator">*</span>
  </label>  
  <input type="file" id="uploadArchivo" name="uploadArchivo" />
  <g:if test="${documentoInstance?.nombreArchivo}">
    <div> 
      <label for="archivo"></label>
      <g:link action="downloadFile" id="${documentoInstance?.id}"> 
        <g:fieldValue bean="${documentoInstance}" field="nombreArchivo"/>
      </g:link>      
    </div>
  </g:if>
</div>

