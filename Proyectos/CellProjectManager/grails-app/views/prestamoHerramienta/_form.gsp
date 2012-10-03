<%@ page import="business.herramienta.PrestamoHerramienta" %>



<div class="fieldcontain ${hasErrors(bean: prestamoHerramientaInstance, field: 'fechaPrestamo', 'error')} required">
	<label for="fechaPrestamo">
		<g:message code="prestamoHerramienta.fechaPrestamo.label" default="Fecha Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:fecha name="fechaPrestamo" precision="day"  value="${prestamoHerramientaInstance?.fechaPrestamo}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoHerramientaInstance, field: 'fechaDevolucion', 'error')} ">
	<label for="fechaDevolucion">
		<g:message code="prestamoHerramienta.fechaDevolucion.label" default="Fecha Devolucion" />
		
	</label>
	<g:fecha name="fechaDevolucion" precision="day"  value="${prestamoHerramientaInstance?.fechaDevolucion}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoHerramientaInstance, field: 'herramienta', 'error')} required">
	<label for="herramienta">
		<g:message code="prestamoHerramienta.herramienta.label" default="Herramienta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="herramienta" name="herramienta.id" from="${business.herramienta.Herramienta.list()}" optionKey="id" required="" value="${prestamoHerramientaInstance?.herramienta?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoHerramientaInstance, field: 'cuadrilla', 'error')} required">
	<label for="cuadrilla">
		<g:message code="prestamoHerramienta.cuadrilla.label" default="Cuadrilla" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuadrilla" name="cuadrilla.id" from="${business.cuadrillas.Cuadrilla.list()}" optionKey="id" required="" value="${prestamoHerramientaInstance?.cuadrilla?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoHerramientaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="prestamoHerramienta.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${prestamoHerramientaInstance?.descripcion}"/>
</div>

