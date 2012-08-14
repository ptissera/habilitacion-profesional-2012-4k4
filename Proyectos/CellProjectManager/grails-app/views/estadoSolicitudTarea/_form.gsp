<%@ page import="business.tarea.EstadoSolicitudTarea" %>



<div class="fieldcontain ${hasErrors(bean: estadoSolicitudTareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="estadoSolicitudTarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${estadoSolicitudTareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoSolicitudTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="estadoSolicitudTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${estadoSolicitudTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoSolicitudTareaInstance, field: 'solicitudes', 'error')} ">
	<label for="solicitudes">
		<g:message code="estadoSolicitudTarea.solicitudes.label" default="Solicitudes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${estadoSolicitudTareaInstance?.solicitudes?}" var="s">
    <li><g:link controller="solicitudDeTarea" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="solicitudDeTarea" action="create" params="['estadoSolicitudTarea.id': estadoSolicitudTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')])}</g:link>
</li>
</ul>

</div>

