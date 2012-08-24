<%@ page import="business.tarea.EquipoDeTarea" %>



<div class="fieldcontain ${hasErrors(bean: equipoDeTareaInstance, field: 'numeroSerie', 'error')} required">
	<label for="numeroSerie">
		<g:message code="equipoDeTarea.numeroSerie.label" default="Numero Serie" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroSerie" required="" value="${equipoDeTareaInstance?.numeroSerie}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: equipoDeTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="equipoDeTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${equipoDeTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: equipoDeTareaInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="equipoDeTarea.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipo" name="tipo.id" from="${business.tarea.TipoEquipoDeTarea.list()}" optionKey="id" required="" value="${equipoDeTareaInstance?.tipo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: equipoDeTareaInstance, field: 'tareasPorSitio', 'error')} required">
	<label for="tareasPorSitio">
		<g:message code="equipoDeTarea.tareasPorSitio.label" default="Tareas Por Sitio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tareasPorSitio" name="tareasPorSitio.id" from="${business.tarea.TareasPorSitio.list()}" optionKey="id" required="" value="${equipoDeTareaInstance?.tareasPorSitio?.id}" class="many-to-one"/>
</div>

