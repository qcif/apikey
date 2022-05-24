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

class APIKey {

    String apikey
    String userId //system wide user identifier
    String userEmail //stored redundantly for debug purposes
    boolean enabled
    App app
    Date dateCreated

    Date lastUsed
    String lastRemoteAddr

    static constraints = {
        apikey size: 6..36
        userEmail email: true
        lastUsed nullable: true
        lastRemoteAddr nullable: true
    }

    static mapping = {
        apikey index: true
        userId index: true
        autoTimestamp true
        version false
    }
}
