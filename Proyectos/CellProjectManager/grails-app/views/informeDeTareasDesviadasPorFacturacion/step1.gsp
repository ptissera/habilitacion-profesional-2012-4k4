
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Informe de tareas desviadas por Facturacion</title>  
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Informe de tareas desviadas por Facturacion - Paso 1/2</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step2" >
        <ol class="property-list">
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Solicitudes Mayores a $:</span>
           <span class="property-value" aria-labelledby="monto-label">
              <g:field type="number" name="monto" value="${flash.monto}"/>             
           </span>
          </li>
           <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Desde:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="desde" precision="day"  value="${flash.desde}"  />               
            </span>
          </li>
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Hasta:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="hasta" precision="day"  value="${flash.hasta}"  />               
            </span>
          </li>          
        </ol>
        <fieldset class="buttons">
          <g:submitButton name="siguiente" class="siguiente" value="Siguiente" />
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
