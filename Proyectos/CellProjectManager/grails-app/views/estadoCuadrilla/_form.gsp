<%@ page import="business.cuadrillas.EstadoCuadrilla" %>



<div class="fieldcontain ${hasErrors(bean: estadoCuadrillaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="estadoCuadrilla.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${estadoCuadrillaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoCuadrillaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="estadoCuadrilla.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${estadoCuadrillaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoCuadrillaInstance, field: 'cuadrillas', 'error')} ">
	<label for="cuadrillas">
		<g:message code="estadoCuadrilla.cuadrillas.label" default="Cuadrillas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${estadoCuadrillaInstance?.cuadrillas?}" var="c">
    <li><g:link controller="cuadrilla" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="cuadrilla" action="create" params="['estadoCuadrilla.id': estadoCuadrillaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'cuadrilla.label', default: 'Cuadrilla')])}</g:link>
</li>
</ul>

</div>

