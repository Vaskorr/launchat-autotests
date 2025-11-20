package tests;

import org.junit.Assert;
import org.junit.Test;
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
}
