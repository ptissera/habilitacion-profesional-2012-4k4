<%@ page import="business.cuadrillas.TipoDocumentacionEmpleado" %>



<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionEmpleadoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoDocumentacionEmpleado.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoDocumentacionEmpleadoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionEmpleadoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoDocumentacionEmpleado.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoDocumentacionEmpleadoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionEmpleadoInstance, field: 'requerida', 'error')} ">
	<label for="requerida">
		<g:message code="tipoDocumentacionEmpleado.requerida.label" default="Requerida" />
		
	</label>
	<g:checkBox name="requerida" value="${tipoDocumentacionEmpleadoInstance?.requerida}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionEmpleadoInstance, field: 'people', 'error')} ">
	<label for="people">
		<g:message code="tipoDocumentacionEmpleado.people.label" default="People" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tipoDocumentacionEmpleadoInstance?.people?}" var="p">
    <li><g:link controller="documentacionEmpleado" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="documentacionEmpleado" action="create" params="['tipoDocumentacionEmpleado.id': tipoDocumentacionEmpleadoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado')])}</g:link>
</li>
</ul>

</div>

