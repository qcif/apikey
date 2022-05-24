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

import org.springframework.beans.factory.config.BeanDefinition

// Place your Spring DSL code here
beans = {
    if (application.config.flyway.enabled) {

        BeanDefinition hibernateDatastore = getBeanDefinition('hibernateDatastore')
        if (hibernateDatastore) {
            def dependsOnList = ['flywayInitializer'] as Set
            if (hibernateDatastore.dependsOn?.length > 0) {
                dependsOnList.addAll(hibernateDatastore.dependsOn)
            }
            hibernateDatastore.dependsOn = dependsOnList as String[]
        }

        BeanDefinition sessionFactoryBeanDef = getBeanDefinition('sessionFactory')

        if (sessionFactoryBeanDef) {
            def dependsOnList = ['flywayInitializer'] as Set
            if (sessionFactoryBeanDef.dependsOn?.length > 0) {
                dependsOnList.addAll(sessionFactoryBeanDef.dependsOn)
            }
            sessionFactoryBeanDef.dependsOn = dependsOnList as String[]
        }
    }
    else {
        log.info "Grails Flyway plugin has been disabled"
    }
}
