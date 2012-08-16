<%@ page import="business.documento.EstadoDocumento" %>



<div class="fieldcontain ${hasErrors(bean: estadoDocumentoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="estadoDocumento.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${estadoDocumentoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoDocumentoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="estadoDocumento.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${estadoDocumentoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoDocumentoInstance, field: 'documentos', 'error')} ">
	<label for="documentos">
		<g:message code="estadoDocumento.documentos.label" default="Documentos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${estadoDocumentoInstance?.documentos?}" var="d">
    <li><g:link controller="documento" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="documento" action="create" params="['estadoDocumento.id': estadoDocumentoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'documento.label', default: 'Documento')])}</g:link>
</li>
</ul>

</div>

