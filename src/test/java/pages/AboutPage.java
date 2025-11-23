package pages;

import com.codeborne.selenide.SelenideElement;
import utils.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class AboutPage extends BasePage {
    SelenideElement backToAppButton = $$("button")
            .filterBy(text("Перейти к приложению"))
            .first().shouldBe(visible);
}
