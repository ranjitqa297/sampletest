package org.gd.automation.core.ui.actions;


import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.util.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;

@Slf4j
public class UIAction {

    WebDriver driver;
    FluentWait<WebDriver> wait;
    Integer implicitTimeout = Integer.valueOf(Config.initConfig().getConfig("timeout.implicit"));
    Long explicitTimeout = Long.valueOf(Config.initConfig().getConfig("timeout.explicit"));
    Long polling = Long.valueOf(Config.initConfig().getConfig("timeout.poll"));

    public UIAction(WebDriver driver) {
        this.driver = driver;
        setWait();
    }

    private void setWait() {
        wait = new FluentWait<>(driver).pollingEvery(
                        Duration.ofMillis(polling))
                .withTimeout(Duration.ofSeconds(explicitTimeout))
                .ignoring(NotFoundException.class);
    }

    public void goToURL(String url) {
        driver.get(url);
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String getVisibleText(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element)).getText();

    }

    public String getPageTitle() {
        return driver.getTitle();

    }

    public boolean openLinkInANewTab(WebElement element) {
        Actions actions = new Actions(driver);
        actions.
                moveToElement(element)
                .keyDown(Keys.COMMAND)
                .click()
                .keyUp(Keys.COMMAND)
                .build()
                .perform();
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
        return true;
    }

    public void delay(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

