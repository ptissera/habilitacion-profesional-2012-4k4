<%@ page import="business.tarea.EstadoTarea" %>



<div class="fieldcontain ${hasErrors(bean: estadoTareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="estadoTarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${estadoTareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="estadoTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${estadoTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoTareaInstance, field: 'tarea', 'error')} ">
	<label for="tarea">
		<g:message code="estadoTarea.tarea.label" default="Tarea" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${estadoTareaInstance?.tarea?}" var="t">
    <li><g:link controller="tareasPorSitio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tareasPorSitio" action="create" params="['estadoTarea.id': estadoTareaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')])}</g:link>
</li>
</ul>

</div>

