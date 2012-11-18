
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Informe de Acontecimiento</title>  
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Informe de Acontecimiento - Paso 1/2</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step2" >
        <ol class="property-list">
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Tipos de Acontecimiento:</span>
           <span class="property-value">
              <g:checkBoxList name="tipoAcontecimientoIds" from="${tipoAcontecimientoListInstance}" value="${tipoAcontecimientoListInstance}" optionKey="id"/>             
           </span>
          </li>
           <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Desde:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="desde" precision="day"  value=""  />               
            </span>
          </li>
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Hasta:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="hasta" precision="day"  value=""  />               
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
