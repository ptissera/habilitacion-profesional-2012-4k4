<%@ page import="business.tarea.Tarea" %>



<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="tarea.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${tareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'monto', 'error')} required">
	<label for="monto">
		<g:message code="tarea.monto.label" default="Monto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="monto" step="any" required="" value="${tareaInstance.monto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="tarea.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" value="${tareaInstance?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="tarea.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${tareaInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="tarea.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${business.tarea.EstadoTarea.list()}" optionKey="id" required="" value="${tareaInstance?.estado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="tarea.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipo" name="tipo.id" from="${business.tarea.TipoTarea.list()}" optionKey="id" required="" value="${tareaInstance?.tipo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'tareaSitio', 'error')} required">
	<label for="tareaSitio">
		<g:message code="tarea.tareaSitio.label" default="Tarea Sitio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tareaSitio" name="tareaSitio.id" from="${business.tarea.TareasPorSitio.list()}" optionKey="id" required="" value="${tareaInstance?.tareaSitio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'documentos', 'error')} ">
	<label for="documentos">
		<g:message code="tarea.documentos.label" default="Documentos" />
		
	</label>
	<g:select name="documentos" from="${business.documento.Documento.list()}" multiple="multiple" optionKey="id" size="5" value="${tareaInstance?.documentos*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'equipos', 'error')} ">
	<label for="equipos">
		<g:message code="tarea.equipos.label" default="Equipos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tareaInstance?.equipos?}" var="e">
    <li><g:link controller="equipoDeTarea" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="equipoDeTarea" action="create" params="['tarea.id': tareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'equipoDeTarea.label', default: 'EquipoDeTarea')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'materiales', 'error')} ">
	<label for="materiales">
		<g:message code="tarea.materiales.label" default="Materiales" />
		
	</label>
	<g:select name="materiales" from="${business.tarea.MaterialDeTarea.list()}" multiple="multiple" optionKey="id" size="5" value="${tareaInstance?.materiales*.id}" class="many-to-many"/>
</div>

