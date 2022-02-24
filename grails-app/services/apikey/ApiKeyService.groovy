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

import grails.gorm.transactions.Transactional

@Transactional
class ApiKeyService {

    @Transactional(readOnly = true)
    List<APIKey> findAllKeys(String sort, String order, int max, int offset) {
        final keys = APIKey.list(sort: sort, order: order, max: max, offset: offset, fetch: [app: 'eager'], readOnly: true)
        return keys
    }

    def enableKey(String apikey, boolean enabled) {
        final result = APIKey.findByApikey(apikey)
        if (result) {
            result.enabled = enabled
            result.save()
        }
        return result
    }

    /**
     * Retrieve a key by apikey and set the last used timestamp and last remote addr properties
     * @param apikey The key to check
     * @param remoteAddr the remote address that is validating this key
     * @return They key
     */
    def validateKey(String apikey, String remoteAddr = null) {
        final result = APIKey.findByApikey(apikey)
        if (result) {
            result.lastUsed = new Date()
            result.lastRemoteAddr = remoteAddr
            result.save()
        }
        return result
    }
}
