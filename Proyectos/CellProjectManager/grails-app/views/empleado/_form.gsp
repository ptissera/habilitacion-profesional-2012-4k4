<%@ page import="business.cuadrillas.Empleado" %>



<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'du', 'error')} ">
	<label for="du">
		<g:message code="empleado.du.label" default="Du" />
		
	</label>
	<g:textField name="du" value="${empleadoInstance?.du}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="empleado.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${empleadoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="empleado.apellido.label" default="Apellido" />
		
	</label>
	<g:textField name="apellido" value="${empleadoInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'legajo', 'error')} ">
	<label for="legajo">
		<g:message code="empleado.legajo.label" default="Legajo" />
		
	</label>
	<g:textField name="legajo" value="${empleadoInstance?.legajo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="empleado.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" value="${empleadoInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'documentacion', 'error')} ">
	<label for="documentacion">
		<g:message code="empleado.documentacion.label" default="Documentacion" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${empleadoInstance?.documentacion?}" var="d">
    <li><g:link controller="documentacionEmpleado" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="documentacionEmpleado" action="create" params="['empleado.id': empleadoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'documentacionEmpleado.label', default: 'DocumentacionEmpleado')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="empleado.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${empleadoInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'fechaBaja', 'error')} required">
	<label for="fechaBaja">
		<g:message code="empleado.fechaBaja.label" default="Fecha Baja" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaBaja" precision="day"  value="${empleadoInstance?.fechaBaja}"  />
</div>

