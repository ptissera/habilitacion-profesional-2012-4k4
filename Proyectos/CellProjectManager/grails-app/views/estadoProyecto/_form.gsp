<%@ page import="business.core.EstadoProyecto" %>



<div class="fieldcontain ${hasErrors(bean: estadoProyectoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="estadoProyecto.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${estadoProyectoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoProyectoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="estadoProyecto.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${estadoProyectoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoProyectoInstance, field: 'proyectos', 'error')} ">
	<label for="proyectos">
		<g:message code="estadoProyecto.proyectos.label" default="Proyectos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${estadoProyectoInstance?.proyectos?}" var="p">
    <li><g:link controller="proyecto" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="proyecto" action="create" params="['estadoProyecto.id': estadoProyectoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'proyecto.label', default: 'Proyecto')])}</g:link>
</li>
</ul>

</div>

