<%@ page import="support.secure.Rol" %>



<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="rol.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${rolInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="rol.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${rolInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'usuarios', 'error')} ">
	<label for="usuarios">
		<g:message code="rol.usuarios.label" default="Usuarios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rolInstance?.usuarios?}" var="u">
    <li><g:link controller="usuario" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="usuario" action="create" params="['rol.id': rolInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'usuario.label', default: 'Usuario')])}</g:link>
</li>
</ul>

</div>

