package au.org.ala.apikey

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import geb.spock.GebReportingSpec

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class HomePageSpec extends GebReportingSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test visiting home page without supporting services"() {
        when: "The home page is visited"
        go '/app_apikey'
        report "home page"

        then: "The title is correct"
        title == "API keys | [Org Name Long]"
    }
}
