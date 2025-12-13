package utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

public class ScreenshotMaker {

    public void takeScreenshot(String name) {
        try {
            // Добавляем небольшую задержку перед скриншотом для полной загрузки страницы
            Thread.sleep(500);
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            if (screenshot != null && screenshot.length > 0) {
                Allure.addAttachment(name, "image/png",
                        new ByteArrayInputStream(screenshot), "png");
                System.out.println("Скриншот создан: " + name + ", размер: " + screenshot.length + " байт");
            } else {
                System.err.println("Не удалось создать скриншот: пустое изображение");
            }
        } catch (Exception e) {
            System.err.println("Не удалось создать скриншот '" + name + "': " + e.getMessage());
        }
    }
}
