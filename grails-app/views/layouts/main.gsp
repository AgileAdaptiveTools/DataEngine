<!DOCTYPE html>
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
		%{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">--}%
		%{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">--}%

    <link rel="stylesheet/less" href="${resource(dir: 'less', file:'bootstrap.less')}" media="all" />
    <link rel="stylesheet/less" href="${resource(dir: 'less', file:'custom.less')}" media="all" />
    <script src="${resource(dir:'js', file:"less-1.3.1.min.js")}"></script>
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
        %{-- top navbar--}%
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a href="/DataEngine" class="brand">SeamlessC2 Data Engine</a>
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li><a href="csvSlurper">Slurpers</a></li>
                            <li><a href="setting">Settings</a></li>
                            <li><a href="about">About</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>

        <div class="container">
            <br />
            <br />
            <g:layoutBody/>
        </div>

		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />

        <!-- Le javascript
            ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->

        <script src="${resource(dir:'js', file:'jquery-1.8.0.js')}"></script>
        <script src="${resource(dir:'js', file:'bootstrap.js')}"></script>

    </body>
</html>
