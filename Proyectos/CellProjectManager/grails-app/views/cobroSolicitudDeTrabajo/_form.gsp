<%@ page import="business.solicitud.CobroSolicitudDeTrabajo" %>



<div class="fieldcontain ${hasErrors(bean: cobroSolicitudDeTrabajoInstance, field: 'solicitud', 'error')} required">
	<label for="solicitud">
		<g:message code="cobroSolicitudDeTrabajo.solicitud.label" default="Solicitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitud" name="solicitud.id" from="${business.tarea.SolicitudDeTarea.list()}" optionKey="id" required="" value="${cobroSolicitudDeTrabajoInstance?.solicitud?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cobroSolicitudDeTrabajoInstance, field: 'fechaCobro', 'error')} required">
	<label for="fechaCobro">
		<g:message code="cobroSolicitudDeTrabajo.fechaCobro.label" default="Fecha Cobro" />
		<span class="required-indicator">*</span>
	</label>
	<g:fecha name="fechaCobro" precision="day"  value="${cobroSolicitudDeTrabajoInstance?.fechaCobro}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: cobroSolicitudDeTrabajoInstance, field: 'monto', 'error')} ">
	<label for="monto">
		<g:message code="cobroSolicitudDeTrabajo.monto.label" default="Monto" />
		
	</label>
	<g:field type="number" name="monto" step="any" value="${cobroSolicitudDeTrabajoInstance.monto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cobroSolicitudDeTrabajoInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="cobroSolicitudDeTrabajo.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="500" value="${cobroSolicitudDeTrabajoInstance?.observaciones}"/>
</div>

