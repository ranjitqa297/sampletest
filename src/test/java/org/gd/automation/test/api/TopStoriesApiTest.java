package org.gd.automation.test.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.api.modules.MostPopular;
import org.gd.automation.core.api.constants.EndPoints;
import org.gd.automation.core.api.modules.TopStories;
import org.gd.automation.core.util.Helper;
import org.gd.automation.core.util.JsonHelper;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j()
public class TopStoriesApiTest {

    TopStories topStories;


    @BeforeClass
    public void init() {

        topStories = new TopStories();

    }

    @Test(enabled = true)
    public void getStoriesWithSection() throws ParseException {
        log.info("Running -----> getStoriesWithSection");
        topStories.validateStoriesWithSection();
    }

}
