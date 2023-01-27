package org.gd.automation.core.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.gd.automation.core.util.Config;
import org.gd.automation.core.util.Helper;
import org.gd.automation.core.util.JsonHelper;
import org.gd.automation.core.util.TestType;


public class BaseAPI implements TestType {

    private static final String BASE_URL = Config.initConfig().getConfig("api.url");
    private static final String API_KEY = Config.initConfig().getConfig("api.key");
    public Helper helper = new Helper();
    public JsonHelper jsonHelper = new JsonHelper();

    public BaseAPI() {
        RestAssured.baseURI = BASE_URL;
        jsonHelper.setTestType(testType());
    }

    private static ThreadLocal<String> endPoint = ThreadLocal.withInitial(() -> null);


    public static void setEndPoint(String apiEndPoint) {
        endPoint.set(apiEndPoint);
    }

    public static String getEndPoint() {
        return endPoint.get();
    }

    public Response get(RequestSpecBuilder requestSpec) {

        Response resp = RestAssured
                .given().log().everything().spec(requestSpec.build())
                .basePath(getEndPoint())
                .contentType(ContentType.JSON)
                .queryParam("api-key", API_KEY)
                .get().andReturn();
        return resp;
    }
    public Response getDefault(RequestSpecBuilder requestSpec) {

        Response resp = RestAssured
                .given().log().everything().spec(requestSpec.build())
                .contentType(ContentType.JSON)
                .queryParam("api-key", API_KEY)
                .get().andReturn();
        return resp;
    }


    @Override
    public String testType() {
        return "API";
    }
}
