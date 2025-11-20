package pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static utils.Consts.BASE_URL;

public class MainPage {
    private final SelenideElement generateCodeButton = $("button").shouldHave(text("Сгенерировать"));
    private final SelenideElement codeTextField = $("input");

    public static MainPage open() {
        Selenide.open(BASE_URL);
        return new MainPage();
    }

    public void generateCode() {
        generateCodeButton.click();
    }

    public String getCode() {
        return codeTextField.getValue();
    }
}
