<%@ page import="business.core.Cliente" %>



<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'razonSocial', 'error')} ">
	<label for="razonSocial">
		<g:message code="cliente.razonSocial.label" default="Razon Social" />
		
	</label>
	<g:textField name="razonSocial" maxlength="50" value="${clienteInstance?.razonSocial}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'cuit', 'error')} ">
	<label for="cuit">
		<g:message code="cliente.cuit.label" default="Cuit" />
		
	</label>
	<g:textField name="cuit" value="${clienteInstance?.cuit}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="cliente.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" value="${clienteInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion', 'error')} ">
	<label for="direccion">
		<g:message code="cliente.direccion.label" default="Direccion" />
		
	</label>
	<g:textField name="direccion" value="${clienteInstance?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="cliente.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${clienteInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'contactoNombre', 'error')} ">
	<label for="contactoNombre">
		<g:message code="cliente.contactoNombre.label" default="Contacto Nombre" />
		
	</label>
	<g:textField name="contactoNombre" value="${clienteInstance?.contactoNombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'contactoTelefono', 'error')} ">
	<label for="contactoTelefono">
		<g:message code="cliente.contactoTelefono.label" default="Contacto Telefono" />
		
	</label>
	<g:textField name="contactoTelefono" value="${clienteInstance?.contactoTelefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'contactoEmail', 'error')} ">
	<label for="contactoEmail">
		<g:message code="cliente.contactoEmail.label" default="Contacto Email" />
		
	</label>
	<g:field type="email" name="contactoEmail" value="${clienteInstance?.contactoEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'licitaciones', 'error')} ">
	<label for="licitaciones">
		<g:message code="cliente.licitaciones.label" default="Licitaciones" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${clienteInstance?.licitaciones?}" var="l">
    <li><g:link controller="proyecto" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="proyecto" action="create" params="['cliente.id': clienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'proyecto.label', default: 'Proyecto')])}</g:link>
</li>
</ul>

</div>

