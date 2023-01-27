package org.gd.automation.test.ui;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.gd.automation.core.ui.BaseUI;
import org.gd.automation.core.ui.pages.HomePage;
import org.testng.annotations.Test;

import static org.gd.automation.core.util.JsonHelper.testData;

public class UITest extends BaseUI {

    @Test(enabled = true)
    public void validateHealthPage() throws JsonProcessingException {
        HomePage homePage = new HomePage(getDriver());
        homePage.openApp();
        homePage.validatePage(testData().getValue("homeTitle"));
        homePage.openLinkInANewTab("health");
        homePage.validatePage(testData().getValue("healthTitle"));


    }


}
