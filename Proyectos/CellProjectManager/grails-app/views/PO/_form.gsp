<%@ page import="business.core.PO" %>



<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'fechaRecibida', 'error')} required">
	<label for="fechaRecibida">
		<g:message code="PO.fechaRecibida.label" default="Fecha Recibida" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaRecibida" precision="day"  value="${POInstance?.fechaRecibida}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'monto', 'error')} required">
	<label for="monto">
		<g:message code="PO.monto.label" default="Monto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="monto" step="any" required="" value="${POInstance.monto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'esExtra', 'error')} ">
	<label for="esExtra">
		<g:message code="PO.esExtra.label" default="Es Extra" />
		
	</label>
	<g:checkBox name="esExtra" value="${POInstance?.esExtra}" />
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'nombreArchivo', 'error')} ">
	<label for="archivo">
		<g:message code="PO.archivo.label" default="Archivo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="archivo" name="archivo" />
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'cobro', 'error')} ">
	<label for="cobro">
		<g:message code="PO.cobro.label" default="Cobro" />
		
	</label>
	<g:select id="cobro" name="cobro.id" from="${business.core.Cobro.list()}" optionKey="id" value="${POInstance?.cobro?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'solicitud', 'error')} ">
	<label for="solicitud">
		<g:message code="PO.solicitud.label" default="Solicitud" />
		
	</label>
	<g:select id="solicitud" name="solicitud.id" from="${business.tarea.SolicitudDeTarea.list()}" optionKey="id" value="${POInstance?.solicitud?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

