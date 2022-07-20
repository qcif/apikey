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
<!doctype html>
<html>
<head>
    <meta name="layout" content="${grailsApplication.config.getProperty('skin.layout')}"/>
    <meta name="section" content="home"/>
    <title>Add an App | ${grailsApplication.config.getProperty('skin.orgNameLong')} </title>
</head>
<body>
<div class="row">
    <div class="col-md-9" id="page-body" role="main">
        <h1>${success ? 'App added' : 'App already exists'}</h1>
        <p>
          <g:if test="${success}">
            <h3>App name: ${app.name}</h3>
            <h3>Created: ${app.dateCreated}</h3><br/>
          </g:if>
        </p>

        <g:link url="/apikey" class="btn btn-primary">Back to options</g:link>
    </div>

</div>
</body>
</html>
