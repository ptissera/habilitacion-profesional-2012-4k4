<%@ page import="business.cuadrillas.TipoDocumentacionIntegranteCuadrilla" %>



<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoDocumentacionIntegranteCuadrilla.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoDocumentacionIntegranteCuadrillaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoDocumentacionIntegranteCuadrilla.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoDocumentacionIntegranteCuadrillaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: 'diaAntesVencimiento', 'error')} required">
	<label for="diaAntesVencimiento">
		<g:message code="tipoDocumentacionIntegranteCuadrilla.diaAntesVencimiento.label" default="Dia Antes Vencimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="diaAntesVencimiento" from="${1..30}" class="range" required="" value="${fieldValue(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: 'diaAntesVencimiento')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentacionIntegranteCuadrillaInstance, field: 'documentacionIntegranteCuadrilla', 'error')} ">
	<label for="documentacionIntegranteCuadrilla">
		<g:message code="tipoDocumentacionIntegranteCuadrilla.documentacionIntegranteCuadrilla.label" default="Documentacion Integrante Cuadrilla" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tipoDocumentacionIntegranteCuadrillaInstance?.documentacionIntegranteCuadrilla?}" var="d">
    <li><g:link controller="documentacionIntegranteCuadrilla" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="documentacionIntegranteCuadrilla" action="create" params="['tipoDocumentacionIntegranteCuadrilla.id': tipoDocumentacionIntegranteCuadrillaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'documentacionIntegranteCuadrilla.label', default: 'DocumentacionIntegranteCuadrilla')])}</g:link>
</li>
</ul>

</div>

