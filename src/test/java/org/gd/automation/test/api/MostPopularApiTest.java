package org.gd.automation.test.api;


import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.api.modules.MostPopular;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j()
public class MostPopularApiTest {

    MostPopular mostPopular;

    @BeforeClass
    public void init() {
        mostPopular = new MostPopular();
    }

    @Test(enabled = true)
    public void validateEmailedWithPeriod() {
        log.info("Running -----> validateEmailedWithPeriod");
        mostPopular.validateEmailed();
    }

    @Test(enabled = true)
    public void validateViewedWithPeriod() {
        log.info("Running -----> validateEmailedWithPeriod");
        mostPopular.validateViewed();
    }

    @Test(enabled = true)
    public void validateSharedWithPeriod() {
        log.info("Running -----> validateEmailedWithPeriod");
        mostPopular.validateShared();
    }
}
