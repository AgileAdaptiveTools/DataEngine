<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>PDL Service Documentation and Sandbox</title>
</head>

<body>

    <h2>REST Service Documentation and Sandbox for /pdl</h2>

    <p>This service provides full CRUD capability for the <a href="/DataEngine/pdl/pdl.gsp">Ingest Description Language</a> (PDL),
        used to describe a data source for use in the DataEngine.</p>
    <p>There are 4 ways to use this service, corresponding to standard REST services:</p>
    <ul>
        <li><a href="#getUrl"><tt>GET /DataEngine/pdl</tt></a> retrieves an PDL file</li>
        <li><a href="#POST"><tt>POST /DataEngine/pdl</tt></a> creates an PDL file, assigns a UUID to it, and returns the URI to the PDL</li>
        <li><a href="#PUT"><tt>PUT /DataEngine/pdl</tt></a> updates an PDL file</li>
        <li><a href="#DELETE"><tt>DELETE /DataEngine/pdl</tt></a> deletes an PDL file</li>
    </ul>

    <h3>Notes</h3>
    <ul>
        <li>Most browsers do not support all 4 methods (supporting only GET and POST).  Therefore, POST
            has been overloaded to serve all of the "non-GET" functionality.  To use POST as a POST, simply
            call the URL normally.  To use it as a PUT or DELETE, call the POST URL,
            and append <tt>_method=PUT</tt> for PUT or <tt>_method=DELETE</tt> for DELETE (note the initial underscore).
            This will enable the proper methods to be used.</li>
        <li>All methods return a REST-compliant status code (200, 404, etc.).  The body of response can be one of two
            types:
            <ul>
                <li>requested content (usually for status code 200)</li>
                <li>message in JSON format, which gives more details than the standard HTTP codes can provide,
                    for example, <br/>
                        <tt>{"status":404,"remark":"Resource not found"}</tt>
                    <br/> or <br/>
                        <tt>{"status":201,"remark":"Created","pdl_uri":"http://localhost:8080/DataEngine/pdl/226"}</tt>
                </li>
            </ul>
        </li>
        <li>If you're behind a firewall with a proxy server, you will need to set the <a href="http://localhost:8080/DataEngine/setting#networkProxy">Network Proxy settings</a></li>
    </ul>

    <h3>Syntax</h3>
    <pre>/DataEngine/pdl</pre>

    <h3>Parameters</h3>
    <table class="table">
        <tr>
            <td><strong>uuid</strong> (optional for GET, required for PUT and DELETE, not allowed in POST)</td>
            <td>the UUID assigned to the PDL when it was first created.  This, combined with the this REST service's URL
                produces the PDL URI.  In the GET variant of this service, if this parameter is left out, will
                return all PDLs currently in the database.  If specified, it returns the specified instance.
                See <a href="#Examples">examples</a> for more information.
            </td>
        </tr>
        <tr>
            <td><strong>_method</strong> (optional)</td>
            <td> For the POST variant only.  This parameter enables POST to act as specified method for
                browsers that do not support all 4 REST methods.  Only "PUT" and "DELETE" are supported.
                See <a href="#Examples">examples</a> for more information.
            </td>
        </tr>
    </table>

<h3 id="Examples">Examples</h3>

<h4>Sample PDL files</h4>
<p>Below are a sampling of PDL files:
    <ul>
    <li><a href="/DataEngine/sample_files/join_data_1_pdl.json">Simple PDL for a local cache of a wikimapia feed of hospitals</a></li>
    <li><a href="/DataEngine/sample_files/join_data_2_pdl.json">Simple PDL for a local CSV file</a></li>
</ul>
</p>

<h4>GET</h4>
<p>The GET variant of the web service retrieves PDL(s).</p>

<table class="table">
    <tr>
        <td>GET <a href="/DataEngine/pdl/1"><tt>/DataEngine/pdl/1</tt></a>
        </td>
        <td>retrieves the resource whose UUID=1
        </td>
    </tr>
    <tr>
        <td>GET <a href="/DataEngine/pdl"><tt>/DataEngine/pdl</tt></a>
        </td>
        <td>retrieves all of the PDLs currently in the DataEngine
        </td>
    </tr>
</table>


<h4>POST</h4>
<p>The POST variant of the web service performs 2 functions:
    <ul>
        <li>create a new resource</li>
        <li>using the parameter <tt>_method</tt>, send PUT and DELETE requests using POST for browsers
            that do not support PUT and DELETE methods</li>
    </ul>
</p>

<table class="table">
    <tr>
        <td>POST <tt>/DataEngine/pdl</tt>
        </td>
        <td>create new PDL resource.  Note that you MUST specify a JSON string in the body
        of the request or you will get a 400 error indicating missing required data.  Also, do not specify
        a uuid when using POST to create a new resource---DataEngine will automatically assign a
        uuid when writing to the database
        </td>
    </tr>
    <tr>
        <td>POST <tt>/DataEngine/pdl/123?_method=PUT</tt>
        </td>
        <td>POST request acting as a PUT request using <tt>_method</tt> parameter.   See the PUT section
            for more details.
        </td>
    </tr>
    <tr>
        <td>POST <tt>/DataEngine/pdl/123?_method=DELETE</tt>
        </td>
        <td>POST request acting as a DELETE request using <tt>_method</tt> parameter.  See the DELETE section
            for more details.
        </td>
    </tr>
</table>



<h4>PUT</h4>
<p>The PUT variant of the web service updates an existing resource
</p>

<table class="table">
    <tr>
        <td>PUT <tt>/DataEngine/pdl/123</tt>
        </td>
        <td>updates the PDL resource named 123.  Note that you MUST specify a JSON string in the body
        of the request or you will get a 400 error indicating missing required data
        </td>
    </tr>
</table>


<h4>DELETE</h4>
<p>The DELETE variant of the web service deletes an existing resource
</p>

<table class="table">
    <tr>
        <td>DELETE <tt>/DataEngine/pdl/123</tt>
        </td>
        <td>deletes the PDL resource named 123
        </td>
    </tr>
</table>


</body>
</html>