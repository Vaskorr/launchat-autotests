package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;


abstract public class BaseTests {

    private void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
    }

    @Before
    public void init() {
        setUp();
    }

    @After
    public void onDone() {
        Selenide.closeWebDriver();
    }
}
