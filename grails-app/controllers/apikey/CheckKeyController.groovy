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

import au.org.ala.web.SSO
import grails.converters.JSON

class CheckKeyController {

    def apiKeyService

    def index() {}

    @SSO(gateway = true)
    def checkKey() {
        def result = APIKey.findByApikey(params.apikey)
        [valid:result!=null && result.enabled, key:result]
    }

    def webserviceCheck() {
        final apikey = params['apikey']
        final remoteAddr = request.remoteAddr
        final result = apiKeyService.validateKey(apikey, remoteAddr)

        def jsonValue
        if (result) {
            if (result.enabled) {
                log.info('Valid apikey request apikey={} userId={} userEmail={} app={} remoteAddr={}', params.apikey, result.userId, result.userEmail, result.app.name, remoteAddr)
                jsonValue = [
                        valid:true,
                        userId:result.userId,
                        userEmail: result.userEmail,
                        app: result.app.name
                ]
            } else {
                log.info('Disabled apikey request apikey={} userId={} userEmail={} app={} remoteAddr={}', params.apikey, result.userId, result.userEmail, result.app.name, remoteAddr)
                jsonValue = [valid: false]
            }
        } else {
            log.warn("Invalid apikey request apikey={} remoteAddr={}", params.apikey, remoteAddr)
            jsonValue = [valid: false]
        }
        return render(jsonValue as JSON, contentType:'application/json', status: 200)
    }
}
