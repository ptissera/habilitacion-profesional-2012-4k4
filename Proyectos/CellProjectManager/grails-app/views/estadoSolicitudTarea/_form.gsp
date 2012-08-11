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
	<g:select name="solicitudes" from="${business.tarea.SolicitudDeTarea.list()}" multiple="multiple" optionKey="id" size="5" value="${estadoSolicitudTareaInstance?.solicitudes*.id}" class="many-to-many"/>
</div>

