<%@ page import="business.tarea.Tarea" %>



<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tareaInstance?.descripcion}"/>
</div>



