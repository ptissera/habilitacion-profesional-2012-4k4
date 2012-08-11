<%@ page import="business.herramienta.EstadoHerramienta" %>



<div class="fieldcontain ${hasErrors(bean: estadoHerramientaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="estadoHerramienta.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${estadoHerramientaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoHerramientaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="estadoHerramienta.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${estadoHerramientaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoHerramientaInstance, field: 'herramientas', 'error')} ">
	<label for="herramientas">
		<g:message code="estadoHerramienta.herramientas.label" default="Herramientas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${estadoHerramientaInstance?.herramientas?}" var="h">
    <li><g:link controller="herramienta" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="herramienta" action="create" params="['estadoHerramienta.id': estadoHerramientaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'herramienta.label', default: 'Herramienta')])}</g:link>
</li>
</ul>

</div>

