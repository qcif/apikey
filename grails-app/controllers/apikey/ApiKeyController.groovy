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
import au.org.ala.web.NoSSO
import au.org.ala.web.SSO

@SSO
@AlaSecured(value = CASRoles.ROLE_ADMIN, statusCode = 403, view='../magicword')
class ApiKeyController {

    static allowedMethods = [enableKey: 'POST']

    def apiKeyService

    def index() {
        def max = params.int('max', 100)
        def offset = params.int('offset', 0)
        def sort = params.get('sort', 'app.name')
        def order = params.get('order', 'asc')
        def keys = apiKeyService.findAllKeys(sort, order, max, offset)
        respond keys
    }

    @NoSSO
    def enableKey() {
        withForm {
            def key = params['key']
            def enabled = params.boolean('enabled', true)

            def max = params.int('max')
            def offset = params.int('offset')
            def sort = params['sort']
            def order = params['order']
            if (key) {
                def result = apiKeyService.enableKey(key, enabled)
                if (!result) {
                    return render(status: 404)
                }
                log.info("Key {} key={} user={}", result.enabled ? 'enabled' : 'disabled', result.apikey, request.remoteUser)
            } else {
                flash.message = 'No key provided'
            }
            return redirect(action: 'index', params: [max: max, offset: offset, sort: sort, order: order].findAll { it.value })
        }.invalidToken {
            return render(status: 403, view: '../magicword')
        }
    }
}
