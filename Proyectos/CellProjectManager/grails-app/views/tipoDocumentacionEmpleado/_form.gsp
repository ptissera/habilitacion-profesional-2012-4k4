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
</di>

