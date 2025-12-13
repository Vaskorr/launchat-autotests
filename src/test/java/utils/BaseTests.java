package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;


abstract public class BaseTests {

    @Rule(order = Integer.MIN_VALUE)
    public TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot("FAILED: " + description.getMethodName());
        }
    };

    private void takeScreenshot(String name) {
        try {
            if (Selenide.webdriver().object() == null) {
                System.err.println("Браузер уже закрыт, скриншот невозможен: " + name);
                return;
            }
            Thread.sleep(500);
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            if (screenshot != null && screenshot.length > 0) {
                Allure.addAttachment(name, "image/png",
                        new ByteArrayInputStream(screenshot), "png");
                System.out.println("Скриншот создан: " + name);
            } else {
                System.err.println("Скриншот пустой: " + name);
            }
        } catch (Exception e) {
            System.err.println("Не удалось создать скриншот '" + name + "': " + e.getMessage());
        }
    }

    @BeforeClass
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true)
        );
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
    }
}
