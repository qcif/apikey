/*
 * Copyright (C) 2022 Atlas of Living Australia
 * All Rights Reserved.
 *
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 */

package apikey

import au.org.ala.web.AlaSecured
import au.org.ala.web.CASRoles
import au.org.ala.web.SSO

class AppController {

    LocalAuthService localAuthService

    def index() {}

    @SSO
    @AlaSecured(value = CASRoles.ROLE_ADMIN, statusCode = 403)
    def addAnApp() {
        def result = App.findByName(params.name)
        if (!result) {
            App app = new App([name:params.name])
            def userDetails = localAuthService.userDetails()
            app.userId =  userDetails[0]
            app.userEmail = userDetails[0]
            app.save(validate: true, flush: true)
            if (app.hasErrors()) {
                ["success":false, authorised:true]
            } else {
                ["success":true, app:app, authorised:true]
            }
        } else {
            ["success":false]
        }
    }
}
