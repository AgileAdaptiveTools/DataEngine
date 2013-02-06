<%--
  Created by IntelliJ IDEA.
  User: hkong
  Date: 11/15/12
  Time: 6:57 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Slurped Viewer</title>
</head>
<body>

    <form class="form-horizontal">
        <fieldset>
            <div id="legend" class="">
                <legend class="">Slurped Viewer</legend>
            </div>

            <label class="control-label">Original filename</label>
            <div class="controls">
                <input type="text" class="input-xlarge" readonly="readonly" value="${origName}">
            </div>

            <label class="control-label">MIME Type</label>
            <div class="controls">
                <input type="text" readonly="readonly" value="${mimetype}">
            </div>

            <label class="control-label">File size</label>
            <div class="controls">
                <input type="text" readonly="readonly" value="${filesize} bytes">
            </div>

            <label class="control-label">Content</label>
            <div class="controls">
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        %{--<li class="active"><a href="#1" data-toggle="tab">Tabular</a></li>--}%
                        <li class="active"><a href="#2" data-toggle="tab">JSON</a></li>
                        <li class=""><a href="#3" data-toggle="tab">CWL</a></li>
                        <li class=""><a href="#10" data-toggle="tab">Raw</a></li>
                    </ul>
                    <div class="tab-content">
                        %{--<div class="tab-pane active" id="1">--}%
                            %{--<table>--}%
                                %{--<thead>--}%
                                %{--<tr>--}%
                                    %{--<th>x</th>--}%
                                    %{--<th>y</th>--}%
                                %{--</tr>--}%
                                %{--</thead>--}%
                                %{--<tbody>--}%
                                %{--<tr>--}%
                                    %{--<td>1</td>--}%
                                    %{--<td>2</td>--}%
                                %{--</tr>--}%
                                %{--</tbody>--}%
                            %{--</table>--}%
                        %{--</div>--}%
                        <div class="tab-pane active" id="2">
                            <div class="textarea">
                                <textarea type="" class="">${ feed.toJson( true ) }</textarea>
                            </div>
                        </div>
                        <div class="tab-pane" id="3">
                            <div class="textarea">
                                <textarea type="" class="">${ feed.toCwl( true ) }</textarea>
                            </div>
                        </div>
                        <div class="tab-pane" id="10">
                            <div class="textarea">
                                <textarea type="" class="">${ feed.toString() }</textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </fieldset>
    </form>
</body>
</html>