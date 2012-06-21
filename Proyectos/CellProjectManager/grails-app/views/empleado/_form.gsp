<%@ page import="business.cuadrillas.Empleado" %>



<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'du', 'error')} ">
	<label for="du">
		<g:message code="empleado.du.label" default="Du" />
		
	</label>
	<g:textField name="du" value="${empleadoInstance?.du}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="empleado.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${empleadoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="empleado.apellido.label" default="Apellido" />
		
	</label>
	<g:textField name="apellido" value="${empleadoInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'legajo', 'error')} ">
	<label for="legajo">
		<g:message code="empleado.legajo.label" default="Legajo" />
		
	</label>
	<g:textField name="legajo" value="${empleadoInstance?.legajo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="empleado.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" value="${empleadoInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoInstance, field: 'documentacion', 'error')} ">	
<table>
            <thead>
              <tr><g:message code="empleado.documentacion.label" default="Documentacion" /></tr>
              <tr>              
                <th><g:message code="documentacionEmpleado.tipoDocumento.label" default="Tipo Documento" /></th>					
            <g:sortableColumn property="vigenciaDesde" title="${message(code: 'documentacionEmpleado.vigenciaDesde.label', default: 'Vigencia Desde')}" />					
            <g:sortableColumn property="vigenciaHasta" title="${message(code: 'documentacionEmpleado.vigenciaHasta.label', default: 'Vigencia Hasta')}" />					
            <g:sortableColumn property="descripcion" title="${message(code: 'documentacionEmpleado.descripcion.label', default: 'Descripcion')}" />									
            </tr>
            </thead>
            <tbody>
            <g:each in="${empleadoInstance?.documentacion}" status="i" var="documentacionEmpleadoInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                <td><g:link action="show" id="${documentacionEmpleadoInstance.id}">${fieldValue(bean: documentacionEmpleadoInstance, field: "tipoDocumento")}</g:link></td>					
              <td><g:formatDate date="${documentacionEmpleadoInstance.vigenciaDesde}" /></td>					
              <td><g:formatDate date="${documentacionEmpleadoInstance.vigenciaHasta}" /></td>					
              <td>${fieldValue(bean: documentacionEmpleadoInstance, field: "descripcion")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>

</div>
