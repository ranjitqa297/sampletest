package org.gd.automation.core.ui;

import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.util.JsonHelper;
import org.gd.automation.core.util.TestType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
@Slf4j
public class BaseUI implements TestType {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal();
    FluentWait<ThreadLocal<WebDriver>> wait;
    public JsonHelper jsonHelper = new JsonHelper();


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver.set(new ChromeDriver(chromeOptions));
        jsonHelper.setTestType(testType());
        log.info("Driver has started ...... ");
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        getDriver().quit();
        log.info("Driver has closed ...... ");
    }

    @Override
    public String testType() {
        return "WEB";
    }
}
