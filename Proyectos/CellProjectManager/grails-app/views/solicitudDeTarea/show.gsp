
<%@ page import="business.tarea.SolicitudDeTarea" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'solicitudDeTarea.label', default: 'SolicitudDeTarea')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-solicitudDeTarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>

      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="show-solicitudDeTarea" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">      
      <ul class="errors" role="alert"><li>${flash.error}</li></ul>        
    </g:if>
    <ol class="property-list solicitudDeTarea">

      <g:if test="${solicitudDeTareaInstance?.fechaAlta}">
        <li class="fieldcontain">
          <span id="fechaAlta-label" class="property-label"><g:message code="solicitudDeTarea.fechaAlta.label" default="Fecha Alta" /></span>

          <span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate format="dd/MM/yyyy" date="${solicitudDeTareaInstance?.fechaAlta}" /></span>

        </li>
      </g:if>

      <g:if test="${solicitudDeTareaInstance?.cuadrilla}">
        <li class="fieldcontain">
          <span id="cuadrilla-label" class="property-label"><g:message code="solicitudDeTarea.cuadrilla.label" default="Cuadrilla" /></span>

          <span class="property-value" aria-labelledby="cuadrilla-label">${solicitudDeTareaInstance?.cuadrilla?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${solicitudDeTareaInstance?.estado}">
        <li class="fieldcontain">
          <span id="estado-label" class="property-label"><g:message code="solicitudDeTarea.estado.label" default="Estado" /></span>

          <span class="property-value" aria-labelledby="estado-label">${solicitudDeTareaInstance?.estado?.encodeAsHTML()}</span>

        </li>
      </g:if>

      <g:if test="${solicitudDeTareaInstance?.tarea}">
        <li class="fieldcontain">

          <span id="tarea-label" class="property-label"><g:message code="solicitudDeTarea.tarea.label" default="Tareas Por Sitio" /></span>					
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
                <td>${fieldValue(bean: tareaInstance, field: "ordenEjecucion")}</td>					
                <td><g:formatDate format="dd/MM/yyyy" date="${tareaInstance.fechaInicio}" /></td>					
              <td>${fieldValue(bean: tareaInstance, field: "sitio")}</td>					
              <td>${fieldValue(bean: tareaInstance, field: "tipoTarea")}</td>					
              <td>${fieldValue(bean: tareaInstance, field: "estado")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>

      <g:if test="${solicitudDeTareaInstance?.pos}">
        <li class="fieldcontain">

          <span id="pos-label" class="property-label">PO</span>
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
                <td><g:formatDate format="dd/MM/yyyy" date="${POInstance.fechaRecibida}" /></td>					
              <td>${fieldValue(bean: POInstance, field: "monto")}</td>					
              <td><g:checkBox name="esExtra" value="${POInstance.esExtra}" disabled="true" /></td>					
              <td>${fieldValue(bean: POInstance, field: "nombreArchivo")}</td>											
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>

      <g:if test="${solicitudDeTareaInstance?.prestamos}">
        <li class="fieldcontain">
          <span id="prestamos-label" class="property-label"><g:message code="solicitudDeTarea.prestamos.label" default="Prestamos" /></span>
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
                <td><g:formatDate format="dd/MM/yyyy" date="${prestamoHerramientaInstance.fechaPrestamo}" /></td>        
              <td>${fieldValue(bean: prestamoHerramientaInstance, field: "herramienta")}</td>        
              <td>${fieldValue(bean: prestamoHerramientaInstance, field: "descripcion")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>

      <g:if test="${solicitudDeTareaInstance?.documentos}">
        <li class="fieldcontain">
          <span id="documentos-label" class="property-label"><g:message code="solicitudDeTarea.documentos.label" default="Documentos" /></span>

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
                <td>${fieldValue(bean: documentoInstance, field: "tipo")}</td>
                <td>${fieldValue(bean: documentoInstance, field: "observaciones")}</td>
                <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaRealizado}" /></td>
              <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaEnviado}" /></td>
              <td><g:formatDate format="dd/MM/yyyy" date="${documentoInstance.fechaAprobado}" /></td>
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>

      <g:if test="${solicitudDeTareaInstance?.viaticos}">
        <li class="fieldcontain">
          <span id="viaticos-label" class="property-label"><g:message code="solicitudDeTarea.viaticos.label" default="Solicitudes de viaticos" /></span>

          <table>
            <thead>
              <tr>	
            <g:sortableColumn property="fechaCreacion" title="${message(code: 'solicitudDeViaticos.fechaCreacion.label', default: 'Fecha Creacion')}" />					
            <g:sortableColumn property="fechaPago" title="${message(code: 'solicitudDeViaticos.fechaPago.label', default: 'Fecha Pago')}" />					
            <g:sortableColumn property="monto" title="${message(code: 'solicitudDeViaticos.monto.label', default: 'Monto')}" />					
            <g:sortableColumn property="observaciones" title="${message(code: 'solicitudDeViaticos.observaciones.label', default: 'Observaciones')}" />					
            <th><g:message code="solicitudDeViaticos.estado.label" default="Estado" /></th>					
            </tr>
            </thead>
            <tbody>
            <g:each in="${solicitudDeTareaInstance.viaticos}" status="i" var="solicitudDeViaticosInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">											
                <td><g:formatDate format="dd/MM/yyyy" date="${solicitudDeViaticosInstance.fechaCreacion}" /></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${solicitudDeViaticosInstance.fechaPago}" /></td>					
              <td>${fieldValue(bean: solicitudDeViaticosInstance, field: "monto")}</td>					
              <td>${fieldValue(bean: solicitudDeViaticosInstance, field: "observaciones")}</td>					
              <td>${fieldValue(bean: solicitudDeViaticosInstance, field: "estado")}</td>

              </tr>
            </g:each>
            </tbody>
          </table>
        </li>
      </g:if>
      <g:if test="${solicitudDeTareaInstance?.pagos}">
        <li class="fieldcontain">
          <span id="viaticos-label" class="property-label"><g:message code="solicitudDeTarea.viaticos.label" default="Solicitudes de pagos" /></span>      
          <table>
            <thead>
              <tr>
            <g:sortableColumn property="fechaCreacion" title="${message(code: 'solicitudPagoCuadrilla.fechaCreacion.label', default: 'Fecha Creacion')}" />					
            <g:sortableColumn property="fechaPago" title="${message(code: 'solicitudPagoCuadrilla.fechaPago.label', default: 'Fecha Pago')}" />					
            <g:sortableColumn property="porcentaje" title="${message(code: 'solicitudPagoCuadrilla.porcentaje.label', default: 'Porcentaje')}" />					
            <g:sortableColumn property="monto" title="${message(code: 'solicitudPagoCuadrilla.monto.label', default: 'Monto')}" />					
            <g:sortableColumn property="observaciones" title="${message(code: 'solicitudPagoCuadrilla.observaciones.label', default: 'Observaciones')}" />					
            <th><g:message code="solicitudPagoCuadrilla.estado.label" default="Estado" /></th>	
            </tr>
            </thead>
            <tbody>
            <g:each in="${solicitudDeTareaInstance.pagos}" status="i" var="solicitudPagoCuadrillaInstance">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:formatDate format="dd/MM/yyyy" date="${solicitudPagoCuadrillaInstance.fechaCreacion}" /></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${solicitudPagoCuadrillaInstance.fechaPago}" /></td>					
              <td>${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "porcentaje")}</td>					
              <td>${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "monto")}</td>					
              <td>${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "observaciones")}</td>					
              <td>${fieldValue(bean: solicitudPagoCuadrillaInstance, field: "estado")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </li>
      </g:if>
    </ol>
    <g:form>

      <fieldset class="buttons_add">            
        <g:if test="${solicitudDeTareaInstance.hasEstadoCreada()}">
          <g:link class="ejecutar" action="pasarEnEjecutacion"  id="${solicitudDeTareaInstance?.id}" >Pasar En Ejecucion</g:link>
        </g:if>          
        <g:link class="viaticos" controller="solicitudDeViaticos" action="create">Solicitar Viaticos</g:link>
        <g:link class="pagar" action="create" controller="solicitudPagoCuadrilla" >Solicitar Pago</g:link>                  
        <g:if test="${solicitudDeTareaInstance.hasDocumentos()}">
          <g:link class="sendEmail" action="enviarDocumentacionACliente">Enviar doc. a Cliente</g:link>
        </g:if>
      </fieldset>

      <fieldset class="buttons">
        <g:hiddenField name="id" value="${solicitudDeTareaInstance?.id}" />
        <g:link class="edit" action="edit" id="${solicitudDeTareaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
      </fieldset>
    </g:form>
  </div>
</body>
</html>
