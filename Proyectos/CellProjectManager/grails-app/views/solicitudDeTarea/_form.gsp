<%@ page import="business.tarea.SolicitudDeTarea" %>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'cuadrilla', 'error')} required">
	<label for="cuadrilla">
		<g:message code="solicitudDeTarea.cuadrilla.label" default="Cuadrilla" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuadrilla" name="cuadrilla.id" from="${business.cuadrillas.Cuadrilla.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.cuadrilla?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="solicitudDeTarea.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${business.tarea.EstadoSolicitudTarea.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.estado?.id}" class="many-to-one"/>
</div>

<g:if test="${solicitudDeTareaInstance?.tareasPorSitio}">
<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'tareasPorSitio', 'error')} ">
	<label for="tareasPorSitio">
		<g:message code="solicitudDeTarea.tareasPorSitio.label" default="Tareas Por Sitio" />
		
	</label>
          <table>
            <thead>
              <tr>					
            <g:sortableColumn property="ordenEjecucion" title="Ord" />					
            <g:sortableColumn property="fechaInicio" title="${message(code: 'tareasPorSitio.fechaInicio.label', default: 'Fecha Inicio')}" />					
            <th><g:message code="tareasPorSitio.sitio.label" default="Sitio" /></th>					
            <th><g:message code="tareasPorSitio.tarea.label" default="Tarea" /></th>					
            <th><g:message code="tareasPorSitio.estado.label" default="Estado" /></th>				
            </tr>
            </thead>
            <tbody>
            <g:each in="${solicitudDeTareaInstance.tareasPorSitio}" status="i" var="tareasPorSitioInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                <td><g:link action="show" controller="tareasPorSitio" id="${tareasPorSitioInstance.id}">${fieldValue(bean: tareasPorSitioInstance, field: "ordenEjecucion")}</g:link></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${tareasPorSitioInstance.fechaInicio}" /></td>					
              <td>${fieldValue(bean: tareasPorSitioInstance, field: "sitio")}</td>					
              <td>${fieldValue(bean: tareasPorSitioInstance, field: "tarea")}</td>					
              <td>${fieldValue(bean: tareasPorSitioInstance, field: "estado")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>
</div>
</g:if>

<g:if test="${solicitudDeTareaInstance?.pos}">   
<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'pos', 'error')} ">
	<label for="pos"> POs </label>       
          <table>
            <thead>
              <tr>					
            <g:sortableColumn property="fechaRecibida" title="${message(code: 'PO.fechaRecibida.label', default: 'Fecha Recibida')}" />					
            <g:sortableColumn property="monto" title="${message(code: 'PO.monto.label', default: 'Monto')}" />					
            <g:sortableColumn property="esExtra" title="${message(code: 'PO.esExtra.label', default: 'Es Extra')}" />															
            <g:sortableColumn property="archivo" title="${message(code: 'PO.archivo.label', default: 'Archivo')}" />
            </tr>
            </thead>
            <tbody>
            <g:each in="${solicitudDeTareaInstance.pos}" status="i" var="POInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                <td><g:link action="show" controller="PO" id="${POInstance.id}"><g:formatDate format="dd/MM/yyyy" date="${POInstance.fechaRecibida}" /></g:link></td>					
              <td>${fieldValue(bean: POInstance, field: "monto")}</td>					
              <td><g:formatBoolean boolean="${POInstance.esExtra}" /></td>					
              <td>${fieldValue(bean: POInstance, field: "nombreArchivo")}</td>											
              </tr>
            </g:each>
            </tbody>
          </table>
</div>
</g:if>

<g:if test="${solicitudDeTareaInstance?.prestamos}">   
<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'prestamos', 'error')} ">
	<label for="prestamos">
		<g:message code="solicitudDeTarea.prestamos.label" default="Prestamos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${solicitudDeTareaInstance?.prestamos?}" var="p">
    <li><g:link controller="prestamoHerramienta" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>

</div>
</g:if>
