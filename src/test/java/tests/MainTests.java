package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.AboutPage;
import pages.ChatEntryPage;
import pages.MainPage;
import utils.BaseTests;

import java.util.regex.Pattern;

import static utils.Consts.CHAT_CODE_REGEX;

public class MainTests extends BaseTests {

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
        System.out.println(aboutPage.getCurrentUrl());
        Assert.assertTrue(aboutPage.getCurrentUrl().contains("about"));
    }
}
