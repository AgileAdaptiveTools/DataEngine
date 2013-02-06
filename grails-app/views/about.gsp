<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Play with Bootstrap</title>
	</head>
	<body>

            <h1>About SeamlessC2 DataEngine</h1>
            <p>Click on the tabs below to review info about this webapp.</p>

            <row class="span8">
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#1" data-toggle="tab">Webapp Info</a></li>
                        <li><a href="#2" data-toggle="tab">Plug-ins</a></li>
                        <li><a href="#3" data-toggle="tab">Acknowledgements</a></li>
                        <li class="pull-right"><a href="#4" data-toggle="tab">Developer</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="1">
                            <h2>Webapp Info</h2>
                            <ul>
                                <li>App version: <g:meta name="app.version"/></li>
                                <li>Grails version: <g:meta name="app.grails.version"/></li>
                                <li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
                                <li>JVM version: ${System.getProperty('java.version')}</li>
                                <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
                                <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                                <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                                <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                                <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
                            </ul>
                        </div>
                        <div class="tab-pane" id="2">
                            <h2>Installed Plugins</h2>
                            <ul>
                                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                                    <li>${plugin.name} - ${plugin.version}</li>
                                </g:each>
                            </ul>
                        </div>
                        <div class="tab-pane" id="3">
                            <h2>Thanks!</h2>
                            <p>Thanks to the following third party resources.</p>
                            <ul>
                                <li><a href="http://twitter.github.com/bootstrap/index.html">Bootstrap (web GUI framework)</a></li>

                            </ul>
                        </div>
                        <div class="tab-pane" id="4">
                            <h2>Available Controllers:</h2>
                            <ul>
                                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                                    <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                                </g:each>
                            </ul>
                        </div>
                    </div>
                </div>
            </row>

        %{--below is only needed if it's not already in main.gsp--}%
        %{--<script src="js/jquery.js"></script>--}%
        %{--<script src="js/bootstrap-tab.js"></script>--}%

	</body>
</html>
