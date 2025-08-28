package solidgate.chorniak.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import java.time.Duration;

public abstract class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = Duration.ofSeconds(10).toMillis();
        Configuration.pageLoadTimeout = Duration.ofSeconds(30).toMillis();
    }
}
