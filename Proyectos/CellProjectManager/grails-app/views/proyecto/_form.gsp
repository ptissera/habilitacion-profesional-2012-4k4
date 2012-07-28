<%@ page import="business.core.Proyecto" %>



<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="proyecto.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${proyectoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'fechaCreacion', 'error')} required">
	<label for="fechaCreacion">
		<g:message code="proyecto.fechaCreacion.label" default="Fecha Creacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreacion" precision="day"  value="${proyectoInstance?.fechaCreacion}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'fechaInicio', 'error')} ">
	<label for="fechaInicio">
		<g:message code="proyecto.fechaInicio.label" default="Fecha Inicio" />
		
	</label>
	<g:datePicker name="fechaInicio" precision="day"  value="${proyectoInstance?.fechaInicio}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'fechaFin', 'error')} ">
	<label for="fechaFin">
		<g:message code="proyecto.fechaFin.label" default="Fecha Fin" />
		
	</label>
	<g:datePicker name="fechaFin" precision="day"  value="${proyectoInstance?.fechaFin}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'estadoProyecto', 'error')} required">
	<label for="estadoProyecto">
		<g:message code="proyecto.estadoProyecto.label" default="Estado Proyecto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estadoProyecto" name="estadoProyecto.id" from="${business.core.EstadoProyecto.list()}" optionKey="id" required="" value="${proyectoInstance?.estadoProyecto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'licitacion', 'error')} required">
	<label for="licitacion">
		<g:message code="proyecto.licitacion.label" default="Licitacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="licitacion" name="licitacion.id" from="${business.core.Licitacion.list()}" optionKey="id" required="" value="${proyectoInstance?.licitacion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'usuario', 'error')} ">
	<label for="usuario">
		<g:message code="proyecto.usuario.label" default="Usuario" />
		
	</label>
	<g:select id="usuario" name="usuario.id" from="${support.secure.User.list()}" optionKey="id" value="${proyectoInstance?.usuario?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'solicitudes', 'error')} ">
	<label for="solicitudes">
		<g:message code="proyecto.solicitudes.label" default="Solicitudes" />
		
	</label>
	<g:select name="solicitudes" from="${business.core.SolicitudDeTarea.list()}" multiple="multiple" optionKey="id" size="5" value="${proyectoInstance?.solicitudes*.id}" class="many-to-many"/>
</div>

