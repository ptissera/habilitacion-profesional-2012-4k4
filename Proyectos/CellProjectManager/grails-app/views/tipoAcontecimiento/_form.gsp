<%@ page import="business.tarea.TipoAcontecimiento" %>



<div class="fieldcontain ${hasErrors(bean: tipoAcontecimientoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoAcontecimiento.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoAcontecimientoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoAcontecimientoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoAcontecimiento.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoAcontecimientoInstance?.descripcion}"/>
</div>
