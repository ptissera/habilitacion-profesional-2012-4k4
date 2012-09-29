<%@ page import="support.tool.ParametrosDelSistema" %>



<div class="fieldcontain ${hasErrors(bean: parametrosDelSistemaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="parametrosDelSistema.nombre.label" default="Nombre" />		
	</label>
	<g:fieldValue bean="${parametrosDelSistemaInstance}" field="nombre"/>
</div>

<div class="fieldcontain ${hasErrors(bean: parametrosDelSistemaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="parametrosDelSistema.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${parametrosDelSistemaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: parametrosDelSistemaInstance, field: 'valor', 'error')} required">
	<label for="valor">
		<g:message code="parametrosDelSistema.valor.label" default="Valor" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="valor" required="" value="${parametrosDelSistemaInstance?.valor}"/>
</div>

