<%@ page import="support.secure.Usuario" %>



<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nombreUsuario', 'error')} required">
	<label for="nombreUsuario">
		<g:message code="usuario.nombreUsuario.label" default="Nombre Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombreUsuario" required="" value="${usuarioInstance?.nombreUsuario}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="usuario.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="clave" required="" value="${usuarioInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="usuario.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${usuarioInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'apellido', 'error')} required">
	<label for="apellido">
		<g:message code="usuario.apellido.label" default="Apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" required="" value="${usuarioInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="usuario.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${usuarioInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'rol', 'error')} required">
	<label for="rol">
		<g:message code="usuario.rol.label" default="Rol" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="rol" name="rol.id" from="${support.secure.Rol.list()}" optionKey="id" required="" value="${usuarioInstance?.rol?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'habilitado', 'error')} ">
	<label for="habilitado">
		<g:message code="usuario.habilitado.label" default="Habilitado" />
		
	</label>
	<g:checkBox name="habilitado" value="${usuarioInstance?.habilitado}" />
</div>

