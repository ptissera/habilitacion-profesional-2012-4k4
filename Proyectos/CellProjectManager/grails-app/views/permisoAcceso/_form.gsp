<%@ page import="business.core.PermisoAcceso" %>



<div class="fieldcontain ${hasErrors(bean: permisoAccesoInstance, field: 'fechaDesde', 'error')} required">
	<label for="fechaDesde">
		<g:message code="permisoAcceso.fechaDesde.label" default="Fecha Desde" />
		<span class="required-indicator">*</span>
	</label>
	<g:fecha name="fechaDesde" precision="day"  value="${permisoAccesoInstance?.fechaDesde}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: permisoAccesoInstance, field: 'fechaHasta', 'error')} required">
	<label for="fechaHasta">
		<g:message code="permisoAcceso.fechaHasta.label" default="Fecha Hasta" />
		<span class="required-indicator">*</span>
	</label>
	<g:fecha name="fechaHasta" precision="day"  value="${permisoAccesoInstance?.fechaHasta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: permisoAccesoInstance, field: 'archivo', 'error')} required">
	<label for="archivo">
		<g:message code="permisoAcceso.nombreArchivo.label" default="Archivo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="uploadArchivo" name="uploadArchivo" />
  <g:if test="${permisoAccesoInstance?.nombreArchivo}">
    <div> 
      <label for="archivo"></label>
      <g:link action="downloadFile" id="${permisoAccesoInstance?.id}"> 
        <g:fieldValue bean="${permisoAccesoInstance}" field="nombreArchivo"/>
      </g:link>      
    </div>
  </g:if>
</div>



