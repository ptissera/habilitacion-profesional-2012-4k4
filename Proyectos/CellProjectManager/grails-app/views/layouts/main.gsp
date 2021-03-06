<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'loginControl.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'selectedControl.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'checkboxList.css')}" type="text/css">
  <g:layoutHead/>
  <r:layoutResources />
  <g:resourcesCalendar lang="es"/>
</head>
<body>
  <div id="grailsLogo" role="banner" style="width: 100%; height: 90px;"><img src="${resource(dir: 'images', file: 'celltower.png')}" style="padding: 10px 10px; width: 100px;height: 100px;  position:absolute;z-index: 1"/>
  <div style="padding: 43px 132px; width: 600px; color: #bbddcc; font-size: 35px; position:absolute;z-index: 1">Coming Manager Project v1.0</div>
<div style="padding: 45px 130px; width: 600px; color: green; font-size: 35px; position:absolute;z-index: 1">Coming Manager Project v1.0</div>
  </div>
  <div id="header">
    <g:loginControl />
    <g:selectedItems />
  </div>
<g:layoutBody/>
<div class="footer" role="contentinfo"></div>
<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<g:javascript library="application"/>
<r:layoutResources />
</body>
</html>