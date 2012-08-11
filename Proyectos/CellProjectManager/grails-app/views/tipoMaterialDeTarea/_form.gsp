<%@ page import="business.tarea.TipoMaterialDeTarea" %>



<div class="fieldcontain ${hasErrors(bean: tipoMaterialDeTareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoMaterialDeTarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoMaterialDeTareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoMaterialDeTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoMaterialDeTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoMaterialDeTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoMaterialDeTareaInstance, field: 'materiales', 'error')} ">
	<label for="materiales">
		<g:message code="tipoMaterialDeTarea.materiales.label" default="Materiales" />
		
	</label>
	<g:select name="materiales" from="${business.tarea.MaterialDeTarea.list()}" multiple="multiple" optionKey="id" size="5" value="${tipoMaterialDeTareaInstance?.materiales*.id}" class="many-to-many"/>
</div>

