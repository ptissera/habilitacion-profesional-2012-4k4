<%@ page import="business.cuadrillas.Cuadrilla" %>



<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="cuadrilla.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${cuadrillaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="cuadrilla.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${cuadrillaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'propia', 'error')} ">
	<label for="propia">
		<g:message code="cuadrilla.propia.label" default="Propia" />
		
	</label>
	<g:checkBox name="propia" value="${cuadrillaInstance?.propia}" />
</div>

<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'estadoCuadrilla', 'error')} required">
	<label for="estadoCuadrilla">
		<g:message code="cuadrilla.estadoCuadrilla.label" default="Estado Cuadrilla" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estadoCuadrilla" name="estadoCuadrilla.id" from="${business.cuadrillas.EstadoCuadrilla.list()}" optionKey="id" required="" value="${cuadrillaInstance?.estadoCuadrilla?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'operarios', 'error')} ">
	<label for="operarios">
		<g:message code="cuadrilla.operarios.label" default="Operarios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cuadrillaInstance?.operarios?}" var="o">
    <li><g:link controller="integranteCuadrilla" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="integranteCuadrilla" action="create" params="['cuadrilla.id': cuadrillaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'historialDeCambios', 'error')} ">
	<label for="historialDeCambios">
		<g:message code="cuadrilla.historialDeCambios.label" default="Historial De Cambios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cuadrillaInstance?.historialDeCambios?}" var="h">
    <li><g:link controller="historialCuadrilla" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="historialCuadrilla" action="create" params="['cuadrilla.id': cuadrillaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'historialCuadrilla.label', default: 'HistorialCuadrilla')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'prestatmosHerramientas', 'error')} ">
	<label for="prestatmosHerramientas">
		<g:message code="cuadrilla.prestatmosHerramientas.label" default="Prestatmos Herramientas" />
		
	</label>
	<g:select name="prestatmosHerramientas" from="${business.core.PrestamoHerramienta.list()}" multiple="multiple" optionKey="id" size="5" value="${cuadrillaInstance?.prestatmosHerramientas*.id}" class="many-to-many"/>
</div>

