<%@ page import="business.tarea.Acontecimiento" %>



<div class="fieldcontain ${hasErrors(bean: acontecimientoInstance, field: 'fechaCreacion', 'error')} required">
	<label for="fechaCreacion">
		<g:message code="acontecimiento.fechaCreacion.label" default="Fecha Creacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:fecha name="fechaCreacion" value="${acontecimientoInstance?.fechaCreacion}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: acontecimientoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="acontecimiento.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${acontecimientoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: acontecimientoInstance, field: 'tipoAcontecimiento', 'error')} required">
	<label for="tipoAcontecimiento">
		<g:message code="acontecimiento.tipoAcontecimiento.label" default="Tipo Acontecimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoAcontecimiento" name="tipoAcontecimiento.id" from="${business.tarea.TipoAcontecimiento.list()}" optionKey="id" required="" value="${acontecimientoInstance?.tipoAcontecimiento?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: acontecimientoInstance, field: 'creador', 'error')} required">
          <label for="creador">
                  <g:message code="acontecimiento.creador.label" default="Creador" />
                  <span class="required-indicator">*</span>
          </label>
          <g:select id="creador" name="creador.id" from="${support.secure.Usuario.list()}" optionKey="id" required="" value="" class="many-to-one"/>
</div>
