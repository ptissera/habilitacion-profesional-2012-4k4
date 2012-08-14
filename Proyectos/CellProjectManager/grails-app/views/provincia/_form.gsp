<%@ page import="business.core.Provincia" %>



<div class="fieldcontain ${hasErrors(bean: provinciaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="provincia.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${provinciaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provinciaInstance, field: 'sitios', 'error')} ">
	<label for="sitios">
		<g:message code="provincia.sitios.label" default="Sitios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${provinciaInstance?.sitios?}" var="s">
    <li><g:link controller="sitio" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="sitio" action="create" params="['provincia.id': provinciaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'sitio.label', default: 'Sitio')])}</g:link>
</li>
</ul>

</div>

