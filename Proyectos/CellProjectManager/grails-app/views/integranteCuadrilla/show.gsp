
<%@ page import="business.cuadrillas.IntegranteCuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'integranteCuadrilla.label', default: 'IntegranteCuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-integranteCuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>				
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-integranteCuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list integranteCuadrilla">
			
				<g:if test="${integranteCuadrillaInstance?.du}">
				<li class="fieldcontain">
					<span id="du-label" class="property-label"><g:message code="integranteCuadrilla.du.label" default="Du" /></span>
					
						<span class="property-value" aria-labelledby="du-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="du"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="integranteCuadrilla.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.apellido}">
				<li class="fieldcontain">
					<span id="apellido-label" class="property-label"><g:message code="integranteCuadrilla.apellido.label" default="Apellido" /></span>
					
						<span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="apellido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.legajo}">
				<li class="fieldcontain">
					<span id="legajo-label" class="property-label"><g:message code="integranteCuadrilla.legajo.label" default="Legajo" /></span>
					
						<span class="property-value" aria-labelledby="legajo-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="legajo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="integranteCuadrilla.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${integranteCuadrillaInstance}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="integranteCuadrilla.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${integranteCuadrillaInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${integranteCuadrillaInstance?.fechaBaja}">
				<li class="fieldcontain">
					<span id="fechaBaja-label" class="property-label"><g:message code="integranteCuadrilla.fechaBaja.label" default="Fecha Baja" /></span>
					
						<span class="property-value" aria-labelledby="fechaBaja-label"><g:formatDate date="${integranteCuadrillaInstance?.fechaBaja}" /></span>
					
				</li>
				</g:if>
				<g:if test="${integranteCuadrillaInstance?.documentacion}">
        <li class="fieldcontain">
          
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
                <td><g:link action="show" controller="documentacionIntegranteCuadrilla" id="${documentacionIntegranteCuadrillaInstance.id}">${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "tipoDocumento")}</g:link></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${documentacionIntegranteCuadrillaInstance.vigenciaDesde}" /></td>					
              <td><g:formatDate format="dd/MM/yyyy" date="${documentacionIntegranteCuadrillaInstance.vigenciaHasta}" /></td>					
              <td>${fieldValue(bean: documentacionIntegranteCuadrillaInstance, field: "descripcion")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>

        </li>
      </g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${integranteCuadrillaInstance?.id}" />
					<g:link class="edit" action="edit" id="${integranteCuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                        <g:link class="add" controller="documentacionIntegranteCuadrilla" action="create" id="${integranteCuadrillaInstance?.id}"><g:message code="default.add.label" args="[message(code: 'documentacionIntegranteCuadrilla.label', default: 'Tipo Documentacion')]"/></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
