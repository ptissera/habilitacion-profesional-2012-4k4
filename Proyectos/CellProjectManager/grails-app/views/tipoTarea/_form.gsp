<%@ page import="business.tarea.TipoTarea" %>



<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoTarea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${tipoTareaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoTarea.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${tipoTareaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTareaInstance, field: 'requierePermisoDeAcceso', 'error')} ">
  <label for="requierePermisoDeAcceso">
    <g:message code="tipoTarea.requierePermisoDeAcceso.label" default="Requiere Permiso De Acceso" />

  </label>
  <g:checkBox name="requierePermisoDeAcceso" value="${tipoTareaInstance?.requierePermisoDeAcceso}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: POInstance, field: 'requiereIngenieria', 'error')} ">
  <label for="requiereIngenieria">
    <g:message code="tipoTarea.requiereIngenieria.label" default="Requiere Ingenieria" />

  </label>
  <g:checkBox name="requiereIngenieria" value="${tipoTareaInstance?.requiereIngenieria}"  />
</div>



