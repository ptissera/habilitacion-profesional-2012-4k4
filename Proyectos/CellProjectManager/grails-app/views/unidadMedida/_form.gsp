<%@ page import="business.tarea.UnidadMedida" %>



<div class="fieldcontain ${hasErrors(bean: unidadMedidaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="unidadMedida.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${unidadMedidaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unidadMedidaInstance, field: 'materiales', 'error')} ">
	<label for="materiales">
		<g:message code="unidadMedida.materiales.label" default="Materiales" />
		
	</label>
	<g:select name="materiales" from="${business.tarea.MaterialDeTarea.list()}" multiple="multiple" optionKey="id" size="5" value="${unidadMedidaInstance?.materiales*.id}" class="many-to-many"/>
</div>

