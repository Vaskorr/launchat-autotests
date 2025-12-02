package utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

public class ScreenshotMaker {

    private void takeScreenshot(String name) {
        try {
            Thread.sleep(1000);
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment(name, "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.err.println("Screenshot failed: " + e.getMessage());
        }
    }
}

