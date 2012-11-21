
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Informe Procentaje De Viaticos En Proyectos</title>  
  </head>
  <body>


    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Informe Procentaje De Viaticos En Proyectos - Paso 1/2</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:form action="step2" >
        <ol class="property-list">
          <li class="fieldcontain">
            <span id="nombre-label" class="property-label">Porcetaje de viaticos mayor a:</span>
           <span class="property-value">
              <g:field type="number" name="porcentaje" value="${flash.porcentaje}"/> %             
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
