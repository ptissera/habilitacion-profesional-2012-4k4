<%@ page import="business.cuadrillas.HistorialCuadrilla" %>



<div class="fieldcontain ${hasErrors(bean: historialCuadrillaInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="historialCuadrilla.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:fecha name="fecha" precision="day"  value="${historialCuadrillaInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: historialCuadrillaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="historialCuadrilla.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${historialCuadrillaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: historialCuadrillaInstance, field: 'cuadrilla', 'error')} required">
	<label for="cuadrilla">
		<g:message code="historialCuadrilla.cuadrilla.label" default="Cuadrilla" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuadrilla" name="cuadrilla.id" from="${business.cuadrillas.Cuadrilla.list()}" optionKey="id" required="" value="${historialCuadrillaInstance?.cuadrilla?.id}" class="many-to-one"/>
</div>

