<%@ page import="business.solicitud.SolicitudPagoCuadrilla" %>

<g:if test="${solicitudPagoCuadrillaInstance?.id}">
<div class="fieldcontain ${hasErrors(bean: solicitudPagoCuadrillaInstance, field: 'fechaPago', 'error')} ">
	<label for="fechaPago">
		<g:message code="solicitudPagoCuadrilla.fechaPago.label" default="Fecha Pago" />
		
	</label>
        <g:fecha name="fechaPago" value="${solicitudPagoCuadrillaInstance.fechaPago}"/>
</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: solicitudPagoCuadrillaInstance, field: 'porcentaje', 'error')} required">
	<label for="porcentaje">
		<g:message code="solicitudPagoCuadrilla.porcentaje.label" default="Porcentaje" />	
	</label>
        <g:if test="${!solicitudPagoCuadrillaInstance?.id}">
        
	<g:select name="porcentaje" from="${0..solicitudPagoCuadrillaInstance.porcentaje}" class="range" required="" value="${fieldValue(bean: solicitudPagoCuadrillaInstance, field: 'porcentaje')}"/>
        </g:if>
  <g:if test="${solicitudPagoCuadrillaInstance?.id}">
        
	<g:fieldValue bean="${solicitudPagoCuadrillaInstance}" field="porcentaje"/>
        </g:if>
        %
</div>

<g:if test="${solicitudPagoCuadrillaInstance?.id}">
<div class="fieldcontain ${hasErrors(bean: solicitudPagoCuadrillaInstance, field: 'monto', 'error')} ">
	<label for="monto">
		<g:message code="solicitudPagoCuadrilla.monto.label" default="Monto" />
		
	</label>
	<g:field type="number" name="monto" step="any" value="${solicitudPagoCuadrillaInstance.monto.toString().replace('.',',')}"/>
</div>
</g:if>
<div class="fieldcontain ${hasErrors(bean: solicitudPagoCuadrillaInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudPagoCuadrilla.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="500" value="${solicitudPagoCuadrillaInstance?.observaciones}"/>
</div>

