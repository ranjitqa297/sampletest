package org.gd.automation.core.api.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.api.BaseAPI;
import org.gd.automation.core.api.constants.EndPoints;
import org.testng.Assert;
import org.testng.TestException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.gd.automation.core.util.Helper.getFormattedDate;
import static org.gd.automation.core.util.JsonHelper.testData;

@Slf4j
public class TopStories extends BaseAPI {

    public void validateStoriesWithSection() throws ParseException {

        String sDate = null;
        String last_updated = null;
        try {
            String sSection = testData().getValue("section");
            sDate = testData().getValue("date");
            AtomicInteger counter = new AtomicInteger();
            setEndPoint(EndPoints.TOP_STORIES_SECTION);
            Response resp = get(new RequestSpecBuilder().addPathParams(new HashMap<>() {{
                put("section", sSection);
            }}));
            last_updated = resp.jsonPath().getString("last_updated");
            resp.jsonPath().getList("results.section").stream().forEach(section -> {
                if (String.valueOf(section).equalsIgnoreCase(sSection) && counter.get() < 3) {
                    counter.getAndIncrement();
                }
            });
            Assert.assertEquals(getFormattedDate("yyyy-MM-dd", last_updated), sDate);
            log.info("Passed: expected last_updated date [ " + sDate + " ] is matched with actual last_updated date  [ " + getFormattedDate("yyyy-MM-dd", last_updated) + " ]");
        } catch (AssertionError e) {
            log.info("Failed: expected last_updated date [ " + sDate + " ] is mismatched with actual last_updated date  [ " + getFormattedDate("yyyy-MM-dd", last_updated) + " ]");
            throw new TestException("Failed: expected last_updated date [ " + sDate + " ] is mismatched with actual last_updated date  [ " + getFormattedDate("yyyy-MM-dd", last_updated) + " ]");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
