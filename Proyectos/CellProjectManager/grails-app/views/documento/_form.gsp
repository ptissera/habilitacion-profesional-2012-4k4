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

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'fechaRealizado', 'error')} ">
  <label for="fechaRealizado">
    <g:message code="documento.fechaRealizado.label" default="Fecha Realizado" />

  </label>
  <g:datePicker name="fechaRealizado" precision="day"  value="${documentoInstance?.fechaRealizado}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'fechaEnviado', 'error')} ">
  <label for="fechaEnviado">
    <g:message code="documento.fechaEnviado.label" default="Fecha Enviado" />

  </label>
  <g:datePicker name="fechaEnviado" precision="day"  value="${documentoInstance?.fechaEnviado}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'fechaAprobado', 'error')} ">
  <label for="fechaAprobado">
    <g:message code="documento.fechaAprobado.label" default="Fecha Aprobado" />

  </label>
  <g:datePicker name="fechaAprobado" precision="day"  value="${documentoInstance?.fechaAprobado}" default="none" noSelection="['': '']" />
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

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'estado', 'error')} required">
  <label for="estado">
    <g:message code="documento.estado.label" default="Estado" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="estado" name="estado.id" from="${business.documento.EstadoDocumento.list()}" optionKey="id" required="" value="${documentoInstance?.estado?.id}" class="many-to-one"/>
</div>

