package pages;

import com.codeborne.selenide.SelenideElement;
import utils.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ChatEntryPage extends BasePage {
    private final SelenideElement enterChatButton = $$("button")
            .filterBy(text("Перейти в чат"))
            .first().shouldBe(visible);

    public void enterChat() {
        enterChatButton.click();
    }
}
