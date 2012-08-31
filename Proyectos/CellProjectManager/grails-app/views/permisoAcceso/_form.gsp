<%@ page import="business.core.PermisoAcceso" %>



<div class="fieldcontain ${hasErrors(bean: permisoAccesoInstance, field: 'fechaDesde', 'error')} required">
	<label for="fechaDesde">
		<g:message code="permisoAcceso.fechaDesde.label" default="Fecha Desde" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaDesde" precision="day"  value="${permisoAccesoInstance?.fechaDesde}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: permisoAccesoInstance, field: 'fechaHasta', 'error')} required">
	<label for="fechaHasta">
		<g:message code="permisoAcceso.fechaHasta.label" default="Fecha Hasta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaHasta" precision="day"  value="${permisoAccesoInstance?.fechaHasta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: permisoAccesoInstance, field: 'archivo', 'error')} required">
	<label for="archivo">
		<g:message code="permisoAcceso.nombreArchivo.label" default="Archivo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="archivo" name="archivo" />
</div>



