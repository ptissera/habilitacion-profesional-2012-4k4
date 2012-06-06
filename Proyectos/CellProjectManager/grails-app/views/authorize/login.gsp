<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<meta name="layout" content="main" />
<title>Login</title>
</head>
<body>
	<div class="content scaffold-create">
		<h1>Login</h1>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<g:form action="authenticate" method="post">
			<fieldset class="form">
				<div
					class="fieldcontain ${hasErrors(bean: userInstance, field: 'userName', 'error')} required">
					<label for="userName"> <g:message
							code="user.userName.label" default="userName" /> <span
						class="required-indicator">*</span>
					</label>
					<g:field type="text" name="userName" required=""
						value="${fieldValue(bean: userInstance, field: 'userName')}" />
				</div>
				<div
					class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
					<label for="password"> <g:message
							code="user.password.label" default="Password" />

					</label>
					<g:field type="password" name="password" maxlength="32"
						value="${userInstance?.password}" />
				</div>
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="login" class="save" value="Login" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
