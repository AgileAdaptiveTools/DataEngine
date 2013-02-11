<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>IDL Documentation</title>
</head>

<body>

    <h2>IDL Documentation</h2>

    <p>The Ingest Description Language is a DSL written using JSON to describe a data feed.  This enables
        the DataEngine to semantically understand a data feed, which greatly expands what the DataEngine can do
        with the data feed.</p>

    <p>IDLs are used throughout the DataEngine, usually using the IDL's UUID as a shorthand for specifying a data feed.
        The UUID is generated via the <a href="/DataEngine/idl/index.gsp">IDL REST services</a>, which provides full CRUD services for working with IDLs in the system.
    </p>

    <h3>Examples</h3>
    <p>The following is a simple example of a IDL file.</p>
    <pre>
{
    "version":"Ingest Description Language (IDL) v1.1301.23",
    "idl_uuid":12345,
    "title":"Hospitals from wikimapia",
    "poc":"john@example.com (John Smith)",
    "creationDate":"2013-01-08T06:00:02Z",
    "modificationDate":"2013-01-08T06:00:02Z",
    "source":"http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json",
    "source_format":"json",
    "source_uri":"http://seamlessc2.mitre.org/DataEngine/source/203451231234",
    "description":"Description of the datafeed",
    "dslv":{
        "$default_includes": "none",
        "id":[ "Integer", "$uuid" ],
        "name":[ "String", "$title" ],
        "date":[ "Date", "MM/DD/YY", "$startTime" ],
        "location.lon":[ "LatLonAlt", "WGS84_Map", "$lon" ],
        "location.lat":[ "LatLonAlt", "WGS84_Map", "$lat" ],
    }
}
    </pre>

    <p>As with any JSON file, the IDL is a set of key/value pairs.  Some keys point to Strings, some to numbers,
        and some to hashmaps.  The main keys are metadata about the data feed, such as source, and POC.  The <tt>dslv</tt> key
        is used to store the actual description of the data feed.</p>
    <p>Below, you'll find a explanations for all supported keys and what they do</p>
    <p>Some additional samples:
        <ul>
            <li><a href="/DataEngine/sample_files/join_data_1_idl.json">Simple IDL for a local cache of a wikimapia feed of hospitals</a></li>
            <li><a href="/DataEngine/sample_files/join_data_2_idl.json">Simple IDL for a local CSV file</a></li>
        </ul>
    </p>

    <h3>Syntax</h3>
    <table class="table">
        <tr>
            <td><strong>creationDate</strong> (generated)</td>
            <td>the date this IDL was first posted - it follows the ISO 8601 convention for combined date and time.</td>
        </tr>
        <tr>
            <td><strong>idl_uuid</strong> (generated)</td>
            <td>the IDL UUID on this system - this is assigned when a POST /idl request is made.  It can be
            found in the Location header of the response.</td>
        </tr>
        <tr>
            <td><strong>description</strong> (optional)</td>
            <td>A human readable text description for this data feed.</td>
        </tr>
        <tr>
            <td><strong>dslv</strong> (optional)</td>
            <td>specifications for how to treat items in a data feed - this is the heart of the IDL, and is described
                in detail below.</td>
        </tr>
        <tr>
            <td><strong>modificationDate</strong> (generated)</td>
            <td>the date this IDL was last updated - it follows the ISO 8601 convention for combined date and time.</td>
        </tr>
        <tr>
            <td><strong>poc</strong> (required)</td>
            <td>Point of contact for this data feed.  It should be of the form <tt>email (full name)</tt></td>
        </tr>
        <tr>
            <td><strong>source</strong> (required)</td>
            <td>URL to the original data source</td>
        </tr>
        <tr>
            <td><strong>source_format</strong> (required)</td>
            <td>the format of the original data source - currently, the following formats are supported
                <ul>
                    <li><tt>csv</tt> - Comma Separated Value</li>
                    <li><tt>json</tt> - JavaScript Object Notation</li>
            </ul>
            </td>
        </tr>
        <tr>
            <td><strong>title</strong> (required)</td>
            <td>Name for this data feed.  It does not need to be unique, but should describe concisely
            what this data feed is</td>
        </tr>
        <tr>
            <td><strong>version</strong> (optional)</td>
            <td>the IDL version</td>
        </tr>
    </table>



