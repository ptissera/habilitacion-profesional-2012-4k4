<%@ page import="business.tarea.TipoTarea" %>



<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoTarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoTareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'complejidad', 'error')} required">
	<label for="complejidad">
		<g:message code="tipoTarea.complejidad.label" default="Complejidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="complejidad" from="${1..10}" class="range" required="" value="${fieldValue(bean: tipoTareaInstance, field: 'complejidad')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'duracionEstimada', 'error')} required">
	<label for="duracionEstimada">
		<g:message code="tipoTarea.duracionEstimada.label" default="Duracion Estimada" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="duracionEstimada" from="${1..100}" class="range" required="" value="${fieldValue(bean: tipoTareaInstance, field: 'duracionEstimada')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'tareas', 'error')} ">
	<label for="tareas">
		<g:message code="tipoTarea.tareas.label" default="Tareas" />
		
	</label>
	<g:select name="tareas" from="${business.tarea.Tarea.list()}" multiple="multiple" optionKey="id" size="5" value="${tipoTareaInstance?.tareas*.id}" class="many-to-many"/>
</div>

