<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to the SeamlessC2 DataEngine</title>

	</head>
	<body>
        <div class="hero-unit">
            <h1>SeamlessC2 DataEngine</h1>
            <p>The SeamlessC2 DataEngine makes it easy for you to build data feeds that you need from existing sources.</p>
        </div>

        <p>The server offers both web apps for users as well as web services for machine-to-machine interaction and systems integration.</p>

        <h2>User Web Apps</h2>
        <p>User interfaces to interact with the various components of the DataEngine.  Most of these apps are always available in the menu.</p>
        <dl class="dl-horizontal">
            <dt> <a href="csvSlurper/index.html">Slurpers</a> </dt>
            <dd> Applications that help ingest data of various formats from other sites </dd>
            <dt> <a href="about">About this webapp</a> </dt>
            <dd> All kinds of technical info about this webapp </dd>
            <dt> <a href="setting">Settings</a> </dt>
            <dd> Modify DataEngine's server-side settings </dd>
        </dl>


        <h2>Web Services</h2>
        <p>RESTful services for software to interact with the DataEngine.</p>
        <dl class="dl-horizontal">
            <dt> <a href="/DataEngine/csvSlurper/index.html">/csvSlurper</a> </dt>
            <dd> Service that ingests a CSV data feed.  Example:  <a href="/DataEngine/csvSlurper?url=http://localhost:8080/DataEngine/sample_files/simple.csv">/DataEngine/csvSlurper?url=http://localhost:8080/DataEngine/sample_files/simple.csv</a></dd>
            <dt> <a href="/DataEngine/idl/index.html">/idl</a> </dt>
            <dd> Service that provide full CRUD for working with IDLs (Ingest Description Language), used to describe a data source</dd>
            <dt> <a href="/DataEngine/join/index.html">/join</a> </dt>
            <dd> Service that provide full CRUD for working with IDLs (Ingest Description Language), used to describe a data source</dd>
            <dt> <a href="/DataEngine/jsonSlurper/index.html">/jsonSlurper</a> </dt>
            <dd> Service that ingests a JSON data feed.  Example:  <a href="/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json">/DataEngine/csvSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json</a></dd>
            <dt> <a href="/DataEngine/source/index.html">/source</a> </dt>
            <dd> Simple service that retrieves a data source from any server.  Example:  <a href="/DataEngine/source?url=http://localhost:8080/DataEngine/product/sample_files_sample_cwl.json">/DataEngine/source?url=http://localhost:8080/DataEngine/product/sample_files_sample_cwl.json</a></dd>
        </dl>

        <h2>Description Files</h2>
        <p>Description files are used throughout the DataEngine to specify information about data feeds and processes.
            Currently, there are 2 kinds of description files
            <dl class="dl-horizontal">
                <dt> <a href="/DataEngine/idl/index.html">IDL</a> </dt>
                <dd> Ingest Description Language files describe data feeds</dd>
                <dt> <a href="/DataEngine/pdl/index.html">PDL</a> </dt>
                <dd> Process Description Language files describe processes to act upon data feeds</dd>
            </dl>
        </p>


    </body>
</html>
