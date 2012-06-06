<%@page isErrorPage='true' %>
<%@page import='org.apache.log4j.Logger' %>
<%@page import='java.io.*' %>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<%!
private String generateStackTrace(final Throwable e) {
	StringWriter stringWriter = new StringWriter();
	e.printStackTrace(new PrintWriter(stringWriter));
	String stack = stringWriter.toString();
	stack = stack.replaceAll("\r", "");
	stack = stack.replaceAll("\t", "    ");
	stack = stack.replaceAll("\n", "<br/>");
	return stack;
}
%>

<%
Integer code = (Integer)request.getAttribute("javax.servlet.error.status_code");
if (code == null) {
	code = 0;
}

Logger logger = Logger.getLogger(getClass());

if (exception == null) {
	exception = (Throwable)request.getAttribute("exception");
}
%>

<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
	'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<meta http-equiv='content-type' content='text/html;charset=utf-8'/>
<meta http-equiv='Cache-Control' content='max-age=0'/>
<meta http-equiv='Cache-Control' content='no-cache'/>
<meta http-equiv='expires' content='0'/>
<meta http-equiv='Expires' content='Tue, 01 Jan 1980 1:00:00 GMT'/>
<meta http-equiv='Pragma' content='no-cache'/>

<title>Sorry, an error has occurred</title>

<script type='text/javascript' src='<c:url value='/js/prototype/prototype.js'/>'></script>

</head>

<body>

	<table cellpadding='20' cellspacing='0'><tr>
		<td>
<%
if (404 == code) {
	logger.debug("404 for '" + request.getAttribute("javax.servlet.error.request_uri") + "'");
%>
				<blockquote>
					<center><h2>Unknown Page</h2></center>
					<p>Sorry, you requested a page that does not exist.</p>
				</blockquote>
<%
}
else {
%>
		Sorry, an error has occurred<br/><br/>

		Please email <a href='mailto:support@yourcompany.com'>Support</a>.
		<P>Be sure to include
		<ul>
			<li>what you were doing,
			<li>when it happened,
			<li>and any other details (like error messages) that might be important.
		</ul>
<%
	if (true /*PropertyManager.getProperty("showExceptionDetails", "false").equals("true")*/) {
		if (exception != null) {
%>
		<p><strong>Exception Detail</strong></p>

		<p>Exception <span class='reference'><%=exception.getClass().getName()%></span></p>

		<div class='indent'>
			<a href='javascript:void(0);'
			   onMouseOver="window.status='Click to expand';return true;"
			   onMouseOut="window.status='';return true;"
			   onClick="Element.toggle('stackTrace');return true;">(View stack trace)</a>

			<table width='500' cellpadding='0' cellspacing='0'>
				<tr><td id='stackTrace' style='display:none'>
					<div class='toggle'><pre><%=generateStackTrace(exception)%></pre></div>
				</td></tr>
			</table>
		</div><br/>
<%
		}
	}
}
%>
		</td>
	</tr></table>
</body>
</html>
