<%@ page import="business.cuadrillas.DocumentacionEmpleado" %>


<g:hiddenField id="empleado" name="empleado.id" value="${documentacionEmpleadoInstance?.empleado?.id}"  optionKey="id" />
<div class="fieldcontain ${hasErrors(bean: documentacionEmpleadoInstance, field: 'tipoDocumento', 'error')} required">
	<label for="tipoDocumento">
		<g:message code="documentacionEmpleado.tipoDocumento.label" default="Tipo Documento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoDocumento" name="tipoDocumento.id" from="${business.cuadrillas.TipoDocumentacionEmpleado.list()}" optionKey="id" required="" value="${documentacionEmpleadoInstance?.tipoDocumento?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentacionEmpleadoInstance, field: 'vigenciaDesde', 'error')} required">
	<label for="vigenciaDesde">
		<g:message code="documentacionEmpleado.vigenciaDesde.label" default="Vigencia Desde" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="vigenciaDesde" precision="day"  value="${documentacionEmpleadoInstance?.vigenciaDesde}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: documentacionEmpleadoInstance, field: 'vigenciaHasta', 'error')} required">
	<label for="vigenciaHasta">
		<g:message code="documentacionEmpleado.vigenciaHasta.label" default="Vigencia Hasta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="vigenciaHasta" precision="day"  value="${documentacionEmpleadoInstance?.vigenciaHasta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: documentacionEmpleadoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="documentacionEmpleado.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${documentacionEmpleadoInstance?.descripcion}"/>
</div>


