
<%@ page import="business.tarea.*" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>    
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Tareas - Paso 4/5</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step5" >

        <ol class="property-list">

          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Fecha Inicio Estimada - Mayor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaInicioEstimada_mayorQue" precision="day"  value="${tareaInstance?.fechaInicio}"  />               
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Menor que:
            </span>
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaInicioEstimada_menorQue" precision="day"  value="${tareaInstance?.fechaInicio}"  />               
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Fecha Fin Estimada Desde - Mayor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaFinEstimada_mayorQue" precision="day"  value="${tareaInstance?.fechaFin}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Menor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaFin_menorQue" precision="day"  value="${tareaInstance?.fechaFin}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Fecha Inicio Real Desde - Mayor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaInicioReal_mayorQue" precision="day"  value="${tareaInstance?.fechaInicioReal}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Menor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaInicioReal_menorQue" precision="day"  value="${tareaInstance?.fechaInicioReal}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Fecha Fin Real Desde - Mayor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaFinReal_mayorQue" precision="day"  value="${tareaInstance?.fechaFinReal}"  /> 
              
            </span>
          </li>
          
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">
              Menor que:
            </span>            
            <span class="property-value" aria-labelledby="nombre-label">
              <g:fecha name="fechaFinReal_menorQue" precision="day"  value="${tareaInstance?.fechaFinReal}"  /> 
              
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
