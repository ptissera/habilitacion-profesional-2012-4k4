<!doctype html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>Coming SA</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'selectedControl.css')}" type="text/css">    
  <g:javascript library="jquery" />
  <g:javascript>
    $("#accordion > li").click(function(){

    if(false == $(this).next().is(':visible')) {
    $('#accordion > ul').slideUp(300);
    }
    $(this).next().slideToggle(300);
    });

    $('#accordion > ul:eq(0)').show();

  </g:javascript>
</head>
<body>
  <a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<g:menuPrincipal />

<div id="page-body" role="main">
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>
  <g:vencimientos />
</div>
</body>
</html>
