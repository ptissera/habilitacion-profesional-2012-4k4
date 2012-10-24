
<%@ page import="business.cuadrillas.Cuadrilla" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>Reporte</title>
    <style type="text/css">
      .CheckBoxList {
        height: 100px; overflow: auto; overflow-x: hidden; width: 200px; border: 0px solid #000;
        list-style-type: none; margin: 0; padding:0px
      }
      .CheckBoxList li  {
        padding:2px
      }
      .CheckBoxList li input[type='checkbox']  {
        margin-right: 12px;
      }
    </style>
  </head>
  <body>
    

    <div id="show-reporte" class="content scaffold-show" role="main">
      <h1>Reporte Documentacion Operarios - Paso 1/3</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
  <g:form action="step1" >
    <ol class="property-list">
      <li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="provincia.nombre.label" default="Seleccione Cuadrillas" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label">
                                                  <g:checkBoxList name="cuadrillaIds" from="${Cuadrilla.list()}" value="${cuadrillasListInstance}" optionKey="id"/>
                                                  
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