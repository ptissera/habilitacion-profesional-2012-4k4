
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte de Solicitudes de Viaticos - Paso 2/3</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step3" >

        <ol class="property-list">

          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Fecha Creación - Mayor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaCreacion_mayorQue" precision="day"  value="${solicitudViaticoInstance?.fechaCreacion}"  />               
            </span>
          </li>
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Menor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaCreacion_menorQue" precision="day"  value="${solicitudViaticoInstance?.fechaCreacion}"  />               
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Fecha Pago Desde - Mayor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaPago_mayorQue" precision="day"  value="${solicitudViaticoInstance?.fechaPago}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Menor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaPago_menorQue" precision="day"  value="${solicitudViaticoInstance?.fechaPago}"  /> 
              
            </span>
          </li>
          
         <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Monto Desde $:</span>
           <span class="property-value" aria-labelledby="monto-label">
              <g:field type="number" name="montoDesde" value="${params.montoDesde}"/>             
           </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Monto Hasta $:</span>
           <span class="property-value" aria-labelledby="monto-label">
              <g:field type="number" name="montoHasta" value="${params.montoHasta}"/>             
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
