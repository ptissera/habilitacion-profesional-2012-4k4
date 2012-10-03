<%@ page import="business.core.Proyecto" %>



<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'licitacion', 'error')} ">
	<label for="licitacion">
		<g:message code="proyecto.licitacion.label" default="Licitacion" />
		
	</label>
	<g:textField name="licitacion" value="${proyectoInstance?.licitacion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="proyecto.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${proyectoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="proyecto.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${proyectoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'fechaInicio', 'error')} ">
	<label for="fechaInicio">
		<g:message code="proyecto.fechaInicio.label" default="Fecha Inicio" />
		
	</label>
	<g:fecha name="fechaInicio" precision="day"  value="${proyectoInstance?.fechaInicio}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'fechaFin', 'error')} ">
	<label for="fechaFin">
		<g:message code="proyecto.fechaFin.label" default="Fecha Fin" />
		
	</label>
	<g:fecha name="fechaFin" precision="day"  value="${proyectoInstance?.fechaFin}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: proyectoInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="proyecto.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${business.core.Cliente.list()}" optionKey="id" required="" value="${proyectoInstance?.cliente?.id}" class="many-to-one"/>
</div>

