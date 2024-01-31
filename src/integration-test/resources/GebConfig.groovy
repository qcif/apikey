import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

/*
 * This file configures the geb tests for chrome and firefox.
 * The integrationTest gradle task allows running with only one driver at time.
 *
 * The path to the drivers is set in build.gradle.
 */

environments {

    // --- Chrome / Chromium ---

    // run using: ./gradlew -Dgeb.env=chrome iT
    chrome {
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments("--remote-allow-origins=*")
            new ChromeDriver(o)
        }
    }

    // run using: ./gradlew -Dgeb.env=chromeHeadless iT
    chromeHeadless {
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments('headless')
            o.addArguments("window-size=1920,1080")
            o.addArguments('--disable-dev-shm-usage')
            o.addArguments("--remote-allow-origins=*")
            new ChromeDriver(o)
        }
    }

    // --- FireFox ---

    // run using: ./gradlew -Dgeb.env=firefox iT
    firefox {
        driver = { new FirefoxDriver() }
    }

    // run using: ./gradlew -Dgeb.env=firefoxHeadless iT
    firefoxHeadless {
        driver = {
            FirefoxOptions o = new FirefoxOptions()
            o.addArguments('-headless')
            new FirefoxDriver(o)
        }
    }
}