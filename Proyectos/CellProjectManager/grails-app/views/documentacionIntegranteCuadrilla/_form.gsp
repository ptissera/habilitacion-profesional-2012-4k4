<%@ page import="business.cuadrillas.DocumentacionIntegranteCuadrilla" %>



<div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'vigenciaDesde', 'error')} required">
	<label for="vigenciaDesde">
		<g:message code="documentacionIntegranteCuadrilla.vigenciaDesde.label" default="Vigencia Desde" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="vigenciaDesde" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaDesde}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'vigenciaHasta', 'error')} required">
	<label for="vigenciaHasta">
		<g:message code="documentacionIntegranteCuadrilla.vigenciaHasta.label" default="Vigencia Hasta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="vigenciaHasta" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaHasta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="documentacionIntegranteCuadrilla.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${documentacionIntegranteCuadrillaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'tipoDocumento', 'error')} required">
	<label for="tipoDocumento">
		<g:message code="documentacionIntegranteCuadrilla.tipoDocumento.label" default="Tipo Documento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoDocumento" name="tipoDocumento.id" from="${business.cuadrillas.TipoDocumentacionIntegranteCuadrilla.list()}" optionKey="id" required="" value="${documentacionIntegranteCuadrillaInstance?.tipoDocumento?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentacionIntegranteCuadrillaInstance, field: 'integrante', 'error')} required">
	<label for="integrante">
		<g:message code="documentacionIntegranteCuadrilla.integrante.label" default="Integrante" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="integrante" name="integrante.id" from="${business.cuadrillas.IntegranteCuadrilla.list()}" optionKey="id" required="" value="${documentacionIntegranteCuadrillaInstance?.integrante?.id}" class="many-to-one"/>
</div>

