<%@ page import="business.herramienta.Herramienta" %>



<div class="fieldcontain ${hasErrors(bean: herramientaInstance, field: 'numeroDeSerie', 'error')} required">
	<label for="numeroDeSerie">
		<g:message code="herramienta.numeroDeSerie.label" default="Numero De Serie" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroDeSerie" required="" value="${herramientaInstance?.numeroDeSerie}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: herramientaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="herramienta.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${herramientaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: herramientaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="herramienta.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${herramientaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: herramientaInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="herramienta.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${business.herramienta.EstadoHerramienta.list()}" optionKey="id" required="" value="${herramientaInstance?.estado?.id}" class="many-to-one"/>
</div>


