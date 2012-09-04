
<%@ page import="business.core.Proyecto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proyecto.label', default: 'Proyecto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <g:javascript library="jquery" />
                <g:javascript>
                  
                  function seleccionarUsuario(it , idUsuario , idProyecto ) {
                 
                    if(confirm('¿Confirma Seleccion de proyecto?')){                      
                      ${remoteFunction(controller:"proyecto", action:"userForProject", params:"'idUsuario=' + it.selectedIndex + '&idProyecto=' + idProyecto", onComplete:'updateMessage(XMLHttpRequest)')};
                    } else {
                      it.selectedIndex = idUsuario;
                    }
                  }
                  
                  function updateMessage(resp){       
                    $('#message').attr("style", "display:blocl");
                    $('#message').text(resp.responseText);                    
                  }
                  
               </g:javascript>
	</head>
	<body>
		<a href="#list-proyecto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>		
		<div id="list-proyecto" class="content scaffold-list" role="main">
			<h1>Asignar Proyecto</h1>
			
                        <div id="message" name="message" class="message" role="status" style="display: none;">${flash.message}</div>
			
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="licitacion" title="${message(code: 'proyecto.licitacion.label', default: 'Licitacion')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'proyecto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="usuario" title="${message(code: 'proyecto.usuario.label', default: 'Usuario')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${proyectoInstanceList}" status="i" var="proyectoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: proyectoInstance, field: "licitacion")}</td>
					
						<td>${fieldValue(bean: proyectoInstance, field: "nombre")}</td>
					
						<td>
                                                      <g:select id="usuario" name="usuario.id" from="${support.secure.Usuario.list()}" onchange="seleccionarUsuario(this , ${proyectoInstance?.usuario?.id}, ${proyectoInstance?.id})" optionKey="id" value="${proyectoInstance?.usuario?.id}" class="many-to-one" noSelection="['null': '']"/>                                                
                                                      
                                                </td>
										
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${proyectoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
