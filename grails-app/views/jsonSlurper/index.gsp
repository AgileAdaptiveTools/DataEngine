<%--
  Created by IntelliJ IDEA.
  User: hkong
  Date: 11/15/12
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>JSON Slurper Documentation and Sandbox</title>
</head>

<body>

    <h2>REST Service Documentation and Sandbox for /jsonSlurper</h2>

    <p>This service processes a non-streaming JSON-formatted data feed, and outputs it in one of the supported output formats.</p>
    <p>There are 2 ways to use this service:</p>
    <ul>
        <li><a href="#getUrl"><tt>GET /DataEngine/jsonSlurper</tt></a> processes a JSON file specified at a URL</li>
        <li><a href="#POST"><tt>POST /DataEngine/jsonSlurper</tt></a> processes an uploaded JSON file</li>
    </ul>

    <h3>Syntax</h3>
    <pre>/DataEngine/jsonSlurper</pre>

    <h3>Parameters</h3>
    <table class="table">
        <tr>
            <td><strong>force_mime_type</strong> (optional)</td>
            <td>response MIME type.  This should not normally be used, since the service returns the correct MIME type for each format (see below), however,
                this may be useful for applications that needs the MIME type to be specified differently than the standards, or for debugging.  For example,
                <ul>
                    <li><a href="/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/simple.json&format=JSON&force_mime_type=text/plain">/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/simple.csv&force_mime_type=text/plain</a>
                        returns a MIME type of "text/plain" instead of the standard "application/json"</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><strong>format</strong> (optional)</td>
            <td>output format.  Currently, the following formats are supported:
                <ul>
                    <li>JSON (default if format parameter is not specified)</li>
                    <li>CWL (Common Widget Lanaguage)</li>
                    <li>TEXT</li>
                    <li>DEBUG</li>
                </ul>
                For example,
                <ul>
                    <li>default (JSON):  <a href="/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json">/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json</a></li>
                    <li>CWL:  <a href="/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json&format=CWL">/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json&format=CWL</a></li>
                    <li>TEXT:  <a href="/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json&format=TEXT">/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json&format=TEXT</a></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><strong>idl</strong> (optional)</td>
            <td>the IDL "sidecar" description that describes the data for more accurate ingest.  See the <a href="/DataEngine/idl/index.html">IDL documentation</a> for more details.
            Note that this is an optional field.  If it is not provided, the system will try to guess how to ingest the data, and currently, this
            automatic ingest process is not very accurate.  The parameter can either be  an IDL UUID which is returned when the IDL was first posted to this server,
            or it can be a full URL to an IDL resource on this or any other server.
                <ul>
                    <li><a href="/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json&idl=1">/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json&idl=1</a>
                        returns the data after applying IDL UUID=1 to it.  Compare to the same URL without the idl parameter.</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><strong>inputFile</strong> (required for POST version of service)</td>
            <td>the enclosed file from local disk encoded as "multipart/form-data".  For a working example, see <a href="#sandbox">Sandbox section</a> below.
            </td>
        </tr>
        <tr>
            <td><strong>url</strong> (required for GET version of service)</td>
            <td>fully qualified data feed endpoint URL, for example
                <ul>
                    <li>Simple JSON file:  <a href="/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json">/DataEngine/jsonSlurper?url=http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json</a></li>
                </ul>
            </td>
        </tr>
    </table>

    <h3>Notes</h3>
    <ul>
        <li>If you're behind a firewall with a proxy server, you will need to set the <a href="http://localhost:8080/DataEngine/setting#networkProxy">Network Proxy settings</a></li>
    </ul>

    <h3>Sandbox</h3>
    <p>Use the form below to interactively try the POST version of this service</p>
    <g:form controller="jsonSlurper" class="form-horizontal" action="play" method='post' enctype='multipart/form-data' >
        <fieldset>
            <div id="legend" class="">
                <legend class="">JSON Slurper</legend>
            </div>


            <div class="control-group">
                <label class="control-label">Select a JSON file</label>
                <!-- File Upload -->
                <div class="controls">
                    <input class="input-file" id="inputFile" name="inputFile" type="file">
                </div>
            </div>



            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-success">Select</button>
                </div>
            </div>

        </fieldset>
    </g:form>

</body>
</html>