<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <title>Join Documentation and Sandbox</title>
</head>

<body>

    <h2>REST Service Documentation and Sandbox for /join</h2>

    <p>This service provides the ability to <a ="http://en.wikipedia.org/wiki/Join_%28SQL%29">join</a> together 2 data feeds.
        Currently, the following join operations are available:
        <ul>
            <li>left outer join - a union of the data in feed2 into feed1, so that the total number
                                    of items is the same as in feed1, but each item after the join contains the union
                                    of data in the two feeds.  For more details, see <a href="http://en.wikipedia.org/wiki/Join_%28SQL%29#Left_outer_join">Left outer Join</a> in the wikipedia </li>
        </ul>
    </p>



</body>
</html>