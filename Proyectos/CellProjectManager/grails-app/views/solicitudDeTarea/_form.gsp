<%@ page import="business.tarea.SolicitudDeTarea" %>



<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="solicitudDeTarea.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${solicitudDeTareaInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'tareasPorSitio', 'error')} ">
	<label for="tareasPorSitio">
		<g:message code="solicitudDeTarea.tareasPorSitio.label" default="Tareas Por Sitio" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${solicitudDeTareaInstance?.tareasPorSitio?}" var="t">
    <li><g:link controller="tareasPorSitio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tareasPorSitio" action="create" params="['solicitudDeTarea.id': solicitudDeTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'po', 'error')} ">
	<label for="po">
		<g:message code="solicitudDeTarea.po.label" default="Po" />
		
	</label>
	<g:select name="po" from="${business.core.PO.list()}" multiple="multiple" optionKey="id" size="5" value="${solicitudDeTareaInstance?.po*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'cuadrilla', 'error')} ">
	<label for="cuadrilla">
		<g:message code="solicitudDeTarea.cuadrilla.label" default="Cuadrilla" />
		
	</label>
	<g:select name="cuadrilla" from="${business.cuadrillas.Cuadrilla.list()}" multiple="multiple" optionKey="id" size="5" value="${solicitudDeTareaInstance?.cuadrilla*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'pago', 'error')} required">
	<label for="pago">
		<g:message code="solicitudDeTarea.pago.label" default="Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pago" name="pago.id" from="${business.core.Pago.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.pago?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'proyecto', 'error')} required">
	<label for="proyecto">
		<g:message code="solicitudDeTarea.proyecto.label" default="Proyecto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="proyecto" name="proyecto.id" from="${business.core.Proyecto.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.proyecto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="solicitudDeTarea.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${business.tarea.EstadoSolicitudTarea.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.estado?.id}" class="many-to-one"/>
</div>

