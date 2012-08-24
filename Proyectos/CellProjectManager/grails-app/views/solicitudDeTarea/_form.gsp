<%@ page import="business.tarea.SolicitudDeTarea" %>


<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'proyecto', 'error')} required">
	<label for="proyecto">
		<g:message code="solicitudDeTarea.proyecto.label" default="Proyecto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="proyecto" name="proyecto.id" from="${business.core.Proyecto.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.proyecto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'cuadrilla', 'error')} required">
	<label for="cuadrilla">
		<g:message code="solicitudDeTarea.cuadrilla.label" default="Cuadrilla" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuadrilla" name="cuadrilla.id" from="${business.cuadrillas.Cuadrilla.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.cuadrilla?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="solicitudDeTarea.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${business.tarea.EstadoSolicitudTarea.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.estado?.id}" class="many-to-one"/>
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

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'pos', 'error')} ">
	<label for="pos">
		<g:message code="solicitudDeTarea.pos.label" default="Pos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${solicitudDeTareaInstance?.pos?}" var="p">
    <li><g:link controller="PO" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="PO" action="create" params="['solicitudDeTarea.id': solicitudDeTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'PO.label', default: 'PO')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'prestamos', 'error')} ">
	<label for="prestamos">
		<g:message code="solicitudDeTarea.prestamos.label" default="Prestamos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${solicitudDeTareaInstance?.prestamos?}" var="p">
    <li><g:link controller="prestamoHerramienta" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="prestamoHerramienta" action="create" params="['solicitudDeTarea.id': solicitudDeTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'prestamoHerramienta.label', default: 'PrestamoHerramienta')])}</g:link>
</li>
</ul>

</div>

