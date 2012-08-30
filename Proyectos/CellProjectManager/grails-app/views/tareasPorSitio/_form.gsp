<%@ page import="business.tarea.TareasPorSitio" %>



<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'ordenEjecucion', 'error')} required">
	<label for="ordenEjecucion">
		<g:message code="tareasPorSitio.ordenEjecucion.label" default="Orden Ejecucion" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="ordenEjecucion" min="1" required="" value="${tareasPorSitioInstance.ordenEjecucion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'fechaInicio', 'error')} required">
	<label for="fechaInicio">
		<g:message code="tareasPorSitio.fechaInicio.label" default="Fecha Inicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicio" precision="day"  value="${tareasPorSitioInstance?.fechaInicio}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'sitio', 'error')} required">
	<label for="sitio">
		<g:message code="tareasPorSitio.sitio.label" default="Sitio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sitio" name="sitio.id" from="${business.core.Sitio.list()}" optionKey="id" required="" value="${tareasPorSitioInstance?.sitio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'tarea', 'error')} required">
	<label for="tarea">
		<g:message code="tareasPorSitio.tarea.label" default="Tarea" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tarea" name="tarea.id" from="${business.tarea.Tarea.list()}" optionKey="id" required="" value="${tareasPorSitioInstance?.tarea?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="tareasPorSitio.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${business.tarea.EstadoTarea.list()}" optionKey="id" required="" value="${tareasPorSitioInstance?.estado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'documentos', 'error')} ">
	<label for="documentos">
		<g:message code="tareasPorSitio.documentos.label" default="Documentos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tareasPorSitioInstance?.documentos?}" var="d">
    <li><g:link controller="documento" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'materialDeTarea', 'error')} ">
	<label for="materialDeTarea">
		<g:message code="tareasPorSitio.materialDeTarea.label" default="Material De Tarea" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tareasPorSitioInstance?.materialDeTarea?}" var="m">
    <li><g:link controller="materialDeTarea" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'equipoDeTarea', 'error')} ">
	<label for="equipoDeTarea">
		<g:message code="tareasPorSitio.equipoDeTarea.label" default="Equipo De Tarea" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tareasPorSitioInstance?.equipoDeTarea?}" var="e">
    <li><g:link controller="equipoDeTarea" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'permisos', 'error')} ">
	<label for="permisos">
		<g:message code="tareasPorSitio.permisos.label" default="Permisos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tareasPorSitioInstance?.permisos?}" var="p">
    <li><g:link controller="permisoAcceso" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="tareasPorSitio.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" value="${tareasPorSitioInstance?.observaciones}"/>
</div>

