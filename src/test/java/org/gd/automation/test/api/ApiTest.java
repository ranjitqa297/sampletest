package org.gd.automation.test.api;


import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.api.modules.MostPopular;
import org.gd.automation.core.api.modules.TopStories;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;

@Slf4j()
public class ApiTest {

    MostPopular mostPopular;
    TopStories topStories;
    @BeforeClass
    public void init() {
        mostPopular = new MostPopular();
        topStories = new TopStories();
    }

    @Test(enabled = true,priority = 0)
    public void validateEmailedWithPeriod() {
        log.info("Running -----> validateEmailedWithPeriod");
        mostPopular.validateEmailed();
    }

    @Test(enabled = true,priority = 1)
    public void validateViewedWithPeriod() {
        log.info("Running -----> validateEmailedWithPeriod");
        mostPopular.validateViewed();
    }

    @Test(enabled = true,priority = 3)
    public void validateSharedWithPeriod() {
        log.info("Running -----> validateEmailedWithPeriod");
        mostPopular.validateShared();
    }


    @Test(enabled = true,priority = 4)
    public void getStoriesWithSection() throws ParseException {
        log.info("Running -----> getStoriesWithSection");
        topStories.validateStoriesWithSection();
    }

}
