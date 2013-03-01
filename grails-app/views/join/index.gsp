<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <title>Join Service Documentation and Sandbox</title>
</head>

<body>

    <h2>REST Service Documentation and Sandbox for /join</h2>

    <p>This service provides the ability to <a ="http://en.wikipedia.org/wiki/Join_%28SQL%29">join</a> together 2 data feeds.
        Currently, the following join operations are available:
        <ul>
            <li>left outer join - a union of the data in feed2 into feed1, so that the total number
                                    of items is the same as in feed1, but each item after the join contains the union
                                    of data in the two feeds.  If feed1 and feed2 has the same item with different values, the value of
                                    feed2 is used.  For more details, see <a href="http://en.wikipedia.org/wiki/Join_%28SQL%29#Left_outer_join">Left outer Join</a> in the wikipedia </li>
        </ul>
    </p>

    <h3>Syntax</h3>

    The general form for the <tt>join</tt> operation is
    <pre>/DataEngine/join/[type]</pre>

    Currently, the following syntax are available.  Parameters for these are identical except where noted.
    <dl class="dl-horizontal">
        <table class="table">
            <tr>
                <td><tt>/DataEngine/join/leftOuter</tt></td>
                <td> left outer join </td>
            </tr>
        </table>
    </dl>

    <h3>Parameters</h3>
    <table class="table">
        <tr>
            <td><strong>idls</strong> (required)</td>
            <td>the input data sources, described using the ID of <a href="/DataEngine/idl/index.html">IDLs</a>, separated by a comma.  For example,
                <a href="/DataEngine/join/leftOuter?idls=1,2&pdl=1">/DataEngine/join/leftOuter?idls=1,2&pdl=1</a> (assuming there are IDLs
                named 1 and 2, and a PDL named 1.
            </td>
        </tr>

        <tr>
            <td><strong>pdl</strong> (required)</td>
            <td>the <a href="/DataEngine/pdl/index.html">process description (PDL) file</a> , described using the ID of the PDL.  For example,
                <a href="/DataEngine/join/leftOuter?idls=1,2&pdl=1">/DataEngine/join/leftOuter?idls=1,2&pdl=1</a> (assuming there are IDLs
                named 1 and 2, and a PDL named 1.
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
            </td>
        </tr>
    </table>

</body>
</html>