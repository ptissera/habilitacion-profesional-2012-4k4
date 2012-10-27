
<%@ page import="business.cuadrillas.TipoDocumentacionIntegranteCuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Documentacion Operarios - Paso 3/4</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step4" >

        <ol class="property-list">

          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Vigencia Desde - Mayor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="vigenciaDesde_mayorQue" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaDesde}"  />               
            </span>
          </li>
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Menor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="vigenciaDesde_menorQue" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaDesde}"  />               
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Vigencia Hasta - Mayor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="vigenciaHasta_mayorQue" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaHasta}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Menor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="vigenciaHasta_menorQue" precision="day"  value="${documentacionIntegranteCuadrillaInstance?.vigenciaHasta}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Seleccione Tipo de Documento</span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:checkBoxList name="tiposDocumentosIds" from="${TipoDocumentacionIntegranteCuadrilla.list()}" value="${TipoDocumentacionIntegranteCuadrilla.list()}" optionKey="id"/>
            </span>
          </li>
        </ol>
        <fieldset class="buttons">
          <g:submitButton name="buscar" class="buscar" value="Buscar" />
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
