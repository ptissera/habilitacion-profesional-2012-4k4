<%@ page import="business.tarea.TipoTarea" %>



<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoTarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoTareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoTareaInstance?.descripcion}"/>
</div>



