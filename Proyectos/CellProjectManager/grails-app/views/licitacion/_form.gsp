<%@ page import="business.core.Licitacion" %>



<div class="fieldcontain ${hasErrors(bean: licitacionInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="licitacion.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${licitacionInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: licitacionInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="licitacion.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${licitacionInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: licitacionInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="licitacion.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${business.core.Cliente.list()}" optionKey="id" required="" value="${licitacionInstance?.cliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: licitacionInstance, field: 'proyectos', 'error')} ">
	<label for="proyectos">
		<g:message code="licitacion.proyectos.label" default="Proyectos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${licitacionInstance?.proyectos?}" var="p">
    <li><g:link controller="proyecto" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="proyecto" action="create" params="['licitacion.id': licitacionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'proyecto.label', default: 'Proyecto')])}</g:link>
</li>
</ul>

</div>

