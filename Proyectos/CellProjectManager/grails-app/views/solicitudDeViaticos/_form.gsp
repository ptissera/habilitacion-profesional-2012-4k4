<%@ page import="business.solicitud.SolicitudDeViaticos" %>

<g:javascript library="jquery" />
    <jqui:resources theme="ui-lightness" />
    <script type="text/javascript">
        $(document).ready(function()
        {
          $("#fechaPago").datepicker({dateFormat: 'dd/mm/yy'});
        })
    </script>
<g:if test="${solicitudDeViaticosInstance?.id}">
<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'fechaPago', 'error')} ">
	<label for="fechaPago">
		<g:message code="solicitudDeViaticos.fechaPago.label" default="Fecha Pago" />
		
	</label>
	<g:fecha name="fechaPago" defaultValue="${solicitudDeViaticosInstance.fechaPago}"/>
   
</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'solicitud', 'error')} required">
	<label for="solicitud">
		<g:message code="solicitudDeViaticos.solicitud.label" default="Solicitud" />
		
	</label>
	${solicitudDeViaticosInstance?.solicitud}
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'fechaCreacion', 'error')} required">
	<label for="fechaCreacion">
		<g:message code="solicitudDeViaticos.fechaCreacion.label" default="Fecha Creacion" />
		
	</label>
	<g:formatDate format="dd/MM/yyyy" date="${solicitudDeViaticosInstance?.fechaCreacion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'monto', 'error')} ">
	<label for="monto">
		<g:message code="solicitudDeViaticos.monto.label" default="Monto" />
		
	</label>
	<g:field type="number" name="monto" step="any" format="#,##" value="${solicitudDeViaticosInstance.monto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudDeViaticos.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="500" value="${solicitudDeViaticosInstance?.observaciones}"/>
</div>