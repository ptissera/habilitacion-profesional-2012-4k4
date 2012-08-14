<%@ page import="business.core.Sitio" %>



<div class="fieldcontain ${hasErrors(bean: sitioInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="sitio.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${sitioInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sitioInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="sitio.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="direccion" required="" value="${sitioInstance?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sitioInstance, field: 'latitud', 'error')} ">
	<label for="latitud">
		<g:message code="sitio.latitud.label" default="Latitud" />
		
	</label>
	<g:textField name="latitud" value="${sitioInstance?.latitud}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sitioInstance, field: 'longitud', 'error')} ">
	<label for="longitud">
		<g:message code="sitio.longitud.label" default="Longitud" />
		
	</label>
	<g:textField name="longitud" value="${sitioInstance?.longitud}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sitioInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="sitio.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" value="${sitioInstance?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sitioInstance, field: 'provincia', 'error')} required">
	<label for="provincia">
		<g:message code="sitio.provincia.label" default="Provincia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="provincia" name="provincia.id" from="${business.core.Provincia.list()}" optionKey="id" required="" value="${sitioInstance?.provincia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sitioInstance, field: 'tareasPorSitio', 'error')} ">
	<label for="tareasPorSitio">
		<g:message code="sitio.tareasPorSitio.label" default="Tareas Por Sitio" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${sitioInstance?.tareasPorSitio?}" var="t">
    <li><g:link controller="tareasPorSitio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tareasPorSitio" action="create" params="['sitio.id': sitioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tareasPorSitio.label', default: 'TareasPorSitio')])}</g:link>
</li>
</ul>

</div>

