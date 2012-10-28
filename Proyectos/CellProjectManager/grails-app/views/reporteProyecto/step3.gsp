
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Proyectos - Paso 3/4</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step4" >

        <ol class="property-list">

          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Fecha Creación - Mayor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaCreacion_mayorQue" precision="day"  value="${proyectoInstance?.fechaCreacion}"  />               
            </span>
          </li>
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Menor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaCreacion_menorQue" precision="day"  value="${proyectoInstance?.fechaCreacion}"  />               
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Fecha Inicio Desde - Mayor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaInicio_mayorQue" precision="day"  value="${proyectoInstance?.fechaInicio}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Menor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaInicio_menorQue" precision="day"  value="${proyectoInstance?.fechaInicio}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Fecha Fin Desde - Mayor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaFin_mayorQue" precision="day"  value="${proyectoInstance?.fechaFin}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Menor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaFin_menorQue" precision="day"  value="${proyectoInstance?.fechaFin}"  /> 
              
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
