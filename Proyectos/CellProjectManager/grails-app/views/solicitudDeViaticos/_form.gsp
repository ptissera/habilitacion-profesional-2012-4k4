<%@ page import="business.solicitud.SolicitudDeViaticos" %>



<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'solicitud', 'error')} required">
	<label for="solicitud">
		<g:message code="solicitudDeViaticos.solicitud.label" default="Solicitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitud" name="solicitud.id" from="${business.tarea.SolicitudDeTarea.list()}" optionKey="id" required="" value="${solicitudDeViaticosInstance?.solicitud?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'fechaCreacion', 'error')} required">
	<label for="fechaCreacion">
		<g:message code="solicitudDeViaticos.fechaCreacion.label" default="Fecha Creacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreacion" precision="day"  value="${solicitudDeViaticosInstance?.fechaCreacion}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'fechaPago', 'error')} ">
	<label for="fechaPago">
		<g:message code="solicitudDeViaticos.fechaPago.label" default="Fecha Pago" />
		
	</label>
	<g:datePicker name="fechaPago" precision="day"  value="${solicitudDeViaticosInstance?.fechaPago}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'monto', 'error')} ">
	<label for="monto">
		<g:message code="solicitudDeViaticos.monto.label" default="Monto" />
		
	</label>
	<g:field type="number" name="monto" step="any" value="${solicitudDeViaticosInstance.monto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudDeViaticos.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="500" value="${solicitudDeViaticosInstance?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeViaticosInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="solicitudDeViaticos.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${business.solicitud.EstadoSolicitudDeViaticos.list()}" optionKey="id" required="" value="${solicitudDeViaticosInstance?.estado?.id}" class="many-to-one"/>
</div>

