package pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static utils.Consts.BASE_URL;

public class MainPage extends BasePage {
    private final SelenideElement generateCodeButton = $$("button")
            .filterBy(text("Сгенерировать"))
            .first();
    private final SelenideElement deleteCodeButton = $$("button")
            .filterBy(text("Удалить код"))
            .first();
    private final SelenideElement initializeButton = $$("button")
            .filterBy(text("Инициировать подключение"))
            .first();
    private final SelenideElement codeTextField = $("input");

    public static MainPage open() {
        Selenide.open(BASE_URL);
        return new MainPage();
    }

    public void generateCode() {
        generateCodeButton.click();
    }

    public void deleteCode() {
        deleteCodeButton.click();
    }

    public ChatEntryPage initializeConnection() {
        initializeButton.click();
        return new ChatEntryPage();
    }

    public String getCode() {
        return codeTextField.getValue();
    }
}
