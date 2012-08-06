<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<meta name="layout" content="main" />
<title>Ingreso</title>
</head>
<body>
	<div class="content scaffold-create">
		<h1>Ingresar al Sistema</h1>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<g:form action="authenticate" method="post">
			<fieldset class="form">
				<div
					class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nombreUsuario', 'error')} required">
					<label for="nombreUsuario"> <g:message
							code="usuario.nombreUsuario.label" default="nombreUsuario" /> <span
						class="required-indicator">*</span>
					</label>
					<g:field type="text" name="nombreUsuario" required=""
						value="${fieldValue(bean: usuarioInstance, field: 'nombreUsuario')}" />
				</div>
				<div
					class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'clave', 'error')} ">
					<label for="clave"> <g:message
							code="usuario.clave.label" default="Clave" />

					</label>
					<g:field type="password" name="clave" maxlength="32"
						value="${usuarioInstance?.clave}" />
				</div>
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="login" class="save" value="Ingresar" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
