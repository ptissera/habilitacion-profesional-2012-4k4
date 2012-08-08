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
	<table>
				<thead>
                                  <tr><g:message code="cuadrilla.operarios.label" default="Operarios" /></tr>
					<tr>					
						<g:sortableColumn property="du" title="${message(code: 'empleado.du.label', default: 'Du')}" />					
						<g:sortableColumn property="nombre" title="${message(code: 'empleado.nombre.label', default: 'Nombre')}" />					
						<g:sortableColumn property="apellido" title="${message(code: 'empleado.apellido.label', default: 'Apellido')}" />					
						<g:sortableColumn property="legajo" title="${message(code: 'empleado.legajo.label', default: 'Legajo')}" />					
						<g:sortableColumn property="telefono" title="${message(code: 'empleado.telefono.label', default: 'Telefono')}" />					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'empleado.fechaAlta.label', default: 'Fecha Alta')}" />					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cuadrillaInstance?.operarios}" status="i" var="empleadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
						<td>${fieldValue(bean: empleadoInstance, field: "du")}</td>					
						<td>${fieldValue(bean: empleadoInstance, field: "nombre")}</td>					
						<td>${fieldValue(bean: empleadoInstance, field: "apellido")}</td>					
						<td>${fieldValue(bean: empleadoInstance, field: "legajo")}</td>					
						<td>${fieldValue(bean: empleadoInstance, field: "telefono")}</td>					
						<td><g:formatDate date="${empleadoInstance.fechaAlta}" /></td>					
					</tr>
				</g:each>
				</tbody>
			</table>
</div>

