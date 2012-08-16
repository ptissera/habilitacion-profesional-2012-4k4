<%@ page import="business.documento.TipoDocumento" %>



<div class="fieldcontain ${hasErrors(bean: tipoDocumentoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoDocumento.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoDocumentoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoDocumento.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoDocumentoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentoInstance, field: 'documentos', 'error')} ">
	<label for="documentos">
		<g:message code="tipoDocumento.documentos.label" default="Documentos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tipoDocumentoInstance?.documentos?}" var="d">
    <li><g:link controller="documento" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="documento" action="create" params="['tipoDocumento.id': tipoDocumentoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'documento.label', default: 'Documento')])}</g:link>
</li>
</ul>

</div>

