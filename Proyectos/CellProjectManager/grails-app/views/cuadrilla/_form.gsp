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
	<label for="operarios">
		<g:message code="cuadrilla.operarios.label" default="Operarios" />
		
	</label>
	<g:select name="operarios" from="${business.cuadrillas.Empleado.list()}" multiple="multiple" optionKey="id" size="5" value="${cuadrillaInstance?.operarios*.id}" class="many-to-many"/>
</div>

