<%--
  Created by IntelliJ IDEA.
  User: hkong
  Date: 11/2/12
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Documentation</title>
</head>
<body>

<h2>REST Service Documentation for /source</h2>

<p>This service returns the raw non-streaming data feed at at a specified URL. It is useful as a local proxy service for an external data feed.</p>

<h3>Syntax</h3>
<pre>/DataEngine/source</pre>

<h3>Parameters</h3>
<table class="table">
    <tr>
        <td><strong>url</strong> (required)</td>
        <td>fully qualified data feed endpoint URL, for example
            <ul>
                <li>Simple CSV file:  <a href="http://localhost:8080/DataEngine/source?url=http://localhost:8080/DataEngine/sample_files/yourmapper-markers.csv">/DataEngine/source?url=http://localhost:8080/DataEngine/sample_files/yourmapper-markers.csv</a></li>
                <li>Simple JSON file:  <a href="/DataEngine/source?url=http://localhost:8080/DataEngine/sample_files/simple_cwl.json">/DataEngine/source?url=http://localhost:8080/DataEngine/sample_files/simple_cwl.json</a></li>
                <li>External website:  <a href="/DataEngine/source?url=http://www.google.com">/DataEngine/source?url=http://www.google.com</a>  (See Notes below.)</li>
            </ul>
        </td>
    </tr>
</table>

<h3>Notes</h3>
<ul>
    <li>If you're behind a firewall with a proxy server, you will need to set the <a href="http://localhost:8080/DataEngine/setting#networkProxy">Network Proxy settings</a></li>
</ul>

</body>
</html>