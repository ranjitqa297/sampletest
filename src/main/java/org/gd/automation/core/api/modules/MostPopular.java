package org.gd.automation.core.api.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.gd.automation.core.api.BaseAPI;
import org.gd.automation.core.api.constants.EndPoints;
import org.testng.Assert;
import org.testng.TestException;

import java.util.HashMap;

import static org.gd.automation.core.util.Helper.isUrlValid;
import static org.gd.automation.core.util.JsonHelper.testData;

@Slf4j

public class MostPopular extends BaseAPI {

    public void validateEmailed() {

        int num_results = 0;
        int resultJsonArrayLength = 0;
        try {
            setEndPoint(EndPoints.MOST_POPULAR_EMAILED_PERIOD);
            Response resp = get(new RequestSpecBuilder().addPathParams(new HashMap<>() {{
                put("period", testData().getValue("period"));
            }}));
            num_results = resp.jsonPath().getInt("num_results");
            resultJsonArrayLength = resp.jsonPath().getList("results").size();
            Assert.assertEquals(num_results, resultJsonArrayLength);
            log.info("Passed: num_results [ " + num_results + " ] is matched with results count [ " + resultJsonArrayLength + " ]");
        } catch (AssertionError e) {
            throw new TestException("Failed: num_results [ " + num_results + " ] is mismatched with results count [ " + resultJsonArrayLength + " ]");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void validateViewed() {
        try {
            setEndPoint(EndPoints.MOST_POPULAR_EMAILED_PERIOD);
            Response resp = get(new RequestSpecBuilder().addPathParams(new HashMap<>() {{
                put("period", testData().getValue("period"));
            }}));
            resp.jsonPath().getList("results.url").stream().forEach(url -> {

                Assert.assertTrue(isUrlValid(url.toString()));
                log.info("Passed: num_results [ " + url.toString() + " ] is valid");
            });
        } catch (AssertionError e) {

            throw new TestException("Failed: url is not valid");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void validateShared() {
        int num_results_period_share_type = 0;
        int num_results_period = 0;
        try {
            Response respPeriod = getDefault(new RequestSpecBuilder().addPathParams(new HashMap<>() {{
                put("period", testData().getValue("period"));
            }}).setBasePath(EndPoints.MOST_POPULAR_SHARED_PERIOD));
            num_results_period = respPeriod.jsonPath().getInt("num_results");

            Response respPeriodShareType = getDefault(new RequestSpecBuilder().addPathParams(new HashMap<>() {{
                put("period", testData().getValue("period"));
                put("share_type", testData().getValue("shareType"));
            }}).setBasePath(EndPoints.MOST_POPULAR_SHARED_PERIOD_SHARE_TYPE));
            num_results_period_share_type = respPeriodShareType.jsonPath().getInt("num_results");

            Assert.assertEquals(num_results_period_share_type, num_results_period);
            log.info("Passed: num_results_period_share_type [ " + num_results_period_share_type + " ] is matched with num_results_period [ " + num_results_period + " ]");
        } catch (AssertionError e) {
            throw new TestException("Failed: num_results_period_share_type [ " + num_results_period_share_type + " ] is mismatched with num_results_period [ " + num_results_period + " ]");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
