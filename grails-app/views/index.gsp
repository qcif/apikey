%{--
  - Copyright (C) 2022 Atlas of Living Australia
  - All Rights Reserved.
  -
  - The contents of this file are subject to the Mozilla Public
  - License Version 1.1 (the "License"); you may not use this file
  - except in compliance with the License. You may obtain a copy of
  - the License at http://www.mozilla.org/MPL/
  -
  - Software distributed under the License is distributed on an "AS
  - IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
  - implied. See the License for the specific language governing
  - rights and limitations under the License.
  --}%

<%@ page import="apikey.App" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="${grailsApplication.config.getProperty('skin.layout')}"/>
    <meta name="section" content="home"/>
    <title>API keys |  ${grailsApplication.config.getProperty('skin.orgNameLong')}</title>
    <asset:stylesheet src="application.css" />
</head>
<body>
<div class="row">
    <auth:ifAllGranted roles="ROLE_ADMIN">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Add an App</h3>
                </div>
                <div class="panel-body">
                    <g:form controller="App" action="addAnApp">
                        <div class="form-group">
                            <label>App name</label>
                            <input class="form-control" type="text" size="36" width="200" name="name"/>
                            <p class="help-block">Please specify a unique name for you application</p>
                        </div>
                        <g:submitButton name="submit" value="Add App" class="btn btn-primary"/>
                    </g:form>
                </div>
            </div>
        </div>
    </auth:ifAllGranted>
    <auth:ifAllGranted roles="ROLE_ADMIN">
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Get an API key</h3>
            </div>
            <div class="panel-body">
                <g:form action="submit" controller="getKey">
                    <div class="form-group">
                        <label for="appName">App name:</label>
                        <g:select class="form-control" optionKey="name" optionValue="name" name="appName" from="${App.findAll()}" /><br/>
                    </div>
                    <p class="help-block">
                        This is currently only available for ${grailsApplication.config.getProperty('skin.orgNameLong')} developers.<br/>
                        These keys give <b>write</b> access to certain applications.
                    </p>
                    <g:submitButton name="Generate a key for this app" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </div>
    </auth:ifAllGranted>
    <div class="clearfix"></div>
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Check an API key</h3>
            </div>
            <div class="panel-body">
                <g:form method="GET" action="checkKey" controller="checkKey">
                    <div class="form-group">
                        <label>Key</label>
                        <input class="form-control" type="text" size="36" width="200" name="apikey"/>
                        <p class="help-block">To check the validity of a check, paste the key in the text box below and hit "Check key"</p>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Check key"/> <auth:ifAllGranted roles="ROLE_ADMIN"><g:link controller="apiKey" class="btn btn-link">View all keys</g:link></auth:ifAllGranted>
                </g:form>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Check an API key using webservices</h3>
            </div>
            <div class="panel-body">
                <g:form controller="checkKey" action="webserviceCheck" method="get">
                    <div class="form-group">
                        <label>Key</label>
                        <input class="form-control" type="text" size="36" width="200" name="apikey"/>
                        <p class="help-block">
                            To test the webservice check, use this form.
                            The response will be in JSON.
                        </p>
                    </div>
                    <g:submitButton name="submit" value="Check key via webservice" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