<h3>DSLV Description</h3>

    <p>The <tt>"dslv"</tt> key specifies how to treat items in the data feed.  It is made up of 2 kinds of specifications:
        <ul>
            <li>feed specifications, which describes the feed and all items in the feed (a kind of global specification at the
                the feed level</li>
            <li>field specifications, which describes each column/tag/field within an item</li>
        </ul>
    </p>

<h4>Feed specifications</h4>
    <p>Feed specifications describes the feed and all items in the feed.</p>
    <table class="table">
        <tr>
            <td><strong>"$default_includes"</strong> (optional)</td>
            <td>One of the features of the dslv is to let you specify which column/tag/fields to include in the output feed.
                This key lets you include all or include only those specified.  The possible values are:
                <ul>
                <li><tt>"all"</tt> - (default) which includes all columns/tags/fields, and only modifies them if they are
                    specifically described</li>
                <li><tt>"none"</tt> - which only includes a column/tag/field if it is specifically described</li>
            </ul>
            </td>
        </tr>
    </table>

<h4>Field specifications</h4>
    <p>Field specifications describe individual columns/tags/fields of each item in the feed.  The keys for field
        specifications are the columns/tags/fields names of the original feed.  For example, in this JSON feed,
        <pre>
{
    "employees": [
        { "fName":"John", "lName":"Doe", yrs:5 },
        { "fName":"Anna", "lName":"Smith", yrs:6 },
        { "fName":"Peter", "lName":"Jones", yrs:1 }
    ]
}
        </pre>
        a dslv description for the <tt>firstname</tt> field may be <pre>"fname": ["String","first_name"]</pre>
        which says to treat <tt>"fname"</tt> as a String, and rename it to <tt>"first_name"</tt> in the output.
        </p>

        <p>In general, field specifications follow the format
           <pre>"field name": [ "Type", params, ..., "new name" ]</pre>
            where <tt>"Type"</tt> is the data type of the column/tag/field, <tt>params</tt> is a type-specific
            set of parameters needed to process the data, and <tt>"new name"</tt> is the new name for this column/tag/field
            in the output feed.  A discussion of the supported Types and their parameters follows.
        </p>

    <h5>Normalized Data Fields</h5>

        <p>The <tt>"new name"</tt> specification has a special use case.  If the name starts with the reserved character
            <tt>"$"</tt>, it is used to map to one of the normalized DataEngine fields.  These normalized fields represent
            data that are most frequently used, and by mapping, it makes the semantics of that data that much more
            clear.</p>
        <p>Here are the accepted normalized DataEngine data fields.  To use one, simply use the normalized field name
            for <tt>"new name"</tt>.</p>

        <table class="table">
            <tr>
                <td><strong><tt>"$description"</tt></strong></td>
                <td>an item's description</td>
            </tr>
            <tr>
                <td><strong><tt>"$lat"</tt></strong></td>
                <td>an item's geographic latitude</td>
            </tr>
            <tr>
                <td><strong><tt>"$lon"</tt></strong></td>
                <td>an item's geographic longitude</td>
            </tr>
            <tr>
                <td><strong><tt>"$startTime"</tt></strong></td>
                <td>timestamp for this item</td>
            </tr>
            <tr>
                <td><strong><tt>"$title"</tt></strong></td>
                <td>an item's display name</td>
            </tr>
            <tr>
                <td><strong><tt>"$uuid"</tt></strong></td>
                <td>an item's UUID - note that in order to refer to each individual item, a UUID is required, and if
                no field is mapped to <tt>"$uuid"</tt>, the system will automatically generate one for each item.</td>
            </tr>
        </table>

    <h5>Date Type</h5>

    <p>The Date type is used for integers.  Its generic form is <pre><tt>"name": [ "Date", "format", "newName" ]</tt></pre>
    </p>
    <table>
        <tr>
            <td><strong><tt>"format"</tt></strong></td>
            <td>The format of the date in the original feed (all dates in output are ISO 8601).  Currently, the
                following formats are supported:
                <ul>
                    <li><tt>"ISO8601"</tt></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><strong><tt>"new name"</tt></strong></td>
            <td>The new name for the column/tag/field in the output</td>
        </tr>
    </table>

    <h5>Integer Type</h5>

    <p>The Integer type is used for integers.  Its generic form is <pre><tt>"name": [ "Integer", "newName" ]</tt></pre>
    </p>
    <table>
        <tr>
            <td><strong><tt>"new name"</tt></strong></td>
            <td>The new name for the column/tag/field in the output</td>
        </tr>
    </table>

    <h5>LatLonAlt Type</h5>

    <p>The LatLonAlt type is used for geographic coordinates.  Its generic form is <pre><tt>"name": [ "LatLonAlt", "format", "newName" ]</tt></pre>
    </p>
    <table>
        <tr>
            <td><strong><tt>"format"</tt></strong></td>
            <td>The format of the geographic coordinate.  Currently, the following formats are supported:
                <ul>
                    <li><tt>"WGS84_Map"</tt></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><strong><tt>"new name"</tt></strong></td>
            <td>The new name for the column/tag/field in the output</td>
        </tr>
    </table>

    <h5>String Type</h5>

    <p>The String type is the most basic type, used for any text strings.  It is the default for all columns/tags/fields in a data feed
        if a IDL is not specified.  Its generic form is <pre><tt>"name": [ "String", "newName" ]</tt></pre>
    </p>
    <table>
        <tr>
            <td><strong><tt>"new name"</tt></strong></td>
            <td>The new name for the column/tag/field in the output</td>
        </tr>
    </table>




</body>
</html>