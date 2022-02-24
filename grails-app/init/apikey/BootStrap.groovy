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

class BootStrap {

    def init = { servletContext ->
        preloadQueries()
    }
    def destroy = {
    }

    private def preloadQueries() {
        def apps = App.findAll()
        def appNames = apps*.name.toSet()
        if(!appNames.contains("collectory")){ new App([name:"collectory"]).save() }
        if(!appNames.contains("biocache")){ new App([name:"biocache"]).save() }
        if(!appNames.contains("spatialportal")){ new App([name:"spatialportal"]).save() }
        if(!appNames.contains("bdrs")){ new App([name:"bdrs"]).save() }
        if(!appNames.contains("fieldcapture")){ new App([name:"fieldcapture"]).save() }
        if(!appNames.contains("soils2sat")){ new App([name:"soils2sat"]).save() }
        if(!appNames.contains("alerts")){ new App([name:"alerts"]).save() }
        if(!appNames.contains("avh")){ new App([name:"avh"]).save() }
        if(!appNames.contains("ozcam")){ new App([name:"ozcam"]).save() }
        if(!appNames.contains("regions")){ new App([name:"regions"]).save() }
        if(!appNames.contains("specieslists")){ new App([name:"specieslists"]).save() }
        if(!appNames.contains("fielddata")){ new App([name:"fielddata"]).save() }
        if(!appNames.contains("sightings")){ new App([name:"sightings"]).save() }
        if(!appNames.contains("bowerbird")){ new App([name:"bowerbird"]).save() }
        if(!appNames.contains("said")){ new App([name:"said"]).save() }
        if(!appNames.contains("emmet")){ new App([name:"emmet"]).save() }
        if(!appNames.contains("danno")){ new App([name:"danno"]).save() }
        if(!appNames.contains("amrin")){ new App([name:"amrin"]).save() }
        if(!appNames.contains("fishmap")){ new App([name:"fishmap"]).save() }
        if(!appNames.contains("biolink")){ new App([name:"biolink"]).save() }
        if(!appNames.contains("edgar")){ new App([name:"edgar"]).save() }
        if(!appNames.contains("volunteerportal")){ new App([name:"volunteerportal"]).save() }
        if(!appNames.contains("m.ala.org.au")){ new App([name:"m.ala.org.au"]).save() }
    }
}
