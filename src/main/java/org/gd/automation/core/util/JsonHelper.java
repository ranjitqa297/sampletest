package org.gd.automation.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHelper {
    private static JSONObject data;
    private static JsonHelper json;


    public static void setTestType(String testType) {
        JsonHelper.testType = testType;
    }

    private static String testType;

    public JsonHelper()  {
        String file = this.getClass().getClassLoader().getResource("data.json").getFile();
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data= new JSONObject(content);
    }


    public static JsonHelper testData() throws JsonProcessingException {
        if (json == null) {
            json = new JsonHelper();

        }
        return json;
    }

    public String getValue(String key) {
        return data.getJSONArray(testType).getJSONObject(0).getString(key);

    }

}
