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

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'solicitudDeTarea', 'error')} required">
	<label for="solicitudDeTarea">
		<g:message code="tareasPorSitio.solicitudDeTarea.label" default="Solicitud De Tarea" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitudDeTarea" name="solicitudDeTarea.id" from="${business.tarea.SolicitudDeTarea.list()}" optionKey="id" required="" value="${tareasPorSitioInstance?.solicitudDeTarea?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'herramientas', 'error')} ">
	<label for="herramientas">
		<g:message code="tareasPorSitio.herramientas.label" default="Herramientas" />
		
	</label>
	<g:select name="herramientas" from="${business.herramienta.PrestamoHerramienta.list()}" multiple="multiple" optionKey="id" size="5" value="${tareasPorSitioInstance?.herramientas*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareasPorSitioInstance, field: 'permisos', 'error')} ">
	<label for="permisos">
		<g:message code="tareasPorSitio.permisos.label" default="Permisos" />
		
	</label>
	<g:select name="permisos" from="${business.core.PermisoAcceso.list()}" multiple="multiple" optionKey="id" size="5" value="${tareasPorSitioInstance?.permisos*.id}" class="many-to-many"/>
</div>

