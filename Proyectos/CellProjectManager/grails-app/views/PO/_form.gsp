<%@ page import="business.core.PO" %>



<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'fechaRecibida', 'error')} required">
  <label for="fechaRecibida">
    <g:message code="PO.fechaRecibida.label" default="Fecha Recibida" />
    <span class="required-indicator">*</span>
  </label>
  <g:datePicker name="fechaRecibida" precision="day"  value="${POInstance?.fechaRecibida}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'monto', 'error')} required">
  <label for="monto">
    <g:message code="PO.monto.label" default="Monto" />
    <span class="required-indicator">*</span>
  </label>
  <g:field type="number" name="monto" step="any" required="" value="${POInstance.monto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'esExtra', 'error')} ">
  <label for="esExtra">
    <g:message code="PO.esExtra.label" default="Es Extra" />

  </label>
  <g:checkBox name="esExtra" value="${POInstance?.esExtra}" />
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'nombreArchivo', 'error')} ">
  <label for="archivo">
    <g:message code="PO.archivo.label" default="Archivo" />
    <span class="required-indicator">*</span>
  </label>

  <input type="file" id="uploadArchivo" name="uploadArchivo" />
  <g:if test="${POInstance?.nombreArchivo}">
    <div> 
      <label for="archivo"></label>
      <g:link action="downloadFile" id="${POInstance?.id}"> 
        <g:fieldValue bean="${POInstance}" field="nombreArchivo"/>
      </g:link>      
    </div>
  </g:if>
</div>
