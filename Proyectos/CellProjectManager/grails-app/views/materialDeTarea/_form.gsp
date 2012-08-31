<%@ page import="business.tarea.MaterialDeTarea" %>



<div class="fieldcontain ${hasErrors(bean: materialDeTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="materialDeTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${materialDeTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialDeTareaInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="materialDeTarea.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="cantidad" min="1" required="" value="${materialDeTareaInstance.cantidad}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialDeTareaInstance, field: 'esDeCliente', 'error')} ">
	<label for="esDeCliente">
		<g:message code="materialDeTarea.esDeCliente.label" default="Es De Cliente" />
		
	</label>
	<g:checkBox name="esDeCliente" value="${materialDeTareaInstance?.esDeCliente}" />
</div>

<div class="fieldcontain ${hasErrors(bean: materialDeTareaInstance, field: 'unidad', 'error')} required">
	<label for="unidad">
		<g:message code="materialDeTarea.unidad.label" default="Unidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="unidad" name="unidad.id" from="${business.tarea.UnidadMedida.list()}" optionKey="id" required="" value="${materialDeTareaInstance?.unidad?.id}" class="many-to-one"/>
</div>


