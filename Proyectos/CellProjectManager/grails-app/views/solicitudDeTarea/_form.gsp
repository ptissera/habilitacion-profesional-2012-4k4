<%@ page import="business.tarea.SolicitudDeTarea" %>

<div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'cuadrilla', 'error')} required">
  <label for="cuadrilla">
    <g:message code="solicitudDeTarea.cuadrilla.label" default="Cuadrilla" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="cuadrilla" name="cuadrilla.id" from="${business.cuadrillas.Cuadrilla.list()}" optionKey="id" required="" value="${solicitudDeTareaInstance?.cuadrilla?.id}" class="many-to-one"/>
</div>

<g:if test="${solicitudDeTareaInstance?.tarea}">
  <div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'tarea', 'error')} ">
    <label for="tarea">
      <g:message code="solicitudDeTarea.tarea.label" default="Tareas Por Sitio" />

    </label>
    <table>
      <thead>
        <tr>					
      <g:sortableColumn property="ordenEjecucion" title="Ord" />					
      <g:sortableColumn property="fechaInicio" title="${message(code: 'tarea.fechaInicio.label', default: 'Fecha Inicio')}" />					
      <th><g:message code="tarea.sitio.label" default="Sitio" /></th>					
      <th><g:message code="tarea.tarea.label" default="Tarea" /></th>					
      <th><g:message code="tarea.estado.label" default="Estado" /></th>				
      </tr>
      </thead>
      <tbody>
      <g:each in="${solicitudDeTareaInstance.tarea}" status="i" var="tareaInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
          <td><g:link action="show" controller="tarea" id="${tareaInstance.id}">${fieldValue(bean: tareaInstance, field: "ordenEjecucion")}</g:link></td>					
        <td><g:formatDate format="dd/MM/yyyy" date="${tareaInstance.fechaInicio}" /></td>					
        <td>${fieldValue(bean: tareaInstance, field: "sitio")}</td>					
        <td>${fieldValue(bean: tareaInstance, field: "tipoTarea")}</td>					
        <td>${fieldValue(bean: tareaInstance, field: "estado")}</td>
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
    </table
</div>
</g:if>
<g:if test="${solicitudDeTareaInstance?.prestamos}">   
  <div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'prestamos', 'error')} ">
    <label for="prestamos">
      <g:message code="solicitudDeTarea.prestamos.label" default="Prestamos" />		
    </label>
    <table>
      <thead>
        <tr>
      <g:sortableColumn property="fechaPrestamo" title="${message(code: 'prestamoHerramienta.fechaPrestamo.label', default: 'Fecha Prestamo')}" />      
      <th><g:message code="prestamoHerramienta.herramienta.label" default="Herramienta" /></th>      
      <g:sortableColumn property="descripcion" title="${message(code: 'prestamoHerramienta.descripcion.label', default: 'Descripcion')}" />
      </tr>
      </thead>
      <tbody>
      <g:each in="${solicitudDeTareaInstance.prestamos}" status="i" var="prestamoHerramientaInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
          <td><g:link action="show" controller="prestamoHerramienta" id="${prestamoHerramientaInstance.id}"><g:formatDate format="dd/MM/yyyy" date="${prestamoHerramientaInstance.fechaPrestamo}" /></g:link></td>        
        <td>${fieldValue(bean: prestamoHerramientaInstance, field: "herramienta")}</td>        
        <td>${fieldValue(bean: prestamoHerramientaInstance, field: "descripcion")}</td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</g:if>

<g:if test="${solicitudDeTareaInstance?.documentos}">
  <div class="fieldcontain ${hasErrors(bean: solicitudDeTareaInstance, field: 'documentos', 'error')} ">
    <label for="documentos">
      <g:message code="solicitudDeTarea.documentos.label" default="Documentos" />

    </label>
    <table>
      <thead>
        <tr>
      <th><g:message code="documento.tipo.label" default="Tipo" /></th>
      <g:sortableColumn property="observaciones" title="${message(code: 'documento.observaciones.label', default: 'Observaciones')}" />
      <g:sortableColumn property="fechaRealizado" title="${message(code: 'documento.fechaRealizado.label', default: 'Fecha Realizado')}" />
      <g:sortableColumn property="fechaEnviado" title="${message(code: 'documento.fechaEnviado.label', default: 'Fecha Enviado')}" />
      <g:sortableColumn property="fechaAprobado" title="${message(code: 'documento.fechaAprobado.label', default: 'Fecha Aprobado')}" />
      </tr>
      </thead>
      <tbody>
      <g:each in="${solicitudDeTareaInstance?.documentos}" status="i" var="documentoInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td><g:link action="show" controller="documento" id="${documentoInstance.id}">${fieldValue(bean: documentoInstance, field: "tipo")}</g:link></td>
        <td>${fieldValue(bean: documentoInstance, field: "observaciones")}</td>
        <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaRealizado}" /></td>
        <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaEnviado}" /></td>
        <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaAprobado}" /></td>
        </tr>
      </g:each>
      </tbody>
    </table>

  </div>
</g:if>
