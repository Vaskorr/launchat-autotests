package utils;

import com.codeborne.selenide.WebDriverRunner;

abstract public class BasePage {
    public String getCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }
}
