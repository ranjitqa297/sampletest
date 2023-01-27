package org.gd.automation.core.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.ui.actions.UIAction;
import org.gd.automation.core.util.Config;
import org.gd.automation.core.util.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.TestException;

import java.text.ParseException;

@Slf4j
public class HomePage {
    UIAction uiAction;
    Helper helper = new Helper();

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        uiAction = new UIAction(driver);
    }

    @FindBy(css = "div[data-testid*='nav'] a[href$='health']")
    public WebElement health_nav_link;

    @FindBy(css = "span[data-testid='todays-date']")
    public WebElement today_date_label;

    public void openApp() {
        try {
            uiAction.goToURL(Config.initConfig().getConfig("web.url"));
            log.info("Passed: Application has lunched.....");
        } catch (Exception e) {
            throw new TestException("Failed: Unable to open application");
        }
    }

    public void validatePage(String pageTitle) {
        try {
            uiAction.delay(1);
            Assert.assertEquals(uiAction.getPageTitle(), pageTitle);
            log.info("Passed: expected page title [ " + pageTitle + " ] is matched actual page title [ " + uiAction.getPageTitle() + " ]");
        } catch (AssertionError e) {
            throw new TestException("Failed: expected page title [ " + pageTitle + " ] is mismatched with actual page title [ " + uiAction.getPageTitle() + " ]");
        } catch (Exception e) {
            throw new TestException("Failed: Unable to validate the page title");
        }
    }

    public void validateTodayDate(String pageTitle) throws ParseException {
        String todayDateExp = null;
        String todayDateAct = null;
        try {
            todayDateExp = helper.getTodayFormattedDate("EEEE, MMMM dd,yyyy");
            todayDateAct = uiAction.getVisibleText(today_date_label).trim();
            Assert.assertEquals(todayDateAct, todayDateExp);
            log.info("Passed: expected today's date [ " + todayDateExp + " ] is matched with actual today's date captured from the page [ " + todayDateAct + " ]");
        } catch (AssertionError e) {
            throw new TestException("Failed: expected today's date [ " + todayDateExp + " ] is mismatched with actual today's date captured from the page [ " + todayDateAct + " ]");
        } catch (Exception e) {
            throw new TestException("Failed: Unable to validate the today's date");
        }
    }

    public void openLinkInANewTab(String linkName) {
        try {
            if (linkName.equalsIgnoreCase("health"))
                Assert.assertTrue(uiAction.openLinkInANewTab(health_nav_link));

        } catch (AssertionError e) {
            throw new TestException("Failed: Unable to open the link in a new tab");
        } catch (Exception e) {
            throw new TestException("Failed: Unable to open the link in a new tab");
        }
    }

}
