<%@ page import="business.cuadrillas.IntegranteCuadrilla" %>
<%@ page import="support.secure.Usuario" %>


<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'tipoDocumento', 'error')} ">
  <label for="tipoDocumento">
    <g:message code="integranteCuadrilla.tipoDocumento.label" default="Tipo Documento" />

  </label>
  <g:select id="tipoDocumento" name="tipoDocumento.id" from="${business.cuadrillas.TipoDocumentoIdentificacion.list()}" optionKey="id" required="" value="${integranteCuadrillaInstance?.tipoDocumento?.id}" class="many-to-one"/>
</div>


<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'documento', 'error')} ">
  <label for="documento">
    <g:message code="integranteCuadrilla.documento.label" default="Documento" />

  </label>
  <g:textField name="documento" value="${integranteCuadrillaInstance?.documento}"/>
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

<g:if test="${cuadrilaInstance.haveJefeCuadrilla()==integranteCuadrillaInstance?.esJefeCuadrilla}">
  <div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'esJefeCuadrilla', 'error')} ">
    <label for="esJefeCuadrilla">
      <g:message code="integranteCuadrilla.esJefeCuadrilla.label" default="Jefe de Cuadrilla" />

    </label>
    <g:checkBox name="esJefeCuadrilla"  value="${integranteCuadrillaInstance?.esJefeCuadrilla}" />
  </div>

  <div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'usuario', 'error')} required">
    <label for="usuario">
      <g:message code="integranteCuadrilla.usuario.label" default="Usuario" />
      <span class="required-indicator">*</span>
    </label>
    <g:select id="usuario" name="usuario.id" from="${support.secure.Usuario.list()}" optionKey="id" required="" value="${integranteCuadrillaInstance?.usuario?.id}" class="many-to-one"/>
  </div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: integranteCuadrillaInstance, field: 'documentacion', 'error')} ">			
  <table>
    <thead>
      <tr><g:message code="documentacionIntegrante.documentacion.label" default="Documentacion" /></tr>
    <tr>              
      <th><g:message code="documentacionIntegranteCuadrilla.tipoDocumento.label" default="Tipo Documento" /></th>					
    <g:sortableColumn property="vigenciaDesde" title="${message(code: 'documentacionIntegranteCuadrilla.vigenciaDesde.label', default: 'Vigencia Desde')}" />					
    <g:sortableColumn property="vigenciaHasta" title="${message(code: 'documentacionIntegranteCuadrilla.vigenciaHasta.label', default: 'Vigencia Hasta')}" />					
    <g:sortableColumn property="descripcion" title="${message(code: 'documentacionIntegranteCuadrilla.descripcion.label', default: 'Descripcion')}" />									
    </tr>
    </thead>
    <tbody>
    <g:each in="${integranteCuadrillaInstance?.documentacion}" status="i" var="documentacionIntegranteCuadrillaInstance">
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


