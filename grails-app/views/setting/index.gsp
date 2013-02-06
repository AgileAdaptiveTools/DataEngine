<%--
  Created by IntelliJ IDEA.
  User: hkong
  Date: 10/31/12
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>DataEngine Settings</title>
</head>
<body>

    <g:if test="${flash.message}">
        "${flash.message}"
    </g:if>

    <g:form controller="setting" class="form-horizontal" action="saveSettings" method='post' enctype='multipart/form-data' >
        <fieldset>
            <legend>Data Engine Settings</legend>

            <h5 id="#networkProxy">Network Proxy</h5>
            <div class="control-group">
                <label class="control-label">Enable network proxy</label>
                <div class="controls">
                    <label class="checkbox">
                        <input type="checkbox" value="true" id='useProxy' name='useProxy' ${useProxy?'checked="true"':''}>
                    </label>
                    <span class="help-block">Toggle on to use the network proxy server, toggle off to use the network directly without a proxy server.</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="proxyhostport">Hostname and port</label>
                <div class="controls">
                    <input type="text" placeholder="proxy.company.org:8080" class="input-xxlarge" id="proxyhostport" name="proxyhostport" value="${proxyhostport}">
                    <span class="help-block">Network proxy server hostname and port (e.g., proxy.company.org:8080)</span>
                </div>
            </div>
        </fieldset>

        <div class="control-group">
            <div class="controls">
                <g:actionSubmit value="Save Changes" action="saveSettings" class="btn btn-success" />
                <g:actionSubmit value="Cancel" action="cancel" class="btn btn-warning"/>
            </div>
        </div>



    </g:form>

</body>
</html>