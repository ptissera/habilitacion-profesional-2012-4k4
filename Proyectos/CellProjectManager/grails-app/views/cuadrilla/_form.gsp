<%@ page import="business.cuadrillas.Cuadrilla" %>



<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="cuadrilla.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${cuadrillaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuadrillaInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="cuadrilla.estado.label" default="Estado" />
		
	</label>
	<g:textField name="estado" value="${cuadrillaInstance?.estado}"/>
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
					
						<td><g:link action="show" id="${empleadoInstance.id}">${fieldValue(bean: empleadoInstance, field: "du")}</g:link></td>
					
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

