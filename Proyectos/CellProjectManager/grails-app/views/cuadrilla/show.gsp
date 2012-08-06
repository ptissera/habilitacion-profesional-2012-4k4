
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuadrilla.label', default: 'Cuadrilla')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cuadrilla" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cuadrilla" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cuadrilla">
			
				<g:if test="${cuadrillaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="cuadrilla.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${cuadrillaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="cuadrilla.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${cuadrillaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.propia}">
				<li class="fieldcontain">
					<span id="propia-label" class="property-label"><g:message code="cuadrilla.propia.label" default="Propia" /></span>
					
						<span class="property-value" aria-labelledby="propia-label"><g:formatBoolean boolean="${cuadrillaInstance?.propia}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.estadoCuadrilla}">
				<li class="fieldcontain">
					<span id="estadoCuadrilla-label" class="property-label"><g:message code="cuadrilla.estadoCuadrilla.label" default="Estado Cuadrilla" /></span>
					
						<span class="property-value" aria-labelledby="estadoCuadrilla-label"><g:link controller="estadoCuadrilla" action="show" id="${cuadrillaInstance?.estadoCuadrilla?.id}">${cuadrillaInstance?.estadoCuadrilla?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.operarios}">
				<li class="fieldcontain">
					<span id="operarios-label" class="property-label"><g:message code="cuadrilla.operarios.label" default="Operarios" /></span>
					
						<g:each in="${cuadrillaInstance.operarios}" var="o">
						<span class="property-value" aria-labelledby="operarios-label"><g:link controller="integranteCuadrilla" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.historialDeCambios}">
				<li class="fieldcontain">
					<span id="historialDeCambios-label" class="property-label"><g:message code="cuadrilla.historialDeCambios.label" default="Historial De Cambios" /></span>
					
						<g:each in="${cuadrillaInstance.historialDeCambios}" var="h">
						<span class="property-value" aria-labelledby="historialDeCambios-label"><g:link controller="historialCuadrilla" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuadrillaInstance?.prestatmosHerramientas}">
				<li class="fieldcontain">
					<span id="prestatmosHerramientas-label" class="property-label"><g:message code="cuadrilla.prestatmosHerramientas.label" default="Prestatmos Herramientas" /></span>
					
						<g:each in="${cuadrillaInstance.prestatmosHerramientas}" var="p">
						<span class="property-value" aria-labelledby="prestatmosHerramientas-label"><g:link controller="prestamoHerramienta" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cuadrillaInstance?.id}" />
					<g:link class="edit" action="edit" id="${cuadrillaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
