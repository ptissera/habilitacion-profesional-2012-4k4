<%@ page import="business.tarea.TipoEquipoDeTarea" %>



<div class="fieldcontain ${hasErrors(bean: tipoEquipoDeTareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoEquipoDeTarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoEquipoDeTareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoEquipoDeTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoEquipoDeTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoEquipoDeTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoEquipoDeTareaInstance, field: 'equipos', 'error')} ">
	<label for="equipos">
		<g:message code="tipoEquipoDeTarea.equipos.label" default="Equipos" />
		
	</label>
	<g:select name="equipos" from="${business.tarea.EquipoDeTarea.list()}" multiple="multiple" optionKey="id" size="5" value="${tipoEquipoDeTareaInstance?.equipos*.id}" class="many-to-many"/>
</div>

