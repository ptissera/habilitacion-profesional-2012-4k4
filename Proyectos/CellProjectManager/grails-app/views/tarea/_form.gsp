<%@ page import="business.tarea.Tarea" %>



<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'ordenEjecucion', 'error')} required">
  <label for="ordenEjecucion">
    <g:message code="tarea.ordenEjecucion.label" default="Orden de Ejecucion" />
    <span class="required-indicator">*</span>
  </label>
  <g:field type="number" name="ordenEjecucion" min="1" required="" value="${tareaInstance.ordenEjecucion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'fechaInicio', 'error')} required">
  <label for="fechaInicio">
    <g:message code="tarea.fechaInicio.label" default="Fecha Inicio" />
    <span class="required-indicator">*</span>
  </label>
  <g:fecha name="fechaInicio" value="${tareaInstance?.fechaInicio}"  />
</div>



<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'fechaFin', 'error')} required">
  <label for="fechaFin">
    <g:message code="tarea.fechaFin.label" default="Fecha Fin" />
    <span class="required-indicator">*</span>
  </label>
  <g:fecha name="fechaFin"  value="${tareaInstance?.fechaFin}"  />
</div>


<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'sitio', 'error')} required">
  <label for="sitio">
    <g:message code="tarea.sitio.label" default="Sitio" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="sitio" name="sitio.id" from="${business.core.Sitio.list()}" optionKey="id" required="" value="${tareaInstance?.sitio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'tipoTarea', 'error')} required">
  <label for="tipoTarea">
    <g:message code="tarea.tIpoTarea.label" default="Tipo de Tarea" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="tipoTarea" name="tipoTarea.id" from="${business.tarea.TipoTarea.list()}" optionKey="id" required="" value="${tareaInstance?.tipoTarea?.id}" class="many-to-one"/>
</div>

<g:if test="${tareaInstance?.id}">
  <div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'estado', 'error')} required">
    <label for="estado">
      <g:message code="tarea.estado.label" default="Estado" />
      <span class="required-indicator">*</span>
    </label>
    <g:select id="estado" name="estado.id" from="${business.tarea.EstadoTarea.list()}" optionKey="id" required="" value="${tareaInstance?.estado?.id}" class="many-to-one"/>
  </div>
</g:if>

<g:if test="${tareaInstance?.materialDeTarea}">
  <div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'materialDeTarea', 'error')} ">
    <label for="materialDeTarea">
      <g:message code="tarea.materialDeTarea.label" default="Material De Tarea" />

    </label>
    <table>
      <thead>
        <tr>
      <g:sortableColumn property="descripcion" title="${message(code: 'materialDeTarea.descripcion.label', default: 'Descripcion')}" />
      <g:sortableColumn property="cantidad" title="${message(code: 'materialDeTarea.cantidad.label', default: 'Cantidad')}" />
      <g:sortableColumn property="esDeCliente" title="${message(code: 'materialDeTarea.esDeCliente.label', default: 'Es De Cliente')}" />
      <th><g:message code="materialDeTarea.unidad.label" default="Unidad" /></th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${tareaInstance.materialDeTarea}" status="i" var="materialDeTareaInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
          <td><g:link action="show" controller="materialDeTarea" id="${materialDeTareaInstance.id}">${fieldValue(bean: materialDeTareaInstance, field: "descripcion")}</g:link></td>
        <td>${fieldValue(bean: materialDeTareaInstance, field: "cantidad")}</td>
        <td><g:formatBoolean boolean="${materialDeTareaInstance.esDeCliente}" /></td>
        <td>${fieldValue(bean: materialDeTareaInstance, field: "unidad")}</td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</g:if>

<g:if test="${tareaInstance?.equipoDeTarea}">
  <div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'equipoDeTarea', 'error')} ">
    <label for="equipoDeTarea">
      <g:message code="tarea.equipoDeTarea.label" default="Equipo De Tarea" />

    </label>	
    <table>
      <thead>
        <tr>
      <g:sortableColumn property="numeroSerie" title="${message(code: 'equipoDeTarea.numeroSerie.label', default: 'Numero Serie')}" />
      <g:sortableColumn property="descripcion" title="${message(code: 'equipoDeTarea.descripcion.label', default: 'Descripcion')}" />
      <th><g:message code="equipoDeTarea.tipo.label" default="Tipo" /></th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${tareaInstance.equipoDeTarea}" status="i" var="equipoDeTareaInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
          <td><g:link action="show" controller="equipoDeTarea" id="${equipoDeTareaInstance.id}">${fieldValue(bean: equipoDeTareaInstance, field: "numeroSerie")}</g:link></td>
        <td>${fieldValue(bean: equipoDeTareaInstance, field: "descripcion")}</td>
        <td>${fieldValue(bean: equipoDeTareaInstance, field: "tipo")}</td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</g:if>  

<g:if test="${tareaInstance?.permisos}">
  <div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'permisos', 'error')} ">
    <label for="permisos">
      <g:message code="tarea.permisos.label" default="Permisos" />

    </label>
    <table>
      <thead>
        <tr>					
      <g:sortableColumn property="fechaDesde" title="${message(code: 'permisoAcceso.fechaDesde.label', default: 'Fecha Desde')}" />					
      <g:sortableColumn property="fechaHasta" title="${message(code: 'permisoAcceso.fechaHasta.label', default: 'Fecha Hasta')}" />					
      <g:sortableColumn property="nombreArchivo" title="${message(code: 'permisoAcceso.nombreArchivo.label', default: 'Archivo')}" />										
      </tr>
      </thead>
      <tbody>
      <g:each in="${tareaInstance.permisos}" status="i" var="permisoAccesoInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
          <td><g:link action="show" controller="permisoAcceso" id="${permisoAccesoInstance.id}"><g:formatDate format="dd/MM/yyyy" date="${permisoAccesoInstance.fechaDesde}" /></g:link></td>					
        <td><g:formatDate format="dd/MM/yyyy" date="${permisoAccesoInstance.fechaHasta}" /></td>					
        <td>${fieldValue(bean: permisoAccesoInstance, field: "nombreArchivo")}</td>					
        </tr>
      </g:each>
      </tbody>
    </table>

  </div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'documentoDeIngenieria', 'error')} ">
  <label for="archivo">
    <g:message code="documento.documentoDeIngenieria.label" default="Documento De Ingenieria" />
    <span class="required-indicator">*</span>
  </label>
  <input type="file" id="uploadArchivo" name="uploadArchivo" />
  <g:if test="${tareaInstance?.documentoDeIngenieria}">
    <div> 
      <label for="archivo"></label>
      <g:link action="downloadFile" id="${tareaInstance?.id}"> 
        <g:fieldValue bean="${tareaInstance}" field="documentoDeIngenieria"/>
      </g:link>      
    </div>
  </g:if>
</div>


<div class="fieldcontain ${hasErrors(bean: tareaInstance, field: 'observaciones', 'error')} ">
  <label for="observaciones">
    <g:message code="tarea.observaciones.label" default="Observaciones" />

  </label>
  <g:textField name="observaciones" value="${tareaInstance?.observaciones}"/>
</div>

