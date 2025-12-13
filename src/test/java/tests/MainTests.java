package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.junit.Test;
import pages.AboutPage;
import pages.ChatEntryPage;
import pages.MainPage;
import utils.BaseTests;
import utils.ScreenshotMaker;

import java.util.regex.Pattern;

import static utils.Consts.CHAT_CODE_REGEX;

public class MainTests extends BaseTests {

    private final ScreenshotMaker screenshotMaker = new ScreenshotMaker();

    /**
     * TC-MAIN-001
     */
    @Test
    public void testCodeGeneration() {
        MainPage mainPage = MainPage.open();
        mainPage.generateCode();
        String code = mainPage.getCode();
        Assert.assertTrue(Pattern.compile(CHAT_CODE_REGEX).matcher(code).matches());
    }

    /**
     * TC-MAIN-002
     */
    @Test
    public void testCodeDelete() {
        MainPage mainPage = MainPage.open();
        mainPage.generateCode();
        String code = mainPage.getCode();
        Assert.assertTrue(Pattern.compile(CHAT_CODE_REGEX).matcher(code).matches());
        mainPage.deleteCode();
        code = mainPage.getCode();
        Assert.assertTrue(code.isEmpty());
    }

    /**
     * TC-MAIN-003
     */
    @Test
    public void testInitializeButton() {
        MainPage mainPage = MainPage.open();
        mainPage.generateCode();
        String code = mainPage.getCode();
        Assert.assertTrue(Pattern.compile(CHAT_CODE_REGEX).matcher(code).matches());
        ChatEntryPage chatEntryPage = mainPage.initializeConnection();
        String pageUrl = chatEntryPage.getCurrentUrl();
        Assert.assertTrue(pageUrl.contains("chat/" + code + "/entry"));
    }

    /**
     * TC-MAIN-004
     */
    @Test
    public void testInactiveInitializeButton() {
        MainPage mainPage = MainPage.open();
        Assert.assertFalse(mainPage.isInitializeButtonEnabled());
        mainPage.generateCode();
        Assert.assertTrue(mainPage.isInitializeButtonEnabled());
    }

    /**
     * TC-MAIN-005
     */
    @Test
    public void testAboutButton() {
        MainPage mainPage = MainPage.open();
        AboutPage aboutPage = mainPage.openAbout();
        Assert.assertTrue(aboutPage.getCurrentUrl().contains("about"));
    }

    /**
     * TC-MAIN-006
     */
    @Test
    public void testScreenshotsInDifferentResolutions() {
        String originalSize = Configuration.browserSize;
        String[] resolutions = {"1920x1080", "1280x720", "892x873", "390x844", "320x480"};

        for (String resolution : resolutions) {
            Configuration.browserSize = resolution;
            Selenide.closeWebDriver();
            MainPage mainPage = MainPage.open();
            screenshotMaker.takeScreenshot("Скриншот в разрешении " + resolution);
            Assert.assertTrue(mainPage.getCurrentUrl().contains("launchat"));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Configuration.browserSize = originalSize;
        Selenide.closeWebDriver();
        MainPage.open();
    }
}
