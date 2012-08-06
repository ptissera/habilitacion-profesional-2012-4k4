<%@ page import="business.cuadrillas.IntegranteCuadrilla" %>



<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'du', 'error')} ">
	<label for="du">
		<g:message code="integranteCuadrilla.du.label" default="Du" />
		
	</label>
	<g:textField name="du" value="${integranteCuadrillaInstance?.du}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="integranteCuadrilla.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${integranteCuadrillaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="integranteCuadrilla.apellido.label" default="Apellido" />
		
	</label>
	<g:textField name="apellido" value="${integranteCuadrillaInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'legajo', 'error')} ">
	<label for="legajo">
		<g:message code="integranteCuadrilla.legajo.label" default="Legajo" />
		
	</label>
	<g:textField name="legajo" value="${integranteCuadrillaInstance?.legajo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="integranteCuadrilla.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" value="${integranteCuadrillaInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'documentacion', 'error')} ">			
        <table>
                <thead>
                        <tr>	
                                <th><g:message code="documentacionIntegranteCuadrilla.tipoDocumento.label" default="Tipo Documento" /></th>
                                <g:sortableColumn property="vigenciaDesde" title="${message(code: 'documentacionIntegranteCuadrilla.vigenciaDesde.label', default: 'Vigencia Desde')}" />					
                                <g:sortableColumn property="vigenciaHasta" title="${message(code: 'documentacionIntegranteCuadrilla.vigenciaHasta.label', default: 'Vigencia Hasta')}" />					
                                <g:sortableColumn property="descripcion" title="${message(code: 'documentacionIntegranteCuadrilla.descripcion.label', default: 'Descripcion')}" />																
                        </tr>
                </thead>
                <tbody>
                <g:each in="${documentacionIntegranteCuadrillaInstanceList}" status="i" var="documentacionIntegranteCuadrillaInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                                <td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "tipoDocumento")}</td>					
                                <td><g:formatDate format="dd/MM/yyyy" date="${documentacionIntegranteCuadrillaInstance.vigenciaDesde}" /></td>					
                                <td><g:formatDate format="dd/MM/yyyy" date="${documentacionIntegranteCuadrillaInstance.vigenciaHasta}" /></td>
                                <td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "descripcion")}</td>					
                        </tr>
                </g:each>
                </tbody>
        </table>			
</div>
